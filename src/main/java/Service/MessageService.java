package Service;


import Model.Message;

import java.util.ArrayList;

import DAO.MessageDAO;


public class MessageService {
    private MessageDAO messageDAO;


    // instance construct of private DAO
    public MessageService(){
        messageDAO = new MessageDAO();
    }



    public Message newMessage(Message message) {
        if(!message.getMessage_text().isEmpty() && message.getMessage_text().length() < 255){
        return messageDAO.newMessage(message);
        } else {
        return null;
        }
    }


    public ArrayList<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }


    // public Message getMessageById(){
    //     return messageDAO.getMessageById(message.getMessageById);
    // }
}
