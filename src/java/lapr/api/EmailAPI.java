package lapr.api;

/**
 * The API used to send emails.
 */
public interface EmailAPI {
    /**
     * Send an email.
     * @param address Address that will receive the email.
     * @param message Message of the body of the email.
     * @return True if the email was sent, false otherwise.
     */
    boolean sendEmail(String address, String message);
}
