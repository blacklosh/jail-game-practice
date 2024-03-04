import api.Strategy;
import examples.EyeForEyeStrategy;
import examples.LieManStrategy;
import examples.RandomStrategy;
import examples.TrueManStrategy;
import service.GameExecutor;
import service.GameResult;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Strategy> games = new LinkedList<>();

        games.add(new RandomStrategy());
        games.add(new TrueManStrategy());
        games.add(new LieManStrategy());
        games.add(new EyeForEyeStrategy());

        battle(games, true);
    }

    private static void battle(List<Strategy> strategies, boolean log) {
        Map<String, Double> scores = new HashMap<>();
        for (Strategy strategy : strategies) scores.put(strategy.getClass().getSimpleName(), 0.0);
        for(Strategy first : strategies) {
            for(Strategy second : strategies) {
                String firstName = first.getClass().getSimpleName();
                String secondName = second.getClass().getSimpleName();
                if(firstName.equals(secondName)) continue;

                GameExecutor gameExecutor = new GameExecutor(first, second);
                GameResult result = gameExecutor.execute(log);
                double firstStrategyScore = scores.get(firstName) + result.getFirstStrategyAverageResult();
                double secondStrategyScore = scores.get(secondName) + result.getSecondStrategyAverageResult();
                scores.put(firstName, firstStrategyScore);
                scores.put(secondName, secondStrategyScore);
            }
        }
        for(Strategy strategy : strategies) {
            String strategyName = strategy.getClass().getSimpleName();
            // Делим на кол-во противников и на два, т.к. играли с каждым два раза
            double score = scores.get(strategyName) / (strategies.size() - 1) / 2;
            System.out.println("Strategy " + strategyName + " average score: " + score);
        }
    }
}