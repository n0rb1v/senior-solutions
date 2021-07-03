package ebgame;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameRepositoryTest {

    GameRepository gameRepository = new GameRepository();

    @BeforeEach
    void init(){
        gameRepository.loadFile();
    }

    @Test
    void biggestScoreDiff() {
        assertEquals(3, gameRepository.biggestScoreDiff().getScoreDiff());
    }

    @Test
    void goalnumber() {
        assertEquals(3,gameRepository.goalnumber("Belgium"));
    }

    @Test
    void maxScore() {
        assertEquals("Italy",gameRepository.maxScore());
    }
}