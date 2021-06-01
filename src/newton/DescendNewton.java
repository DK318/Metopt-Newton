package newton;

import expression.Expression;
import util.VectorUtil;

public class DescendNewton extends OneDimensionalNewton {
    public DescendNewton(Expression function, double[] x, double eps) {
        super(function, x, eps);
    }

    @Override
    protected double[] getDirection(double[][] hesse, double[] minusGrad) {
        double[] p = super.getDirection(hesse, minusGrad);

        System.arraycopy(minusGrad, 1, minusGrad, 0, minusGrad.length - 1);
        minusGrad[minusGrad.length - 1] = 0;
        p[x.length] = 0;

        double condition = VectorUtil.scalarMultiply(p, minusGrad);
        if (condition < 0) {
            p = minusGrad;
        }

        return p;
    }
}
