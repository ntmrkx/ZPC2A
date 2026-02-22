package game.command;

import game.Game;

/**
 * Represents a single command in the game.
 * The Console class uses this interface to execute commands.
 *
 * @author Myroslav Tsykunov
 */
public interface Command {

    /**
     * Returns the name of the command.
     *
     * @return command name
     */
    String name();

    /**
     * Returns a short description of the command.
     * Used when displaying help.
     *
     * @return command description
     */
    String help();

    /**
     * Executes the command.
     *
     * @param game current game instance
     * @param arg argument provided by the user
     * @return result message that will be displayed to the player
     */
    String execute(Game game, String arg);
}
