package game;

import game.command.Console;
import game.command.*;
import game.model.Player;
import game.model.NPC;

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

        initNPCs();

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

    public boolean checkWin() {
        if (currentLocation.getId() == 7) {
            return true;
        }
        return false;
        //TODO
    }

    private void initNPCs() {

        Location hall = world.findByName("Hall");
        hall.addNpc(new NPC(
                "guard",
                "Welcome to the Hall. Your journey begins here."
        ));

        Location servers = world.findByName("Servers");
        servers.addNpc(new NPC(
                "admin",
                "These servers keep the whole world running."
        ));

        Location backrooms = world.findByName("Backrooms");
        backrooms.addNpc(new NPC(
                "stranger",
                "You should not be here... but now it is too late."
        ));

        Location luba = world.findByName("Luba");
        luba.addNpc(new NPC(
                "luba",
                "You made it. This is the end of your journey."
        ));
    }



}
