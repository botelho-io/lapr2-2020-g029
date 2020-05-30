/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.model;

import autorizacao.AutorizacaoFacade;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {
    private final AutorizacaoFacade m_oAutorizacao;
    private RegisterOrganization ro;
    

    public App()
    {
        this.m_oAutorizacao = new AutorizacaoFacade();
        this.ro = new RegisterOrganization();
    }
    
    public AutorizacaoFacade getAutorizacaoFacade()
    {
        return this.m_oAutorizacao;
    }

    public RegisterOrganization getRegisterOrganization() {
        return ro;
    }
}
    
    
