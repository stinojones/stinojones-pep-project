package DAO;

import java.sql.*;
import Util.ConnectionUtil;
import Model.Account;


public class AccountDAO {

    // Register User
    public Account registerAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO Account (username, password) VALUES(?, ?)";
            // Return Generated keys says that after the update, send back keys that were autoincremented made
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();

            //result set of prepared statement you just set with generatedKeys(the autoincremented id's)
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                // getting account id that was just then autoincremented and made
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return null;
    }


    // Get Account by Username

    public Account getAccountByUsername(String username) {
        String sql = "SELECT * FROM Account WHERE username = ?";
        
        try (Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Account account = new Account(rs.getInt("account_id"),
                rs.getString("username"), 
                rs.getString("password"));
                
                return account;
            }

        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return null;
    }






    // Login

    public Account login(Account account) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "SELECT * FROM Account WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                return new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"), 
                    rs.getString("password"));  
                   } 

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        return null;
        
    }




















    
}
