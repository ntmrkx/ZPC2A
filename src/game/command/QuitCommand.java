package game.command;

import game.Game;
import game.command.Command;

public class QuitCommand implements Command {

    public String name() { return "finish"; }

    public String help() { return "finishes the game"; }

    @Override
    public void execute(Game game, String arg) {
        System.out.println("The end.");
        game.stop();
    }
}
