package expression;

import java.util.function.BinaryOperator;

public class Divide extends BinaryExpression {

    protected Divide(final Expression first, final Expression second) {
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
