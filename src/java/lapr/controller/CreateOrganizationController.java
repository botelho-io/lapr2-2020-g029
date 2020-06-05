package lapr.controller;

import lapr.api.EmailAPI;
import lapr.api.PswGeneratorAPI;
import lapr.model.*;
import lapr.regist.RegistOrganization;

public class CreateOrganizationController {
    /**
     * Name of the organization.
     */
    private RegistOrganization m_oRegist;

    /**
     * The manager password.
     */
    private String m_strManagerPassword;

    /**
     * The collaborator password.
     */
    private String m_strCollaboratorPassword;
    private Organization m_oOrg;

    /**
     * An instance of PswGeneratorAPI.
     */
    private PswGeneratorAPI m_oPswrd;

    /**
     * An instance of EmailAPI.
     */
    private EmailAPI m_oMail;

    /**
     * The manager email.
     */
    private String m_strEmailM;

    /**
     * The collaborator email.
     */
    private String m_strEmailC;

    public CreateOrganizationController() {
        AppPOE m_oPOE = AppPOE.getInstance();
        App m_oApp = m_oPOE.getApp();
        m_oRegist = m_oApp.getRegistOrganization();
        m_oPswrd = m_oApp.getPswGeneratorAPI();
        m_oMail = m_oApp.getEmailAPI();
    }

    /**
     * Build a new instance of organization receiving the name, nameM ,strEmailM, nameC and strEmailC.
     *
     * @param name the name of the organization.
     * @param nameM the name of the manager.
     * @param strEmailM the email of the manager.
     * @param nameC the name of the collaborator.
     * @param strEmailC the email of the collaborator.
     *@return true if all parameter are valid.
     */
    public boolean newOrganization(String name, String nameM, String strEmailM, String nameC, String strEmailC) {
        m_strEmailM = strEmailM;
        m_strEmailC = strEmailC;
        m_strManagerPassword = m_oPswrd.generatePassword(m_strEmailM);
        m_strCollaboratorPassword = m_oPswrd.generatePassword(m_strEmailC);
        Collaborator c = Organization.newCollaborator(nameC, m_strEmailC, m_strCollaboratorPassword);
        if(!Organization.validatesCollaborator(c))
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
