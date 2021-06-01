package newton;

import expression.Expression;
import linear.Dichotomy;

public class OneDimensionalNewton extends ClassicalNewton {
    public OneDimensionalNewton(Expression function, double[] x, double eps) {
        super(function, x, eps);
    }

    @Override
    protected double getAlpha() {
        return new Dichotomy(function, x, p, eps).minimize();
    }
}
