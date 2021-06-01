package marquardt;

import expression.Expression;

public class MarquardtSecondVariant extends AbstractMarquardt {
    public MarquardtSecondVariant(Expression function, double[] x, double tauZero, double beta, double eps) {
        super(function, x, tauZero, beta, eps);
    }

    @Override
    protected boolean checkLS(double[][] ls) {
        double[][] cholesky = new double[ls.length][ls.length];
        for (int i = 1; i < ls.length; i++) {
            for (int j = 1; j < ls.length; j++) {
                if (i == j) {
                    double sum = 0;
                    for (int p = 1; p <= i - 1; p++) {
                        sum += cholesky[i][p] * cholesky[i][p];
                    }
                    double el = ls[i][i] - sum;
                    if (el < 0) {
                        return false;
                    } else {
                        cholesky[i][i] = Math.sqrt(el);
                    }
                } else {
                    double sum = 0;
                    for (int p = 1; p <= i - 1; p++) {
                        sum += cholesky[j][p] * cholesky[i][p];
                    }
                    cholesky[i][j] = (ls[i][j] - sum) / cholesky[j][j];
                }
            }
        }
        return true;
    }
}
