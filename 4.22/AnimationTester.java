// Original source: http://horstmann.com/oodp2/solutions/Ch4/Ex21/AnimationTester.java
// Assignment only requires redrawing of plane after leaving frame

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * 4.22 Problem statement:
 * Modify the animation program to make the moving shape reappear on the
 * left-hand side after it disappears from the frame.
 */

/**
 * This program implements an animation that moves
 * a plane shape.
 */
public class AnimationTester
{
    /**
     Construct a frame and animate a plane in it.
     */
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();

        final MoveableShape shape
                = new PlaneShape(0, 0, CAR_WIDTH);

        final ShapeIcon icon = new ShapeIcon(shape,
                ICON_WIDTH, ICON_HEIGHT);

        final JLabel label = new JLabel(icon);
        frame.setLayout(new FlowLayout());
        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        final int DELAY = 10;  // NOTE: Changed from 100ms to 10ms
        // milliseconds between timer ticks
        Timer t = new Timer(DELAY, new
                ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        // New code for assignment: If the left of the shape has gone past the edge of the frame
                        if (shape.getX() > frame.getWidth()){
                            //Move the shape left by frame width + shape width
                            shape.translate(-1 * (frame.getWidth() + shape.getWidth()), 0);
                        }
                        else {
                            //original code:
                            shape.translate(1, 0);
                        }
                        label.repaint();
                    }
                });
        t.start();
    }

    private static final int ICON_WIDTH = 400;
    private static final int ICON_HEIGHT = 100;
    private static final int CAR_WIDTH = 100;
}