package lapr.api.defaults;

import lapr.api.EmailAPI;
import lapr.utils.Constants;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class EmailAPIAdapter implements EmailAPI {
    FileWriter fstream;
    BufferedWriter out;

    public EmailAPIAdapter() {
        try {
            fstream = new FileWriter(Constants.emailsFile, true);
            out = new BufferedWriter(fstream);
        } catch (IOException e) {
            // TODO: handle exception.
            System.out.println(e.getMessage());
        }
    }

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
        try {
            out.write(msg);
            return true;
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void close() throws IOException {
        out.flush();
        out.close();
        fstream.close();
    }
}
