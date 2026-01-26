package game;

public class Main {
    public static void main(String[] args) {
        World world = World.loadGameDataFromResources("/world.json");
        Game game = new Game(world);
        game.run();
    }
}
