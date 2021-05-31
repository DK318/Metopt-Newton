package expression;

public interface Expression {
    double evaluate(double... args);
    Expression differentiate(int var);
    int getArity();
}
