package marquardt;

import expression.Expression;
import matrix.ConjugateGradientsSolver;
import matrix.Gradient;
import matrix.HesseMatrix;

import static util.MatrixUtil.*;
import static util.MatrixUtil.norm;

public class MarquardtFirstVariant {
    private double[] x;
    private double tauZero;
    private final double beta;
    private final double eps;
    private final Expression function;
    private final Gradient gradient;
    private final HesseMatrix hesseMatrix;

    public MarquardtFirstVariant(Expression function, double[] x, double tauZero, double beta, double eps) {
        this.function = function;
        this.gradient = new Gradient(function);
        this.hesseMatrix = new HesseMatrix(function);
        this.x = x;
        this.tauZero = tauZero;
        this.beta = beta;
        this.eps = eps;
    }

    public double[] minimize() {
        double fx = function.evaluate(x);
        double[] p;
        do {
            double[] grad = gradient.evaluate(x);
            double[] minusGrad = multiplyByScalar(grad, -1);
            double[][] hesse = hesseMatrix.evaluate(x);
            double tau = tauZero;
            double[] y;
            double fy;
            while (true) {
                double[][] ls = addMatrix(hesse, multiplyMatrixByScalar(getIdentity(function.getArity()), tau));
                p = ConjugateGradientsSolver.solve(ls, minusGrad, eps, 1000000000);
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
