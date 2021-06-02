package linear;

import expression.Expression;
import util.MatrixUtil;

public class Dichotomy {
    private double leftBound = 1;
    private double rightBound = 1000;
    private final Expression function;
    private final double[] x;
    private final double[] p;
    private final double eps;

    public Dichotomy(final Expression function, final double[] x, final double[] p, final double eps) {
        this.function = function;
        this.x = x;
        this.p = p;
        this.eps = eps;
    }

    private double f(final double val) {
        return function.evaluate(MatrixUtil.add(x, MatrixUtil.multiplyByScalar(p, val)));
    }

    public double minimize() {
        double x1 = 0;
        while (!halt()) {
            x1 = (leftBound + rightBound) / 2;
            final double fx1Left = f(x1 - eps);
            final double fx1Right = f(x1 + eps);
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
