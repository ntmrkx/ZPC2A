package game;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class World {
    private ArrayList<Location> locations;


    public static World loadGameDataFromResources(String resourcePath) {
        //Vytvoření objektu pro práci s JSON souborem
        Gson gson = new Gson();

            try (var is = World.class.getResourceAsStream(resourcePath)) {
                if (is == null) {
                    throw new RuntimeException("Resource not found: " + resourcePath);
                }
                try (Reader rd = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                    return gson.fromJson(rd, World.class);
                }

        } catch (Exception e) {
            throw new RuntimeException("Chyba při načítání JSON: " + e.getMessage());
        }
    }

    public Location findById(int id) {
        for (Location l : locations) {
            if (l.getId() == id) return l;
        }
        return null;
    }

    public Location findByName(String name) {
        String n = name.toLowerCase().trim();
        for (Location l : locations) {
            if (l.getName().toLowerCase().equals(n)) return l;
        }
        return null;
    }

    public Location getStartLocation() {
        Location start = findById(1);
        if (start == null)
            throw new IllegalStateException("Start location not found");
        return start;
    }
}
