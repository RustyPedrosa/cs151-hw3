import javax.swing.*;
import java.awt.*;

public class SizeableSlider {
    public SizeableSlider() {
            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.add(new JSlider());

            JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
            bottomPanel.add(new JSlider());
            bottomPanel.add(new JPanel());

    }
}
