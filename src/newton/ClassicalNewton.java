package newton;

import expression.Expression;
import matrix.GaussSolver;
import matrix.Gradient;
import matrix.HesseMatrix;
import util.VectorUtil;

public class ClassicalNewton {
    private final Gradient gradient;
    private final HesseMatrix hesseMatrix;
    private double[] x;
    private final double eps;

    public ClassicalNewton(Expression function, double[] x, double eps) {
        this.gradient = new Gradient(function);
        this.hesseMatrix = new HesseMatrix(function);
        this.eps = eps;
        this.x = x;
    }

    public double[] minimize() {
        while (true) {
            double[] grad = gradient.evaluate(x);
            double[][] hesse = hesseMatrix.evaluate(x);
            double[] p = new double[x.length + 1];
            GaussSolver.solve(hesse, VectorUtil.multiplyByScalar(grad, -1.0), p, 1e-7);
            x = VectorUtil.add(x, p);
            if (halt(p)) {
                break;
            }
        }
        return x;
    }

    private boolean halt(double[] p) {
        return VectorUtil.norm(p) < eps;
    }
}
