/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.ipp.isep.dei.lapr2.t4j.main.model;

import pt.ipp.isep.dei.lapr2.t4j.autorizacao.AutorizacaoFacade;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {
    private final AutorizacaoFacade m_oAutorizacao;
    

    public App()
    {
        this.m_oAutorizacao = new AutorizacaoFacade();
    }
    
    public AutorizacaoFacade getAutorizacaoFacade()
    {
        return this.m_oAutorizacao;
    }
}
    
    
