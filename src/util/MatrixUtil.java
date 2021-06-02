package util;

import java.util.stream.IntStream;

public class MatrixUtil {
    private MatrixUtil() {
        // Util class.
    }

    public static double[] multiply(double[][] matrix, double[] x) {
        int n = x.length - 1;
        double[] result = new double[x.length];
        IntStream.range(0, n + 1)
                .forEach(i -> IntStream.range(0, n + 1)
                        .forEach(j -> result[i] += matrix[i][j] * x[j]));
        return result;
    }

    public static double[] add(double[] lhs, double[] rhs) {
        double[] result = new double[lhs.length];
        IntStream.range(0, lhs.length).forEach(i -> result[i] = lhs[i] + rhs[i]);
        return result;
    }

    public static double[] subtract(double[] lhs, double[] rhs) {
        double[] result = new double[lhs.length];
        IntStream.range(0, lhs.length).forEach(i -> result[i] = lhs[i] - rhs[i]);
        return result;
    }

    public static double[] multiplyByScalar(double[] vec, double num) {
        double[] result = new double[vec.length];
        IntStream.range(0, vec.length).forEach(i -> result[i] = vec[i] * num);
        return result;
    }

    public static double scalarMultiply(double[] lhs, double[] rhs) {
        return IntStream.range(0, lhs.length).mapToDouble(i -> lhs[i] * rhs[i]).sum();
    }

    public static double norm(double[] vec) {
        return Math.sqrt(scalarMultiply(vec, vec));
    }

    public static double[] multiplyMatrixByVector(double[][] matrix, double[] vector) {
        double[] result = new double[vector.length];
        IntStream.range(1, vector.length)
                .forEach(i -> IntStream.range(1, vector.length)
                .forEach(j -> result[i] += matrix[i][j] * vector[j]));
        return result;
    }

    public static double[][] multiplyVectorByTransposedVector(double[] vector, double[] transposed) {
        double[][] result = new double[vector.length][vector.length];
        IntStream.range(1, vector.length)
                .forEach(i -> IntStream.range(1, vector.length)
                .forEach(j -> result[i][j] = vector[i] * transposed[j]));
        return result;
    }

    public static double[][] getIdentity(int n) {
        double[][] result = new double[n + 1][n + 1];
        IntStream.range(1, n + 1).forEach(i -> result[i][i] = 1);
        return result;
    }

    public static double[][] addMatrix(double[][] lhs, double[][] rhs) {
        double[][] result = new double[lhs.length][lhs.length];
        IntStream.range(1, lhs.length)
                .forEach(i -> IntStream.range(1, lhs.length)
                .forEach(j -> result[i][j] = lhs[i][j] + rhs[i][j]));
        return result;
    }

    public static double[][] subtractMatrix(double[][] lhs, double[][] rhs) {
        double[][] result = new double[lhs.length][lhs.length];
        IntStream.range(1, lhs.length)
                .forEach(i -> IntStream.range(1, lhs.length)
                        .forEach(j -> result[i][j] = lhs[i][j] - rhs[i][j]));
        return result;
    }

    public static double[][] multiplyMatrixByScalar(double[][] matrix, double val) {
        double[][] result = new double[matrix.length][matrix.length];
        IntStream.range(1, matrix.length)
                .forEach(i -> IntStream.range(1, matrix.length)
                .forEach(j -> result[i][j] = matrix[i][j] * val));
        return result;
    }

    public static double[][] multiplyMatrixByMatrix(double[][] lhs, double[][] rhs) {
        double[][] result = new double[lhs.length][lhs.length];
        IntStream.range(1, lhs.length)
                .forEach(i -> IntStream.range(1, lhs.length)
                .forEach(j -> IntStream.range(1, lhs.length)
                .forEach(k -> result[i][j] += lhs[i][k] * rhs[k][j])));
        return result;
    }

    public static double[][] transpose(double[][] matrix) {
        double[][] result = new double[matrix.length][matrix.length];
        IntStream.range(1, matrix.length)
                .forEach(i -> IntStream.range(1, matrix.length)
                .forEach(j -> result[i][j] = matrix[j][i]));
        return result;
    }
}
