package expression;

import java.util.Set;
import java.util.function.UnaryOperator;

public abstract class UnaryExpression implements Expression {

    private final Expression term;

    protected UnaryExpression(final Expression term) {
        this.term = term;
    }

    @Override
    public double evaluate(final double... args) {
        return getFunction().apply(term.evaluate(args));
    }

    protected abstract UnaryOperator<Double> getFunction();

    @Override
    public Set<Integer> getArguments() {
        return term.getArguments();
    }
}
