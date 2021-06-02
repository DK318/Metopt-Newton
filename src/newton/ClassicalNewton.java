package newton;

import expression.Expression;
import matrix.ConjugateGradientsSolver;
import matrix.GaussSolver;

public class ClassicalNewton extends AbstractNewton {
    public ClassicalNewton(Expression function, double[] x, double eps) {
        super(function, x, eps);
    }

    @Override
    protected double[] getDirection(double[][] hesse, double[] minusGrad) {
        return ConjugateGradientsSolver.solve(hesse, minusGrad, eps, 100000000);
    }

    @Override
    protected double getAlpha() {
        return 1;
    }
}
