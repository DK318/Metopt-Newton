package linear;

import java.util.Iterator;
import java.util.function.Function;

public class DichotomyStrategy extends AbstractMinimizationStrategy {
    private double x;

    public DichotomyStrategy(Function<Double, Double> function, double leftBound, double rightBound) {
        super(function, leftBound, rightBound);
        x = (rightBound + leftBound) / 2;
    }

    @Override
    public Iterator<IterationResult> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return (rightBound - leftBound > EPS);
            }

            @Override
            public IterationResult next() {
                if (hasNext()) {
                    final double leftValue = function.apply(x - EPS);
                    final double rightValue = function.apply(x + EPS);

                    if (leftValue < rightValue) {
                        rightBound = x;
                    } else {
                        leftBound = x;
                    }
                }

                x = (rightBound + leftBound) / 2;
                return new IterationResult(leftBound, rightBound, x, function.apply(x));
            }
        };
    }
}
