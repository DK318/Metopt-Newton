package matrix;

import expression.Expression;

public class HesseMatrix {
    private final Expression[][] data;
    private final int n;

    public HesseMatrix(Expression function) {
        this.n = function.getArity();
        this.data = new Expression[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                data[i][j] = function.differentiate(i).differentiate(j);
            }
        }
    }

    public double[][] evaluate(double x) {
        double[][] result = new double[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                result[i][j] = data[i][j].evaluate(x);
            }
        }
        return result;
    }
}
