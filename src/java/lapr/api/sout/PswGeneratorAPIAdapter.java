package lapr.api.sout;

import lapr.api.PswGeneratorAPI;

import java.io.IOException;
import java.util.Random;

/**
 * Represents an API adapter used to generate passwords.
 */
public class PswGeneratorAPIAdapter implements PswGeneratorAPI {
    @Override
    public String generatePassword(String emailOfUser) {
        final String valid = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklçzxcvbnm0123456789";
        final Random rand = new Random();
        StringBuilder pswdBuilder = new StringBuilder();
        for (int i = 0; i < 7; i++)
            pswdBuilder.append(valid.charAt(rand.nextInt(valid.length())));
        final String pswd = pswdBuilder.toString();
        final String msg = String.format(
                    "*******************************************************\n"+
                    "PswGeneratorAPI - Requested password for [%s]\n"+
                    "~~~~~~ BEGIN ~~~~~~\n"+
                    "Password [%s]\n"+
                    "~~~~~~  END  ~~~~~~\n"+
                    "*******************************************************\n\n\n"
                ,emailOfUser, pswd);
        System.out.println(msg);
        return pswd;
    }

    @Override
    public void close() {
    }
}
