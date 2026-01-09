package game.command;

public class Command {
    public String action;
    public String target;

    public Command(String action, String target) {
        this.action = action;
        this.target = target;
    }
}
