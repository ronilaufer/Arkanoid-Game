package Geometry;

/**
 * Represents a point in a 2D Cartesian coordinate system.
 */
public class Point {
    private double x;
    private double y;

    /**
     * A small threshold value used for comparing double values.
     */

    static final double COMPARISON_THRESHOLD = 0.01;
    /**
     * Constructs a Point with the specified x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    // constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Computes the distance between this point and another point.
     *
     * @param other the other point to calculate the distance from
     * @return the distance between this point and the given point
     */
    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double powX = Math.pow(this.x - other.x, 2);
        double powY = Math.pow(this.y - other.y, 2);
        double distance = Math.sqrt(powX + powY);
        return distance;
    }

    /**
     * Compares two double values for equality with a small threshold.
     *
     * @param a the first double value
     * @param b the second double value
     * @return true if the absolute difference is smaller than the threshold, false otherwise
     */
    public boolean doubleEquals(double a, double b) {
        return  Math.abs(a - b) < Point.COMPARISON_THRESHOLD;
    }

    /**
     * Compares this point with another point for equality.
     *
     * @param other the other point to compare
     * @return true if both points have the same x and y coordinates within a small threshold, false otherwise
     */
    // equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        if (other == null) {  // NullPointerExcept
            return false;
        }
        if (doubleEquals(this.x, other.x) && doubleEquals(this.y, other.y)) {
            return true;
        }
        return false;
    }



    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate
     */
    // Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate
     */
    public double getY() {
        return this.y;
    }
    /**
     * Sets the x-coordinate of the object.
     * This method updates the x-coordinate of the object to the specified value.
     * It is typically used to modify the position of the object in a 2D space.
     * @param x The new x-coordinate to set. This value will replace the current x-coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }
}
