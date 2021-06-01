package newton;

import expression.Expression;
import matrix.GaussSolver;
import matrix.Gradient;
import matrix.HesseMatrix;
import util.VectorUtil;

public class OneDimensionalNewton {
    private final Expression function;
    private final Gradient gradient;
    private final HesseMatrix hesseMatrix;
    private double[] x;
    private double[] p;
    private final double eps;

    public OneDimensionalNewton(Expression function, double[] x, double eps) {
        this.function = function;
        this.gradient = new Gradient(function);
        this.hesseMatrix = new HesseMatrix(function);
        this.eps = eps;
        this.x = x;
    }

    public double[] minimize() {
        double alpha;

        while (true) {
            double[] grad = gradient.evaluate(x);
            double[][] hesse = hesseMatrix.evaluate(x);
            p = new double[x.length + 1];
            GaussSolver.solve(hesse, VectorUtil.multiplyByScalar(grad, -1.0), p, 1e-7);
            alpha = new Dichotomy().minimize();

            x = VectorUtil.add(x, VectorUtil.multiplyByScalar(p, alpha));
            if (halt(grad)) {
                break;
            }
        }
        return x;
    }

    private boolean halt(double[] p) {
        return VectorUtil.norm(p) < eps;
    }

    private class Dichotomy {
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
