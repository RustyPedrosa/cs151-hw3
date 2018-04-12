import java.util.ArrayList;

public class ChatBoxController {
    private ChatBox model;
    private ChatBoxView view;

    public ChatBoxController(ChatBox model, ChatBoxView view){
        this.model = model;
        this.view = view;
    }

    public void updateMessages(){
        ChatBoxView.updateMessages(model.getMessages());
    }
    

    public void addMessage(String message){
        model.addMessage(message);
    }

    public ArrayList<String> getMessages(){
        return model.getMessages();
    }
}
