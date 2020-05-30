/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controller;

import java.util.List;
import autorizacao.model.PapelUtilizador;

/**
 *
 * @author paulomaio
 */
public class AutenticacaoController
{
    private AppPOE m_oApp;
    
    public AutenticacaoController()
    {
        this.m_oApp = AppPOE.getInstance();
    }
    
    public boolean doLogin(String strId, String strPwd)
    {
        return this.m_oApp.doLogin(strId, strPwd);
    }
    
    public List<PapelUtilizador> getPapeisUtilizador()
    {
        if (this.m_oApp.getSessaoAtual().isLoggedIn())
        {
            return this.m_oApp.getSessaoAtual().getPapeisUtilizador();
        }
        return null;
    }

    public void doLogout()
    {
        this.m_oApp.doLogout();
    }
}
