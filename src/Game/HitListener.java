package Game;

import Sprite.Ball;
import Sprite.Block;

/**
 * The {@code HitListener} interface should be implemented by any object that wants
 * to be notified when a {@link Block} is hit by a {@link Ball}.
 * This is part of the observer pattern used to handle events in the game,
 * such as updating scores, removing blocks, or playing sounds when collisions occur.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.
    /**
     * This method is called whenever the {@code beingHit} block is hit.
     * Implementing classes should define what actions to take when a hit occurs,
     * such as updating a counter, changing the game state, or notifying the player.
     *
     * @param beingHit the {@link Block} that was hit
     * @param hitter   the {@link Ball} that caused the hit
     */
    void hitEvent(Block beingHit, Ball hitter);
}
