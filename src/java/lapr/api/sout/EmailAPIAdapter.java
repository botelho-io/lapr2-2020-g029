package lapr.api.sout;

import lapr.api.EmailAPI;

import java.util.Calendar;

/**
 * The API adapter used to send emails.
 */
public class EmailAPIAdapter implements EmailAPI {
    /**
     * Send an email.
     *
     * @param address Address that will receive the email.
     * @param message Message of the body of the email.
     * @return True if the email was sent, false otherwise.
     */
    @Override
    public boolean sendEmail(String address, String message) {
        final String msg = String.format(
                    "*******************************************************\n"+
                    "EmailAPI - Email to [%s]\n"+
                    "Date - %s\n"+
                    "~~~~~~ E-MAIL BEGIN ~~~~~~\n"+
                    "%s\n"+
                    "~~~~~~  E-MAIL END  ~~~~~~\n"+
                    "*******************************************************\n\n\n"
                ,address, Calendar.getInstance().getTime().toString(), message);
        System.out.println(msg);
        return true;
    }

    @Override
    public void close() {
    }

}
