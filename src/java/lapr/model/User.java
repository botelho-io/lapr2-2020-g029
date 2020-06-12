/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import authorization.model.UserRole;
import lapr.utils.Role;

import java.io.Serializable;
import java.util.*;

/**
 * Represents a user that can login into the system.
 */
public class User implements Serializable  {
    /**
     * The name of the user.
     */
    private String m_strName;
    /**
     * The email of the user.
     */
    private String m_strEmail;
    /**
     * The password of the user.
     */
    private String m_strPassword;
    private Set<UserRole> m_lstRoles = new HashSet<UserRole>();

    public User(String name, String email, String password, UserRole role) {
        this(name, email, password, new UserRole[]{role});
    }

    public User(String name, String email, String password, UserRole[] Roles) {
        this.m_strName = name;
        this.m_strEmail = email;
        this.m_strPassword = password;
        this.m_lstRoles = new HashSet<UserRole>(Arrays.asList(Roles));
    }

    public String getName() {
        return m_strName;
    }

    public void setName(String name) {
        this.m_strName = name;
    }

    public String getEmail() {
        return m_strEmail;
    }

    public void setEmail(String email) {
        this.m_strEmail = email;
    }

    public boolean hasEmail(String email) {
        return this.m_strEmail.equals(email);
    }

    public boolean hasPassword(String strPwd)
    {
        return this.m_strPassword.equals(strPwd);
    }

    public boolean hasRole(Role oRole)
    {
        for(UserRole role: this.m_lstRoles)
        {
            if (role.hasId(oRole))
                return true;
        }
        return false;
    }

    public List<UserRole> getRoles()
    {
        return new ArrayList<>(this.m_lstRoles);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.m_strEmail);
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
        User obj = (User) o;
        return Objects.equals(m_strEmail, obj.getEmail());
    }

    @Override
    public String toString()
    {
        return String.format("%s - %s", this.m_strName, this.m_strEmail);
    }

}
