package util.tabulator;

import expression.*;
import newton.AbstractNewton;
import newton.ClassicalNewton;
import newton.DescendNewton;
import newton.OneDimensionalNewton;
import quasinewton.AbstractQuasiMethod;
import quasinewton.BFSMethod;
import quasinewton.PowellMethod;

import java.net.CacheRequest;
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

    public static void tabulateQuazi(final Expression function, final double eps, final double... x) {
        tabulate(new BFSMethod(function, x, eps));
        tabulate(new PowellMethod(function, x, eps));
    }

    public static void tabulatePodgon(final Expression function, final double eps, final double... x) {
        tabulatePodgon(new ClassicalNewton(function, x, eps));
        tabulatePodgon(new OneDimensionalNewton(function, x, eps));
        tabulatePodgon(new DescendNewton(function, x, eps));
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

    private static void tabulate(final AbstractQuasiMethod newtonMethod) {
        final List<AbstractNewton.Iteration> iterations = newtonMethod.getIterations();

        System.out.println("Newton method: " + newtonMethod.getClass().getSimpleName());
        System.out.println("\\begin{center}");
        System.out.println("\\begin{tabular}{|c|c|c|c|}");
        System.out.println("\t \\hline Итерация & $|p_k|$ & $x_k$ & $\\alpha$ \\\\");
        iterations.forEach(Tabulator::printIteration);
        System.out.println("\\hline");
        System.out.println("\\end{tabular}");
        System.out.println("\\end{center}");
    }

    private static void tabulatePodgon(final AbstractNewton newtonMethod) {
        System.out.println(String.format("[%s]", newtonMethod.getIteratios().stream().map(AbstractNewton.Iteration::getX).map(s -> String.format("(%s, %s)", s[1], s[2])).collect(Collectors.joining(", "))) );
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


//        final Expression expression1 = new Square(new Square(new Subtract(new Variable(1), 1)));
//        final Expression expression2 = new Square(new Square(new Add(new Variable(2), 1)));
//        final Expression expression3 = new Multiply(1, new Multiply(new Square(new Variable(1)), new Square(new Variable(2))));
//        final Expression expression4 = new Multiply(new Multiply(new Variable(1), new Square(new Variable(1))), new Variable(2));
//        final Expression expression5 = new Multiply(1, new Multiply(new Variable(1), new Multiply(new Variable(2), new Square(new Variable(2)))));

        final Expression expression1 = new Multiply(100, new Square(new Subtract(new Variable(2), new Square(new Variable(1)))));
        final Expression expression2 = new Square(new Subtract(1, new Variable(1)));

        tabulatePodgon(new Add(expression1, expression2), 1e-2, 0, -1.2, 1);
    }
}
