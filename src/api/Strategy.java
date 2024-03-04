package api;

import java.util.List;

public interface Strategy {

    Decision play(int levelNumber, List<Decision> lastGames);

}
