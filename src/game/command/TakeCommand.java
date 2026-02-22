package game.command;

import game.Game;
import game.model.Item;

/**
 * Command that allows the player to take an item
 * from the current location and add it to the inventory.
 * Usage: take <item name>
 *
 * @author Myroslav Tsykunov
 */
public class TakeCommand implements Command {

    /**
     * @return name of the command used in console input
     */
    public String name() {
        return "take";
    }

    /**
     * @return short help description
     */
    public String help() {
        return "Take an item: take <name>";
    }

    /**
     * Executes the take command.
     *
     * @param game current game instance
     * @param arg name of the item to take
     * @return result message shown to the player
     */
    @Override
    public String execute(Game game, String arg) {

        if (arg == null || arg.isBlank()) {
            return "Usage: take <name>";
        }

        Item item = game.getCurrentLocation().takeItem(arg.toLowerCase());

        if (item == null) {
            return "This item is not here.";
        }

        game.getPlayer().getInventory().add(item);

        return "You took: " + item.getName();
    }
}
