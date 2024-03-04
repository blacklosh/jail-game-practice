package service;

import api.Decision;
import api.Strategy;

import java.util.ArrayList;
import java.util.List;

public class GameExecutor {

    private final Strategy firstStrategy;
    private final Strategy secondStrategy;

    private final int roundsNumber;

    private final List<Decision> firstStrategyDecisions;
    private final List<Decision> secondStrategyDecisions;

    private int firstStrategyScore;
    private int secondStrategyScore;

    public GameExecutor(Strategy firstStrategy, Strategy secondStrategy) {
        this.firstStrategy = firstStrategy;
        this.secondStrategy = secondStrategy;

        roundsNumber = 200 + (int)(Math.random() * 100);
        firstStrategyDecisions = new ArrayList<>();
        secondStrategyDecisions = new ArrayList<>();

        firstStrategyScore = 0;
        secondStrategyScore = 0;
    }

    public GameResult execute(boolean log) {
        if(log)
            System.out.println("Starting game between " + firstStrategy.getClass().getSimpleName() +
                " and " + secondStrategy.getClass().getSimpleName() + " with " + roundsNumber + " rounds");

        for (int round = 0; round < roundsNumber; round++) {
            Decision firstStrategyDecision = firstStrategy.play(round, secondStrategyDecisions);
            Decision secondStrategyDecision = secondStrategy.play(round, firstStrategyDecisions);

            firstStrategyDecisions.add(firstStrategyDecision);
            secondStrategyDecisions.add(secondStrategyDecision);

            if(Decision.COOPERATE.equals(firstStrategyDecision)) {
                if(Decision.COOPERATE.equals(secondStrategyDecision)) {
                    // Оба сотрудничают
                    firstStrategyScore += 3;
                    secondStrategyScore += 3;
                } else {
                    // Второй предаёт
                    secondStrategyScore += 5;
                }
            } else {
                if(Decision.COOPERATE.equals(secondStrategyDecision)) {
                    // Первый предаёт
                    firstStrategyScore += 5;
                } else {
                    // Оба предают
                    firstStrategyScore++;
                    secondStrategyScore++;
                }
            }
        }

        if(log) {
            System.out.println("Game finished:");
            for(Decision d : firstStrategyDecisions) {
                if(Decision.COOPERATE.equals(d)) {
                    System.out.print("#");
                } else {
                    System.out.print("_");
                }
            }
            System.out.println();
            for(Decision d : secondStrategyDecisions) {
                if(Decision.COOPERATE.equals(d)) {
                    System.out.print("#");
                } else {
                    System.out.print("_");
                }
            }
            System.out.println();
            System.out.println("Total scores:");
            System.out.println(firstStrategy.getClass().getSimpleName() + ": " + firstStrategyScore + " points");
            System.out.println(secondStrategy.getClass().getSimpleName() + ": " + secondStrategyScore + " points");
        }

        return new GameResult(firstStrategyScore / (double) roundsNumber, secondStrategyScore / (double) roundsNumber);
    }
}
