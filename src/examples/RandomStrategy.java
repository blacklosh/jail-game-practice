package examples;

import api.Decision;
import api.Strategy;

import java.util.List;

public class RandomStrategy implements Strategy {
    @Override
    public Decision play(int levelNumber, List<Decision> lastGames) {
        return (int)(Math.random() * 2) == 0 ? Decision.COOPERATE : Decision.LIE;
    }
}
