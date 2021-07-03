package ebgame;

public class GameService {
    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game biggestScooreDiff() {
        return gameRepository.biggestScoreDiff();
    }

    public int goalNumber(String s) {
        return gameRepository.goalnumber(s);
    }

    public String maxScore() {
        return gameRepository.maxScore();
    }
}
