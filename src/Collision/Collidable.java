package Collision;

import Sprite.Ball;
import Geometry.Point;
import Geometry.Rectangle;

/**
 * The {@code Collidable} interface should be implemented by any object
 * that can be collided with (e.g., blocks, walls, paddles).
 *
 * <p>
 * It defines methods for retrieving the collision boundary and
 * responding to collision events by updating the velocity of the object
 * that hit it.
 * </p>
 */
public interface Collidable {
    // Return the "collision shape" of the object.

    /**
     * Returns the "collision shape" of the object.
     * This shape is used to detect collisions with other objects (typically a {@link Ball}).
     *
     * @return the {@link Rectangle} that defines the shape of the object for collision detection
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    /**
     * Notifies the object that a collision occurred at a specific point with a given velocity.
     * This method calculates and returns the new velocity of the ball after the collision,
     * according to the object's response (such as bouncing off a wall or changing direction).
     *
     * @param hitter           the {@link Ball} that hit the object (can be used for additional behavior or tracking)
     * @param collisionPoint   the point at which the collision occurred
     * @param currentVelocity  the velocity of the ball just before the collision
     * @return a new {@link Velocity} representing the expected velocity of the ball after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}

