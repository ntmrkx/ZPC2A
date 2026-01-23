package game.model;

public class NPC {
    private String name;
    private String text;

    public NPC(String name, String text) {
        this.name = name.toLowerCase();
        this.text = text;
    }

    public String getName() { return name; }
    public String talk() { return text; }
}
