/**
 * Command that allows player to use items.
 *
 * @author Myroslav Tsykunov
 */

package game.command;

import game.Game;
import game.model.Item;

public class UseCommand implements Command {

    public String name() { return "use"; }

    public String help() { return "Use item: use <item> [cil]"; }

    @Override
    public String execute(Game game, String arg) {
        if (arg == null || arg.isBlank()) return "Using: use <item>";

        String itemName = arg.trim().toLowerCase().split(" ")[0];

        Item item = game.getPlayer().getInventory().remove(itemName);
        if (item == null) return "You don't have that item.";

        return "You used: " + item.getName();
    }

}
