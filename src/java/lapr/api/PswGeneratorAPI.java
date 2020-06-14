package lapr.api;

import java.io.Closeable;
import java.io.Serializable;

/**
 * Represents an API used to generate passwords.
 */
public interface PswGeneratorAPI extends Serializable, Closeable {
    public String generatePassword(String emailOfUser);
}
