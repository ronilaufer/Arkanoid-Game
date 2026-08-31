package Game;

import Collision.Collidable;
import Collision.CollisionInfo;
import Geometry.Line;
import Geometry.Point;

import java.util.ArrayList;
import java.util.List;
/**
 * The {@code GameEnvironment} class holds a collection of objects that can be collided with.
 * It is used to check possible collisions of a moving object with any of the collidables in the game.
 */
public class GameEnvironment {
    private List<Collidable> collidableList = new ArrayList<Collidable>();
    /**
     * Returns the list of collidable objects in the environment.
     *
     * @return a list of {@link Collidable} objects currently in the game environment
     */
    public List<Collidable> getCollidables() {
        return collidableList;
    }

    // add the given collidable to the environment.
    /**
     * Adds a new collidable object to the environment.
     *
     * @param c the {@link Collidable} object to add
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    /**
     * Determines the closest collision that is expected to occur along the specified trajectory.
     *
     * <p>
     * If no collisions are detected, this method returns {@code null}. Otherwise, it returns a
     * {@link CollisionInfo} object containing the closest collision point and the relevant collidable.
     * </p>
     *
     * @param trajectory the {@link Line} representing the object's movement path
     * @return a {@link CollisionInfo} object with details about the closest collision,
     * or {@code null} if no collision is detected
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closePoint = null;
        Collidable closeCollidable = null;
        Point point;

        for (Collidable c : collidableList) {
            point = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());

            if (point != null) {
                if (closePoint == null) {
                    closePoint = point;
                    closeCollidable = c;
                } else if (trajectory.start().distance(point) < trajectory.start().distance(closePoint)) {
                    closePoint = point;
                    closeCollidable = c;
                }
            }
        }
        if (closePoint == null) {
            return null;
        }
        return new CollisionInfo(closePoint, closeCollidable);
    }
    /**
     * Removes the specified collidable object from the list of collidables.
     *
     * @param c the {@link Collidable} to be removed
     */
    public void deleteCollidable(Collidable c) {
        collidableList.remove(c);
    }
}
