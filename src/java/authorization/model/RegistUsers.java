/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authorization.model;

import lapr.model.User;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author paulomaio
 */
public class RegistUsers implements Serializable {
    private Set<User> m_lstUsers = new HashSet<>();
    
    public boolean addUser(User utlz) {
        if (utlz != null)
            return this.m_lstUsers.add(utlz);
        return false;
    }
    
    public boolean removeUser(User utlz) {
        if (utlz != null)
            return this.m_lstUsers.remove(utlz);
        return false;
    }
    
    public User searchUser(String email) {
        for(User utlz: this.m_lstUsers)
            if(utlz.hasEmail(email))
                return utlz;
        return null;
    }
    
    public boolean hasUser(String email) {
        User utlz = searchUser(email);
        if (utlz != null)
            return this.m_lstUsers.contains(utlz);
        return false;
    }
    
    public boolean hasUser(User utlz)
    {
        return this.m_lstUsers.contains(utlz);
    }
}

