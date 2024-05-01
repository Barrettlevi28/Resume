
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class JunitTest {

    @Test
    public void testJunit() {
        System.out.println("Gradle Test Worked");
        assertEquals("Testing Junit", 1, 1);
    }

    @Test
    public void testStartGame() {
        GameLogic game = new GameLogic();
        game.startGame(1);
        assertEquals(game.player.getName(), "Glenn the Adventurer");
        assertEquals(game.player.getRace(), Race.ORC);
        GameLogic game2 = new GameLogic();
        game2.startGame(2);
        assertEquals(game.player.getName(), "Glenn the Adventurer");
        assertEquals(game.player.getRace(), Race.ORC);
        GameLogic game3 = new GameLogic();
        game3.startGame(3);
        assertEquals(game.player.getName(), "Glenn the Adventurer");
        assertEquals(game.player.getRace(), Race.ORC);
        GameLogic game4 = new GameLogic();
        game4.startGame(4);
        assertEquals(game.player.getName(), "Glenn the Adventurer");
        assertEquals(game.player.getRace(), Race.ORC);
    }

}
