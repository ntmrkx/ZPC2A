package game.command;

import game.Game;

public interface Command {
    String name();
    String help();
    String execute(Game game, String arg);
}
