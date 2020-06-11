package lapr.api;

import java.io.Closeable;
import java.io.Serializable;

public interface PswGeneratorAPI extends Serializable, Closeable {
    public String generatePassword(String emailOfUser);
}
