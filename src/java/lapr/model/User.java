/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import authorization.model.Role;
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

    private Role m_enumRole;

    public User(String strName, String strEmail, String strPassword, Role enumRole) {
        this.m_strName = strName;
        this.m_strEmail = strEmail;
        this.m_strPassword = strPassword;
        this.m_enumRole = enumRole;
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

    public boolean hasRole(Role oRole) {
        return m_enumRole.equals(oRole);
    }

    public Role getRole()
    {
        return m_enumRole;
    }

    @Override
    public int hashCode() {
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
        if (o == null || getClass() != o.getClass())
            return false;
        // field comparison
        User obj = (User) o;
        return Objects.equals(m_strEmail, obj.getEmail());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s",m_enumRole.name() , this.m_strName, this.m_strEmail);
    }

}
