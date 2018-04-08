import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ClockTester {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        ClockIcon ci = new ClockIcon(500);
        JLabel clockLabel = new JLabel(ci);  //Why does an icon need to be in a label?
//
//        final int FIELD_WIDTH = 20;
//        final JTextField textField = new JTextField(FIELD_WIDTH);

        frame.setLayout(new FlowLayout());
        frame.add(clockLabel);
//        frame.add(label);
//        frame.add(textField);
//
//        ActionListener listener = new
//                ActionListener()
//                {
//                    public void actionPerformed(ActionEvent event)
//                    {
//                        label.repaint();
//                        Date now = new Date();
//                        textField.setText(now.toString());
//                    }
//                };
//
//        final int DELAY = 1000;
//        // milliseconds between timer ticks
//        Timer t = new Timer(DELAY, listener);
//        t.start();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
