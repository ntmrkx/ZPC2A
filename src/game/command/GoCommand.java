package game.command;

import game.Game;
import game.command.Command;
import game.Location;

public class GoCommand implements Command {

    public String name() { return "go"; }
    public String help() { return "Move: go <direction>"; }

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
        if (arg.isBlank()) {
            System.out.println("Using: go <direction>");
            return;
        }

        Location current = game.getCurrentLocation();
        Location next = current.moveOrStay(arg);

        if (next == current) {
            System.out.println("You cannot go this way.");
            return;
        }

        game.setCurrentLocation(next);
        game.printLocation();
    }
}
