package tests;

import game.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    @Test
    void loadWorld_returnsNotNull() {
        World w = World.loadGameDataFromResources("res/world.json");
        assertNotNull(w);
    }

    @Test
    void startLocation_isId1() {
        World w = World.loadGameDataFromResources("res/world.json");
        Location start = w.findById(1);
        assertNotNull(start);
        assertEquals(1, start.getId());
    }

    @Test
    void findByName_findsHall() {
        World w = World.loadGameDataFromResources("res/world.json");
        Location hall = w.findByName("Hall");
        assertNotNull(hall);
        assertEquals("Hall", hall.getName());
    }
}
