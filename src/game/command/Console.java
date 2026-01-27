package game.command;

import game.Game;

import java.util.HashMap;
import java.util.Map;

public class Console {

    public String[] parse(String input) {
        if (input == null || input.isBlank()) {
            return new String[]{"", ""};
        }

        String[] parts = input.trim().split("\\s+", 2);

        String command = parts[0].toLowerCase();
        String argument = (parts.length > 1) ? parts[1] : "";

        return new String[]{command, argument};
    }

        private Map<String, Command> commands = new HashMap<>();


        public void register(Command command) {
            commands.put(command.getName(), command);
        }

        public void process(Game game, String input) {
            Console parser = new Console();
            String[] parsed = parser.parse(input);

            Command command = commands.get(parsed[0]);

            if (command == null) {
                System.out.println("unknown command! try to type 'help'");
                return;
            }

            command.execute(game, parsed[1]);
        }

        public void printHelp() {
            for (Command c : commands.values()) {
                System.out.println(c.getName() + " – " + c.getDescription());
            }
        }}
}
