package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import game.model.*;

public class Location {
    private String id;
    private String name;
    private String description;

    private final List<Item> items = new ArrayList<>();

    private final List<NPC> npcs = new ArrayList<>();

    private Map<String, String> exits = new HashMap<>();

    private Map<String, Location> linkedExits = new HashMap<>();

    public Location() { }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    public Map<String, String> getExitIds() { return exits; }

    public void setLinkedExits(Map<String, Location> linkedExits) {
        this.linkedExits = linkedExits;
    }

    public Location getExit(String direction) {
        if (direction == null) return null;
        return linkedExits.get(direction.toLowerCase());
    }


    public Location moveOrStay(String direction) {
        Location next = getExit(direction);
        return (next != null) ? next : this;
    }

    public Item takeItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }

    public NPC getNpc(String name) {
        name = name.toLowerCase();

        for (NPC npc : npcs) {
            if (npc.getName().equals(name)) {
                return npc;
            }
        }
        return null;
    }

}
