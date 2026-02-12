package game.command;

import game.Game;

/**
 * Command that displays the content of the player's inventory.
 * This command allows the player to see all collected items.
 * It retrieves the inventory from the Player object
 * and returns its formatted text representation.
 * Usage: inventory
 * Part of the Command pattern implementation.
 *
 * @author Myroslav Tsykunov
 */
public class InventoryCommand implements Command {

    /**
     * @return name of the command used in console input
     */
    public String name() {
        return "inventory";
    }

    /**
     * @return short help description of the command
     */
    public String help() {
        return "Shows all items in inventory";
    }

    /**
     * Executes the inventory command.
     *
     * @param game current game instance
     * @return formatted inventory content
     */
    @Override
    public String execute(Game game, String arg) {
        return game.getPlayer().getInventory().asText();
    }
}
