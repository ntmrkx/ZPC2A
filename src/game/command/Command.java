package game.command;

import game.Game;

/**
 * Represents a single command in the game.
 * Each concrete command (e.g. GoCommand, TakeCommand, TalkCommand)
 * implements this interface and defines its own behavior.
 * This interface is a part of the Command design pattern,
 * where each user action is encapsulated in a separate class.
 * The Console class uses this interface to execute commands
 * dynamically based on user input.
 *
 * @author Myroslav Tsykunov
 */
public interface Command {

    /**
     * Returns the name of the command.
     * This name is used to match user input in the console.
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
