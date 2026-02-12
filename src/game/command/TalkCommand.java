/**
 * Command that allows  player to speak with NPCs.
 *
 * @author Myroslav Tsykunov
 */

package game.command;

import game.Game;
import game.model.NPC;

public class TalkCommand implements Command {

    public String name() { return "talk"; }

    public String help() { return "Talk to NPC: talk <name>"; }

    @Override
    public String execute(Game game, String argument) {

        if (argument == null || argument.isBlank()) {
            return "Usage: talk <npc> [answer]";
        }

        String[] parts = argument.split(" ", 2);
        String npcName = parts[0].toLowerCase();
        String answer = (parts.length > 1) ? parts[1].trim() : "";

        NPC npc = game.getCurrentLocation().getNpc(npcName);
        if (npc == null) {
            return "There is no NPC with that name here.";
        }

        if (npcName.equals("luba")) {
            return game.talkToLuba(answer);
        }

        return npc.talk();
    }

}
