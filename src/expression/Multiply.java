package expression;

import java.util.function.BinaryOperator;

public class Multiply extends BinaryExpression {

    public Multiply(final Expression first, final Expression second) {
        super(first, second);
    }

    public Multiply(final Expression first, final double second) {
        super(first, second);
    }

    public Multiply(final double first, final Expression second) {
        super(first, second);
    }

    public Multiply(final double first, final double second) {
        super(first, second);
    }

    @Override
    protected BinaryOperator<Double> getFunction() {
        return (a, b) -> a * b;
    }

    @Override
    public Expression differentiate(final int var) {
        return new Add(
                new Multiply(first.differentiate(var), second),
                new Multiply(first, second.differentiate(var))
        );
    }
}
