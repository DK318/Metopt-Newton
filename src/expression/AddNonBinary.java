package expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AddNonBinary implements Expression {

    private Expression[] expressions;

    public AddNonBinary(final Expression... expressions) {
        this.expressions = expressions;
    }

    @Override
    public double evaluate(final double... args) {
        return Arrays.stream(expressions).mapToDouble(e -> e.evaluate(args)).sum();
    }

    @Override
    public Expression differentiate(final int var) {
        final List<Expression> adds = new ArrayList<>();
        for (int i = 0; i < expressions.length; ++i) {
            final List<Expression> curr = new ArrayList<>();
            for (int j = 0; j < expressions.length; ++j) {
                if (i != j) {
                    curr.add(expressions[j]);
                } else {
                    curr.add(expressions[j].differentiate(var));
                }
            }
            adds.add(new MultiplyNonBinary(curr.toArray(Expression[]::new)));
        }
        return new AddNonBinary(adds.toArray(Expression[]::new));
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
