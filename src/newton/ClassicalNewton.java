package newton;

import expression.Expression;
import util.VectorUtil;

public class ClassicalNewton {
    private final Expression function;
    private final double eps;

    public ClassicalNewton(Expression function, double eps) {
        this.function = function;
        this.eps = eps;
    }



    private boolean halt(double[] p) {
        return VectorUtil.norm(p) < eps;
    }
}
