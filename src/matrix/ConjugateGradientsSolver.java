package matrix;

import static util.MatrixUtil.*;

public class ConjugateGradientsSolver {
    private ConjugateGradientsSolver() {

    }

    public static double[] solve(double[][] matrix, double[] f, double eps, int maxIterations) {
        int n = f.length - 1;
        double[] x = new double[n + 1];
        double[] r = subtract(f, multiply(matrix, x));
        double[] z = r.clone();
        int iteration = 0;
        while (!halt(r, f, eps) && iteration < maxIterations) {
            double[] az = multiply(matrix, z);
            double rr = scalarMultiply(r, r);
            double alpha = rr / scalarMultiply(az, z);
            x = add(x, multiplyByScalar(z, alpha));
            r = subtract(r, multiplyByScalar(az, alpha));
            double beta = scalarMultiply(r, r) / rr;
            z = add(r, multiplyByScalar(z, beta));
            iteration++;
        }
        return x;
    }

    private static boolean halt(double[] r, double[] f, double eps) {
        return norm(r) / norm(f) < eps;
    }
}
