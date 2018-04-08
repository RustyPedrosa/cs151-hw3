import javax.swing.*;  //Icon
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.lang.Math.PI;

/**
 * 4.18 Problem Statement:
 * Write a class ClockIcon that implements the Icon interface type. Draw an
 * analog clock whose hour, minute, and second hands show the current
 * time. To get the current hours and minutes, construct an object of type
 * GregorianCalendar with the default constructor.
 */

/**
 * An Icon that shows the current time (hour, minute, second) as an analog clock.
 *
 * Style inspired by Seiko "Baby Monster" watch
 * Measurements taken at 500px diameter
 *
 * Chapter ring:
 * 40 thickness (black)
 * 6x28 5-minute marks (white)
 * 50x100 hour marks (black)
 * 36x86 hour marks (white)
 *
 *
 */
public class ClockIcon implements Icon {
    private int diameter; //multiple of 250 recommended

    //Orange Monster colors
    private Color chapterRingBGColor = Color.BLACK;
    private Color faceColor = new Color(0xE8, 0x68, 0x10);

    //Snow Monster colors

    //10th anniversary green monster colors

    /**
     * Creates a new ClockIcon*
     * @param diameter Outer diameter of clock
     */
    public ClockIcon(int diameter) {
        this.diameter = diameter;

    }
    /**
     * Draw the icon at the specified location.  Icon implementations
     * may use the Component argument to get properties useful for
     * painting, e.g. the foreground or background color.
     *
     * @param c a {@code Component} to get properties useful for painting
     * @param g the graphics context
     * @param x the X coordinate of the icon's top-left corner
     * @param y the Y coordinate of the icon's top-left corner
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        //---------------------------------------------------------------------------
        // Black Chapter Ring background
        //---------------------------------------------------------------------------
        double chapterRingThickness = diameter * 30 / 500;
        Ellipse2D.Double chapterRingBG = new Ellipse2D.Double(x, y, diameter, diameter);
        g2.setColor(chapterRingBGColor);
        g2.fill(chapterRingBG);

        //---------------------------------------------------------------------------
        // Orange Face
        //---------------------------------------------------------------------------
        Ellipse2D.Double face = new Ellipse2D.Double(
                x + chapterRingThickness,
                y + chapterRingThickness,
                diameter - 2 * chapterRingThickness,
                diameter - 2 * chapterRingThickness);

        g2.setColor(faceColor);
        g2.fill(face);  // g2.fill(outline);


        //---------------------------------------------------------------------------
        // Hour indices
        //---------------------------------------------------------------------------
        int antiBorderCrossing = 1 * diameter / 500;

        // 1st rectangle was designed at 9:00, so 12:00 is the 4th rectangle
        // Sadly, don't have extra time to do special 12:00 marker
        double hourMarkerWidth = diameter * 96 / 500;
        double hourMarkerHeight = diameter * 50 / 500;
        double hourMarkerFillWidth = diameter * 82 / 500;
        double hourMarkerFillHeight = diameter * 36 / 500;
        for (int hour = 1; hour <= 12; hour++){
            //Black border
            Rectangle2D.Double hourMarker = new Rectangle2D.Double(
                    x + antiBorderCrossing,  // Shift slightly so it doesn't pop out past the edge of the ring
                    y + diameter / 2 - hourMarkerHeight / 2,
                    hourMarkerWidth - antiBorderCrossing / 2,  // Take away some width to compensate for shift
                    hourMarkerHeight);

            g2.setColor(Color.BLACK);
            g2.fill(hourMarker);
            //g2.rotate(rotateBy);
            //---------------------------------------------------------------------------
            //White fill
            Rectangle2D.Double hourMarkerFill = new Rectangle2D.Double(
                    x + diameter * (hourMarkerWidth - hourMarkerFillWidth)*.5 / 500 + antiBorderCrossing,  //center within black border
                    y + diameter / 2 - hourMarkerFillHeight / 2,
                    hourMarkerFillWidth,
                    hourMarkerFillHeight);
            g2.setColor(Color.WHITE);
            g2.fill(hourMarkerFill);
            g2.rotate(PI / 6, diameter / 2, diameter / 2);
        }

        //---------------------------------------------------------------------------
        // 5 minute indices
        //---------------------------------------------------------------------------
        for (double mark = 0; mark < 12 * 5; mark++){
            //Don't draw over hour markers - it shouldn't be visible but just in case...
            if (mark % 5 != 0) {
                //White mark
                double minuteMarkerFillWidth = diameter * 20 / 500;
                double minuteMarkerFillHeight = diameter * 4 / 500;
                Rectangle2D.Double hourMarkerFill = new Rectangle2D.Double(
                        x + diameter * (hourMarkerWidth - hourMarkerFillWidth) * .5 / 500 + antiBorderCrossing,
                        y + diameter / 2 - minuteMarkerFillHeight / 2,
                        minuteMarkerFillWidth,
                        minuteMarkerFillHeight);
                g2.setColor(Color.WHITE);
                g2.fill(hourMarkerFill);
            }
            //Rotate whether mark is drawn or not
            g2.rotate(PI / 6 / 5, diameter / 2, diameter / 2);
        }

        //---------------------------------------------------------------------------
        // Center
        //---------------------------------------------------------------------------
        int centerDiameter = diameter * 50/500;
        Ellipse2D.Double center = new Ellipse2D.Double(
                x + diameter / 2 - centerDiameter / 2,
                y + diameter / 2 - centerDiameter / 2,
                centerDiameter,
                centerDiameter
        );
        g2.setColor(chapterRingBGColor);
        g2.fill(center);

        //---------------------------------------------------------------------------
        // Get current time
        //---------------------------------------------------------------------------
        GregorianCalendar gc = new GregorianCalendar();
        AffineTransform tdc = g2.getTransform();   // Also keep track of which way is up - todo: CHANGE THIS IF 12:00 GETS NEW SYMBOL

        //---------------------------------------------------------------------------
        // Hour hand
        //---------------------------------------------------------------------------
        // Rotate everything that will be drawn after this point.  Don't bother calculating seconds towards hour hand rotation...
        g2.rotate((gc.get(Calendar.HOUR) + (float)gc.get(Calendar.MINUTE) / 60) * PI / 6, diameter / 2, diameter / 2);

        //Black border
        int[] hourBorderXpoints = {diameter / 2 + centerDiameter / 2  , diameter / 2 + 12 , diameter / 2 + 42, diameter / 2 + 0,   diameter / 2 + -42, diameter / 2 - 12, diameter / 2 - centerDiameter / 2};
        int[] hourBorderYpoints = {diameter / 2                       , diameter / 2 - 46 , diameter / 2 - 30, diameter / 2 - 136, diameter / 2 - 30,  diameter / 2 - 46, diameter / 2 + 0};
        Polygon hourHand = new Polygon(
                hourBorderXpoints
                , hourBorderYpoints
                , hourBorderXpoints.length
        );
        g2.setColor(chapterRingBGColor);
        g2.fill(hourHand);

        //White fill - first half
        int[] hourFillXpoints = {diameter / 2 + 3 , diameter / 2 + 30 , diameter / 2 + 3};
        int[] hourFillYpoints = {diameter / 2 - 56 , diameter / 2 - 42 , diameter / 2 - 116};
        Polygon hourHandFillTop = new Polygon(
                hourFillXpoints
                , hourFillYpoints
                , hourFillXpoints.length
        );
        g2.setColor(Color.WHITE);
        g2.fill(hourHandFillTop);

        //White fill - second half
        int[] hourFillXpointsFlipped = {diameter / 2 - 3 , diameter / 2 - 30 , diameter / 2 - 3};
        Polygon hourHandFillBottom = new Polygon(
                hourFillXpointsFlipped
                , hourFillYpoints
                , hourFillXpointsFlipped.length
        );
        g2.setColor(Color.WHITE);
        g2.fill(hourHandFillBottom);


        //---------------------------------------------------------------------------
        // Minute hand
        //---------------------------------------------------------------------------
        g2.setTransform(tdc);
        // Rotate everything that will be drawn after this point.
        gc.set(Calendar.MINUTE, 45);
        gc.set(Calendar.SECOND, 0);
        g2.rotate((gc.get(Calendar.MINUTE) + (float)gc.get(Calendar.SECOND) / 60) * PI / 30, diameter / 2, diameter / 2);

        //Black border - second line = first line reverse order (excluding tip)
        int[] minuteBorderXpoints = {diameter / 2 + 20  , diameter / 2 + 6  , diameter / 2 + 6,  diameter / 2 + 12,  diameter / 2 + 6,    diameter / 2 + 0,
                diameter / 2 - 6, diameter / 2 - 12, diameter / 2 - 6, diameter / 2 - 6, diameter / 2 - 20
        };
        int[] minuteBorderYpoints = {diameter / 2       , diameter / 2 - 24 , diameter / 2 - 42, diameter / 2 - 42,  diameter / 2 - 202,  diameter / 2 - 212,
                diameter / 2 - 202, diameter / 2 - 42, diameter / 2 - 42, diameter / 2 - 24, diameter / 2
        };
        Polygon minuteHand = new Polygon(
                minuteBorderXpoints
                , minuteBorderYpoints
                , minuteBorderXpoints.length
        );
        g2.setColor(chapterRingBGColor);
        g2.fill(minuteHand);

        //White fill 8/46, 3/200, 0/208
        int[] minuteFillXpoints = {diameter / 2 + 8 ,  diameter / 2 + 3   , diameter / 2      , diameter / 2 - 3 , diameter / 2 - 8};
        int[] minuteFillYpoints = {diameter / 2 - 46 , diameter / 2 - 200 , diameter / 2 - 208, diameter / 2 - 200 ,diameter / 2 - 46};
        Polygon minuteHandFill = new Polygon(
                minuteFillXpoints
                , minuteFillYpoints
                , minuteFillXpoints.length
        );
        g2.setColor(Color.WHITE);
        g2.fill(minuteHandFill);

        //---------------------------------------------------------------------------
        // Second hand
        //---------------------------------------------------------------------------
        
    }

    /**
     * Returns the icon's width.
     *
     * @return an int specifying the fixed width of the icon.
     */
    @Override
    public int getIconWidth() {
        return diameter;
    }

    /**
     * Returns the icon's height.
     *
     * @return an int specifying the fixed height of the icon.
     */
    @Override
    public int getIconHeight() {
        return diameter;
    }
}