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

        if (arg == null || arg.isBlank()) {
            return "Usage: use <item> [npc]";
        }

        String[] parts = arg.split(" ", 2);
        String itemName = parts[0].toLowerCase();
        String targetNpc = (parts.length > 1) ? parts[1].toLowerCase() : null;

        Item item = game.getPlayer().getInventory().get(itemName);

        if (item == null) {
            return "You don't have this item.";
        }

        if (targetNpc != null) {
            return game.giveItemToNpc(item, targetNpc);
        }

        return game.useItem(item);
    }

}
