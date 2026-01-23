package game;

public class Main {
    public static void main(String[] args) {
        GameData gameData = GameData.loadGameDataFromResources("/world.json");
        Game game = new Game();
        game.run();
    }
}
