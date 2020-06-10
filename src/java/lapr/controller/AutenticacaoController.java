/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.controller;

import java.util.List;

import autorizacao.AuthFacade;
import autorizacao.model.UserRole;

/**
 *
 * @author paulomaio
 */
public class AutenticacaoController {
    private AuthFacade m_oAuthFacade;
    
    public AutenticacaoController()
    {
        this.m_oAuthFacade = AppPOE.getInstance().getApp().getAuthFacade();
    }
    
    public boolean doLogin(String strId, String strPwd)
    {
        return this.m_oAuthFacade.doLogin(strId, strPwd).isLoggedIn();
    }
    
    public List<UserRole> getPapeisUtilizador()
    {
        if (this.m_oAuthFacade.getSessaoAtual().isLoggedIn())
        {
            return this.m_oAuthFacade.getSessaoAtual().getPapeisUtilizador();
        }
        return null;
    }

    public void doLogout()
    {
        this.m_oAuthFacade.doLogout();
    }
}
