package game.command;

import game.Game;

public interface Command {
    String name();
    String help();
    void execute(Game game, String arg);
}
