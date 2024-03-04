package examples;

import api.Decision;
import api.Strategy;

import java.util.List;

public class EyeForEyeStrategy implements Strategy {
    @Override
    public Decision play(int levelNumber, List<Decision> lastGames) {
        if(levelNumber == 0) {
            return Decision.COOPERATE;
        }
        return lastGames.get(levelNumber - 1);
    }
}
