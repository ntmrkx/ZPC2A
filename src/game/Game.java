package game;

import game.command.Console;
import game.command.*;
import game.model.*;

import java.util.Scanner;

public class Game {

    private World world;
    private Location currentLocation;
    private Player player;
    private boolean running;
    private boolean lubaQuestStarted = false;
    private boolean lubaQuestSolved = false;


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
        initItems();

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
        System.out.println("To reach the end you will need to find a Luboshh.");
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

    private void initItems() {

        world.findByName("Hall")
                .addItem(new Item("badge"));

        world.findByName("Archive")
                .addItem(new Item("oldbook"));

        world.findByName("Servers")
                .addItem(new Item("usb"));

        world.findByName("Store")
                .addItem(new Item("coin"));

        world.findByName("Backrooms")
                .addItem(new Item("note"));

        world.findByName("Office")
                .addItem(new Item("pen"));

        world.findByName("Luba")
                .addItem(new Item("trophy"));
    }

    public String talkToLuba(String answer) {

        if (lubaQuestSolved) {
            return "Luba: You already solved my riddle. You are the winner.";
        }

        if (!lubaQuestStarted) {
            lubaQuestStarted = true;
            return """
                Luba: To win, solve my riddle.
                I speak without a mouth and hear without ears.
                I have no body, but I come alive with wind.
                What am I?
                (answer using: talk luba <answer>)
                """;
        }

        boolean hasBadge = player.getInventory().has("badge");
        boolean hasUsb = player.getInventory().has("usb");
        boolean hasNote = player.getInventory().has("note");

        if (!hasBadge || !hasUsb || !hasNote) {
            return "Luba: You are not ready. Bring me: badge, usb, note.";
        }

        if (answer == null || answer.isBlank()) {
            return "Luba: Answer my riddle using: talk luba <answer>";
        }

        if (answer.equalsIgnoreCase("echo")) {
            lubaQuestSolved = true;
            stop();
            return "Luba: Correct. You solved my riddle. YOU WIN!";
        }

        return "Luba: That is not correct. Try again.";
    }


    public String canEnter(Location target) {
        String name = target.getName().toLowerCase();

        if (name.equals("backrooms") && !player.getInventory().has("badge")) {
            return "This place is locked. You need: badge";
        }

        if (name.equals("luba")) {
            boolean hasUsb = player.getInventory().has("usb");
            boolean hasNote = player.getInventory().has("note");
            if (!hasUsb || !hasNote) {
                return "Luba is locked. You need: "
                        + (hasUsb ? "" : "usb ")
                        + (hasNote ? "" : "note ");
            }
        }

        return null;
    }


}
