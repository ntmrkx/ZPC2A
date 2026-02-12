package game.command;

import game.Game;
import game.Location;

/**
 * Command that moves the player to another location.
 *
 * @author Myroslav Tsykunov
 */
public class GoCommand implements Command {

    @Override
    public String name() {
        return "go";
    }

    @Override
    public String help() {
        return "go <location name>";
    }

    @Override
    public String execute(Game game, String argument) {
        if (argument == null || argument.isBlank()) {
            return "Usage: go <location>";
        }

        Location target = game.getWorld().findByName(argument.trim());
        if (target == null) {
            return "No such location.";
        }

        if (!game.getCurrentLocation().getExits().contains(target.getId())) {
            return "You cannot go there from here.";
        }

        String lockMsg = game.canEnter(target);
        if (lockMsg != null) {
            return lockMsg;
        }

        game.setCurrentLocation(target);

        return game.describeLocation();
    }
}
