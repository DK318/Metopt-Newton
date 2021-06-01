package quasinewton;

import expression.Expression;

import static util.MatrixUtil.*;

public class PowellMethod extends AbstractQuasiMethod {
    public PowellMethod(Expression function, double[] x, double eps) {
        super(function, x, eps);
    }

    @Override
    protected double[][] nextG(double[] v, double[] deltaW, double[] deltaX, double[][] g) {
        double[] deltaXLine = add(deltaX, v);
        return subtractMatrix(
                g,
                multiplyMatrixByScalar(
                        multiplyVectorByTransposedVector(deltaXLine, deltaXLine),
                        1 / scalarMultiply(deltaW, deltaXLine)
                )
        );
    }
}
