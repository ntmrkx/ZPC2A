package game.command;

import game.Game;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles user input and command execution.
 * The Console class is responsible for:
 * - registering available commands
 * - parsing user input
 * - finding and executing the correct command
 * - printing command results
 *
 * @author Myroslav Tsykunov
 */
public class Console {

    /** List of registered commands */
    private List<Command> commands = new ArrayList<>();

    /**
     * Registers a new command in the console.
     *
     * @param command command to be registered
     */
    public void register(Command command) {
        commands.add(command);
    }

    /**
     * Processes user input, finds matching command and executes it.
     *
     * @param game current game instance
     * @param input raw user input
     */
    public void process(Game game, String input) {
        String[] parsed = parse(input);
        String commandName = parsed[0];

        for (Command command : commands) {
            if (command.name().equals(commandName)) {
                String out = command.execute(game, parsed[1]);
                if (out != null && !out.isBlank()) {
                    System.out.println(out);
                }
                return;
            }
        }

        System.out.println("Unknown command! Type 'help'.");
    }

    /**
     * Parses raw user input into command name and argument.
     *
     * @param input raw input string
     * @return String array where:
     *         index 0 = command name,
     *         index 1 = argument
     */
    private String[] parse(String input) {

        if (input == null) {
            return new String[]{"", ""};
        }

        input = input.trim();

        if (input.isEmpty()) {
            return new String[]{"", ""};
        }

        String[] parts = input.split(" ");

        String command = parts[0].toLowerCase();
        String argument = "";

        if (parts.length > 1) {
            argument = parts[1].toLowerCase();
        }

        return new String[]{command, argument};
    }

    /**
     * Returns formatted help text for all registered commands.
     *
     * @return list of commands and their descriptions
     */
    public String getHelpText() {
        StringBuilder sb = new StringBuilder();
        for (Command c : commands) {
            sb.append(c.name())
                    .append(" - ")
                    .append(c.help())
                    .append("\n");
        }
        return sb.toString();
    }
}
