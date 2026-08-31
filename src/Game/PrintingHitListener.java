package Game;

import Sprite.Ball;
import Sprite.Block;

/**
 * The {@code PrintingHitListener} class implements the {@link HitListener} interface.
 * It is a simple listener used primarily for debugging or demonstration purposes.
 * When a {@link Block} is hit, this listener can be configured to print a message
 * to the console (currently the method body is empty).
 */
public class PrintingHitListener implements HitListener {
    /**
     * This method is called whenever the specified {@code Block} is hit by a {@code Ball}.
     * In this implementation, the method does nothing, but it can be modified
     * to print a message or log the collision event for debugging.
     *
     * @param beingHit the {@link Block} that was hit
     * @param hitter   the {@link Ball} that caused the hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
    }
}

