package lapr.controller;

import lapr.api.EmailAPI;
import lapr.api.PswGeneratorAPI;
import lapr.model.*;
import lapr.regist.RegistOrganization;

public class AddOrganizationController {

    private RegistOrganization m_oRegist;
    private String m_strManagerPassword;
    private String m_strCollaboratorPassword;
    private Organization m_oOrg;
    private PswGeneratorAPI m_oPswrd;
    private EmailAPI m_oMail;
    private String m_strEmailM;
    private String m_strEmailC;

    public AddOrganizationController() {
        AppPOE m_oPOE = AppPOE.getInstance();
        App m_oApp = m_oPOE.getApp();
        m_oRegist = m_oApp.getRegistOrganization();
        m_oPswrd = m_oApp.getPswGeneratorAPI();
        m_oMail = m_oApp.getEmailAPI();
    }


    public boolean newOrganization (String name, String nameM, String strEmailM, String nameC, String strEmailC) {
        m_strEmailM = strEmailM;
        m_strEmailC = strEmailC;
        m_strManagerPassword = m_oPswrd.generatePassword(m_strEmailM);
        m_strCollaboratorPassword = m_oPswrd.generatePassword(m_strEmailC);
        Colaborator c = Organization.newColaborator(nameC, m_strEmailC, m_strCollaboratorPassword);
        if(!Organization.validatesColaborator(c))
            return false;
        Manager m = Organization.newManager(nameM, m_strEmailM, m_strManagerPassword);
        if(!Organization.validatesManager(m))
            return false;
        m_oOrg = m_oRegist.newOrganization(name, m, c);
        return m_oRegist.validateOrganization(m_oOrg);
    }

    // TODO: Extract to config file?
    private static final String CollaboratorEmail = "Hello new collaborator!\nHere's your password: [%s].\n\nHave a great day!";
    private static final String ManagerEmail = "Hello new Manager!\nHere's your password: [%s].\n\nHave a great day!";

    public boolean registOrganizacation() {
        if(m_oRegist.add(m_oOrg)) {
            boolean b = true;
            b = b & m_oMail.sendEmail(m_strEmailC, String.format(CollaboratorEmail, m_strCollaboratorPassword));
            b = b & m_oMail.sendEmail(m_strEmailM, String.format(ManagerEmail, m_strManagerPassword));
            return b;
        } else return false;
    }
}
