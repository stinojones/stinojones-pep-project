package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.Account;
import Model.Message;
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
        app.post("/messages", this:: newMessageHandler);
        app.get("/messages", this:: getAllMessagesHandler);
        app.get("/messages/{message_id}", this:: getMessageByIdHandler);
        app.delete("/messages/{message_id}", this:: deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this:: updateMessageByIdHandler);



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


    private void newMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Message message = om.readValue(ctx.body(), Message.class);
        Message newMessage = messageService.newMessage(message);
        if(newMessage != null) {
            ctx.json(om.writeValueAsString(newMessage));
        } else {
            ctx.status(400);
        }
    }

    private void getAllMessagesHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        ArrayList<Message> messages = messageService.getAllMessages();

        ctx.json(om.writeValueAsString(messages));
    }


    private void getMessageByIdHandler(Context ctx) throws JsonProcessingException {
        // paramater way of getting info rather than om
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = messageService.getMessageById(messageId);

        if (message != null) {
            ctx.json(message);
        } else {
            // empty body
            ctx.result(""); 
            }
    }

    private void deleteMessageByIdHandler(Context ctx) throws JsonProcessingException {
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = messageService.deleteMessageById(messageId);

        if(message != null) {
            ctx.json(message);
        } else {
            ctx.result("");
        } 
        
    }


    private void updateMessageByIdHandler(Context ctx) throws JsonProcessingException{
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));

        ObjectMapper om = new ObjectMapper();
        Message message = om.readValue(ctx.body(), Message.class);
        String messageTextUpdate = message.getMessage_text();

        Message updatedMessage = messageService.updateMessageById(messageId, messageTextUpdate);

        if(updatedMessage != null){
            ctx.json(updatedMessage);
        } else {
            ctx.status(400);
        }
      

    }

}
