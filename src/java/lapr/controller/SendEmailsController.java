package lapr.controller;

import lapr.model.Freelancer;

import java.util.Map;

public class SendEmailsController {
    Map<Freelancer, String> msgs;
    public void getMessage() {
        msgs = SendEmailTask.getMessages();
    }

    public void sendMessages() {
        SendEmailTask.sendMessages(msgs);
    }
}
