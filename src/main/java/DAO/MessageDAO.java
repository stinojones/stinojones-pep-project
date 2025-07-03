package DAO;

import java.sql.*;
import java.util.ArrayList;

import Util.ConnectionUtil;
import Model.Message;

public class MessageDAO {
    
    // New Message
    public Message newMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO Message (posted_by, message_text, time_posted_epoch) VALUES(?, ?, ?)";
        
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                int generated_message_id = (int) rs.getLong(1);
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
            
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        return null;
    }



    // Retrieve All Messages
    public ArrayList<Message> getAllMessages(){
        ArrayList<Message> messages = new ArrayList<>();

        try {
            Connection connection = ConnectionUtil.getConnection();

            String sql = "SELECT * FROM Message";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                int message_id = rs.getInt("message_id");
                int posted_by = rs.getInt("posted_by");
                String message_text = rs.getString("message_text");
                long time_posted_epoch = rs.getLong("time_posted_epoch");
                
                Message message = new Message(message_id, posted_by, message_text, time_posted_epoch);

                messages.add(message);
            }
            
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        return messages;
    }



    // Retrieve Message by it's Id

    public Message getMessageById(int message_id){

        try {
            Connection connection = ConnectionUtil.getConnection();

            String sql = "SELECT * FROM Message WHERE message_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message_id);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                int posted_by = rs.getInt("posted_by");
                String message_text = rs.getString("message_text");
                long time_posted_epoch = rs.getLong("time_posted_epoch");
                
                return new Message(message_id, posted_by, message_text, time_posted_epoch);

            }
            
            
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        return null;
    }

    // Delete Message by it's Id

      public void deleteMessageById(int message_id){

      try {
            Connection connection = ConnectionUtil.getConnection();

            String sql = "DELETE FROM Message WHERE message_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message_id);
            // execute update for updates and deletions || execute query for queries
            preparedStatement.executeUpdate();

            
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }



    // Update Message by it's Id

    public String updateMessageById(int message_id, String message_text){

        try {
            Connection connection = ConnectionUtil.getConnection();
            String sql = "UPDATE Message SET message_text = ? WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, message_text);
            preparedStatement.setInt(2, message_id);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        return null;
        
    }

    // Get all messages of an account

    public ArrayList<Message> getAllMessageFromAccount(int account_id){
        ArrayList<Message> messages = new ArrayList<>();

        try {
            Connection connection = ConnectionUtil.getConnection();
            String sql = "SELECT * FROM Message WHERE posted_by = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account_id);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                int message_id = rs.getInt("message_id");
                int posted_by = rs.getInt("posted_by");
                String message_text = rs.getString("message_text");
                long time_posted_epoch = rs.getLong("time_posted_epoch");
                
                Message message = new Message(message_id, posted_by, message_text, time_posted_epoch);

                messages.add(message);
            }

            
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        return messages;
    }
}
