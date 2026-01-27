package game.command;

import game.World;
import game.command.Command;
import game.model.Player;
import game.Game;

public class GoCommand implements Command {

    private World game;
    private Player player;


    public String go(String exit) {
        for (int i = 0; i < game.getLocations().size(); i++) {
            if (game.getLocations().get(i).getName().contains(exit.toLowerCase().trim())) {
                if (player.getCurrentLocation().getExits().contains(game.getLocations().get(i).getId())) {
                    player.setCurrentLocation(game.getLocations().get(i));
                    System.out.println("Current location: " + player.getCurrentLocation());
                }
            }
        }
        return "smthn";
    }

    public GoCommand(World game, Player player) {
        this.game = game;
        this.player = player;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void execute(Game game, String argument) {

    }
}