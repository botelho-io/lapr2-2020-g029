package lapr.controller;

import lapr.model.Freelancer;

import java.util.Map;

/**
 * Class that is responsible for send emails.
 * @author André Botelho and Ricardo Moreira.
 */
public class SendEmailsController {
    Map<Freelancer, String> msgs;

    /**
     * Gets the messages to be sent.
     */
    public void getMessage() {
        msgs = SendEmailTask.getMessages();
    }

    /**
     * Sends emails with messages.
     */
    public void sendMessages() {
        SendEmailTask.sendMessages(msgs);
    }
}
