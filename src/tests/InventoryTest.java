package tests;

import game.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void addItem_thenGetReturnsItem() {
        Inventory inv = new Inventory();
        inv.add(new Item("usb"));

        assertNotNull(inv.get("usb"));
        assertEquals("usb", inv.get("usb").getName());
    }

    @Test
    void removeItem_itemIsGone() {
        Inventory inv = new Inventory();
        inv.add(new Item("coin"));

        assertNotNull(inv.remove("coin"));
        assertNull(inv.get("coin"));
    }
}
