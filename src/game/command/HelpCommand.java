package game.command;

import game.Game;

/**
 * Command that displays all available commands.
 * Usage: help
 *
 * @author Myroslav Tsykunov
 */
public class HelpCommand implements Command {

    /** Reference to console for registered commands */
    private Console console;

    /**
     * Creates a HelpCommand with access to the console.
     *
     * @param console console containing registered commands
     */
    public HelpCommand(Console console) {
        this.console = console;
    }

    /**
     * @return name of the command used in console input
     */
    @Override
    public String name() {
        return "help";
    }

    /**
     * @return short description of the command
     */
    @Override
    public String help() {
        return "Displays list of available commands";
    }

    /**
     * Executes the help command.
     *
     * @param game current game instance
     * @return formatted list of commands
     */
    @Override
    public String execute(Game game, String arg) {
        return console.getHelpText();
    }
}
