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
    private final Set<User> m_setUsers;

    public RegistUsers() {
        this(new HashSet<>());
    }

    public RegistUsers(Set<User> m_setUsers) {
        this.m_setUsers = m_setUsers;
    }

    public boolean registerUser(User utlz) {
        if (utlz != null)
            return this.m_setUsers.add(utlz);
        return false;
    }
    
    public User searchUser(String email) {
        for(User utlz: this.m_setUsers)
            if(utlz.hasEmail(email))
                return utlz;
        return null;
    }
    
    public boolean hasUser(User utlz)
    {
        return this.m_setUsers.contains(utlz);
    }
}

