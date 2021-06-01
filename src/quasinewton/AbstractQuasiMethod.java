package quasinewton;

import expression.Expression;
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
        double[][] g = getIdentity(function.getArity());
        double[] prevW = multiplyByScalar(gradient.evaluate(x), -1);
        double[] p = prevW;
        double alpha = 1; //new Dichotomy(function, x, p, eps).minimize(); (bad accuracy)
        double[] prevX = x.clone();
        x = add(prevX, multiplyByScalar(p, alpha));
        double[] deltaX = subtract(x, prevX);
        while (!halt(prevW)) {
            double[] w = multiplyByScalar(gradient.evaluate(x), -1);
            double[] deltaW = subtract(w, prevW);
            prevW = w;
            double[] v = multiplyMatrixByVector(g, deltaW);

            g = nextG(v, deltaW, deltaX, g);

            p = multiplyMatrixByVector(g, w);
            alpha = 1; //new Dichotomy(function, x, p, eps * 1e-4).minimize(); (bad accuracy) =(
            prevX = x.clone();
            x = add(prevX, multiplyByScalar(p, alpha));
            deltaX = subtract(x, prevX);
        }
        return x;
    }

    private boolean halt(double[] deltaX) {
        return norm(deltaX) < eps;
    }
}
