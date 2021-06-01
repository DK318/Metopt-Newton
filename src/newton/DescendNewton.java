package newton;

import expression.Expression;
import util.MatrixUtil;

public class DescendNewton extends OneDimensionalNewton {
    public DescendNewton(Expression function, double[] x, double eps) {
        super(function, x, eps);
    }

    @Override
    protected double[] getDirection(double[][] hesse, double[] minusGrad) {
        double[] p = super.getDirection(hesse, minusGrad);

        double condition = MatrixUtil.scalarMultiply(p, minusGrad);
        if (condition < 0) {
            p = minusGrad;
        }

        return p;
    }
}
