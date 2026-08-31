package Game;

import Sprite.Ball;
import Sprite.Block;
/**
 * The {@code BallRemover} class implements {@link HitListener} and is responsible for
 * removing balls from the game when they hit certain blocks (e.g., "death" blocks).
 * It also updates the counter that tracks the remaining balls.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;
    /**
     * Constructs a {@code BallRemover} with a reference to the game and the counter of remaining balls.
     *
     * @param game           the game from which balls will be removed
     * @param remainingBalls the counter tracking how many balls remain in the game
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease(1);
        this.game.removeSprite(hitter);
    }
}
