package quasinewton;

import expression.Expression;

import static util.MatrixUtil.*;

public class BFSMethod extends AbstractQuasiMethod {
    public BFSMethod(Expression function, double[] x, double eps) {
        super(function, x, eps);
    }

    @Override
    protected double[][] nextG(double[] v, double[] deltaW, double[] deltaX, double[][] g) {
        double rho = scalarMultiply(v, deltaW);
        double scalar = scalarMultiply(deltaX, deltaW);
        double[] r = subtract(
                multiplyByScalar(v, 1 / rho),
                multiplyByScalar(deltaX, 1 / scalar)
        );
        double[][] first = multiplyMatrixByScalar(
                multiplyVectorByTransposedVector(deltaX, deltaX), 1 / scalar
        );
        double[][] second = multiplyMatrixByScalar(
                multiplyMatrixByMatrix(
                        multiplyVectorByTransposedVector(
                                multiplyMatrixByVector(g, deltaW),
                                deltaW
                        ),
                        transpose(g)
                ), 1 / rho);
        double[][] third = multiplyVectorByTransposedVector(
                multiplyByScalar(r, rho),
                r
        );
        return addMatrix(
                subtractMatrix(
                        subtractMatrix(
                                g, first
                        ),
                        second
                ),
                third
        );
    }
}
