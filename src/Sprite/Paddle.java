package Sprite;

import Collision.Collidable;
import Collision.Velocity;
import Game.Game;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The {@code Paddle} class represents the player's paddle in the game.
 * It can move left and right based on keyboard input and responds to ball collisions.
 * The paddle is both a {@link Sprite} and a {@link Collidable}.
 */
public class Paddle extends Rectangle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private int speed;

    /**
     * Constructs a new {@code Paddle}.
     *
     * @param keyboard  the keyboard sensor to control the paddle
     * @param upperLeft the upper-left position of the paddle
     * @param width     the width of the paddle
     * @param height    the height of the paddle
     * @param speed     the number of pixels the paddle moves per frame
     */
    public Paddle(KeyboardSensor keyboard, Point upperLeft, int width, int height, int speed) {
        super(upperLeft, width, height);
        this.keyboard = keyboard;
        this.speed = speed;
    }

    /**
     * Moves the paddle to the left by its speed.
     * Wraps around to the right edge if it moves off the left side.
     */
    public void moveLeft() {
        Point newUpperLeft = this.getUpperLeft();
        Point newUpperRight = new Point(this.getUpperLeft().getX() + getWidth(), this.getUpperLeft().getY());
        newUpperLeft.setX(getUpperLeft().getX() - this.speed);
        if (newUpperRight.getX() < 0) {
            newUpperLeft.setX(800 - getWidth());
        }
    }

    /**
     * Moves the paddle to the right by its speed.
     * Wraps around to the left edge if it moves off the right side.
     */
    public void moveRight() {
        Point newUpperLeft = this.getUpperLeft();
        newUpperLeft.setX(getUpperLeft().getX() + this.speed);
        if (newUpperLeft.getX() > 800) {
            newUpperLeft.setX(0);
        }
    }

    /**
     * Called once per frame. Checks if left or right keys are pressed and moves the paddle accordingly.
     */
    // Sprite
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given {@link DrawSurface}.
     *
     * @param d the draw surface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.fillRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(), (int) getWidth(), (int) getHeight());
    }

    // Collidable

    /**
     * Returns the collision rectangle representing the paddle.
     *
     * @return the paddle's {@link Rectangle}
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double distance = getWidth() / 5;
        Point p1 = new Point(getUpperLeft().getX() + distance, getUpperLeft().getY());
        Point p2 = new Point(getUpperLeft().getX() + 2 * distance, getUpperLeft().getY());
        Point p3 = new Point(getUpperLeft().getX() + 3 * distance, getUpperLeft().getY());
        Point p4 = new Point(getUpperLeft().getX() + 4 * distance, getUpperLeft().getY());
        Point p5 = new Point(getUpperLeft().getX() + 5 * distance, getUpperLeft().getY());
        Line area1 = new Line(getUpperLeft(), p1);
        Line area2 = new Line(p1, p2);
        Line area3 = new Line(p2, p3);
        Line area4 = new Line(p3, p4);
        Line area5 = new Line(p4, p5);

        Line linePoint = new Line(collisionPoint, collisionPoint);
        Line rightLine = new Line(getUpperLeft().getX() + getWidth(), getUpperLeft().getY(),
                getUpperLeft().getX() + getWidth(), getUpperLeft().getY() + getHeight());
        Line leftLine = new Line(getUpperLeft().getX(), getUpperLeft().getY(), getUpperLeft().getX(),
                getUpperLeft().getY() + getHeight());

        double newAngel = 50;
        if (area1.isPointInVertLine(collisionPoint)) {
            newAngel = 210;
        } else if (area2.isPointInVertLine(collisionPoint)) {
            newAngel = 240;
        } else if (area3.isPointInVertLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        } else if (area4.isPointInVertLine(collisionPoint)) {
            newAngel = 300;
        } else if (area5.isPointInVertLine(collisionPoint)) {
            newAngel = 330;
        } else if (linePoint.isIntersecting(rightLine) || linePoint.isIntersecting(leftLine)) {
            return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
        }
        double speedV = currentVelocity.getSpeed();
        return currentVelocity.fromAngleAndSpeed(newAngel, speedV);
    }

    /**
     * Adds this paddle to the game as both a sprite and a collidable object.
     *
     * @param g the {@link Game} to add the paddle to
     */
    // Add this paddle to the game.
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
