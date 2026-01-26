package game;

import game.command.*;
import game.model.*;

import java.util.Scanner;

public class Game {

    private World world;
    private Player player;

    private Location currentLocation;
    private boolean running;

    private CommandProcessor processor;
    private Scanner scanner;

    public Game(World world) {
        this.world = world;
        this.player = new Player();
        this.currentLocation = world.getStartLocation();
        this.processor = new CommandProcessor();
        this.scanner = new Scanner(System.in);

        registerCommands();
    }

    public void run() {
        running = true;
        printIntro();

        while (running) {
            printPrompt();
            String input = readInput();
            handleInput(input);
        }

        printOutro();
    }

    public void stop() {
        running = false;
    }


    private void printIntro() {
        System.out.println("Welcome to the Game!");
        System.out.println("Type 'help' for a list of commands.");
        printLocation();
    }

    private void printOutro() {
        System.out.println("Thanks for playing!");
    }

    private void printPrompt() {
        System.out.print("> ");
    }

    private String readInput() {
        return scanner.nextLine();
    }

    private void handleInput(String input) {
        if (input == null || input.isBlank()) {
            System.out.println("Type a command (for example: help).");
            return;
        }
        processor.process(this, input);
    }


    private void registerCommands() {
        processor.register(new GoCommand());
        processor.register(new TakeCommand());
        processor.register(new UseCommand());
        processor.register(new TalkCommand());
        processor.register(new InventoryCommand());
        processor.register(new HelpCommand(processor));
        processor.register(new QuitCommand());
    }


    public void printLocation() {
        System.out.println(currentLocation.getDescription());
    }


    public World getWorld() {
        return world;
    }

    public Player getPlayer() {
        return player;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean isRunning() {
        return running;
    }

}
