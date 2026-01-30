package game;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class World {
    private ArrayList<Location> locations;


    public static World loadGameDataFromResources(String resourcePath) {
        //Vytvoření objektu pro práci s JSON souborem
        Gson gson = new Gson();

        //Načtení souboru gamedata.json, musí být ve složce res/resources, ta musí být označena jako resource složka projektu
        try (Reader rd = new FileReader(resourcePath)) {

            //Přečte celý JSON a vytvoří instanci GameData, naplní vlastnosti podle názvů klíčů v JSONU, vrátí se hotová třída GameData
            return gson.fromJson(
                    rd,
                    World.class
            );

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
