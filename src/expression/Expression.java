package expression;

import java.util.Set;

public interface Expression {
    double evaluate(double... args);
    Expression differentiate(int var);
    Set<Integer> getArguments();

    default int getArity() {
        return getArguments().size();
    }
}
