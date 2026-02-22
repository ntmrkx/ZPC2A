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
    private boolean backroomsUnlocked = false;
    private boolean lubaUnlocked = false;
    private boolean officeUnlocked = false;



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
                "'Everything we hear is an opinion, not a fact. Everything we see is a perspective, not the truth.'\n" +
                        "- Marcus Aurelius"
        ));

        Location luba = world.findByName("Luba");
        luba.addNpc(new NPC(
                "luba",
                "'To live is to suffer, to survive is to find some meaning in the suffering.'\n" +
                        "- Friedrich Nietzsche"
        ));
    }

    private void initItems() {

        world.findByName("Hall")
                .addItem(new Item("badge"));

        world.findByName("Archive")
                .addItem(new Item("code"));

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
