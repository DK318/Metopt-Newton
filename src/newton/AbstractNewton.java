package newton;

import expression.Expression;
import matrix.Gradient;
import matrix.HesseMatrix;
import util.MatrixUtil;

public abstract class AbstractNewton {
    protected final Expression function;
    protected final Gradient gradient;
    protected final HesseMatrix hesseMatrix;
    protected double[] x;
    protected double[] p;
    protected final double eps;

    public AbstractNewton(Expression function, double[] x, double eps) {
        this.function = function;
        this.gradient = new Gradient(function);
        this.hesseMatrix = new HesseMatrix(function);
        this.eps = eps;
        this.x = x;
    }

    protected abstract double[] getDirection(double[][] hesse, double[] minusGrad);

    protected abstract double getAlpha();

    public double[] minimize() {
        while (true) {
            double[] minusGrad = MatrixUtil.multiplyByScalar(gradient.evaluate(x), -1);
            double[][] hesse = hesseMatrix.evaluate(x);
            p = getDirection(hesse, minusGrad);
            if (halt(p)) {
                break;
            }

            double alpha = getAlpha();

            x = MatrixUtil.add(x, MatrixUtil.multiplyByScalar(p, alpha));
        }
        return x;
    }

    private boolean halt(double[] p) {
        return MatrixUtil.norm(p) < eps;
    }
}
