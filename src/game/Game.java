package game;

import game.command.Console;
import game.command.*;
import game.model.Player;

import java.util.Scanner;

public class Game {

    private World world;
    private Location currentLocation;
    private Player player;
    private boolean running;

    private Console console;
    private Scanner scanner;

    public Game() {
        init();
    }

    private void init() {
        world = World.loadGameDataFromResources("res/world.json");
        currentLocation = world.getStartLocation();
        player = new Player();

        console = new Console();
        registerCommands();

        scanner = new Scanner(System.in);
    }

    private void registerCommands() {
        console.register(new GoCommand());
        console.register(new TakeCommand());
        console.register(new InventoryCommand());
        console.register(new HelpCommand(console));
        console.register(new QuitCommand());
        console.register(new TalkCommand());
        console.register(new UseCommand());
    }


    public void run() {
        System.out.println("Welcome to the LAST MISTAKE!");
        System.out.println("To reach the end you will need to find a Luboshh, he ");
        System.out.println(currentLocation.getDescription());

        running = true;

        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input == null || input.trim().isEmpty()) {
                System.out.println("Please type a command.");
                continue;
            }

            console.process(this, input);
        }

        System.out.println("Game over.");
    }

    public void stop() {
        running = false;
    }

    public World getWorld() {
        return world;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location loc) {
        currentLocation = loc;
    }

    public Player getPlayer() {
        return player;
    }

    public String describeLocation() {
        StringBuilder sb = new StringBuilder();
        sb.append(currentLocation.getDescription()).append("\n");

        sb.append("You can go to: ");
        if (currentLocation.getExits().isEmpty()) {
            sb.append("(nowhere)");
        } else {
            for (Integer id : currentLocation.getExits()) {
                Location l = world.findById(id);
                if (l != null) sb.append(l.getName()).append(", ");
            }
            if (sb.toString().endsWith(", ")) {
                sb.setLength(sb.length() - 2);
            }
        }
        sb.append("\n");

        sb.append("Items here: ").append(currentLocation.itemsAsText()).append("\n");
        sb.append("NPC here: ").append(currentLocation.npcsAsText());

        return sb.toString();
    }

}
