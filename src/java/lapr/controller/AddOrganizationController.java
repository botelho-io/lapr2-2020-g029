package lapr.controller;

import lapr.model.*;

public class AddOrganizationController {

    private AppPOE m_oApp;
    private App m_oPlataforma;
    private Organization m_oOrganizacao;
    private String m_strPwd;
    private RegisterOrganization ro;

    public AddOrganizationController()
    {
        this.m_oApp = AppPOE.getInstance();
        this.m_oPlataforma = m_oApp.getApp);
    }


    public boolean newOrganization (String name, String nameM, String emailM, String passwordM,
                                   String nameC, String emailC, String passwordC)
    {
        try
        {
            ro = this.m_oPlataforma.getRegisterOrganization();
            Manager manager = Organization.newManager(nameM, emailM, passwordM);
            Colaborator colaborator = Organization.newColaborator(nameC, emailC, passwordC);
            this.m_oOrganizacao = ro.newOrganization( name, manager, colaborator);
            return ro.validatesOrganization(this.m_oOrganizacao);
        }
        catch(RuntimeException ex)
        {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            this.m_oOrganizacao = null;
            return false;
        }
    }


    public boolean registerOrganizacation()
    {
        return ro.registerOrganization(this.m_oOrganizacao);
    }

    public String getOrganizacaoString()
    {
        return this.m_oOrganizacao.toString();
    }
}
