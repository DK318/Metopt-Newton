package quasinewton;

import expression.Expression;
import linear.Fibonacci;
import linear.GoldenRatio;
import matrix.Gradient;
import newton.AbstractNewton;
import util.MatrixUtil;

import java.util.ArrayList;
import java.util.List;

import static util.MatrixUtil.*;

public abstract class AbstractQuasiMethod {
    protected final Expression function;
    protected final Gradient gradient;
    protected double[] x;
    protected final double eps;

    public AbstractQuasiMethod(Expression function, double[] x, double eps) {
        this.function = function;
        this.eps = eps;
        this.gradient = new Gradient(function);
        this.x = x;
    }

    protected abstract double[][] nextG(double[] v, double[] deltaW, double[] deltaX, double[][] g);

    public double[] minimize() {
        List<AbstractNewton.Iteration> iterations = getIterations();
        return iterations.get(iterations.size() - 1).getX();
    }

    public List<AbstractNewton.Iteration> getIterations() {
        double[][] g = getIdentity(function.getArity());
        double[] prevW = multiplyByScalar(gradient.evaluate(x), -1);
        double[] p = prevW;
        double alpha = new Fibonacci(function, x, p, eps).minimize();
        double[] prevX = x.clone();
        x = add(prevX, multiplyByScalar(p, alpha));
        double[] deltaX = subtract(x, prevX);
        final List<AbstractNewton.Iteration> iterations = new ArrayList<>();
        while (!halt(deltaX)) {
            double[] w = multiplyByScalar(gradient.evaluate(x), -1);
            double[] deltaW = subtract(w, prevW);
            prevW = w;
            double[] v = multiplyMatrixByVector(g, deltaW);

            g = nextG(v, deltaW, deltaX, g);

            p = multiplyMatrixByVector(g, w);
            alpha = new Fibonacci(function, x, p, eps).minimize();
            prevX = x.clone();
            x = add(prevX, multiplyByScalar(p, alpha));
            iterations.add(new AbstractNewton.Iteration(iterations.size() + 1, MatrixUtil.norm(p), x, alpha));
            deltaX = subtract(x, prevX);
        }
        return iterations;
    }

    private boolean halt(double[] deltaX) {
        return norm(deltaX) < eps;
    }
}
