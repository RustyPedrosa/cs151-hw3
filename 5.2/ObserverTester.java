//Original source: http://horstmann.com/oodp2/solutions/solutions.html

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/*
 * 5.2 Problem Statement:
 * Improve Exercise 1 by making the graph view editable. Attach a mouse listener to
 * the panel that paints the graph. When the user clicks on a point, move the nearest
 * data point to the mouse click. Then update the model and ensure that both the
 * number view and the graph view are notified of the change so that they can
 * refresh their contents. Hint: Look up the API documentation for the MouseListener
 * interface type. In your listener, you need to take action in the mousePressed
 * method. Implement the remaining methods of the interface type to do nothing.
 */
/**
   A class for testing an implementation of the Observer pattern.
*/
public class ObserverTester
{
   /**
      Creates a DataModel and attaches barchart and textfield listeners
      @param args unused
   */
   public static void main(String[] args)
   {
      ArrayList<Double> data = new ArrayList<Double>();

      data.add(33.0);
      data.add(44.0);
      data.add(22.0);
      data.add(22.0);

      DataModel model = new DataModel(data);

      TextFrame frame = new TextFrame(model);

      BarFrame barFrame = new BarFrame(model);

      model.attach(barFrame);
      model.attach(frame);
   }
}
