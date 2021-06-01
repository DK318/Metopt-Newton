import expression.*;
import marquardt.MarquardtFirstVariant;
import marquardt.MarquardtSecondVariant;
import matrix.Gradient;
import matrix.HesseMatrix;
import newton.ClassicalNewton;
import newton.DescendNewton;
import newton.OneDimensionalNewton;
import quasinewton.BFSMethod;
import quasinewton.PowellMethod;
import util.MatrixUtil;

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
        /*Expression function = new Add(
                new Add(
                        new Add(
                                new Square(
                                        new Add(
                                                new Variable(1),
                                                new Multiply(10, new Variable(2))
                                        )
                                ),
                                new Multiply(
                                        5,
                                        new Square(
                                                new Subtract(
                                                        new Variable(3),
                                                        new Variable(4)
                                                )
                                        )
                                )
                        ),
                        new Square(
                                new Square(
                                        new Subtract(
                                                new Variable(2),
                                                new Multiply(
                                                        2, new Variable(3)
                                                )
                                        )
                                )
                        )
                ),
                new Multiply(
                        10,
                        new Square(
                                new Square(
                                        new Subtract(
                                                new Variable(1),
                                                new Variable(4)
                                        )
                                )
                        )
                )
        );*/
        /*Expression function = new Add(
                new Square(
                        new Subtract(
                                new Add(
                                        new Square(new Variable(1)),
                                        new Variable(2)
                                ),
                                11
                        )
                ),
                new Square(
                        new Subtract(
                                new Add(
                                        new Variable(1),
                                        new Square(new Variable(2))
                                ),
                                7
                        )
                )
        );*/
        BFSMethod newton = new BFSMethod(function, new double[]{0, -10, 10}, 1e-4);
        System.out.println(Arrays.toString(newton.minimize()));
//        double[][] matrix1 = {
//                {0, 0, 0, 0},
//                {0, 1, 2, 3},
//                {0, 2, 4, 6},
//                {0, 3, 6, 9}
//        };
//        double[][] matrix2 = {
//                {0, 0, 0, 0},
//                {0, 3, 1, 8},
//                {0, 2, 6, 9},
//                {0, 2, 2, 9}
//        };
//        System.out.println(Arrays.deepToString(MatrixUtil.multiplyMatrixByMatrix(matrix1, matrix2)));
//        double[] vector1 = {0, 3, 2, 4};
//        double[] vector2 = {0, 7, 3, 4};
//        System.out.println(Arrays.deepToString(MatrixUtil.getIdentity(5)));
    }
}
