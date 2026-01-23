package game.command;

import game.Game;

public interface Command {

    String getName();
    String getDescription();

    void execute(Game game, String argument);
}
