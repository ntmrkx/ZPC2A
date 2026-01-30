package game.model;

public class Item {

    private final String name;

    public Item(String name) {
        this.name = name.toLowerCase();
    }

    public String getName() {
        return name;
    }
}
