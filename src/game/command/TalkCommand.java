package game.command;

import game.Game;
import game.command.Command;
import game.model.NPC;

public class TalkCommand implements Command {

    public String name() { return "talk"; }

    public String help() { return "Talk to NPC: talk <name>"; }

    @Override
    public void execute(Game game, String arg) {
        if (arg.isBlank()) {
            System.out.println("Using: talk <name>");
            return;
        }

        NPC npc = game.getCurrentLocation().getNpc(arg.toLowerCase());
        if (npc == null) {
            System.out.println("There is no NPC with that name.");
            return;
        }

        System.out.println(npc.talk());
    }
}
