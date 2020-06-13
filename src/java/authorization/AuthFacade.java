/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authorization;

import authorization.model.RegistUsers;
import authorization.model.UserSession;
import lapr.model.User;
import java.io.Serializable;

/**
 *
 * @author paulomaio
 */
public class AuthFacade implements Serializable {
    private transient UserSession m_oSession = null;
    private final RegistUsers m_oUsers = new RegistUsers();
    
    public boolean registerUser(User utlz)
    {
        return this.m_oUsers.registerUser(utlz);
    }
    public UserSession doLogin(String strId, String strPwd) {
        User utlz = this.m_oUsers.searchUser(strId);
        if (utlz != null)
            if (utlz.hasPassword(strPwd))
                this.m_oSession = new UserSession(utlz);
        return getCurrentSession();
    }
    public UserSession getCurrentSession() {
        return this.m_oSession;
    }
    public boolean hasUser(User uzr) {
        return m_oUsers.hasUser(uzr);
    }
}
