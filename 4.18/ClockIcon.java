import javax.swing.*;  //Icon
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.lang.Math.PI;

/**
 * An Icon that shows the current time (hour, minute, second) as an analog clock.
 * Style inspired by Seiko "Baby Monster" watch series
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
        // Face
        //---------------------------------------------------------------------------
        Ellipse2D.Double face = new Ellipse2D.Double(
                x + chapterRingThickness,
                y + chapterRingThickness,
                diameter - 2 * chapterRingThickness,
                diameter - 2 * chapterRingThickness);

        g2.setColor(faceColor);
        g2.fill(face);  // g2.fill(outline);

        // todo: Logos and other text on face


        //---------------------------------------------------------------------------
        // Hour indices
        //---------------------------------------------------------------------------
        int abc; //anti boarder crossing - keeps shapes from protruding past edge of circle
        abc = 5 * diameter / 500;
        final int cnt = diameter / 2; // center

        //12:00 index --------------------------------------------------

        //Black border
        int[] borderXpoints = {cnt - 49 * diameter / 500 , cnt - 49  * diameter / 500 , cnt - 1   * diameter / 500  , cnt + 1   * diameter / 500, cnt + 49  * diameter / 500, cnt + 49    * diameter / 500};
        int[] borderYpoints = {abc + 0  * diameter / 500 , abc + 74 * diameter / 500 , abc + 104 * diameter / 500  , abc + 104 * diameter / 500, abc + 74 * diameter / 500, abc + 0    * diameter / 500};
        Polygon twelve = new Polygon(
                borderXpoints
                , borderYpoints
                , borderXpoints.length
        );
        g2.setColor(chapterRingBGColor);
        g2.fill(twelve);

        //White fill - first half
        int[] twelveLeftFillXpoints = {cnt - 42 * diameter / 500 , cnt - 4 * diameter / 500 , cnt - 4  * diameter / 500, cnt - 42 * diameter / 500};
        int[] twelveLeftFillYpoints = {abc + 4  * diameter / 500 , abc + 0 * diameter / 500 , abc + 94 * diameter / 500, abc + 70 * diameter / 500};
        Polygon twelveFillLeft = new Polygon(
                twelveLeftFillXpoints
                , twelveLeftFillYpoints
                , twelveLeftFillXpoints.length
        );
        g2.setColor(Color.WHITE);
        g2.fill(twelveFillLeft);

        //White fill - second half
        int[] twelveRightFillXpoints = {cnt + 42 * diameter / 500 ,  cnt + 4 * diameter / 500  , cnt + 4  * diameter / 500  , cnt + 42 * diameter / 500 };
        int[] twelveRightFillYpoints = {abc + 4  * diameter / 500 ,  abc + 0 * diameter / 500  , abc + 94 * diameter / 500  , abc + 70 * diameter / 500 };
        Polygon twelveRightFill = new Polygon(
                twelveRightFillXpoints
                , twelveRightFillYpoints
                , twelveRightFillXpoints.length
        );
        g2.setColor(Color.WHITE);
        g2.fill(twelveRightFill);

        // Hourly indices --------------------------------------------------
        abc = 1 * diameter / 500;  //Smaller abc works for normal hour markers
        // Rotate so 12:00 is up - This was added to fix chapter ring being drawn starting at 9:00 (bad idea)
        g2.rotate((PI * 2) * 4 / 12, cnt, cnt);
        //Normal indices
        double hourMarkerWidth = diameter * 96 / 500;
        double hourMarkerHeight = diameter * 50 / 500;
        double hourMarkerFillWidth = diameter * 82 / 500;
        double hourMarkerFillHeight = diameter * 36 / 500;

        for (int hour = 2; hour <= 12; hour++){
            //Black border
            Rectangle2D.Double hourMarker = new Rectangle2D.Double(
                    x + abc,  // Shift slightly so it doesn't pop out past the edge of the ring
                    y + cnt - hourMarkerHeight / 2,
                    hourMarkerWidth - abc / 2,  // Take away some width to compensate for shift
                    hourMarkerHeight);

            g2.setColor(Color.BLACK);
            g2.fill(hourMarker);
            //g2.rotate(rotateBy);
            //---------------------------------------------------------------------------
            //White fill  //todo: this isn't scaling nicely to 250px diameter
            Rectangle2D.Double hourMarkerFill = new Rectangle2D.Double(
                    x + diameter * (hourMarkerWidth - hourMarkerFillWidth) * .5 / 500 + abc,  //center within black border
                    y + cnt - hourMarkerFillHeight / 2,
                    hourMarkerFillWidth,
                    hourMarkerFillHeight);
            g2.setColor(Color.WHITE);
            g2.fill(hourMarkerFill);
            g2.rotate(PI / 6, cnt, cnt);
        }

        //---------------------------------------------------------------------------
        // 5 minute indices
        //---------------------------------------------------------------------------
        for (double mark = 0; mark < 12 * 5; mark++){
            //Don't draw over hour markers - It would be visible if drawn over the 12:00 marker
            if (mark % 5 != 0) {
                //White mark
                double minuteMarkerFillWidth = diameter * 20 / 500;
                double minuteMarkerFillHeight = diameter * 4 / 500;
                Rectangle2D.Double hourMarkerFill = new Rectangle2D.Double(
                        x + diameter * (hourMarkerWidth - hourMarkerFillWidth) * .5 / 500 + abc,
                        y + cnt - minuteMarkerFillHeight / 2,
                        minuteMarkerFillWidth,
                        minuteMarkerFillHeight);
                g2.setColor(Color.WHITE);
                g2.fill(hourMarkerFill);
            }
            //Rotate whether mark is drawn or not
            g2.rotate(PI / 6 / 5, cnt, cnt);
        }

        //---------------------------------------------------------------------------
        // Center
        //---------------------------------------------------------------------------
        int centerDiameter = diameter * 50/500;
        Ellipse2D.Double center = new Ellipse2D.Double(
                x + cnt - centerDiameter / 2,
                y + cnt - centerDiameter / 2,
                centerDiameter,
                centerDiameter
        );
        g2.setColor(chapterRingBGColor);
        g2.fill(center);

        //---------------------------------------------------------------------------
        // Get current time
        //---------------------------------------------------------------------------
        GregorianCalendar gc = new GregorianCalendar();
        g2.rotate(-1 * (PI * 2) * 3 / 12, cnt, cnt);  //Undo the rotation we did earlier to work around how we drew hour indices
        AffineTransform tdc = g2.getTransform();   // Also keep track of which way is up


        //---------------------------------------------------------------------------
        // Heartbeat (tick-tock) calculations
        //---------------------------------------------------------------------------
        boolean heartbeat;
        int value = gc.get(Calendar.MILLISECOND);
        int resolution = 1000 / 6;
        int rounded = Math.round(value / resolution) * resolution;
        float nearestSixth = (float) rounded / 1000;
        //System.out.println(value + " " + resolution + " " + rounded + " " + nearestSixth);
        // todo: This still looks weird, but it's going to be too much work to animate "betweens"

        //---------------------------------------------------------------------------
        // Hour hand
        //---------------------------------------------------------------------------
        // Rotate everything that will be drawn after this point.  Don't bother calculating seconds towards hour hand rotation...
        g2.rotate((gc.get(Calendar.HOUR) + (float)gc.get(Calendar.MINUTE) / 60) * PI / 6, cnt, cnt);

        //Black border
        int[] hourBorderXpoints = {cnt + centerDiameter / 2   * diameter / 500 , cnt + 12  * diameter / 500 , cnt + 42 * diameter / 500 , cnt + 0 * diameter / 500 ,   cnt + -42 * diameter / 500 , cnt - 12 * diameter / 500 , cnt - centerDiameter / 2};
        int[] hourBorderYpoints = {cnt + 0  * diameter / 500 , cnt - 46  * diameter / 500 , cnt - 30 * diameter / 500 , cnt - 136 * diameter / 500 , cnt - 30 * diameter / 500 ,  cnt - 46 * diameter / 500 , cnt + 0 * diameter / 500 };
        Polygon hourHand = new Polygon(
                hourBorderXpoints
                , hourBorderYpoints
                , hourBorderXpoints.length
        );
        g2.setColor(chapterRingBGColor);
        g2.fill(hourHand);

        //White fill - first half
        int[] hourFillXpoints = {cnt + 3  * diameter / 500  , cnt + 30 * diameter / 500  , cnt + 3 * diameter / 500 };
        int[] hourFillYpoints = {cnt - 56 * diameter / 500  , cnt - 42 * diameter / 500  , cnt - 116 * diameter / 500 };
        Polygon hourHandFillTop = new Polygon(
                hourFillXpoints
                , hourFillYpoints
                , hourFillXpoints.length
        );
        g2.setColor(Color.WHITE);
        g2.fill(hourHandFillTop);

        //White fill - second half
        int[] hourFillXpointsFlipped = {cnt - 3 * diameter / 500  , cnt - 30 * diameter / 500  , cnt - 3 * diameter / 500 };
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
        heartbeat = true;     // The minute hand "ticks" (moves) every 1/6 second
        float partialSecond = heartbeat ? nearestSixth : ((float)value / 1000);

        // Rotate everything that will be drawn after this point.
        g2.setTransform(tdc);  //start at top dead center
        g2.rotate((gc.get(Calendar.MINUTE) + ((float)gc.get(Calendar.SECOND) + partialSecond) / 60) * PI / 30, cnt, cnt);

        //Black border: second line = first line reverse order (excluding tip)
        int[] minuteBorderXpoints = {cnt + 20 * diameter / 500   , cnt + 6 * diameter / 500   , cnt + 6 * diameter / 500 ,  cnt + 12 * diameter / 500 ,  cnt + 6 * diameter / 500 ,    cnt + 0 * diameter / 500 ,
                cnt - 6 * diameter / 500 , cnt - 12 * diameter / 500 , cnt - 6 * diameter / 500 , cnt - 6 * diameter / 500 , cnt - 20 * diameter / 500
        };
        int[] minuteBorderYpoints = {cnt + 0 * diameter / 500 , cnt - 24 * diameter / 500  , cnt - 42 * diameter / 500 , cnt - 42 * diameter / 500 ,  cnt - 202 * diameter / 500 ,  cnt - 212 * diameter / 500 ,
                cnt - 202 * diameter / 500 , cnt - 42 * diameter / 500 , cnt - 42 * diameter / 500 , cnt - 24 * diameter / 500 , cnt + 0 * diameter / 500
        };
        Polygon minuteHand = new Polygon(
                minuteBorderXpoints
                , minuteBorderYpoints
                , minuteBorderXpoints.length
        );
        g2.setColor(chapterRingBGColor);
        g2.fill(minuteHand);

        //White fill 8/46, 3/200, 0/208
        int[] minuteFillXpoints = {cnt + 8  * diameter / 500  , cnt + 3   * diameter / 500  , cnt + 0   * diameter / 500 , cnt - 3   * diameter / 500 , cnt - 8  * diameter / 500 };
        int[] minuteFillYpoints = {cnt - 46 * diameter / 500  , cnt - 200 * diameter / 500  , cnt - 208 * diameter / 500 , cnt - 200 * diameter / 500 , cnt - 46 * diameter / 500 };
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
        heartbeat = true;     // The second hand "ticks" (moves) every 1/6 second
        partialSecond = heartbeat ? nearestSixth : ((float)value / 1000);


        // Rotate everything that will be drawn after this point.
        g2.setTransform(tdc);  //start at top dead center
        g2.rotate(((float)gc.get(Calendar.SECOND) + partialSecond) * PI / 30, cnt, cnt);

        //Black border: second line = first line reverse order (excluding tip)
        int[] secondBorderXpoints = {cnt + 0   * diameter / 500 , cnt + 16  * diameter / 500 , cnt + 0   * diameter / 500  , cnt + 2   * diameter / 500  , cnt + 2   * diameter / 500  , cnt + 6   * diameter / 500  , cnt + 1   * diameter / 500 , cnt + 1   * diameter / 500  ,
                                     cnt - 1   * diameter / 500 , cnt - 1   * diameter / 500 , cnt - 6   * diameter / 500  , cnt - 2   * diameter / 500  , cnt - 2   * diameter / 500  , cnt - 0   * diameter / 500  , cnt - 16  * diameter / 500 , cnt - 0   * diameter / 500 };
        int[] secondBorderYpoints = {cnt + 54  * diameter / 500 , cnt + 54  * diameter / 500 , cnt + 0   * diameter / 500  , cnt + 0   * diameter / 500  , cnt - 118 * diameter / 500  , cnt - 118 * diameter / 500  , cnt - 158 * diameter / 500 , cnt - 224 * diameter / 500  ,
                                     cnt - 224 * diameter / 500 , cnt - 158 * diameter / 500 , cnt - 118 * diameter / 500  , cnt - 118 * diameter / 500  , cnt + 0   * diameter / 500  , cnt + 0   * diameter / 500  , cnt + 54  * diameter / 500 , cnt + 54  * diameter / 500 };
        Polygon secondHand = new Polygon(
                secondBorderXpoints
                , secondBorderYpoints
                , secondBorderXpoints.length
        );
        g2.setColor(chapterRingBGColor);
        g2.fill(secondHand);

        //White fill 8/46, 3/200, 0/208
        int[] secondFillXpoints = {cnt + 0   * diameter / 500 , cnt + 4   * diameter / 500 , cnt + 0   * diameter / 500 , cnt - 4   * diameter / 500 , cnt - 0   * diameter / 500 };
        int[] secondFillYpoints = {cnt - 120 * diameter / 500 , cnt - 120 * diameter / 500 , cnt - 152 * diameter / 500 , cnt - 120 * diameter / 500 , cnt - 120 * diameter / 500 };
        Polygon secondHandFill = new Polygon(
                secondFillXpoints
                , secondFillYpoints
                , secondFillXpoints.length
        );
        g2.setColor(Color.WHITE);
        g2.fill(secondHandFill);


        //---------------------------------------------------------------------------
        // Center donut screw thing
        //---------------------------------------------------------------------------
        int screwDiameter = diameter * 10/500;
        Ellipse2D.Double screw = new Ellipse2D.Double(
                x + cnt - screwDiameter / 2,
                y + cnt - screwDiameter / 2,
                screwDiameter,
                screwDiameter
        );
        g2.setColor(new Color(0xD0, 0xD0, 0xD0));
        g2.fill(screw);

        int screwCenterDiameter = diameter * 6/500;
        Ellipse2D.Double screwHole = new Ellipse2D.Double(
                x + cnt - screwCenterDiameter / 2,
                y + cnt - screwCenterDiameter / 2,
                screwCenterDiameter,
                screwCenterDiameter
        );
        g2.setColor(Color.BLACK);
        g2.fill(screwHole);

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