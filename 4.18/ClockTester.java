import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/*
 * 4.18 Problem Statement:
 * Write a class ClockIcon that implements the Icon interface type. Draw an
 * analog clock whose hour, minute, and second hands show the current
 * time. To get the current hours and minutes, construct an object of type
 * GregorianCalendar with the default constructor.
 */

/**
 * This program displays an animated clock face, styled after a Seiko wrist watch.
 */
public class ClockTester {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        ClockIcon ci = new ClockIcon(500);
        JLabel clockLabel = new JLabel(ci);  //Why does an icon need to be in a label?

        frame.setLayout(new FlowLayout());
        frame.add(clockLabel);

        // Let's go all out and animate the clock
        ActionListener l = event -> clockLabel.repaint();

        // Update every 10 ms so we can show off second hand tick/heartbeat of 1/6 second like the real watch
        Timer t = new Timer(10, l);
        t.start();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
