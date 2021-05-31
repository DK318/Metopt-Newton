package util;

import java.util.stream.IntStream;

public class VectorUtil {
    private VectorUtil() {
        // Util class.
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
}
