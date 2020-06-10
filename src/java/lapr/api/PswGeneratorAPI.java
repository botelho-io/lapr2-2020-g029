package lapr.api;

import java.io.Serializable;

public interface PswGeneratorAPI extends Serializable {
    public String generatePassword(String emailOfUser);
}
