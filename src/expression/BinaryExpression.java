package expression;

import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public abstract class BinaryExpression implements Expression {

    protected final Expression first;
    protected final Expression second;

    public BinaryExpression(final Expression first, final Expression second) {
        this.first = first;
        this.second = second;
    }

    public BinaryExpression(final Expression first, final double second) {
        this.first = first;
        this.second = new Constant(second);
    }

    public BinaryExpression(final double first, final Expression second) {
        this.first = new Constant(first);
        this.second = second;
    }

    public BinaryExpression(final double first, final double second) {
        this.first = new Constant(first);
        this.second = new Constant(second);
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
