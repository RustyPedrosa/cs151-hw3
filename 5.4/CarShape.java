// Original source: http://horstmann.com/oodp2/solutions/Ch4/Ex21/CarShape.java

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

/**
 A car that can be moved around.
 */
public class CarShape implements MoveableShape //Observer //DEPRECATED
{
    /**
     Constructs a car item.
     @param x the left of the bounding rectangle
     @param y the top of the bounding rectangle
     @param width the width of the bounding rectangle
     */
    public CarShape(int x, int y, int width)
    {
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public void translate(double dx, double dy)
    {
        x += dx;
        y += dy;
    }

    public void draw(Graphics2D g2)
    {
        Rectangle2D.Double body
                = new Rectangle2D.Double(x, y + width / 6,
                width - 1, width / 6);
        Ellipse2D.Double frontTire
                = new Ellipse2D.Double(x + width / 6, y + width / 3,
                width / 6, width / 6);
        Ellipse2D.Double rearTire
                = new Ellipse2D.Double(x + width * 2 / 3, y + width / 3,
                width / 6, width / 6);

        // the bottom of the front windshield
        Point2D.Double r1
                = new Point2D.Double(x + width / 6, y + width / 6);
        // the front of the roof
        Point2D.Double r2
                = new Point2D.Double(x + width / 3, y);
        // the rear of the roof
        Point2D.Double r3
                = new Point2D.Double(x + width * 2 / 3, y);
        // the bottom of the rear windshield
        Point2D.Double r4
                = new Point2D.Double(x + width * 5 / 6, y + width / 6);
        Line2D.Double frontWindshield
                = new Line2D.Double(r1, r2);
        Line2D.Double roofTop
                = new Line2D.Double(r2, r3);
        Line2D.Double rearWindshield
                = new Line2D.Double(r3, r4);

        g2.draw(body);
        g2.draw(frontTire);
        g2.draw(rearTire);
        g2.draw(frontWindshield);
        g2.draw(roofTop);
        g2.draw(rearWindshield);
    }

    //Added accessors for this assignment
    /**
     * Get left of the bounding rectangle
     * @return the left of the bounding rectangle
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Get top of the bounding rectangle
     * @return the top of the bounding rectangle
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Get width of the bounding rectangle
     * @return width of the bounding rectangle
     */
    @Override
    public int getWidth() {
        return width;
    }

    private int x;
    private int y;
    private int width;

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an {@code Observable} object's
     * {@code notifyObservers} method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers}
     */
    @Override
    public void setWidth(int width) {
        this.width = width;
        //
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}