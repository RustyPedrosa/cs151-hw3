import java.util.ArrayList;

/**
 * Chat system
 */
public class ChatBox {
    private ArrayList<String> messages;

    public void addMessage(String message){
        messages.add(message);
    }

    public ArrayList<String> getMessages() {
        return messages;
    }
}
