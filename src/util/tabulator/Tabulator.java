package util.tabulator;

import expression.*;
import newton.AbstractNewton;
import newton.ClassicalNewton;
import newton.DescendNewton;
import newton.OneDimensionalNewton;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Tabulator {

    private Tabulator() {
        // Utility class
    }

    public static void tabulate(final Expression function, final double eps, final double... x) {
        tabulate(new ClassicalNewton(function, x, eps));
        tabulate(new OneDimensionalNewton(function, x, eps));
        tabulate(new DescendNewton(function, x, eps));
    }

    private static void tabulate(final AbstractNewton newtonMethod) {
        final List<AbstractNewton.Iteration> iterations = newtonMethod.getIteratios();

        System.out.println("Newton method: " + newtonMethod.getClass().getSimpleName());
        System.out.println("\\begin{tabular}{|c|c|c|c|}");
        System.out.println("\t \\hline Итерация & $|p_k|$ & $x_k$ & $\\alpha$ \\\\");
        iterations.forEach(Tabulator::printIteration);
        System.out.println("\\end{tabular}");
    }

    private static void printIteration(final AbstractNewton.Iteration iteration) {
        System.out.printf("\t \\hline $%s$ & $%s$ & $%s$ & $%s$ \\\\%n",
                iteration.getN(),
                iteration.getP(),
                getX(iteration.getX()),
                iteration.getAlpha());
    }

    private static String getX(final double[] x) {
        return String.format("(%s)", Arrays.stream(x).skip(1).mapToObj(Double::toString).collect(Collectors.joining(", ")));
    }

    public static void main(final String[] args) {
        tabulate(new Add(
                new Square(new Subtract(new Variable(1), 3)),
                new Square(new Subtract(new Variable(2), 4))
        ), 1e-2, 0, 0, 0);
    }
}
