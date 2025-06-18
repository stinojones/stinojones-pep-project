package DAO;

import java.sql.*;
import Util.ConnectionUtil;
import Model.Account;

import java.util.ArrayList;
import java.util.List;


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


    // Get all Accounts

    // public List<Account> getAllAccounts() {
    //     Connection connection = ConnectionUtil.getConnection();
    //     List<Account> accounts = new ArrayList<>();

    //     try{
    //         String sql = "SELECT * FROM Account";
    //         PreparedStatement preparedStatement = connection.prepareStatement(sql);
    //         ResultSet rs = preparedStatement.getResultSet();

    //         while(rs.next()){
    //             Account account = new Account(rs.getInt("account_id"), 
    //             rs.getString("username"), 
    //             rs.getString("password"));

    //             accounts.add(account);
    //         }
    //     } catch (Exception e){
    //         System.out.println(e.getStackTrace());
    //     }
    //     return accounts;
    // }

   
















    // Login




    // New Message





    // Retrieve All Messages






    // Retrieve Message By ID






    // Delete Message By ID




    // Update Message By Id




    // Retrieve All Messages By Particular User






    
}
