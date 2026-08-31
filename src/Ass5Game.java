/******************
 Name: roni laufer
 ID: 213307770
 Assignment: ass5
 *******************/

import Game.Game;

/**
 * The {@code Ass3Game} class serves as the entry point for the game application.
 *
 * <p>
 * It initializes and runs the game by creating an instance of {@link Game},
 * calling its {@code initialize} method to set up the game environment, and
 * then starting the main game loop with the {@code run} method.
 * </p>
 */
public class Ass5Game {
    /**
     * The main method which starts the game.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}

