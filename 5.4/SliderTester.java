// Original source: http://horstmann.com/oodp2/solutions/Ch4/Ex21/AnimationTester.java
// Assignment only requires redrawing of plane after leaving frame

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * 5.4: SliderTester.java and all required classes to run the program.
 *
 * Implement a program that contains a slider and a car icon. The size of the car
 * should increase or decrease as the slider is moved.
 */

/**
 * This program implements an animation that moves
 * a plane shape.
 */
public class SliderTester
{
    /**
     Construct a frame and animate a plane in it.
     */
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();

        final MoveableShape shape
                = new CarShape(0, 50, CAR_WIDTH);

        final ShapeIcon icon = new ShapeIcon(shape,
                ICON_WIDTH, ICON_HEIGHT);

        final JLabel label = new JLabel(icon);

        //See https://docs.oracle.com/javase/tutorial/uiswing/components/slider.html
        JSlider carSize = new JSlider(JSlider.HORIZONTAL, 0, 400, 100);
        carSize.setMajorTickSpacing(100);
        carSize.setMinorTickSpacing(10);
        carSize.setPaintTicks(true);
        carSize.setPaintLabels(true);
        //carSize.addChangeListener();

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(label);
        frame.add(carSize);

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