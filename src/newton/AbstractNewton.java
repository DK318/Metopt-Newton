package newton;

import expression.Expression;
import matrix.Gradient;
import matrix.HesseMatrix;
import util.VectorUtil;

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
            double[] minusGrad = VectorUtil.multiplyByScalar(gradient.evaluate(x), -1);
            if (halt(minusGrad)) {
                break;
            }
            double[][] hesse = hesseMatrix.evaluate(x);
            p = getDirection(hesse, minusGrad);

            double alpha = getAlpha();

            x = VectorUtil.add(x, VectorUtil.multiplyByScalar(p, alpha));
        }
        return x;
    }

    private boolean halt(double[] p) {
        return VectorUtil.norm(p) < eps;
    }

    protected class Dichotomy {
        private double leftBound = 1;
        private double rightBound = 1000;

        private double f(double val) {
            return function.evaluate(VectorUtil.add(x, VectorUtil.multiplyByScalar(p, val)));
        }

        public double minimize() {
            double x1 = 0;
            while (!halt()) {
                x1 = (leftBound + rightBound) / 2;
                double fx1Left = f(x1 - eps);
                double fx1Right = f(x1 + eps);
                if (fx1Left < fx1Right) {
                    rightBound = x1;
                } else {
                    leftBound = x1;
                }
            }
            return x1;
        }

        private boolean halt() {
            return Math.abs(rightBound - leftBound) < eps;
        }
    }
}
