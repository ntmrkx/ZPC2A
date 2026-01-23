package game.command;

import game.Game;
import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {

    private final Map<String, Command> commands = new HashMap<>();

    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    public void process(Game game, String input) {
        CommandParser parser = new CommandParser();
        String[] parsed = parser.parse(input);

        Command command = commands.get(parsed[0]);

        if (command == null) {
            System.out.println("Neznámý příkaz. Napiš 'help'.");
            return;
        }

        command.execute(game, parsed[1]);
    }

    public void printHelp() {
        for (Command c : commands.values()) {
            System.out.println(c.getName() + " – " + c.getDescription());
        }
    }
}
