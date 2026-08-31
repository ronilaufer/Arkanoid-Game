package Geometry;

import java.util.ArrayList;
import java.util.List;
/**
 * The {@code Rectangle} class represents a rectangle defined by an upper-left point,
 * a width, and a height. It provides methods to retrieve rectangle dimensions and
 * to compute intersection points with a given line.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    /**
     * Constructs a new {@code Rectangle} with the specified upper-left corner,
     * width, and height.
     *
     * @param upperLeft the upper-left corner of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    /**
     * Returns a list of intersection points between the rectangle and the specified line.
     * The rectangle is treated as a set of four lines (its edges), and this method checks
     * each of those edges for intersections with the given line.
     * If there are no intersections, the list may be empty or contain {@code null} values
     * where intersections do not occur.
     *
     * @param line the line to check for intersection with the rectangle
     * @return a list of intersection points (possibly empty or containing {@code null}s)
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> points = new ArrayList<Point>();
        Line line1 = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Line line2 = new Line(this.upperLeft.getX() + this.width, this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        Line line3 = new Line(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height,
                this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Line line4 = new Line(this.upperLeft.getX(), this.upperLeft.getY() + this.height,
                this.upperLeft.getX(), this.upperLeft.getY());

        points.add(line.intersectionWith(line1));
        points.add(line.intersectionWith(line2));
        points.add(line.intersectionWith(line3));
        points.add(line.intersectionWith(line4));
        return points;
    }
    /**
     * Returns the width of the rectangle.
     *
     * @return the width
     */
    // Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }
    /**
     * Returns the height of the rectangle.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point
     */
    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}
