/**
 * Command that prints list of possible commands and their usage.
 *
 * @author Myroslav Tsykunov
 */

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
    public String execute(Game game, String arg) {
        return console.getHelpText();
    }

}
