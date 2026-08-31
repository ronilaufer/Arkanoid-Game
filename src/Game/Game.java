package Game;

import Collision.Collidable;
import Geometry.Point;
import Sprite.Ball;
import Sprite.Block;
import Sprite.Sprite;
import Sprite.SpriteCollection;
import Sprite.Paddle;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Game} class manages the main structure and flow of the game.
 * It creates and handles game objects like blocks, balls, and paddles,
 * as well as the game loop that updates and draws these objects.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private ScoreTrackingListener scoreTrackingListener;

    /**
     * Constructs a new {@code Game}, initializing the sprite and environment
     * collections and creating a GUI window.
     */
    public Game() {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        gui = new GUI("the best game", 800, 600);
        remainingBlocks = new Counter();
        remainingBalls = new Counter();
        score = new Counter();
        scoreTrackingListener = new ScoreTrackingListener(score);
    }

    /**
     * Adds a collidable object to the game's environment.
     *
     * @param c the {@link Collidable} to add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the game's sprite collection.
     *
     * @param s the {@link Sprite} to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.

    /**
     * Initializes a new game:
     * <ul>
     *     <li>Creates the borders as blocks</li>
     *     <li>Creates a pyramid of colored blocks</li>
     *     <li>Adds two balls with initial velocities</li>
     *     <li>Adds a controllable paddle</li>
     * </ul>
     * All objects are added to the appropriate game collections.
     */
    public void initialize() {
        List<Block> blocks = new ArrayList<>();

        Block deathBlock = new Block(new Point(0, 600), 800, 1, Color.MAGENTA);
        blocks.add(deathBlock);
        blocks.add(new Block(new Point(0, 20), 800, 10, Color.MAGENTA));
        blocks.add(new Block(new Point(0, 20), 10, 600, Color.MAGENTA));
        blocks.add(new Block(new Point(790, 20), 10, 600, Color.MAGENTA));

        int blockWidth = 50;
        int blockHeight = 30;
        int startX = 800 - 10 - blockWidth;
        int startY = 100;
        int rows = 6;
        Color[] rowColors = {
                Color.GRAY,
                Color.RED,
                Color.YELLOW,
                Color.BLUE,
                Color.PINK,
                Color.GREEN
        };

//        for (int i=0; i<100; i++){
//            Ball b = new Ball(100, 250, 5, Color.PINK);
//            b.setVelocity(i+4, 5-i);
//            b.setEnvironment(environment);
//            b.addToGame(this);
//        }
//        remainingBalls.increase(100);

        Ball ball = new Ball(100, 100, 5, Color.PINK);
        ball.setVelocity(1, 3);
        ball.setEnvironment(environment);
        ball.addToGame(this);
        Ball ball2 = new Ball(200, 300, 5, Color.PINK);
        ball2.setVelocity(2, -4);
        ball2.setEnvironment(environment);
        ball2.addToGame(this);
        Ball ball3 = new Ball(170, 250, 5, Color.PINK);
        ball3.setVelocity(-3, 5);
        ball3.setEnvironment(environment);
        ball3.addToGame(this);
        remainingBalls.increase(3);

        //PrintingHitListener listener = new PrintingHitListener();
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);

        deathBlock.addHitListener(ballRemover);

        for (int row = 0; row < rows; row++) {
            Color color = rowColors[row];
            int blocksInRow = 12 - row; // Each row gets shorter
            for (int col = 0; col < blocksInRow; col++) {
                int x = startX - col * blockWidth;
                int y = startY + row * blockHeight;
                Block block = new Block(new Point(x, y), blockWidth, blockHeight, color);
                block.addToGame(this);
                remainingBlocks.increase(1);
                //block.addHitListener(listener);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
            }
            //edges
            for (Block b : blocks) {
                b.addToGame(this);
            }
        }

        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Paddle paddle = new Paddle(keyboard, new Point(500, 585), 100, 15, 5);
        paddle.addToGame(this);

        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this);


    }

    /**
     * Runs the game by starting the main animation loop.
     * The loop:
     * <ul>
     *     <li>Draws all sprites</li>
     *     <li>Notifies them that time has passed (for movement)</li>
     *     <li>Maintains a consistent frame rate</li>
     * </ul>
     */
    // Run the game -- start the animation loop.
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (remainingBlocks.getValue() > 0 && remainingBalls.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (remainingBlocks.getValue() == 0) {
            score.increase(100);
            System.out.print("You Win!\nYour score is: " + score.getValue());
            gui.close();
        } else {
            System.out.print("Game Over.\nYour score is: " + score.getValue());
            gui.close();
        }
    }
    /**
     * Removes the specified collidable object from the game environment.
     *
     * @param c the {@link Collidable} to be removed
     */
    public void removeCollidable(Collidable c) {
        environment.deleteCollidable(c);
    }
    /**
     * Removes the specified sprite from the game’s sprite collection.
     *
     * @param s the {@link Sprite} to be removed
     */
    public void removeSprite(Sprite s) {
        sprites.deleteSprite(s);
    }
}

