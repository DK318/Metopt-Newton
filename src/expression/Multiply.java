package expression;

import java.util.function.BinaryOperator;

public class Multiply extends BinaryExpression {

    protected Multiply(final Expression first, final Expression second) {
        super(first, second);
    }

    @Override
    protected BinaryOperator<Double> getFunction() {
        return (a, b) -> a * b;
    }

    @Override
    public Expression differentiate(final int var) {
        return null;
    }
}
