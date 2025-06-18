package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.Account;
import Service.AccountService;
import Service.MessageService;


/** 
 * The endpoints you will need can be
 * found in readme.md as well as the test cases
 */

 
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();

    }
  
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::registerUserHandler);
        app.post("/login", this::loginHandler);



        //must return app
        return app;
    }


    private void registerUserHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(ctx.body(), Account.class);
        Account registeredAccount = accountService.registerAccount(account);
        if(registeredAccount != null) {
            ctx.json(om.writeValueAsString(registeredAccount));
        } else {
            ctx.status(400);
        }

    }

    private void loginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(ctx.body(), Account.class);
        Account loginSuccessfull = accountService.login(account);
        if(loginSuccessfull != null){
            ctx.json(om.writeValueAsString(loginSuccessfull));
        } else {
            ctx.status(401);
        }
    }
}