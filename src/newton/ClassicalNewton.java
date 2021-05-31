package newton;

import expression.Expression;
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
        return null;
    }

    private boolean halt(double[] p) {
        return VectorUtil.norm(p) < eps;
    }
}
