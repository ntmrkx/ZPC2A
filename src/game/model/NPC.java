package game.model;

/**
 * Represents a non-player character (NPC) in the game.
 * NPCs are located in specific locations and can interact
 * with the player through dialogue.
 * Each NPC has a name and predefined dialogue text.
 *
 * @author Myroslav Tsykunov
 */
public class NPC {

    /** Name of the NPC (stored in lowercase) */
    private String name;

    /** Dialogue text spoken by the NPC */
    private String text;

    /**
     * Creates a new NPC with a given name and dialogue text.
     *
     * @param name name of the NPC
     * @param text dialogue text spoken by the NPC
     */
    public NPC(String name, String text) {
        this.name = name.toLowerCase();
        this.text = text;
    }

    /**
     * Returns the name of the NPC.
     *
     * @return NPC name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the dialogue text of the NPC.
     *
     * @return NPC dialogue
     */
    public String talk() {
        return text;
    }
}
