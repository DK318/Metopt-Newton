package linear;

public class IterationResult {
    public double leftBound;
    public double rightBound;
    public double solutionX;
    public double solutionY;

    public IterationResult(final double leftBound, final double rightBound, final double solutionX, final double solutionY) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.solutionX = solutionX;
        this.solutionY = solutionY;
    }
}
