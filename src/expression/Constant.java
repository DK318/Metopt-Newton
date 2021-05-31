package expression;

import java.util.Collections;
import java.util.Set;

public class Constant implements Expression {

    public static final Constant ZERO = new Constant(0);
    public static final Constant ONE = new Constant(1);

    private final double value;

    public Constant(final double value) {
        this.value = value;
    }

    @Override
    public double evaluate(final double... args) {
        return value;
    }

    @Override
    public Expression differentiate(final int var) {
        return ZERO;
    }

    @Override
    public Set<Integer> getArguments() {
        return Collections.emptySet();
    }
}
