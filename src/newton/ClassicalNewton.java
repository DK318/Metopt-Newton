package newton;

import expression.Expression;
import matrix.GaussSolver;

public class ClassicalNewton extends AbstractNewton {
    public ClassicalNewton(Expression function, double[] x, double eps) {
        super(function, x, eps);
    }

    @Override
    protected double[] getDirection(double[][] hesse, double[] minusGrad) {
        double[] p = new double[x.length + 1];
        GaussSolver.solve(hesse, minusGrad, p, eps);
        return p;
    }

    @Override
    protected double getAlpha() {
        return 1;
    }
}
