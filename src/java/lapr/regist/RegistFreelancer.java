package lapr.regist;

import lapr.model.Freelancer;
import lapr.utils.Expertise;

/**
 * Represents the register of all the freelancers available in the system.
 */
public class RegistFreelancer {
    public Freelancer newFreelancer(String strName, String strExpertise, String strEmail, String strNIF, String strIBAN, String strAddress, String strCountry) {
        Expertise exp;
        try {
            exp = Expertise.valueOf(strExpertise.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(strExpertise.trim() + " is not a valid name for an expertise level.");
        }
        return newFreelancer(strName.trim(), exp, strEmail.trim(), strNIF.trim(), strIBAN.trim(), strAddress.trim(), strCountry.trim().toUpperCase());
    }

    public Freelancer newFreelancer(String strName, Expertise unumExpertise, String strEmail, String strNIF, String strIBAN, String strAddress, String strCountry) {
        String id = generateID(strName);
        return null;
    }

    private String generateID(String strName) {
        /*strName.matches()

        String[] names = strName.split("[ ]+");

        if(names.length < 2) throw new IllegalArgumentException("Name provided must include at least firs and last name.");*/
        return "";
    }


}
