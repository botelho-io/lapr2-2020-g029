/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.model;

import autorizacao.AutorizacaoFacade;
import lapr.api.EmailAPI;
import lapr.api.PswGeneratorAPI;
import lapr.regist.RegistOrganization;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {
    private final AutorizacaoFacade m_oAutorizacao;
    private RegistOrganization ro;
    private PswGeneratorAPI m_oPswGeneratorAPI;
    /**
     * The API used to send emails.
     */
    private EmailAPI m_oEmailAPI;


    public App()
    {
        this.m_oAutorizacao = new AutorizacaoFacade();
        this.ro = new RegistOrganization();
    }
    
    public AutorizacaoFacade getAutorizacaoFacade()
    {
        return this.m_oAutorizacao;
    }

    public RegistOrganization getRegistOrganization() {
        return ro;
    }

    public PswGeneratorAPI getPswGeneratorAPI() {
        return this.m_oPswGeneratorAPI;
    }

    /**
     * @return The API used to send emails.
     */
    public EmailAPI getEmailAPI() {
        return this.m_oEmailAPI;
    }
}
    
    
