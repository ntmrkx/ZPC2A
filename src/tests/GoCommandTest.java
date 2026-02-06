package tests;

import game.command.*;
import game.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoCommandTest {

    @Test
    void goToConnectedLocation_changesCurrentLocation() {
        Game game = new Game();
        GoCommand go = new GoCommand();

        String out = go.execute(game, "Archive");
        assertTrue(out != null && !out.isBlank());
        assertEquals("Archive", game.getCurrentLocation().getName());
    }

    @Test
    void goToNotConnectedLocation_doesNotChangeLocation() {
        Game game = new Game();
        GoCommand go = new GoCommand();

        String out = go.execute(game, "Store");
        assertEquals("Hall", game.getCurrentLocation().getName());
        assertTrue(out.toLowerCase().contains("cannot"));
    }
}
