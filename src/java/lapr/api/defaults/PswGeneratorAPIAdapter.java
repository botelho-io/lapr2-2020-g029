package lapr.api.defaults;

import lapr.api.PswGeneratorAPI;

import java.util.Random;

/**
 * Represents an API adapter used to generate passwords.
 */
public class PswGeneratorAPIAdapter implements PswGeneratorAPI {

    /**
     * Converts currency.
     *
     * @param emailOfUser the email of the user to generate a password.
     * @return the passaword generated for the user.
     */
    @Override
    public String generatePassword(String emailOfUser) {
        final String valid = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklçzxcvbnm0123456789";
        final Random rand = new Random();
        StringBuilder pswdBuilder = new StringBuilder();
        for (int i = 0; i < 7; i++)
            pswdBuilder.append(valid.charAt(rand.nextInt(valid.length())));
        return pswdBuilder.toString();
    }

    @Override
    public void close() {
    }
}
