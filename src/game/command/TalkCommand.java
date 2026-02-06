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
    public String execute(Game game, String arg) {
        if (arg == null || arg.isBlank()) return "Using: talk <name>";

        NPC npc = game.getCurrentLocation().getNpc(arg.toLowerCase());
        if (npc == null) return "There is no NPC with that name.";

        return npc.talk();
    }
}
