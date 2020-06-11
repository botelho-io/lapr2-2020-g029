/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authorization.model;

import lapr.utils.Role;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author paulomaio
 */
public class UserRole implements Serializable {
    private Role m_oRole;
    private String m_strDescription;
    
    public UserRole(Role Role)
    {
        if ( (Role == null) )
            throw new IllegalArgumentException("The argument cannot be null or empty");
        
        this.m_oRole = Role;
        this.m_strDescription = Role.name();
    }
    
    public UserRole(Role role, String strDescription)
    {
        if ( (role == null) || (strDescription == null) || (strDescription.isEmpty()))
            throw new IllegalArgumentException("The arguments cannot be null or empty");
        
        this.m_oRole = role;
        this.m_strDescription = strDescription;
    }
    
    public Role getRole()
    {
        return this.m_oRole;
    }
    
    public String getDescription()
    {
        return this.m_strDescription;
    }

    public boolean hasId(Role Id)
    {
        return this.m_oRole.equals(Id);
    }
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.m_oRole);
        return hash;
    }
    
    @Override
    public boolean equals(Object o) {
        // Inspirado em https://www.sitepoint.com/implement-javas-equals-method-correctly/
        
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        // field comparison
        UserRole obj = (UserRole) o;
        return Objects.equals(m_oRole, obj.m_oRole);
    }
    
    @Override
    public String toString()
    {
        return String.format("%s - %s", this.m_oRole, this.m_strDescription);
    }
}
