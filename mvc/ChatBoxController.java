import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * ChatBox Controller
 * handles events from view (Implements action listener for button in View)
 * Also provides getMessages function to pass data from Model to View
 */
public class ChatBoxController{
    private ChatBox model;
    private ChatBoxView view;

    public ChatBoxController(ChatBox model, ChatBoxView view){
        this.model = model;
        this.view = view;

        this.view.setSendButtonListener(new ActionListener() {
            /**
             * Responds to sendButton events by getting the outbound
             * message from the View and adding it to the Model
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = view.getOutgoingMessage();
                model.addMessage(message);
            }
        });
    }

    /**
     * Gets messages from Model for passing to View when there is an update.
     * @return All messages stored in Model
     */
    public ArrayList<String> getMessages(){
        return model.getMessages();
    }
}
