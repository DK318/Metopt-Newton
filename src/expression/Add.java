package expression;

import java.util.function.BinaryOperator;

public class Add extends BinaryExpression {

    protected Add(final Expression first, final Expression second) {
        super(first, second);
    }

    @Override
    protected BinaryOperator<Double> getFunction() {
        return Double::sum;
    }

    @Override
    public Expression differentiate(final int var) {
        return new Add(first.differentiate(var), second.differentiate(var));
    }
}
