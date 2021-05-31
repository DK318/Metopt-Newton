package newton;

import expression.Expression;
import linear.DichotomyStrategy;
import linear.IterationResult;
import matrix.GaussSolver;
import matrix.Gradient;
import matrix.HesseMatrix;
import util.VectorUtil;

public class OneDimensionalNewton {
    private final Expression function;
    private final Gradient gradient;
    private final HesseMatrix hesseMatrix;
    private double[] x;
    private double[] p;
    private final double eps;

    public OneDimensionalNewton(Expression function, double[] x, double eps) {
        this.function = function;
        this.gradient = new Gradient(function);
        this.hesseMatrix = new HesseMatrix(function);
        this.eps = eps;
        this.x = x;
    }

    private double getMin() {
        DichotomyStrategy strategy = new DichotomyStrategy(
                (alpha) -> function.evaluate(VectorUtil.add(x, VectorUtil.multiplyByScalar(p, alpha))),0, 1e9);

        double res = -228;
        for (IterationResult it : strategy) {
            res = it.solutionX;
        }

        return res;
    }

    public double[] minimize() {
        double alpha;

        while (true) {
            double[] grad = gradient.evaluate(x);
            double[][] hesse = hesseMatrix.evaluate(x);
            p = new double[x.length + 1];
            GaussSolver.solve(hesse, VectorUtil.multiplyByScalar(grad, -1.0), p, 1e-7);
            alpha = getMin();

            System.arraycopy(p, 1, p, 0, x.length);
            x = VectorUtil.add(x, VectorUtil.multiplyByScalar(p, alpha));
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
