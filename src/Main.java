import expression.*;
import matrix.Gradient;
import matrix.HesseMatrix;
import newton.ClassicalNewton;
import newton.DescendNewton;
import newton.OneDimensionalNewton;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        Expression function = new Add(new Square(new Variable(1)), new Subtract(new Square(new Variable(2)), new Multiply(new Constant(1.2), new Multiply(new Variable(1), new Variable(2)))));
        Expression function = new Add(new Multiply(new Constant(100), new Square(new Subtract(new Variable(2), new Square(new Variable(1))))), new Square(new Subtract(new Constant(1), new Variable(1))));
//        Expression function = new Add(new Square(new Variable(1)), new Square(new Square(new Variable(2))));
//        HesseMatrix matrix = new HesseMatrix(function);
//        System.out.println(Arrays.deepToString(matrix.evaluate(new double[]{1, 2})));
        //Expression function = new Add(new Square(new Subtract(new Square(new Variable(1)), new Variable(2))), new Square(new Subtract(new Variable(1), new Constant(1))));
//        Gradient gradient = new Gradient(function);
//        System.out.println(Arrays.toString(gradient.evaluate(new double[]{4, 1})));
        //ClassicalNewton newton = new ClassicalNewton(function, new double[]{-1.2, 1}, 1e-7);
        DescendNewton newton = new DescendNewton(function, new double[]{-1.2, 1}, 1e-9);
        System.out.println(Arrays.toString(newton.minimize()));
    }
}
