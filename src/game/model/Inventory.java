/**
 * Represents the player's inventory.
 * The inventory stores items collected by the player during the game.
 * It allows adding, removing, checking and displaying items.
 *
 * @author Myroslav Tsykunov
 */
package game.model;

import java.util.ArrayList;

public class Inventory {

    /** List of items currently stored in the inventory */
    private  ArrayList<Item> items = new ArrayList<>();

    /**
     * Adds an item to the inventory.
     *
     * @param item item to be added
     */
    public void add(Item item) {
        items.add(item);
    }

    /**
     * Returns an item by its name.
     *
     * @param name name of the item
     * @return found Item or null if not present
     */
    public Item get(String name) {
        String n = name.toLowerCase();
        for (Item item : items) {
            if (item.getName().equals(n)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Removes an item from the inventory.
     *
     * @param name name of the item
     * @return removed Item or null if not found
     */
    public Item remove(String name) {
        Item item = get(name);
        if (item != null) {
            items.remove(item);
        }
        return item;
    }

    /**
     * Checks whether the inventory is empty.
     *
     * @return true if inventory contains no items
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Prints the content of the inventory directly to console.
     */
    public void print() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("Inventory:");
        for (Item item : items) {
            System.out.println("- " + item.getName());
        }
    }

    /**
     * Returns a formatted text representation of the inventory.
     *
     * @return formatted inventory content as String
     */
    public String asText() {
        if (items.isEmpty()) return "Inventory is empty.";
        StringBuilder sb = new StringBuilder("Inventory:\n");
        for (Item i : items) sb.append("- ").append(i.getName()).append("\n");
        return sb.toString();
    }

    /**
     * Checks if the inventory contains an item with the given name.
     *
     * @param name name of the item
     * @return true if the item exists in inventory
     */
    public boolean has(String name) {
        return get(name.toLowerCase()) != null;
    }
}
