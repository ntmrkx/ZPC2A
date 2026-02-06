/**
 * Represents a single command that can be executed by the player.
 * Each command has its name, description and execution logic.
 * This interface is part of the Command design pattern.
 * @author Myroslav Tsykunov
 */

package game.command;

import game.Game;

public interface Command {
    String name();
    String help();
    String execute(Game game, String arg);
}
