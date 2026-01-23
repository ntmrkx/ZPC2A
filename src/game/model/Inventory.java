package game.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Item> items = new ArrayList<>();

    public void add(Item item) {
        items.add(item);
    }

    public Item get(String name) {
        for (Item i : items) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    public Item remove(String name) {
        Item found = get(name);
        if (found != null) {
            items.remove(found);
        }
        return found;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void print() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("Inventory:");
        for (Item i : items) {
            System.out.println("- " + i.getName());
        }
    }
}
