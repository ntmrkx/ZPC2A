package game.model;

/**
 * Represents the player in the game.
 * The player has an inventory that stores collected items.
 *
 * @author Myroslav Tsykunov
 */
public class Player {

    /** Player's inventory */
    private Inventory inventory;

    /**
     * Creates a new player with an empty inventory.
     */
    public Player() {
        this.inventory = new Inventory();
    }

    /**
     * Returns the player's inventory.
     *
     * @return inventory instance
     */
    public Inventory getInventory() {
        return inventory;
    }
}
