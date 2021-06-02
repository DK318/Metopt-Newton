package linear;

import expression.Expression;
import util.MatrixUtil;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {
    private double leftBound = -1000;
    private double rightBound = 1000;
    private final Expression function;
    private final double[] x;
    private final double[] p;
    private final double eps;

    public Fibonacci(Expression function, double[] x, double[] p, double eps) {
        this.function = function;
        this.x = x;
        this.p = p;
        this.eps = eps;
    }

    private double f(double val) {
        return function.evaluate(MatrixUtil.add(x, MatrixUtil.multiplyByScalar(p, val)));
    }

    public double minimize() {
        List<Integer> fibonacci = new ArrayList<>();
        fibonacci.add(1);
        fibonacci.add(1);
        int f1 = 1, f2 = 2;
        while (f2 * eps <= rightBound - leftBound) {
            int f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
            fibonacci.add(f2);
        }

        int n = fibonacci.size() - 2;

        double lambda = leftBound + (double) fibonacci.get(n - 1) / fibonacci.get(n + 1) * (rightBound - leftBound);
        double mu = leftBound + (double) fibonacci.get(n) / fibonacci.get(n + 1) * (rightBound - leftBound);
        double fLambda = f(lambda);
        double fMu = f(mu);

        int curIteration = 1;

        while (n - curIteration != 1) {
            if (fLambda < fMu) {
                rightBound = mu;
                mu = lambda;
                fMu = fLambda;
                lambda = leftBound + (double) fibonacci.get(n - 1 - curIteration) / fibonacci.get(n + 1 - curIteration)
                        * (rightBound - leftBound);
                fLambda = f(lambda);
            } else {
                leftBound = lambda;
                lambda = mu;
                fLambda = fMu;
                mu = leftBound + (double) fibonacci.get(n - curIteration) / fibonacci.get(n + 1 - curIteration)
                        * (rightBound - leftBound);
                fMu = f(mu);
            }
            curIteration++;
        }

        return (leftBound + rightBound) / 2;
    }
}
