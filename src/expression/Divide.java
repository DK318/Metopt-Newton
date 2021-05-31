package expression;

import java.util.function.BinaryOperator;

public class Divide extends BinaryExpression {

    public Divide(final Expression first, final Expression second) {
        super(first, second);
    }

    public Divide(final Expression first, final double second) {
        super(first, second);
    }

    public Divide(final double first, final Expression second) {
        super(first, second);
    }

    public Divide(final double first, final double second) {
        super(first, second);
    }

    @Override
    protected BinaryOperator<Double> getFunction() {
        return (a, b) -> a / b;
    }

    @Override
    public Expression differentiate(final int var) {
        return new Divide(
                new Subtract(
                        new Multiply(first.differentiate(var), second),
                        new Multiply(first, second.differentiate(var))
                ),
                new Square(second)
        );
    }
}
