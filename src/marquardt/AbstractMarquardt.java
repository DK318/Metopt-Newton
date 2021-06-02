package marquardt;

import expression.Expression;
import matrix.GaussSolver;
import matrix.Gradient;
import matrix.HesseMatrix;

import static util.MatrixUtil.*;
import static util.MatrixUtil.norm;

public abstract class AbstractMarquardt {
    protected double[] x;
    protected double tauZero;
    protected final double beta;
    protected final double eps;
    protected final Expression function;
    protected final Gradient gradient;
    protected final HesseMatrix hesseMatrix;

    public AbstractMarquardt(Expression function, double[] x, double tauZero, double beta, double eps) {
        this.function = function;
        this.gradient = new Gradient(function);
        this.hesseMatrix = new HesseMatrix(function);
        this.x = x;
        this.tauZero = tauZero;
        this.beta = beta;
        this.eps = eps;
    }

    protected abstract boolean checkLS(double[][] ls);

    public double[] minimize() {
        double fx = function.evaluate(x);
        double[] p = new double[x.length];
        do {
            double[] grad = gradient.evaluate(x);
            double[] minusGrad = multiplyByScalar(grad, -1);
            double[][] hesse = hesseMatrix.evaluate(x);
            double tau = tauZero;
            double[] y;
            double fy;
            while (true) {
                double[][] ls = addMatrix(hesse, multiplyMatrixByScalar(getIdentity(function.getArity()), tau));
                while (!checkLS(ls)) {
                    tau = Math.max(1, 2 * tau);
                }
                GaussSolver.solve(ls, minusGrad, p, eps);
                y = add(x, p);
                fy = function.evaluate(y);
                if (fy > fx) {
                    tau /= beta;
                } else {
                    break;
                }
            }
            x = y.clone();
            fx = fy;
            tauZero *= beta;
        } while (!halt(p));
        return x;
    }

    private boolean halt(double[] p) {
        return norm(p) < eps;
    }
}
