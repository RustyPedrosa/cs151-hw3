// Modified from 5.8 Telephone class

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
   Presents a chatbox GUI for a single lonely person.
*/
public class ChatBoxView
{
   /**
      Constructs a chatbox with message log, input field, and send button.
   */
   public ChatBoxView()
   {
      JPanel messageLogPanel = new JPanel();
      messageLogPanel.setLayout(new BorderLayout());
      messageLog = new JTextArea(10, 25);
      JScrollPane messageLogScroller = new JScrollPane(messageLog);

      final JTextArea inputField = new JTextArea(1, 25);
      inputField.setText("Type here!");
      JScrollPane inputFieldScroller = new JScrollPane(inputField);

      JButton sendButton = new JButton("Send message");
//      sendButton.addActionListener(event -> {
//         connect.record(inputField.getText());
//         inputField.setText("");
//      });

      JPanel buttonPanel = new JPanel();
      buttonPanel.add(sendButton);

      final JFrame frame = new JFrame();
      frame.setTitle("IRCQ");
      frame.add(messageLogScroller, BorderLayout.NORTH);
      frame.add(inputFieldScroller, BorderLayout.CENTER);
      frame.add(buttonPanel, BorderLayout.SOUTH);

      // Replace the default close operation with a window event listener.
      frame.addWindowListener(new
         WindowAdapter()
         {
            public void windowClosing(WindowEvent event)
            {
                  System.exit(0);

            }
         });

      frame.pack();
      frame.setVisible(true);
   }

   /**
      Give instructions to the mail system user.
   */
   public void speak(String output)
   {
      messageLog.append(output);
   }

   public void run(){
   }

   private JTextArea messageLog;
   //private Connection connect;
}
