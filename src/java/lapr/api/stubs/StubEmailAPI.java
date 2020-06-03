package lapr.api.stubs;

import lapr.api.EmailAPI;

public class StubEmailAPI implements EmailAPI {
    /**
     * Send an email.
     *
     * @param address Address that will receive the email.
     * @param message Message of the body of the email.
     * @return True if the email was sent, false otherwise.
     */
    @Override
    public boolean sendEmail(String address, String message) {
        final String msg = String.format("EmailAPI - Email to [%s]\n\t\t**********\n%s\n\t\t**********", address, message);
        System.out.println(msg);
        return true;
    }
}
