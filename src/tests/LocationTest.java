package tests;

import game.model.*;
import game.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void takeItem_removesItemFromLocation() {
        Location loc = new Location();
        loc.addItem(new Item("badge"));

        Item taken = loc.takeItem("badge");
        assertNotNull(taken);
        assertEquals("badge", taken.getName());

        assertNull(loc.takeItem("badge"));
    }

    @Test
    void getNpc_returnsNullWhenNotFound() {
        Location loc = new Location();
        assertNull(loc.getNpc("ghost"));
    }
}
