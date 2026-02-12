package game;

/**
 * Entry point of the application.
 * This class is responsible only for starting the game.
 * It creates a new instance of the Game class and
 * starts the main game loop.
 * The Main class does not contain any game logic.
 *
 * @author Myroslav Tsykunov
 */
public class Main {

    /** Main method that launches the game */
    public static void main(String[] args) {
        new Game().run();
    }
}
