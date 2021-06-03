package gradient;

import expression.Expression;
import matrix.Gradient;
import util.MatrixUtil;

import static util.MatrixUtil.*;

public class SteepestDescend {
    private final Expression function;
    private final Gradient gradient;
    private double[] x;
    private final double eps;

    public SteepestDescend(Expression function, double[] x, double eps) {
        this.function = function;
        this.gradient = new Gradient(function);
        this.x = x;
        this.eps = eps;
    }

    public double[] minimize() {
        while (true) {
            double[] grad = gradient.evaluate(x);
            if (halt(grad)) {
                break;
            }
            double alpha = new Fuck(1, 1000, function, x, grad, eps).minimize();
            if (alpha == 0) {
                alpha = 1;
            }
            x = subtract(x, multiplyByScalar(grad, alpha));
        }
        return x;
    }

    private boolean halt(double[] grad) {
        return norm(grad) < eps;
    }

    private static class Fuck {
        private final double RIGHT_TAU = (Math.sqrt(5) - 1) / 2;
        private final double LEFT_TAU = 1 - RIGHT_TAU;
        private final Expression function;
        private double x1, x2, right, left;
        private final double[] x;
        private final double[] g;
        private final double eps;
        private double f_x1, f_x2;

        public Fuck(double left, double right, Expression function, double[] x, double[] g, double eps) {
            this.function = function;
            this.x = x;
            this.g = g;
            this.eps = eps;
            this.right = right;
            this.left = left;
            x1 = left + LEFT_TAU * (right - left);
            x2 = left + RIGHT_TAU * (right - left);
            f_x1 = f(x1);
            f_x2 = f(x2);
        }

        private double f(double val) {
            return function.evaluate(MatrixUtil.subtract(x, MatrixUtil.multiplyByScalar(g, val)));
        }

        public double minimize() {
            int it = 0;
            while (!converged() && it != 10000) {
                iterate();
                it++;
            }
            if (it == 10000) {
                return 0;
            }
            return x1;
        }

        public boolean converged() {
            return Math.abs(f_x1 - f_x2) < eps;
        }

        public void iterate() {
            if (f_x1 <= f_x2) {
                right = x2;
                x2 = x1;
                x1 = left + LEFT_TAU * (right - left);
                f_x2 = f_x1;
                f_x1 = f(x1);
            } else {
                left = x1;
                x1 = x2;
                x2 = left + RIGHT_TAU * (right - left);
                f_x1 = f_x2;
                f_x2 = f(x2);
            }
        }
    }
}
