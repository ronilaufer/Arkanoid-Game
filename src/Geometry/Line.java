package Geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * The Line class represents a line segment between two points.
 * It provides methods for calculating the line's length, middle point,
 * intersection detection, and more.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Threshold for comparing double values.
     */
    static final double COMPARISON_THRESHOLD = 0.00001;

    /**
     * Constructs a line segment given two points.
     *
     * @param start The starting point of the line.
     * @param end   The ending point of the line.
     */
    // constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a line segment given the coordinates of two points.
     *
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param y2 The y-coordinate of the second point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Checks if two double values are approximately equal.
     *
     * @param a First double value.
     * @param b Second double value.
     * @return True if the values are approximately equal, false otherwise.
     */
    public boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < Line.COMPARISON_THRESHOLD;
    }

    /**
     * Returns the length of the line segment.
     *
     * @return The length of the line.
     */
    // Return the length of the line
    public double length() {
        double len = this.start.distance(this.end);
        return len;
    }

    /**
     * Returns the middle point of the line.
     *
     * @return The midpoint of the line.
     */
    // Returns the middle point of the line
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Returns the start point of the line.
     *
     * @return The starting point.
     */
    // Returns the start point of the line
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return The ending point.
     */
    // Returns the end point of the line
    public Point end() {
        return this.end;
    }

    /**
     * Checks if this line intersects with another line.
     *
     * @param other The other line to check for intersection.
     * @return True if the lines intersect, false otherwise.
     */
    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        if (this.isVertical() && other.isVertical()) { //if the two lines are vertical
            if (!doubleEquals(this.start.getX(), other.start.getX())) { //if they different
                return false;
            }
            double min1 = Math.min(this.start.getY(), this.end.getY());
            double max1 = Math.max(this.start.getY(), this.end.getY());
            double min2 = Math.min(other.start.getY(), other.end.getY());
            double max2 = Math.max(other.start.getY(), other.end.getY());
            if ((max1 < min2 && !doubleEquals(max1, min2)) || (max2 < min1 && !doubleEquals(max2, min1))) {
                return false;
            }
            return true;
        }
        if (this.isVertical()) {
            return other.oneVertical(this);
        }
        if (other.isVertical()) {
            return this.oneVertical(other);
        }

        double m1 = this.getSlope();
        double m2 = other.getSlope();
        double b1 = this.findB();
        double b2 = other.findB();
        if (doubleEquals(m1, m2)) {
            if (!doubleEquals(b1, b2)) {
                return false;
            }
            double min1 = Math.min(this.start.getX(), this.end.getX());
            double max1 = Math.max(this.start.getX(), this.end.getX());
            double min2 = Math.min(other.start.getX(), other.end.getX());
            double max2 = Math.max(other.start.getX(), other.end.getX());
            if ((max1 < min2 && !doubleEquals(max1, min2)) || (max2 < min1 && !doubleEquals(max2, min1))) {
                return false;
            }
            return true;
        }

        double x = (b2 - b1) / (m1 - m2);
        if (this.isInRange(x) && other.isInRange(x)) { //the x is in the range
            return true;
        }
        return false;
    }

    /**
     * Calculates the slope of the line.
     *
     * @return The slope of the line.
     */
    //find slope between 2 points
    public double getSlope() {
        double slope = (start.getY() - end().getY()) / (start().getX() - end().getX());
        return slope;
    }

    /**
     * Finds the y-intercept (b) of the line equation y = mx + b.
     *
     * @return The y-intercept of the line.
     */
    //find the b of function
    public double findB() {
        double b = start().getY() - this.getSlope() * start().getX();
        return b;
    }

    /**
     * Checks if a given x-coordinate is within the range of this line segment.
     *
     * @param x The x-coordinate to check.
     * @return True if the x is within the range, false otherwise.
     */
    //check if x of a point is in a line
    public boolean isInRange(double x) {
        if ((x > Math.min(start.getX(), end.getX()) || doubleEquals(x, Math.min(start.getX(), end.getX())))
                && (x < Math.max(start.getX(), end.getX()) || doubleEquals(x, Math.max(start.getX(), end.getX())))) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the line is vertical (i.e., has an undefined slope).
     *
     * @return True if the line is vertical, false otherwise.
     */
    //check if line is vertical
    public boolean isVertical() {
        if (doubleEquals(start.getX(), end.getX())) {
            return true;
        }
        return false;
    }

    /**
     * Checks if this line intersects with a vertical line.
     *
     * @param vert The vertical line to check.
     * @return true if the vertical line intersects with this line, false otherwise.
     */
    //check 2 lines when 1 of them is vrtical
    public boolean oneVertical(Line vert) {
        if (!this.isInRange(vert.end.getX())) {
            return false;
        }
        double minvert = Math.min(vert.start.getY(), vert.end.getY());
        double maxvert = Math.max(vert.start.getY(), vert.end.getY());

        double m = this.getSlope();
        double b = this.findB();
        double y = m * vert.end.getX() + b;

        if ((minvert < y || doubleEquals(minvert, y)) && (y < maxvert || doubleEquals(y, maxvert))) {
            return true;
        }
        return false;
    }

    /**
     * Checks if this line intersects with both given lines.
     *
     * @param other1 The first line.
     * @param other2 The second line.
     * @return true if this line intersects with both other1 and other2, false otherwise.
     */
    // Returns true if this 2 lines intersect with this line, false otherwise
    public boolean isIntersecting(Line other1, Line other2) {
        if (this.isIntersecting(other1) && this.isIntersecting(other2)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the intersection point between this line and another line.
     *
     * @param other The line to check intersection with.
     * @return The intersection point if the lines intersect, null otherwise.
     */
    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        //if the 2 lines are verticals
        if (this.isVertical() && other.isVertical()) {
            return this.isTouch(other);
        }
        //if one is vert
        if (this.isVertical()) {
            return other.oneVertPoint(this);
        }
        if (other.isVertical()) {
            return this.oneVertPoint(other);
        }
        //if there isnt vertical:
        double m1 = this.getSlope();
        double m2 = other.getSlope();
        //if the sections in the same line
        if (doubleEquals(m1, m2)) {
            return this.isTouch(other);
        }
        double b1 = this.findB();
        double b2 = other.findB();
        double x = (b2 - b1) / (m1 - m2);
        double y = m1 * x + b1;
        return new Point(x, y);
    }

    /**
     * Computes the intersection point when one line is vertical.
     *
     * @param vert The vertical line.
     * @return The intersection point.
     */
    public Point oneVertPoint(Line vert) {
        double m = this.getSlope();
        double b = this.findB();
        double y = m * vert.end.getX() + b;
        Point p = new Point(vert.start.getX(), y);
        return p;
    }

    /**
     * Checks if two lines only touch at a single point without overlapping.
     *
     * @param other The line to check.
     * @return The touching point if they only touch, null otherwise.
     */
    //check if 2 lines only touch each other and not above each other
    public Point isTouch(Line other) {
        if (doubleEquals(this.start.getY(), other.end().getY())) {
            Point p = new Point(this.start.getX(), this.start.getY());
            return p;
        }
        if (doubleEquals(this.end.getY(), other.start().getY())) {
            Point p = new Point(this.end.getX(), this.end.getY());
            return p;
        }
        return null;
    }

    /**
     * Checks if two lines are equal.
     *
     * @param other The line to compare.
     * @return true if the lines are equal, false otherwise.
     */
    // equals -- return true if the lines are equal, false otherwise
    public boolean equals(Line other) {
        if ((this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start))) {
            return true;
        }
        return false;
    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.

    /**
     * Returns the closest intersection point between this line and a given rectangle,
     * measured from the start of the line.
     *
     * <p>If there are no intersection points between the line and the rectangle,
     * this method returns {@code null}.</p>
     *
     * @param rect the {@link Rectangle} to check for intersections
     * @return the closest intersection {@link Point} to the start of the line,
     * or {@code null} if no intersection exists
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = new ArrayList<Point>();
        points = rect.intersectionPoints(this);
        if (points == null) {
            return null;
        }
        Point intersectionPoint = null;
        for (Point p : points) {
            if (p != null) {
                if (intersectionPoint != null) {
                    if (this.start.distance(intersectionPoint) > this.start.distance(p)) {
                        intersectionPoint = p;
                    }
                } else {
                    intersectionPoint = p;
                }
            }
        }
        return intersectionPoint;
    }

    /**
     * Checks whether a given point lies on the vertical projection of this line
     * (i.e., whether the point shares the same Y coordinate as the line's start
     * and falls within the line's X coordinate range).
     *
     * @param p the {@link Point} to check
     * @return {@code true} if the point lies on the horizontal segment at the same Y level;
     * {@code false} otherwise
     */
    public boolean isPointInVertLine(Point p) {
        if (p.getY() == this.start.getY()) {
            double minX = Math.min(this.start.getX(), this.end.getX());
            double maxX = Math.max(this.start.getX(), this.end.getX());
            if (p.getX() <= maxX && p.getX() >= minX) {
                return true;
            }
        }
        return false;
    }
}
