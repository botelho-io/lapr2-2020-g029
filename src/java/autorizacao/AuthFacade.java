/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autorizacao;

import autorizacao.model.UserRole;
import autorizacao.model.RegistUserRoles;
import autorizacao.model.RegistUsers;
import autorizacao.model.SessaoUtilizador;
import lapr.model.User;
import lapr.utils.Role;

import java.io.Serializable;

/**
 *
 * @author paulomaio
 */
public class AuthFacade implements Serializable {

    private SessaoUtilizador m_oSessao = null;
    private final RegistUserRoles m_oPapeis = new RegistUserRoles();
    private final RegistUsers m_oUsers = new RegistUsers();

    public RegistUsers getRegistUser() {
        return m_oUsers;
    }

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
    
    public boolean registUser(User utlz)
    {
        return this.m_oUsers.addUser(utlz);
    }
    
    public boolean existeUtilizador(String strId)
    {
        return this.m_oUsers.hasUser(strId);
    }
    
    
    public SessaoUtilizador doLogin(String strId, String strPwd)
    {
        User utlz = this.m_oUsers.procuraUtilizador(strId);
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

    public boolean hasUser(User uzr) {
        return m_oUsers.hasUser(uzr);
    }
}
