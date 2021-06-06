package quasinewton;

import expression.Expression;
import linear.Fibonacci;
import linear.GoldenRatio;
import matrix.Gradient;

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
        int n = function.getArity();
        double[][] g = getIdentity(n);
        double[] prevW = multiplyByScalar(gradient.evaluate(x), -1);
        double[] p = prevW;
        double alpha = new GoldenRatio(function, x, p, eps).minimize();
        double[] prevX = x.clone();
        x = add(prevX, multiplyByScalar(p, alpha));
        double[] deltaX = subtract(x, prevX);
        int iteration = 1;
        while (!halt(deltaX)) {
            double[] w = multiplyByScalar(gradient.evaluate(x), -1);
            double[] deltaW = subtract(w, prevW);
            prevW = w;
            double[] v = multiplyMatrixByVector(g, deltaW);

            if (iteration != n * 100) {
                g = nextG(v, deltaW, deltaX, g);
            } else {
                iteration = 0;
                g = getIdentity(n);
            }

            p = multiplyMatrixByVector(g, w);
            alpha = new GoldenRatio(function, x, p, eps).minimize();
            prevX = x.clone();
            x = add(prevX, multiplyByScalar(p, alpha));
            deltaX = subtract(x, prevX);
            iteration++;
        }
        return x;
    }

    private boolean halt(double[] deltaX) {
        return norm(deltaX) < eps;
    }
}
