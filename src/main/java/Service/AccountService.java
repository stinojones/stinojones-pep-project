package Service;

import Model.Account;


import DAO.AccountDAO;

public class AccountService { 
    private AccountDAO accountDAO;

    // make instance constructor of private DAO
    public AccountService(){
        accountDAO = new AccountDAO();
    }


    public Account registerAccount(Account account){
        Account existing = accountDAO.getAccountByUsername(account.getUsername());
        
        if(existing == null && !account.getUsername().isBlank() && account.getPassword().length() >= 4) {
            return accountDAO.registerAccount(account);
        } else {return null;}
    }


    public Account login(Account account){

        if(accountDAO.login(account) != null){
            return accountDAO.login(account);
        } else {
            return null;
        }

        


    }
}
