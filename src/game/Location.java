package game;

import java.util.ArrayList;
import java.util.List;
import game.model.*;

public class Location {

    private int id;
    private String name;
    private String description;
    private List<Integer> exits = new ArrayList<>();
    private boolean npc;

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<Integer> getExits() { return exits; }
    public boolean hasNpc() { return npc; }

    @Override
    public String toString() {
        return name;
    }

    private List<Item> items = new ArrayList<>();

    public Item takeItem(String name) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getName().equals(name)) {
                items.remove(i);
                return item;
            }
        }
        return null;
    }
    private List<NPC> npcs = new ArrayList<>();

    public NPC getNpc(String name) {
        for (NPC npc : npcs) {
            if (npc.getName().equals(name)) {
                return npc;
            }
        }
        return null;
    }

    public void addNpc(NPC npc) {
        npcs.add(npc);
    }

}
