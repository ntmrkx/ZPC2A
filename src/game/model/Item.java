package game.model;

/**
 * Represents an item in the game world.
 * Items can be collected by the player and stored
 * in the inventory.
 *
 * @author Myroslav Tsykunov
 */
public class Item {

    /** Name of the item  */
    private String name;

    /**
     * Creates a new item with the given name.
     *
     * @param name name of the item
     */
    public Item(String name) {
        this.name = name.toLowerCase();
    }

    /**
     * Returns the name of the item.
     *
     * @return item name
     */
    public String getName() {
        return name;
    }
}
