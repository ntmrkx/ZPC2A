package game;

import java.util.ArrayList;
import java.util.List;
import game.model.*;

/**
 * Represents a location (room) in the game world.
 * Each location has:
 * - unique identifier (id)
 * - name
 * - description
 * - list of connected locations (exits)
 * - items located inside the room
 * - NPCs present in the room
 * Locations are loaded from JSON and then populated
 * with items and NPCs during game initialization.
 *
 * @author Myroslav Tsykunov
 */
public class Location {

    /** Unique identifier of the location */
    private int id;

    /** Name of the location */
    private String name;

    /** Text description shown to the player */
    private String description;

    /** List of connected location IDs */
    private List<Integer> exits = new ArrayList<>();

    /** Indicates whether the location contains NPC (from JSON) */
    private boolean npc;

    /** Items available in this location */
    private List<Item> items = new ArrayList<>();

    /** NPCs present in this location */
    private List<NPC> npcs = new ArrayList<>();

    /**
     * @return location ID
     */
    public int getId() { return id; }

    /**
     * @return location name
     */
    public String getName() { return name; }

    /**
     * @return location description
     */
    public String getDescription() { return description; }

    /**
     * @return list of connected location IDs
     */
    public List<Integer> getExits() { return exits; }

    /**
     * @return true if location contains NPC (JSON flag)
     */
    public boolean hasNpc() { return npc; }

    /**
     * Returns the name of the location when printed.
     *
     * @return location name
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Removes an item from the location.
     *
     * @param name name of the item
     * @return removed item or null if not found
     */
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

    /**
     * Finds an NPC by name.
     *
     * @param name NPC name
     * @return found NPC or null if not present
     */
    public NPC getNpc(String name) {
        for (NPC npc : npcs) {
            if (npc.getName().equals(name)) {
                return npc;
            }
        }
        return null;
    }

    /**
     * Adds an NPC to the location.
     *
     * @param npc NPC to add
     */
    public void addNpc(NPC npc) {
        npcs.add(npc);
    }

    /**
     * Adds an item to the location.
     *
     * @param item item to add
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Returns formatted text of items present in the location.
     *
     * @return separated item names or "(none)"
     */
    public String itemsAsText() {
        if (items.isEmpty()) return "(none)";
        StringBuilder sb = new StringBuilder();
        for (Item i : items) sb.append(i.getName()).append(", ");
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    /**
     * Returns formatted text of NPCs present in the location.
     *
     * @return separated NPC names or "(none)"
     */
    public String npcsAsText() {
        if (npcs.isEmpty()) return "(none)";
        StringBuilder sb = new StringBuilder();
        for (NPC n : npcs) sb.append(n.getName()).append(", ");
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }
}
