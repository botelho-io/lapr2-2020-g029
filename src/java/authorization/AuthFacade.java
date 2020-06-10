/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authorization;

import authorization.model.UserRole;
import authorization.model.RegistUserRoles;
import authorization.model.RegistUsers;
import authorization.model.UserSession;
import lapr.model.User;
import lapr.utils.Role;

import java.io.Serializable;

/**
 *
 * @author paulomaio
 */
public class AuthFacade implements Serializable {

    private transient UserSession m_oSessao = null;
    private final RegistUserRoles m_oRoles = new RegistUserRoles();
    private final RegistUsers m_oUsers = new RegistUsers();

    public RegistUsers getRegistUser() {
        return m_oUsers;
    }

    public boolean registRoleUser(Role Role)
    {
        UserRole role = this.m_oRoles.newUserRole(Role);
        return this.m_oRoles.addRole(role);
    }
    
    public boolean registRoleUser(Role oRole, String strDescription)
    {
        UserRole role = this.m_oRoles.newUserRole(oRole,strDescription);
        return this.m_oRoles.addRole(role);
    }
    
    public boolean registUser(User utlz)
    {
        return this.m_oUsers.addUser(utlz);
    }
    
    public boolean existsUser(String strId)
    {
        return this.m_oUsers.hasUser(strId);
    }
    
    
    public UserSession doLogin(String strId, String strPwd) {
        User utlz = this.m_oUsers.searchUser(strId);
        if (utlz != null)
            if (utlz.hasPassword(strPwd))
                this.m_oSessao = new UserSession(utlz);
        return getSessaoAtual();
    }
    
    public UserSession getSessaoAtual() {
        return this.m_oSessao;
    }
    
    public void doLogout() {
        if (this.m_oSessao != null)
            this.m_oSessao.doLogout();
        this.m_oSessao = null;
    }

    public UserRole getRole(Role role) {
        return this.m_oRoles.findRole(role);
    }

    public boolean hasUser(User uzr) {
        return m_oUsers.hasUser(uzr);
    }
}
