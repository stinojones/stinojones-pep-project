package Service;

import Model.Account;

import java.util.ArrayList;

import DAO.AccountDAO;

public class AccountService {
    // private DAO
    private AccountDAO accountDAO;
    
    
    
    


    // make instance constructor of private DAO
    public AccountService(){
        accountDAO = new AccountDAO();
    }



    public Account registerAccount(Account account){
        if(!account.getUsername().isBlank() && account.getPassword().length() >= 4) {
            return accountDAO.registerAccount(account);
        } else return null;

        


        

        
    }





    
}
