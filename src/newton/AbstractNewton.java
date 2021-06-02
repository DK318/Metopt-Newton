package newton;

import expression.Expression;
import matrix.Gradient;
import matrix.HesseMatrix;
import util.MatrixUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNewton {
    protected final Expression function;
    protected final Gradient gradient;
    protected final HesseMatrix hesseMatrix;
    protected double[] x;
    protected double[] p;
    protected final double eps;

    public AbstractNewton(final Expression function, final double[] x, final double eps) {
        this.function = function;
        this.gradient = new Gradient(function);
        this.hesseMatrix = new HesseMatrix(function);
        this.eps = eps;
        this.x = x;
    }

    protected abstract double[] getDirection(double[][] hesse, double[] minusGrad);

    protected abstract double getAlpha();

    public double[] minimize() {
        final List<Iteration> iteratios = getIteratios();
        return iteratios.get(iteratios.size() - 1).x;
    }

    public List<Iteration> getIteratios() {
        final List<Iteration> iterations = new ArrayList<>();
        while (true) {
            final double[] minusGrad = MatrixUtil.multiplyByScalar(gradient.evaluate(x), -1);
            if (halt(minusGrad)) {
                break;
            }
            final double[][] hesse = hesseMatrix.evaluate(x);
            p = getDirection(hesse, minusGrad);

            final double alpha = getAlpha();

            x = MatrixUtil.add(x, MatrixUtil.multiplyByScalar(p, alpha));
            iterations.add(new Iteration(iterations.size() + 1, MatrixUtil.norm(p), x, alpha));
        }
        return iterations;
    }

    private boolean halt(final double[] p) {
        return MatrixUtil.norm(p) < eps;
    }

    public static class Iteration {
        private int n;
        private double p;
        private double[] x;
        private double alpha;

        public Iteration(final int n, final double p, final double[] x, final double alpha) {
            this.n = n;
            this.p = p;
            this.x = x;
            this.alpha = alpha;
        }

        public int getN() {
            return n;
        }

        public double getP() {
            return p;
        }

        public double[] getX() {
            return x;
        }

        public double getAlpha() {
            return alpha;
        }
    }
}
