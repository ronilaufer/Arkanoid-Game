package Sprite;

import Collision.Collidable;
import Collision.CollisionInfo;
import Collision.Velocity;
import Game.Game;
import Game.GameEnvironment;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The {@code Ball} class represents a ball that can move and bounce off surfaces
 * in a 2D space. It implements the {@link Sprite} interface, allowing it to be
 * drawn and updated over time.
 *
 * <p>
 * The ball's movement and collision behavior is determined by a {@link Velocity}
 * and the {@link GameEnvironment}, which holds the objects it may collide with.
 * </p>
 */
public class Ball implements Sprite {
    //fields
    private Point center;
    private int r;
    private Color color;
    private Velocity v;
    private Line[] lines;
    private GameEnvironment environment;

    /**
     * Constructs a new Ball with the given center, radius, and color.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Constructs a new Ball with given x, y coordinates for the center,
     * radius, and color.
     *
     * @param x     x-coordinate of the center
     * @param y     y-coordinate of the center
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * @return the x-coordinate of the ball's center as an integer.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * @return the y-coordinate of the ball's center as an integer.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * @return the radius of the ball.
     */
    public int getSize() {
        return r;
    }

    /**
     * @return the color of the ball.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Draws the ball on a given DrawSurface.
     *
     * @param surface the surface on which to draw the ball.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), r);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the velocity to set.
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Sets the velocity of the ball using dx and dy values.
     *
     * @param dx the change in x per move
     * @param dy the change in y per move
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * @return the velocity of the ball. If not set, returns (0,0).
     */
    public Velocity getVelocity() {
        if (v == null) {
            return new Velocity(0, 0);
        }
        return v;
    }

    /**
     * Sets the lines that define the area in which the ball is allowed to bounce.
     *
     * @param lines the array of boundary lines
     */
    public void setLines(Line[] lines) {
        this.lines = lines;
    }

    /**
     * @return the array of lines representing the movement boundaries.
     */
    public Line[] getLines() {
        return lines;
    }

    /**
     * Moves the ball one step according to its velocity.
     * If a collision is detected, the ball's position and velocity are updated accordingly.
     * Also handles edge cases where the ball may be stuck inside an object.
     */
    public void moveOneStep() {
        Point endPoint = this.v.applyToPoint(this.center);
        Line trajectory = new Line(this.center, endPoint);
        CollisionInfo collision = environment.getClosestCollision(trajectory);
        if (collision == null) {
            this.center = endPoint;
        } else {
            Point collisionPoint = collision.collisionPoint();
            Collidable collidableObject = collision.collisionObject();

            double epsilon = 0.01;
            double dx = this.getVelocity().getDx();
            double dy = this.getVelocity().getDy();

            this.center = new Point(collisionPoint.getX() - dx * epsilon, collisionPoint.getY() - dy * epsilon);

            this.v = collidableObject.hit(this, collisionPoint, this.v);

            for (Collidable c : environment.getCollidables()) {
                Rectangle rect = c.getCollisionRectangle();
                if (isInsideRectangle(this.center, rect)) {

                    double newY = rect.getUpperLeft().getY() - this.r - 1;
                    this.center = new Point(this.center.getX(), newY);

                    dx = this.v.getDx();
                    dy = -Math.abs(this.v.getDy());
                    if (Math.abs(dy) < 0.1) {
                        dy = -Math.max(1, this.v.getSpeed());
                    }
                    this.v = new Velocity(dx, dy);
                    break;
                }
            }
        }
    }
    /**
     * Sets the game environment for the ball.
     * The environment provides access to collidable objects for collision detection.
     *
     * @param game the game environment
     */
    public void setEnvironment(GameEnvironment game) {
        this.environment = game;
    }
    /**
     * Called every time unit to update the ball's position.
     * Part of the {@link Sprite} interface.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }
    /**
     * Adds the ball to the specified game as a sprite.
     *
     * @param g the game to add the ball to
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
    /**
     * Returns true if the point is strictly inside the given rectangle.
     *
     * @param p    the point to test
     * @param rect the rectangle to check against
     * @return true if the point is inside the rectangle, false otherwise
     */
    // Returns true if p is strictly inside rect
    private boolean isInsideRectangle(Point p, Rectangle rect) {
        double x = p.getX(), y = p.getY();
        double rx = rect.getUpperLeft().getX();
        double ry = rect.getUpperLeft().getY();
        return x > rx && x < rx + rect.getWidth() && y > ry && y < ry + rect.getHeight();
    }

    /**
     * Sets the color of the object.
     *
     * @param color the new {@link Color} to be assigned to this object
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
