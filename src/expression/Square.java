package expression;

import java.util.function.UnaryOperator;

public class Square extends UnaryExpression {

    protected Square(final Expression term) {
        super(term);
    }

    @Override
    public Expression differentiate(final int var) {
        return new Multiply(Constant.TWO, term);
    }

    @Override
    protected UnaryOperator<Double> getFunction() {
        return a -> a * a;
    }
}