package expression;

import java.util.Set;

public class Variable implements Expression {

    private final int variable;

    public Variable(final int variable) {
        this.variable = variable;
    }

    @Override
    public double evaluate(final double... args) {
        return args[variable];
    }

    @Override
    public Expression differentiate(final int var) {
        return var == variable ? Constant.ONE : Constant.ZERO;
    }

    @Override
    public Set<Integer> getArguments() {
        return Set.of(variable);
    }
}
