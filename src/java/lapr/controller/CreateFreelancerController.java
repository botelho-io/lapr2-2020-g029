package lapr.controller;

import lapr.model.Freelancer;
import lapr.regist.RegistFreelancer;

public class CreateFreelancerController {
    RegistFreelancer reg;
    Freelancer fre;

    public boolean newFreelancer(String strName, String strExpertise, String strEmail, String strNIF, String strIBAN, String strAddress, String strCountry) {
        reg = AppPOE.getInstance().getApp().getRegistFreelancer();
        fre = reg.newFreelancer(strName, strExpertise, strEmail, strNIF, strIBAN, strAddress, strCountry);
    }

    public boolean addFreelancer() {

    }
}
