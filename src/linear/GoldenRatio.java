package linear;

import expression.Expression;
import util.MatrixUtil;

public class GoldenRatio {
    private static final double RES_PHI = 2 - (1 + Math.sqrt(5)) / 2;

    private double leftBound = -10000;
    private double rightBound = 10000;
    private final Expression function;
    private final double[] x;
    private final double[] p;
    private final double eps;

    public GoldenRatio(Expression function, double[] x, double[] p, double eps) {
        this.function = function;
        this.x = x;
        this.p = p;
        this.eps = eps;
    }

    private double f(double val) {
        return function.evaluate(MatrixUtil.add(x, MatrixUtil.multiplyByScalar(p, val)));
    }

    public double minimize() {
        double x1 = leftBound + RES_PHI * (rightBound - leftBound);
        double x2 = rightBound - RES_PHI * (rightBound - leftBound);

        double f1 = f(x1);
        double f2 = f(x2);
        while ((rightBound - leftBound) > eps) {
            if (f1 < f2) {
                rightBound = x2;
                x2 = x1;
                f2 = f1;
                x1 = leftBound + RES_PHI * (rightBound - leftBound);
                f1 = f(x1);
            } else {
                leftBound = x1;
                x1 = x2;
                f1 = f2;
                x2 = rightBound - RES_PHI * (rightBound - leftBound);
                f2 = f(x2);
            }
        }
        return (x1 + x2) / 2;
    }
}
