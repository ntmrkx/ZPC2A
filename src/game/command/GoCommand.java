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
    public void execute(Game game, String argument) {
        if (argument == null || argument.isBlank()) {
            System.out.println("Usage: go <location>");
            return;
        }

        Location target = game.getWorld().findByName(argument);
        if (target == null) {
            System.out.println("No such location.");
            return;
        }

        if (!game.getCurrentLocation().getExits().contains(target.getId())) {
            System.out.println("You cannot go there from here.");
            return;
        }

    }
}
