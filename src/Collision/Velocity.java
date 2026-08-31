package Collision;

import Geometry.Point;

/**
 * The {@code Velocity} class represents the change in position along the x and y axes.
 * It is used to move objects such as balls by applying the velocity to a point.
 */
public class Velocity {
    //fields
    private double dx;
    private double dy;

    /**
     * Constructs a new Velocity with the specified changes in x and y.
     *
     * @param dx the change in the x-axis
     * @param dy the change in the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns the change in x (dx).
     *
     * @return the dx value
     */
    public double getDx() {
        return dx;
    }

    /**
     * Returns the change in y (dy).
     *
     * @return the dy value
     */
    public double getDy() {
        return dy;
    }

    /**
     * Applies this velocity to a given point and returns a new point
     * that is the result of moving the original point by (dx, dy).
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p the original point
     * @return a new point moved according to this velocity
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Creates a velocity based on a given angle and speed.
     * The angle is assumed to be in degrees, measured clockwise from the positive x-axis.
     *
     * @param angle the angle of movement in degrees
     * @param speed the speed (magnitude of the velocity vector)
     * @return a new Velocity object with corresponding dx and dy
     */
//    public static Velocity fromAngleAndSpeed(double angle, double speed) {
//        double dx = Math.cos(angle) * speed;
//        double dy = Math.sin(angle) * speed;
//        return new Velocity(dx, dy);
//    }
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle));
        double dy = speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
    /**
     * Calculates and returns the speed based on the velocity components.
     * The speed is computed using the Euclidean norm (magnitude) of the
     * velocity vector defined by {@code dx} and {@code dy}.
     *
     * @return the scalar speed value
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }
}
