package game.command;

import game.Game;
import java.util.ArrayList;
import java.util.List;

public class Console {

    private  List<Command> commands = new ArrayList<>();

    public void register(Command command) {
        commands.add(command);
    }

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

    public String getHelpText() {
        StringBuilder sb = new StringBuilder();
        for (Command c : commands) {
            sb.append(c.name()).append(" - ").append(c.help()).append("\n");
        }
        return sb.toString();
    }



}
