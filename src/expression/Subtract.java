package expression;

import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Subtract extends BinaryExpression {

    public Subtract(final Expression first, final Expression second) {
        super(first, second);
    }

    public Subtract(final Expression first, final double second) {
        super(first, second);
    }

    public Subtract(final double first, final Expression second) {
        super(first, second);
    }

    public Subtract(final double first, final double second) {
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
