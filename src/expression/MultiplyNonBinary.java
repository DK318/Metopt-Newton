package expression;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class MultiplyNonBinary implements Expression{

    private Expression[] expressions;

    public MultiplyNonBinary(final Expression... expressions) {
        this.expressions = expressions;
    }

    @Override
    public double evaluate(final double... args) {
        return Arrays.stream(expressions).mapToDouble(e -> e.evaluate(args)).reduce(1, (a, b) -> a * b);
    }

    @Override
    public Expression differentiate(final int var) {
        Expression expression = expressions[0];
        for (int i = 1; i < expressions.length; ++i) {
            expression = new Multiply(expression, expressions[i]);
        }
        return expression.differentiate(var);
    }

    @Override
    public Set<Integer> getArguments() {
        return Arrays
                .stream(expressions)
                .map(Expression::getArguments)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }
}
