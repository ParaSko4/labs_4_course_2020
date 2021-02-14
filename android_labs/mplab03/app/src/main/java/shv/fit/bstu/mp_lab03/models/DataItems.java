package shv.fit.bstu.mp_lab03.models;

import java.util.ArrayList;

public class DataItems {
    public ArrayList<MessageModel> messages;

    public ArrayList<MessageModel> getList() {
        return messages;
    }
    public void setList(ArrayList<MessageModel> messages) {
        this.messages = messages;
    }
}
