package Sprite;

import Collision.Collidable;
import Collision.Velocity;
import Game.Game;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import Game.HitNotifier;
import Game.HitListener;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * The {@code Block} class represents a rectangular block that can be both drawn on the screen
 * and participate in collisions. It implements both the {@link Collidable} and {@link Sprite}
 * interfaces.
 *
 * <p>
 * A block has a position (upper-left corner), dimensions (width and height), and a color.
 * When hit by a ball, it updates the ball's velocity based on the side of the collision.
 * </p>
 */
public class Block extends Rectangle implements Collidable, Sprite, HitNotifier {
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructs a new {@code Block} with the specified position, dimensions, and color.
     *
     * @param upperLeft the upper-left point of the block
     * @param width     the width of the block
     * @param height    the height of the block
     * @param color     the color of the block
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        super(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Returns the rectangle that defines the block's boundaries for collision detection.
     *
     * @return the collision rectangle of the block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this;
    }

    /**
     * Notifies the block that it has been hit at a specific point with a specific velocity.
     * Based on the collision point, this method calculates the new velocity of the object
     * after the hit, by reversing the direction appropriately.
     *
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the velocity of the object before the collision
     * @return the new velocity after the collision
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        boolean hitTopOrBottom = false;
        boolean hitLeftOrRight = false;
        double deathY = 790;

        if (collisionPoint.doubleEquals(collisionPoint.getY(), getUpperLeft().getY())
                || collisionPoint.doubleEquals(collisionPoint.getY(), getUpperLeft().getY() + getHeight())) {
            hitTopOrBottom = true;
        }
        if (collisionPoint.doubleEquals(collisionPoint.getX(), getUpperLeft().getX())
                || collisionPoint.doubleEquals(collisionPoint.getX(), getUpperLeft().getX() + getWidth())) {
            hitLeftOrRight = true;
        }

        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        if (hitTopOrBottom) {
            dy = -dy;
        }
        if (hitLeftOrRight) {
            dx = -dx;
        }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }

        return new Velocity(dx, dy);
    }

    /**
     * Draws the block on the given {@link DrawSurface}.
     * The block is filled with its color and outlined in black.
     *
     * @param surface the surface on which the block should be drawn
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(), (int) getWidth(),
                (int) getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(), (int) getWidth(),
                (int) getHeight());
    }

    /**
     * Called once per frame. For a block, this is usually empty since it does not move.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Adds the block to the specified game. It will be both drawn on the screen
     * and included in collision detection.
     *
     * @param g the game to which this block should be added
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * Checks whether the block's color matches the ball's color.
     *
     * @param ball the ball to compare colors with
     * @return {@code true} if the colors match, {@code false} otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }
    /**
     * Removes the block from the specified game, unregistering it as both a {@link Sprite} and a {@link Collidable}.
     *
     * @param game the game from which to remove this block
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * Adds a hit listener to this block.
     * The listener will be notified when the block is hit.
     *
     * @param hl the hit listener to add
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * Removes a hit listener from this block.
     *
     * @param hl the hit listener to remove
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * Returns the color of the block.
     *
     * @return the color of the block
     */
    public Color getColor() {
        return color;
    }
}

