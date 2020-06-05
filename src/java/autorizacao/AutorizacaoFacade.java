/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autorizacao;

import autorizacao.model.UserRole;
import autorizacao.model.RegistoPapeisUtilizador;
import autorizacao.model.RegistoUtilizadores;
import autorizacao.model.SessaoUtilizador;
import lapr.model.User;
import lapr.utils.Role;

/**
 *
 * @author paulomaio
 */
public class AutorizacaoFacade
{
    private SessaoUtilizador m_oSessao = null;
    
    private final RegistoPapeisUtilizador m_oPapeis = new RegistoPapeisUtilizador();
    private final RegistoUtilizadores m_oUtilizadores = new RegistoUtilizadores();
    
    public boolean registaPapelUtilizador(Role Papel)
    {
        UserRole papel = this.m_oPapeis.novoPapelUtilizador(Papel);
        return this.m_oPapeis.addPapel(papel);
    }
    
    public boolean registaPapelUtilizador(Role oPapel, String strDescricao)
    {
        UserRole papel = this.m_oPapeis.novoPapelUtilizador(oPapel,strDescricao);
        return this.m_oPapeis.addPapel(papel);
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

    public UserRole getRole(Role role) {
        return this.m_oPapeis.procuraPapel(role);
    }
}
