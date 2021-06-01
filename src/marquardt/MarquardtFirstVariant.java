package marquardt;

import expression.Expression;

public class MarquardtFirstVariant extends AbstractMarquardt {
    public MarquardtFirstVariant(Expression function, double[] x, double tauZero, double beta, double eps) {
        super(function, x, tauZero, beta, eps);
    }

    @Override
    protected boolean checkLS(double[][] ls) {
        return true;
    }
}
