package lapr.controller;

import lapr.model.Freelancer;
import lapr.lists.RegistFreelancer;
import lapr.utils.Expertise;

import java.util.ArrayList;
import java.util.List;

public class CreateFreelancerController {
    RegistFreelancer reg;
    Freelancer fre;

    public List<String> getValidExpertise() {
        List<String> exp = new ArrayList<>();
        for(Expertise ex : Expertise.values()) {
            exp.add(ex.name());
        }
        return exp;
    }

    public boolean newFreelancer(String strName, String strExpertise, String strEmail, String strNIF, String strIBAN, String strAddress, String strCountry) {
        reg = AppPOE.getInstance().getApp().getRegistFreelancer();
        fre = reg.newFreelancer(strName, strExpertise, strEmail, strNIF, strIBAN, strAddress, strCountry);
        return (fre != null) && reg.validate(fre);
    }

    public boolean addFreelancer() {
        return reg.registerFreelancer(fre);
    }
}
