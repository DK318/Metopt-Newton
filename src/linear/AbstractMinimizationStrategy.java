package linear;

import java.util.function.Function;

public abstract class AbstractMinimizationStrategy implements Iterable<IterationResult> {
    protected static final double EPS = 1e-4;

    protected final Function<Double, Double> function;
    protected double leftBound;
    protected double rightBound;

    public AbstractMinimizationStrategy(final Function<Double, Double> function, final double leftBound, final double rightBound) {
        this.function = function;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }
}
