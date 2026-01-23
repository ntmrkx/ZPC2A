package game;

import java.util.Map;

public class World {
    private final Map<String, Location> locations;
    private final Location startLocation;

    public World(Map<String, Location> locations, Location startLocation) {
        this.locations = locations;
        this.startLocation = startLocation;
    }

    public Location getStartLocation() { return startLocation; }

    public Location getById(String id) {
        return locations.get(id);
    }
}
