package game.command;

import game.Game;
import game.command.Command;
import game.model.Item;

public class TakeCommand implements Command {

    public String name() { return "take"; }

    public String help() { return "Take an item: take <name>"; }

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
            System.out.println("Using: take <name>");
            return;
        }

        Item item = game.getCurrentLocation().takeItem(arg.toLowerCase());
        if (item == null) {
            System.out.println("This item is not here.");
            return;
        }

        game.getPlayer().getInventory().add(item);
        System.out.println("You took: " + item.getName());
    }
}
