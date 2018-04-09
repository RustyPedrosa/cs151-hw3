//Original source: http://horstmann.com/oodp2/solutions/solutions.html

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

import static java.lang.Math.floor;

/**
  A class that implements an Observer object that displays a barchart view of
  a data model.
*/
public class BarFrame extends JFrame implements ChangeListener
{
    /**
      Constructs a BarFrame object
      @param dataModel the data that is displayed in the barchart
    */
    public BarFrame(DataModel dataModel)
    {
        this.dataModel = dataModel;
        a = dataModel.getData();

        setLocation(0,200);
        setLayout(new BorderLayout());

        Icon barIcon = new Icon()
        {
            public int getIconWidth() { return ICON_WIDTH; }
            public int getIconHeight() { return ICON_HEIGHT; }
            public void paintIcon(Component c, Graphics g, int x, int y)
            {
                Graphics2D g2 = (Graphics2D) g;

                g2.setColor(Color.red);

                max = a.get(0);
                for (Double v : a)
                {
                    double val = v;
                    if (val > max)
                    max = val;
                }

                double barHeight = getIconHeight() / a.size();

                int i = 0;
                for (Double v : a)
                {
                   double value = v;

                   double barLength = getIconWidth() * value / max;

                   Rectangle2D.Double rectangle = new Rectangle2D.Double
                      (0, barHeight * i, barLength, barHeight);
                   i++;
                   g2.fill(rectangle);
                }
            }
        };

        add(new JLabel(barIcon));

        MouseListeners listeners = new MouseListeners();
        this.addMouseListener(listeners);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
      Called when the data in the model is changed.
      @param e the event representing the change
    */
    public void stateChanged(ChangeEvent e)
    {
        a = dataModel.getData();
        repaint();
    }


    private ArrayList<Double> a;
    private DataModel dataModel;

    private static final int ICON_WIDTH = 200;
    private static final int ICON_HEIGHT = 200;
    private double max;

    private class MouseListeners extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e)
        {
            super.mouseClicked(e);
            System.out.println("Click: " + e.getPoint());

            // For some reason, on my computer, the top left corner of the frame is at 8,31)
            int clickX = e.getX() - 8;
            int clickY = e.getY() - 31;

            // Figure out which row was clicked
            int barClicked = (int)floor((double) clickY / ICON_HEIGHT * a.size());

            // Adjust the bar value and table value
            double newValue = (double)clickX / ICON_WIDTH * max;

            System.out.println(barClicked + " " + newValue);

            dataModel.update(barClicked, newValue);

        }
    }

}
