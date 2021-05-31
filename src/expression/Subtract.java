package expression;

import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Subtract extends BinaryExpression {

    protected Subtract(final Expression first, final Expression second) {
        super(first, second);
    }

    @Override
    protected BinaryOperator<Double> getFunction() {
        return (a, b) -> a - b;
    }

    @Override
    public Expression differentiate(final int var) {
        return new Subtract(first.differentiate(var), second.differentiate(var));
    }
}
