/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autorizacao;

import autorizacao.model.PapelUtilizador;
import autorizacao.model.RegistoPapeisUtilizador;
import autorizacao.model.RegistoUtilizadores;
import autorizacao.model.SessaoUtilizador;
import lapr.model.User;

/**
 *
 * @author paulomaio
 */
public class AutorizacaoFacade
{
    private SessaoUtilizador m_oSessao = null;
    
    private final RegistoPapeisUtilizador m_oPapeis = new RegistoPapeisUtilizador();
    private final RegistoUtilizadores m_oUtilizadores = new RegistoUtilizadores();
    
    public boolean registaPapelUtilizador(String strPapel)
    {
        PapelUtilizador papel = this.m_oPapeis.novoPapelUtilizador(strPapel);
        return this.m_oPapeis.addPapel(papel);
    }
    
    public boolean registaPapelUtilizador(String strPapel, String strDescricao)
    {
        PapelUtilizador papel = this.m_oPapeis.novoPapelUtilizador(strPapel,strDescricao);
        return this.m_oPapeis.addPapel(papel);
    }

    public boolean registaUtilizadorComPapel(String strNome, String strEmail, String strPassword, String strPapel)
    {
        PapelUtilizador papel = this.m_oPapeis.procuraPapel(strPapel);
        User utlz = this.m_oUtilizadores.newUser(strNome,strEmail,strPassword);
        utlz.addPapel(papel);
        return this.m_oUtilizadores.addUtilizador(utlz);
    }
    
    public boolean registaUtilizador(User utlz)
    {
        return this.m_oUtilizadores.addUtilizador(utlz);
    }
    
    public boolean existeUtilizador(String strId)
    {
        return this.m_oUtilizadores.hasUtilizador(strId);
    }
    
    
    public SessaoUtilizador doLogin(String strId, String strPwd)
    {
        User utlz = this.m_oUtilizadores.procuraUtilizador(strId);
        if (utlz != null)
        {
            if (utlz.hasPassword(strPwd)){
                this.m_oSessao = new SessaoUtilizador(utlz);
            }
        }
        return getSessaoAtual();
    }
    
    public SessaoUtilizador getSessaoAtual()
    {
        return this.m_oSessao;
    }
    
    public void doLogout()
    {
        if (this.m_oSessao != null)
            this.m_oSessao.doLogout();
        this.m_oSessao = null;
    }

    public PapelUtilizador getRole(String role) {
        return this.m_oPapeis.procuraPapel(role);
    }
}
