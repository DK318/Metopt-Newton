package newton;

import expression.Expression;
import linear.Fibonacci;
import linear.GoldenRatio;

public class OneDimensionalNewton extends ClassicalNewton {
    public OneDimensionalNewton(Expression function, double[] x, double eps) {
        super(function, x, eps);
    }

    @Override
    protected double getAlpha() {
        return new GoldenRatio(function, x, p, eps).minimize();
    }
}
