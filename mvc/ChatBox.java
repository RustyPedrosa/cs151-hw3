import java.util.ArrayList;
import java.util.Observable;

/**
 * Chat system Model
 * Stores message data.  Observable
 */
public class ChatBox extends Observable{
    private ArrayList<String> messages;

    /**
     * Chatbox model for storing chat messages
     */
    public ChatBox(){
        messages = new ArrayList<>();
    }

    /**
     * Adds a message to the stored message log, used by Controller
     * to update this Model in response to a
     * @param message message to add to storage
     */
    public void addMessage(String message){
        messages.add(message);
        setChanged();
        notifyObservers();
    }

    /**
     * Returns message list.  Used by Controller to pass messages to View.
     * @return arraylist of all messages
     */
    public ArrayList<String> getMessages() {
        return messages;
    }
}
