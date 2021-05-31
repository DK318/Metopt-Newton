import expression.*;
import org.junit.Assert;
import org.junit.Test;

public class ExpressionTests {

    public static final Constant ZERO = new Constant(0);
    public static final Constant ONE = new Constant(1);
    public static final Constant TWO = new Constant(2);

    private static void assertEquals(final Expression expression, final double expected, final double... args) {
        Assert.assertEquals(expected, expression.evaluate(args), 1e-6);
    }

    @Test
    public void testConstant() {
        assertEquals(ZERO, 0);
        assertEquals(ONE, 1);
        assertEquals(TWO, 2);

        assertEquals(new Constant(1337), 1337);
        assertEquals(new Constant(0.58), 0.58);
        assertEquals(new Constant(1.337), 1.337);
    }

    @Test
    public void testVariables() {
        assertEquals(new Variable(1), 1, 1, 2, 3);
        assertEquals(new Variable(2), 2, 1, 2, 3);
        assertEquals(new Variable(3), 3, 1, 2, 3);

        assertEquals(new Variable(1), 0.5,0.5);
    }

    @Test
    public void testAdd() {
        assertEquals(new Add(ZERO, ZERO), 0);
        assertEquals(new Add(ONE, ONE), 2);
        assertEquals(new Add(ONE, TWO), 3);
        assertEquals(new Add(TWO, TWO), 4);

        assertEquals(new Add(new Constant(1337), new Constant(2.5)), 1339.5);
    }

    @Test
    public void testSubtract() {
        assertEquals(new Subtract(ZERO, ZERO), 0);
        assertEquals(new Subtract(ONE, ONE), 0);
        assertEquals(new Subtract(ONE, TWO), -1);
        assertEquals(new Subtract(TWO, TWO), 0);

        assertEquals(new Subtract(10, 2.5), 7.5);
        assertEquals(new Subtract(new Variable(1), 2.5), 7.5, 10);

        assertEquals(new Subtract(new Constant(1337), new Constant(2.5)), 1334.5);
    }

    @Test
    public void testDifferentiate() {
        assertEquals(new Constant(5).differentiate(1), 0);

        assertEquals(new Variable(1).differentiate(1), 1);
        assertEquals(new Variable(1).differentiate(2), 0);

        final Expression first = new Square(new Variable(1));
        final Expression second = new Square(new Variable(2));

        assertEquals(new Add(first, second).differentiate(1), 10, 5, 6);
        assertEquals(new Multiply(first, second).differentiate(1), 360, 5, 6);
        assertEquals(new Divide(first, second).differentiate(1), 0.2777777, 5, 6);
    }
}
