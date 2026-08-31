package Game;

import Sprite.Ball;
import Sprite.Block;
/**
 * The {@code ScoreTrackingListener} class is responsible for updating the player's score
 * whenever a {@link Block} is hit by a {@link Ball}.
 * This class implements the {@link HitListener} interface and increases the score
 * by a fixed amount (5 points) on each hit event.
 */
public class ScoreTrackingListener extends Counter implements HitListener {
    private Counter currentScore;
    /**
     * Constructs a {@code ScoreTrackingListener} with a given score counter.
     *
     * @param scoreCounter the {@link Counter} that keeps track of the current score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * This method is called whenever a block is hit.
     * It increases the score by 5 points.
     *
     * @param beingHit the {@link Block} that was hit
     * @param hitter   the {@link Ball} that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}