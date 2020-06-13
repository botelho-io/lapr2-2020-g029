/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authorization.model;

import lapr.model.User;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author paulomaio
 */
public class UserSession implements Serializable
{
    private User m_oUser = null;
    
    public UserSession(User oUser) {
        if (oUser == null)
            throw new IllegalArgumentException("Argument cannot be null");
        this.m_oUser = oUser;
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

    public String getEmailUser() {
        if (isLoggedIn())
            return this.m_oUser.getEmail();
        return null;
    }

    public Role getRoleUser() {
        return this.m_oUser.getRole();
    }
}
