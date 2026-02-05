package game.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private final List<Item> items = new ArrayList<>();

    public void add(Item item) {
        items.add(item);
    }

    public Item get(String name) {
        String n = name.toLowerCase();
        for (Item item : items) {
            if (item.getName().equals(n)) {
                return item;
            }
        }
        return null;
    }

    public Item remove(String name) {
        Item item = get(name);
        if (item != null) {
            items.remove(item);
        }
        return item;
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
        for (Item item : items) {
            System.out.println("- " + item.getName());
        }
    }
    public String asText() {
        if (items.isEmpty()) return "Inventory is empty.";
        StringBuilder sb = new StringBuilder("Inventory:\n");
        for (Item i : items) sb.append("- ").append(i.getName()).append("\n");
        return sb.toString();
    }

}
