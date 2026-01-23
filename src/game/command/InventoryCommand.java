package game.command;

import game.Game;
import game.command.Command;

public class InventoryCommand implements Command {

    public String name() { return "inventory"; }

    public String help() { return "writes items in inventory"; }

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
        game.getPlayer().getInventory().print();
    }
}
