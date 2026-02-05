package game.command;

import game.Game;
import game.command.Command;

public class QuitCommand implements Command {

    public String name() { return "finish"; }

    public String help() { return "finishes the game"; }

    @Override
    public String execute(Game game, String arg) {
        game.stop();
        return "The end.";
    }

}
