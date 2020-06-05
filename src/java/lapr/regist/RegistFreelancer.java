package lapr.regist;

import lapr.model.Freelancer;
import lapr.utils.Expertise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents the register of all the freelancers available in the system.
 */
public class RegistFreelancer {
    /**
     * The list of freelancers
     */
    Set<Freelancer> m_setFreelancer;

    /**
     * Constructor.
     */
    public RegistFreelancer() {
        m_setFreelancer = new HashSet<>();
    }

    /**
     * Creates a new freelancer
     * @param strName The name of this freelancer.
     * @param strExpertise The level of expertise of this freelancer. (Must be in the Expertise enum)
     * @param strEmail The unique email of this freelancer.
     * @param strNIF The NIF of this freelancer.
     * @param strIBAN Freelancer's International Bank Account Number.
     * @param strAddress The address of this freelancer.
     * @param strCountry The country where the freelancer resides.
     * @return The new freelancer that was created
     * @throws IllegalArgumentException IF the name does not have at least two words or the strExpertise is not a valid expertise name.
     */
    public Freelancer newFreelancer(String strName, String strExpertise, String strEmail, String strNIF,
                                    String strIBAN, String strAddress, String strCountry)
    throws IllegalArgumentException {
        Expertise exp;
        try {
            exp = Expertise.valueOf(strExpertise.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(strExpertise.trim() + " is not a valid name for an expertise level.");
        }
        return newFreelancer(strName.trim(), exp, strEmail.trim(), strNIF.trim(), strIBAN.trim(), strAddress.trim(), strCountry.trim().toUpperCase());
    }

    /**
     * Creates a new freelancer.
     * @param strName The name of this freelancer.
     * @param enumExpertise The level of expertise of this freelancer.
     * @param strEmail The unique email of this freelancer.
     * @param strNIF The NIF of this freelancer.
     * @param strIBAN Freelancer's International Bank Account Number.
     * @param strAddress The address of this freelancer.
     * @param strCountry The country where the freelancer resides.
     * @return The new freelancer that was created
     * @throws IllegalArgumentException IF the name does not have at least two words.
     */
    public Freelancer newFreelancer(String strName, Expertise enumExpertise, String strEmail, String strNIF,
                                    String strIBAN, String strAddress, String strCountry)
    throws IllegalArgumentException {
        String id = generateID(strName);
        return new Freelancer(id, strName, enumExpertise, strEmail, strNIF, strIBAN, strAddress, strCountry);
    }

    /**
     * Generates a unique id from a name.
     * @param strName The name from which to generate the id.
     * @return The ID that was generated
     * @throws IllegalArgumentException IF the name does not have at least two words.
     */
    private String generateID(String strName) throws IllegalArgumentException {
        String[] names = strName.split("[ ]+");
        if(names.length < 2) throw new IllegalArgumentException("Name provided must include at least firs and last name.");
        String base_id = (String.valueOf(names[0].charAt(0)) + names[names.length - 1].charAt(0)).toUpperCase();
        int num_id = 1;
        while(existsId(base_id+num_id)) {
            num_id++;
        }
        return base_id+num_id;
    }

    /**
     * Checks if another freelancer has the same ID.
     * @param id Id to check
     * @return True if another freelancer has the same id, false otherwise.
     */
    public boolean existsId(String id) {
        for(Freelancer fre : m_setFreelancer) {
            if(fre.getId().equals(id))
                return true;
        }
        return false;
    }

    /**
     * Tests if a freelancer can be added into the register.
     * @param fre The freelancer to test.
     * @return True if the freelancer can be added into the register, false otherwise.
     */
    public boolean validate(Freelancer fre) {
        return !m_setFreelancer.contains(fre);
    }

    /**
     * Adds a freelancer to the register.
     * @param fre The freelancer to add;
     * @return True if the freelancer was added, false otherwise.
     */
    public boolean addFreelancer(Freelancer fre) {
        if(validate(fre))
            return m_setFreelancer.add(fre);
        return false;
    }

    public List<Freelancer> getFreelancers() {
        return new ArrayList<>(this.m_setFreelancer);
    }
}
