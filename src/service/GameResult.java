package service;

public class GameResult {

    private final double firstStrategyAverageResult;
    private final double secondStrategyAverageResult;

    public GameResult(double firstStrategyAverageResult, double secondStrategyAverageResult) {
        this.firstStrategyAverageResult = firstStrategyAverageResult;
        this.secondStrategyAverageResult = secondStrategyAverageResult;
    }

    public double getSecondStrategyAverageResult() {
        return secondStrategyAverageResult;
    }

    public double getFirstStrategyAverageResult() {
        return firstStrategyAverageResult;
    }
}
