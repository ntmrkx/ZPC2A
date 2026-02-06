/**
 * Command that allows player put items to the Inventory.
 *
 * @author Myroslav Tsykunov
 */

package game.command;

import game.Game;
import game.model.Item;

public class TakeCommand implements Command {

    public String name() { return "take"; }

    public String help() { return "Take an item: take <name>"; }

    @Override
    public String execute(Game game, String arg) {
        if (arg == null || arg.isBlank()) return "Using: take <name>";

        Item item = game.getCurrentLocation().takeItem(arg.toLowerCase());
        if (item == null) return "This item is not here.";

        game.getPlayer().getInventory().add(item);
        return "You took: " + item.getName();
    }

}
