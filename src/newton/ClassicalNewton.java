package newton;

import expression.Expression;
import util.VectorUtil;

public class ClassicalNewton {
    private final Expression function;
    private double[] x;
    private final double eps;

    public ClassicalNewton(Expression function, double[] x, double eps) {
        this.function = function;
        this.eps = eps;
        this.x = x;
    }

    public double[] minimize() {

    }

    private boolean halt(double[] p) {
        return VectorUtil.norm(p) < eps;
    }
}
