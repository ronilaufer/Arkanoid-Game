package Collision;

import Geometry.Point;

/**
 * The {@code CollisionInfo} class holds information about a collision event
 * between a moving object (like a ball) and a {@link Collidable} object.
 *
 * <p>
 * It stores both the point at which the collision occurs and the object that
 * was hit.
 * </p>
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidable;
    /**
     * Constructs a new {@code CollisionInfo} object with the given collision point
     * and the collidable object involved in the collision.
     *
     * @param collisionPoint the point at which the collision occurs
     * @param collidable     the object that is collided with
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collidable = collidable;
    }
    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision {@link Point}
     */
    // the point at which the collision occurs.
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the {@link Collidable} object that was hit
     */
    // the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.collidable;
    }
}
