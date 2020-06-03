package lapr.api.stubs;

import lapr.api.PswGeneratorAPI;

public class StubPswGeneratorAPI implements PswGeneratorAPI {
    @Override
    public String generatePassword(String emailOfUser) {
        System.out.println(String.format("PswGeneratorAPI - requested psw for [%s]", emailOfUser));
        return emailOfUser.substring(0, 6);
    }
}
