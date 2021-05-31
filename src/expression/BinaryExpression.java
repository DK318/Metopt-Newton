package expression;

import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public abstract class BinaryExpression implements Expression {

    protected final Expression first;
    protected final Expression second;

    protected BinaryExpression(final Expression first, final Expression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public double evaluate(final double... args) {
        return getFunction().apply(first.evaluate(args), second.evaluate(args));
    }

    protected abstract BinaryOperator<Double> getFunction();

    @Override
    public Set<Integer> getArguments() {
        return List.of(first, second)
                .stream()
                .map(Expression::getArguments)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }
}
