package game.command;

import game.Game;
import game.command.Command;
import game.command.CommandProcessor;

public class HelpCommand implements Command {

    private  CommandProcessor processor;

    public HelpCommand(CommandProcessor processor) {
        this.processor = processor;
    }

    @Override public String name() { return "help"; }

    @Override public String help() { return "Writes commands list"; }

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
