/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authorization.model;

import lapr.model.User;
import lapr.utils.Role;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author paulomaio
 */
public class UserSession implements Serializable
{
    private User m_oUser = null;
    
    private UserSession() {
    }
    
    public UserSession(User oUser) {
        if (oUser == null)
            throw new IllegalArgumentException("Argumento não pode ser nulo.");
        this.m_oUser = oUser;
    }
    
    public void doLogout()
    {
        this.m_oUser = null;
    }
    
    public boolean isLoggedIn()
    {
        return this.m_oUser != null;
    }
    
    public boolean isLoggedInWithRole(Role oRole) {
        if (isLoggedIn())
            return this.m_oUser.hasRole(oRole);
        return false;
    }

    public String getEmailUser()
    {
        if (isLoggedIn())
            return this.m_oUser.getEmail();
        return null;
    }
    
    public List<UserRole> getRolesUser()
    {
        return this.m_oUser.getRoles();
    }
}
