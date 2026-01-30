package game.command;

import game.Game;

public class HelpCommand implements Command {

    private final Console console;

    public HelpCommand(Console console) {
        this.console = console;
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public String help() {
        return "writes commands list";
    }

    @Override
    public void execute(Game game, String arg) {
        console.printHelp();
    }
}
