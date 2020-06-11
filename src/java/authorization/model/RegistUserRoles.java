/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authorization.model;

import lapr.utils.Role;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author paulomaio
 */
public class RegistUserRoles implements Serializable {

    private Set<UserRole> m_lstRoles = new HashSet<UserRole>();
    public UserRole newUserRole(Role Role)
    {
        return new UserRole(Role);
    }
    public UserRole newUserRole(Role role, String strDescription)
    {
        return new UserRole(role,strDescription);
    }
    
    public boolean addRole(UserRole role)
    {
        if (role != null)
            return this.m_lstRoles.add(role);
        return false;
    }
    
    public boolean removeRole(UserRole role)
    {
        if (role != null)
            return this.m_lstRoles.remove(role);
        return false;
    }
    
    public UserRole findRole(Role role)
    {
        for(UserRole p: this.m_lstRoles)
        {
            if(p.hasId(role))
                return p;
        }
        return null;
    }
    
    public boolean hasRole(Role oRole)
    {
        UserRole role = findRole(oRole);
        if (role != null)
            return this.m_lstRoles.contains(role);
        return false;
    }
    
    public boolean hasRole(UserRole role)
    {
        return this.m_lstRoles.contains(role);
    }
}
