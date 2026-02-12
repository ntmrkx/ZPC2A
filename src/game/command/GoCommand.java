package game.command;

import game.Game;
import game.Location;

/**
 * Command that moves the player to another location.
 * The command checks:
 * 1. Whether the target location exists.
 * 2. Whether the location is connected to the current one.
 * 3. Whether the location is locked and requires specific items.
 * If all conditions are satisfied, the player is moved
 * and the new location description is returned.
 * Usage: go <location name>
 * Part of the Command pattern implementation.
 *
 * @author Myroslav Tsykunov
 */
public class GoCommand implements Command {

    /**
     * @return name of the command used in console input
     */
    @Override
    public String name() {
        return "go";
    }

    /**
     * @return short help description of the command
     */
    @Override
    public String help() {
        return "Moves player to another location: go <location>";
    }

    /**
     * Executes the move command.
     *
     * @param game current game instance
     * @param argument name of the target location
     * @return result message or new location description
     */
    @Override
    public String execute(Game game, String argument) {

        if (argument == null || argument.isBlank()) {
            return "Usage: go <location>";
        }

        Location target = game.getWorld().findByName(argument.trim());

        if (target == null) {
            return "No such location.";
        }

        // Check if location is connected (map logic)
        if (!game.getCurrentLocation().getExits().contains(target.getId())) {
            return "You cannot go there from here.";
        }

        // Check if location is locked (game logic)
        String lockMsg = game.canEnter(target);
        if (lockMsg != null) {
            return lockMsg;
        }

        // Move player
        game.setCurrentLocation(target);

        // Show updated location info
        return game.describeLocation();
    }
}
