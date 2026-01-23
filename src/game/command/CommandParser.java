package game.command;

public class CommandParser {

    public String[] parse(String input) {
        if (input == null || input.isBlank()) {
            return new String[] {"", ""};
        }

        String[] parts = input.trim().split("\\s+", 2);

        String command = parts[0].toLowerCase();
        String argument = (parts.length > 1) ? parts[1] : "";

        return new String[] { command, argument };
    }
}
