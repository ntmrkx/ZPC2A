package game;

import java.util.*;
import game.model.Item;
import game.model.NPC;

public class Location {

    private String name;
    private String description;
    private String id;

    private Map<String, Location> exits = new HashMap<>();
    private List<Item> items = new ArrayList<>();
    private List<NPC> npcs = new ArrayList<>();

    public Location(String name, String description) {
        this.name = name.toLowerCase();
        this.description = description;
    }

    public String getDescription() { return description; }

    public void addExit(String direction, Location location) {
        exits.put(direction.toLowerCase(), location);
    }

    public Location getExit(String direction) {
        return exits.get(direction.toLowerCase());
    }

    public void addItem(Item item) { items.add(item); }

    public Item takeItem(String name) {
        for (Item i : items) {
            if (i.getName().equals(name)) {
                items.remove(i);
                return i;
            }
        }
        return null;
    }

    public void addNpc(NPC npc) { npcs.add(npc); }

    public NPC getNpc(String name) {
        for (NPC n : npcs) {
            if (n.getName().equals(name)) return n;
        }
        return null;
    }

    public String getName() { return name; }
    public String getId() { return id; }
}
