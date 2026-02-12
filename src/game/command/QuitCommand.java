/**
 * Command that allows user finish the Game.
 *
 * @author Myroslav Tsykunov
 */

package game.command;

import game.Game;

public class QuitCommand implements Command {

    public String name() { return "finish"; }

    public String help() { return "finishes the game"; }

    @Override
    public String execute(Game game, String arg) {
        game.stop();
        return "The end.";
    }

}
