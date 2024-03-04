package examples;

import api.Decision;
import api.Strategy;

import java.util.List;

public class LieManStrategy implements Strategy {
    @Override
    public Decision play(int levelNumber, List<Decision> lastGames) {
        return Decision.LIE;
    }
}
