package game.command;

import game.Game;
import game.command.Command;
import game.command.Console;

public class HelpCommand implements Command {

    private Console processor;

    public HelpCommand(Console processor) {
        this.processor = processor;
    }

    public String name() { return "help"; }

    public String help() { return "Writes commands list"; }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void execute(Game game, String arg) {
        processor.printHelp();
    }
}
