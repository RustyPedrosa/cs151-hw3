// Original source: http://horstmann.com/oodp2/solutions/Ch4/Ex21/MoveableShape.java

import java.awt.*;

/**
 A shape that can be moved around.
 */
public interface MoveableShape
{
    /**
     Draws the shape.
     @param g2 the graphics context
     */
    void draw(Graphics2D g2);
    /**
     Moves the shape by a given amount.
     @param dx the amount to translate in x-direction
     @param dy the amount to translate in y-direction
     */
    void translate(double dx, double dy);

    //Added accessors for this assignment
    /**
     * Get left of the bounding rectangle
     * @return the left of the bounding rectangle
     */
    int getX();

    /**
     * Get top of the bounding rectangle
     * @return the top of the bounding rectangle
     */
    int getY();

    /**
     * Get width of the bounding rectangle
     * @return width of the bounding rectangle
     */
    int getWidth();

    /**
     * Set width of the bounding rectangle
     */
    void setWidth(int width);

    void setY(int y);
}