// Modified from 5.8 Telephone class

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 Presents a chatbox GUI for a single lonely person.
 */
public class ChatBoxView implements Observer
{
    String message;
    private JTextArea messageLog;
    private JTextArea inputField;
    private JButton sendButton;

    /**
     Constructs a chatbox UI with message log, input field, and send button.
     */
    public ChatBoxView()
    {
        // Message log
        JPanel messageLogPanel = new JPanel();
        messageLogPanel.setLayout(new BorderLayout());
        messageLog = new JTextArea(10, 25);
        JScrollPane messageLogScroller = new JScrollPane(messageLog);

        // Input field
        inputField = new JTextArea(1, 25);
        inputField.setText("Type here!");
        JScrollPane inputFieldScroller = new JScrollPane(inputField);

        // Send button
        sendButton = new JButton("Send message");
        // Action listener will be attached to sendButton by controller

        // Looks nicer if we keep the send button in its own Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sendButton);

        // JFrame to contain UI elements
        final JFrame frame = new JFrame();
        frame.setTitle("IRCQ");  // It's a mashup of IRC and ICQ!
        frame.add(messageLogScroller, BorderLayout.NORTH);
        frame.add(inputFieldScroller, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        // Replace the default close operation with a window event listener.
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent event){
                System.exit(0);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Method for controller to listen for events from sendButton
     * @param actionListener Listener created by Controller
     */
    public void setSendButtonListener(ActionListener actionListener) {
        sendButton.addActionListener(actionListener);
    }

    /**
     * Called by Controller when responding to sendButton clicks.
     * @return Outgoing message to add to message log in Model
     */
    public String getOutgoingMessage() {
        return inputField.getText();
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an {@code Observable} object's
     * {@code notifyObservers} method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers}
     */
    @Override
    public void update(Observable o, Object arg) {
        ChatBox model = (ChatBox) o;

        messageLog.setText("");
        // ideally this should be some kind of system to only append new messages
        for (String message : model.getMessages())
            messageLog.append(message + "\n");

        // If there were multiple ChatBoxes open, it would be important to only clear
        // the sender's inputField.
        inputField.setText("");
    }
}
