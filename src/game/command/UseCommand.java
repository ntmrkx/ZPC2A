package game.command;

import game.Game;
import game.command.Command;
import game.model.Item;

public class UseCommand implements Command {

    public String name() { return "use"; }

    public String help() { return "Use item: use <item> [cil]"; }

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
            System.out.println("Using: use <item> [cil]");
            return;
        }

        String[] parts = arg.split("\\s+", 2);
        String itemName = parts[0].toLowerCase();
        String target = (parts.length > 1) ? parts[1].toLowerCase() : "";

        Item item = game.getPlayer().getInventory().get(itemName);
        if (item == null) {
            System.out.println("You don't have that item.");
            return;
        }

    }
}
