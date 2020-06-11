package lapr.api.defaults;

import lapr.api.PswGeneratorAPI;

import java.util.Random;

public class PswGeneratorAPIAdapter implements PswGeneratorAPI {
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
