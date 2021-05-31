package newton;

import expression.Expression;

public class ClassicalNewton {
    private final Expression function;
    private final double eps;

    public ClassicalNewton(Expression function, double eps) {
        this.function = function;
        this.eps = eps;
    }

    private boolean halt() {
        return false;
    }
}
