package game;

import java.util.HashMap;

public class Location {
    private String name;
    private String description;
    private HashMap<String, String> exits;
    private String id;

    public Location(String name, String description, HashMap<String, String> exits) {
        this.name = name;
        this.description = description;
        this.exits = exits;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<String, String> getExits() {
        return exits;
    }

    public String getId() {
        return id;
    }
}
