package newton;

import expression.Expression;

public class OneDimensionalNewton extends ClassicalNewton {
    public OneDimensionalNewton(Expression function, double[] x, double eps) {
        super(function, x, eps);
    }

    @Override
    protected double getAlpha() {
        return new Dichotomy().minimize();
    }
}
