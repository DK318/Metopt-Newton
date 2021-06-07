package marquardt;

import expression.Expression;
import matrix.ConjugateGradientsSolver;
import matrix.Gradient;
import matrix.HesseMatrix;

import static util.MatrixUtil.*;

public class MarquardtSecondVariant {
    private double[] x;
    private double tauZero;
    private final double eps;
    private final Expression function;
    private final Gradient gradient;
    private final HesseMatrix hesseMatrix;

    public MarquardtSecondVariant(Expression function, double[] x, double eps) {
        this.function = function;
        this.gradient = new Gradient(function);
        this.hesseMatrix = new HesseMatrix(function);
        this.x = x;
        this.tauZero = 0;
        this.eps = eps;
    }

    private double[][] cholesky(double[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j <= i; j++) {
                if (Math.abs(matrix[i][j] - matrix[j][i]) > eps) {
                    return null;
                }
            }
        }

        double[][] result = new double[matrix.length][matrix.length];
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j <= i; j++) {
                if (i == j) {
                    double sum = 0;
                    for (int p = 1; p <= i - 1; p++) {
                        sum += result[i][p] * result[i][p];
                    }
                    double el = matrix[i][i] - sum;
                    if (el < 0) {
                        return null;
                    } else {
                        result[i][i] = Math.sqrt(el);
                    }
                } else {
                    double sum = 0;
                    for (int p = 1; p <= i - 1; p++) {
                        sum += result[j][p] * result[i][p];
                    }
                    if (Math.abs(result[j][j]) < 1e-8) {
                        return null;
                    }
                    result[i][j] = (matrix[i][j] - sum) / result[j][j];
                }
            }
        }
        return result;
    }

    private boolean isPositive(double[][] ls) {
        double[][] b = cholesky(ls);
        if (b == null) {
            return false;
        }

        b = multiplyMatrixByMatrix(b, transpose(b));
        for (int i = 1; i < ls.length; i++) {
            for (int j = 1; j < ls.length; j++) {
                if (Math.abs(ls[i][j] - b[i][j]) > eps) {
                    return false;
                }
            }
        }
        return true;
    }

    public double[] minimize() {
        double[] p;
        do {
            double[] grad = gradient.evaluate(x);
            double[] minusGrad = multiplyByScalar(grad, -1);
            double[][] hesse = hesseMatrix.evaluate(x);
            double[] y;
            while (true) {
                double[][] ls = addMatrix(hesse, multiplyMatrixByScalar(getIdentity(function.getArity()), tauZero));
                if (!isPositive(ls)) {
                    tauZero = Math.max(1, 2 * tauZero);
                    continue;
                }
                p = ConjugateGradientsSolver.solve(ls, minusGrad, eps, 1000000000);
                y = add(x, p);
                x = y.clone();
                break;
            }
        } while (!halt(p));
        return x;
    }

    private boolean halt(double[] p) {
        return norm(p) < eps;
    }
}
