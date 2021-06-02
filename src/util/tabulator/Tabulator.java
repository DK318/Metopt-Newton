package util.tabulator;

import expression.*;
import newton.AbstractNewton;
import newton.ClassicalNewton;
import newton.DescendNewton;
import newton.OneDimensionalNewton;

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
        System.out.println("\\begin{center}");
        System.out.println("\\begin{tabular}{|c|c|c|c|}");
        System.out.println("\t \\hline Итерация & $|p_k|$ & $x_k$ & $\\alpha$ \\\\");
        iterations.forEach(Tabulator::printIteration);
        System.out.println("\\hline");
        System.out.println("\\end{tabular}");
        System.out.println("\\end{center}");
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
//        tabulate(new Add(
//                new Square(new Subtract(new Variable(1), 3)),
//                new Square(new Subtract(new Variable(2), 4))
//        ), 1e-2, 0, 0, 0);


        final Expression expression1 = new Square(new Subtract(new Variable(1), 3));
        final Expression expression2 = new Square(new Subtract(new Variable(2), 4));
        final Expression expression3 = new Multiply(new Square(new Variable(1)), new Square(new Variable(2)));
        final Expression expression4 = new Multiply(2,
            new Multiply(
                    new Multiply(new Variable(1), new Square(new Variable(1))),
                    new Variable(2)
            )
        );

        tabulate(new Add(expression1, new Add(expression2, new Add(expression2, new Add(expression3, expression4)))), 1e-2, 0, 0, 0);
    }
}
