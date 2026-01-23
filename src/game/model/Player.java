package game.model;

public class Player {

    private final Inventory inventory;

    public Player() {
        this.inventory = new Inventory();
    }

    public Inventory getInventory() {
        return inventory;
    }
}
