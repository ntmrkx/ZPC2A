package game.command;

import game.Game;
import game.Location;

public class GoCommand implements Command {

    public String name() {
        return "go";
    }

    public String help() {
        return "go <location name>";
    }

    @Override
    public String execute(Game game, String argument) {
        if (argument == null || argument.isBlank()) {
            return "Usage: go <location>";
        }

        Location target = game.getWorld().findByName(argument);
        if (target == null) {
            return "No such location.";
        }

        if (!game.getCurrentLocation().getExits().contains(target.getId())) {
            return "You cannot go there from here.";
        }

        game.setCurrentLocation(target);

        if (game.checkWin()) {
            game.stop();
            return "You solved the secrets of Luba. YOU WON!";
        }
        return game.describeLocation();
    }
}

