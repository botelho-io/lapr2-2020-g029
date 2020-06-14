package lapr.controller;

import lapr.model.Freelancer;
import lapr.lists.RegistFreelancer;
import lapr.utils.Expertise;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that is responsible for creating a new freelancer and register in the system.
 * @author André Botelho and Ricardo Moreira.
 */
public class CreateFreelancerController {
    /**
     * The freelancer to register.
     */
    RegistFreelancer reg;

    /**
     * The freelancer details.
     */
    Freelancer fre;

    /**
     * Validate the expertise of the freelancer.
     * @return the expertise if valid.
     */
    public List<String> getValidExpertise() {
        List<String> exp = new ArrayList<>();
        for(Expertise ex : Expertise.values()) {
            exp.add(ex.name());
        }
        return exp;
    }

    /**
     * Build a new instance of organization receiving the name, expertise ,email, NIF, IBAN, address and country.
     * @param strName The name of this freelancer.
     * @param strExpertise The level of expertise of this freelancer.
     * @param strEmail The unique email of this freelancer.
     * @param strNIF The NIF of this freelancer.
     * @param strIBAN Freelancer's International Bank Account Number.
     * @param strAddress The address of this freelancer.
     * @param strCountry The country where the freelancer resides.
     * @return True if all parameter are valid, false otherwise.
     */
    public boolean newFreelancer(String strName, String strExpertise, String strEmail, String strNIF, String strIBAN, String strAddress, String strCountry) {
        reg = AppPOE.getInstance().getApp().getRegistFreelancer();
        fre = reg.newFreelancer(strName, strExpertise, strEmail, strNIF, strIBAN, strAddress, strCountry);
        return (fre != null) && reg.validate(fre);
    }

    /**
     * Adds the new freelancer to the register of freelancers.
     * @return True if the freelancer was added.
     */
    public boolean addFreelancer() {
        return reg.registerFreelancer(fre);
    }
}
