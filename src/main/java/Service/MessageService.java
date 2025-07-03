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


    // new message
    public Message newMessage(Message message) {
        if(!message.getMessage_text().isEmpty() && message.getMessage_text().length() < 255){
        return messageDAO.newMessage(message);
        } else {
        return null;
        }
    }

    // get all messages
    public ArrayList<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    // get message by it's id
    public Message getMessageById(int message_id){
        return messageDAO.getMessageById(message_id);
    }

    // delete message by it's id
    public Message deleteMessageById(int message_id){

        Message messageToDelete = messageDAO.getMessageById(message_id);

        if(messageToDelete != null){
            messageDAO.deleteMessageById(message_id);
        }

        return messageToDelete; 
        
    }

    // update message by it's id
    public Message updateMessageById(int message_id, String message_text){
        Message messageUpdated = messageDAO.getMessageById(message_id);
        

        if(messageUpdated != null && !message_text.isBlank() && message_text.length() < 255){
            messageDAO.updateMessageById(message_id, message_text);

            messageUpdated = messageDAO.getMessageById(message_id);

            return messageUpdated;
            
        }

        return null;
    }

    // get all messages from an account
    public ArrayList<Message> getAllMessagesFromAnAccount(int account_id){
        return messageDAO.getAllMessageFromAccount(account_id);
    }

}
