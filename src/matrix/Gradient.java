package matrix;

import expression.Expression;

public class Gradient {
    private final Expression[] gradient;
    private final int n;

    public Gradient(Expression function) {
        this.n = function.getArity();
        this.gradient = new Expression[n + 1];
        for (int i = 1; i <= n; i++) {
            gradient[i] = function.differentiate(i);
        }
    }

    public double[] evaluate(double x) {
        double[] result = new double[n + 1];
        for (int i = 1; i <= n; i++) {
            result[i] = gradient[i].evaluate(x);
        }
        return result;
    }
}
