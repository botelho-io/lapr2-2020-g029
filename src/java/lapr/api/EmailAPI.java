package lapr.api;

import java.io.Closeable;
import java.io.Serializable;

/**
 * The API used to send emails.
 */
public interface EmailAPI extends Serializable, Closeable {
    /**
     * Send an email.
     * @param address Address that will receive the email.
     * @param message Message of the body of the email.
     * @return True if the email was sent, false otherwise.
     */
    boolean sendEmail(String address, String message);
}
