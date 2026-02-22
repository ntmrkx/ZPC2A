package game;

import game.command.Console;
import game.command.*;
import game.model.*;

import java.util.Scanner;

/**
 * Main game class that manages the whole gameplay loop.
 * Loads the world from JSON
 * Creates player and initializes game state
 * Registers commands (Command design pattern)
 * Starts and controls the main loop (reads user input, executes commands)
 * Handles game logic such as locked locations, using items and NPC quest
 *
 * @author Myroslav Tsykunov
 */
public class Game {

    /** Loaded game world containing all locations. */
    private World world;

    /** Current location where the player is. */
    private Location currentLocation;

    /** Player as object. */
    private Player player;

    /** Controlling whether the game loop should continue. */
    private boolean running;

    /** Quest for Luba. */
    private boolean lubaQuestStarted = false;
    private boolean lubaQuestSolved = false;

    /** Locked doors. */
    private boolean backroomsUnlocked = false;
    private boolean lubaUnlocked = false;
    private boolean officeUnlocked = false;

    /** Console to register and execute commands. */
    private Console console;

    /** Scanner for reading user input. */
    private Scanner scanner;

    /** Initializes everything. */
    public Game() {
        init();
    }

    /** Initializes the game. */
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

    /** Registers all available commands into the console. */
    private void registerCommands() {
        console.register(new GoCommand());
        console.register(new TakeCommand());
        console.register(new InventoryCommand());
        console.register(new HelpCommand(console));
        console.register(new QuitCommand());
        console.register(new TalkCommand());
        console.register(new UseCommand());
    }

    /**
     * Starts the game loop.
     * Prints intro text.
     */
    public void run() {
        System.out.println("Welcome to the LAST MISTAKE!");
        System.out.println("=====================================================");
        System.out.println("To reach the end you will need to find a Luboshh.");
        System.out.println("Now you are in the Hall, good luck!");
        System.out.println("=====================================================");
        System.out.println("such a long hall, where your story starts or finishes\n" +
                "You can go to: Archive, Servers\n" +
                "Items here: badge\n" +
                "NPC here: guard");

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

    /** After calling this method, the game will end. */
    public void stop() {
        running = false;
    }

    /** Loading world */
    public World getWorld() {
        return world;
    }

    /**
     * @return current player location
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Sets the current location where the player stands.
     *
     * @param loc new location
     */
    public void setCurrentLocation(Location loc) {
        currentLocation = loc;
    }

    /** Loading Player */
    public Player getPlayer() {
        return player;
    }

    /**
     * Creates a formatted description of the current location.
     * @return formatted text describing current location
     */
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

    /** Creates and adds NPC characters into specific locations. */
    private void initNPCs() {
        Location hall = world.findByName("Hall");
        hall.addNpc(new NPC("guard", "Welcome to the Hall. Your journey begins here."));

        Location servers = world.findByName("Servers");
        servers.addNpc(new NPC("admin", "These servers keep the whole world running."));

        Location backrooms = world.findByName("Backrooms");
        backrooms.addNpc(new NPC("stranger",
                "'Everything we hear is an opinion, not a fact. Everything we see is a perspective, not the truth.'\n" +
                        "- Marcus Aurelius"));

        Location luba = world.findByName("Luba");
        luba.addNpc(new NPC("luba",
                "'To live is to suffer, to survive is to find some meaning in the suffering.'\n" +
                        "- Friedrich Nietzsche"));
    }

    /** Creates and adds items into locations at the start of the game. */
    private void initItems() {
        world.findByName("Hall").addItem(new Item("badge"));
        world.findByName("Archive").addItem(new Item("code"));
        world.findByName("Servers").addItem(new Item("usb"));
        world.findByName("Store").addItem(new Item("coin"));
        world.findByName("Backrooms").addItem(new Item("note"));
        world.findByName("Office").addItem(new Item("pen"));
        world.findByName("Luba").addItem(new Item("trophy"));
    }

    /**
     * Special quest for "Luba".
     * @param answer player's answer (text after "talk luba ...")
     * @return response text from Luba / quest system
     */
    public String talkToLuba(String answer) {

        if (lubaQuestSolved) {
            return "Luba: You already solved my riddle. You are the winner.";
        }

        if (!lubaQuestStarted) {
            lubaQuestStarted = true;
            return " Luba: To win, help me to make my code work.\n 'public class Main {' \n 'public ... void main(String[] args) {'\n 'System.out.println(\"HELLO WORLD!\");'\n What is missing? \n (answer using: talk luba <answer>) ";
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

        if (answer.equalsIgnoreCase("static")) {
            lubaQuestSolved = true;
            stop();
            return "Luba: That works!. You helped me. YOU WIN!";
        }

        return "Luba: Still not working. Try again.";
    }

    /**
     * Checks whether the player can enter a target location.
     *
     * @param target target location
     * @return message explaining why it is locked
     */
    public String canEnter(Location target) {
        String name = target.getName().toLowerCase();

        if (name.equals("backrooms") && !backroomsUnlocked) {
            return "Unauthorized try! Use original staff badge!";
        }

        if (name.equals("luba") && !lubaUnlocked) {
            return "System locked! Use usb do delete the virus.";
        }

        if (name.equals("office") && !officeUnlocked) {
            return "Office is locked. Unlock with code!";
        }

        return null;
    }

    /**
     * Gives an item to an NPC located in the current location.
     *
     * @param item item from player's inventory
     * @param npcName target npc name
     * @return response message about the result
     */
    public String giveItemToNpc(Item item, String npcName) {

        NPC npc = currentLocation.getNpc(npcName);

        if (npc == null) {
            return "That NPC is not here.";
        }

        if (npcName.equals("luba") && item.getName().equals("note")) {
            player.getInventory().remove(item.getName());
            lubaQuestSolved = true;
            stop();
            return "Luba: This is what I needed. You win!";
        }

        return "They don't want this item.";
    }

    /**
     * Uses an item from the player's inventory.
     *
     * @param item item to be used
     * @return response message for the player
     */
    public String useItem(Item item) {

        String name = item.getName();

        if (name.equals("badge")
                && currentLocation.getName().equalsIgnoreCase("Office")) {

            backroomsUnlocked = true;
            return "You used the badge. Backrooms are now unlocked.";
        }

        if (name.equals("usb")
                && currentLocation.getName().equalsIgnoreCase("Backrooms")) {

            lubaUnlocked = true;
            return "System activated. Luba is now accessible.";
        }

        if (name.equals("code")
                && currentLocation.getName().equalsIgnoreCase("Store")) {

            officeUnlocked = true;
            return "Entrance permitted!";
        }

        return "You can't use that here.";
    }
}