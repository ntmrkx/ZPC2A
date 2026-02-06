/**
 * Command that shows Items in Inventory to player.
 *
 * @author Myroslav Tsykunov
 */

package game.command;

import game.Game;
import game.command.Command;

public class InventoryCommand implements Command {

    public String name() { return "inventory"; }

    public String help() { return "writes items in inventory"; }

    @Override
    public String execute(Game game, String arg) {
        return game.getPlayer().getInventory().asText();
    }

}
