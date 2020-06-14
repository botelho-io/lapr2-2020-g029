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
 * @author André Botelho and Ricardo Moreira.
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

    /**
     * The role of the user.
     */
    private Role m_enumRole;

    /**
     * Constructor.
     * @param strName The name of the user.
     * @param strEmail The email of the user.
     * @param strPassword The unique password of the user.
     * @param enumRole The role of the user.
     */
    public User(String strName, String strEmail, String strPassword, Role enumRole) {
        this.m_strName = strName;
        this.m_strEmail = strEmail;
        this.m_strPassword = strPassword;
        this.m_enumRole = enumRole;
    }

    /**
     * Returns the name of the name.
     * @return the name of the name.
     */
    public String getName() {
        return m_strName;
    }


    /**
     * Modifies the name of the user.
     * @param name The name of the user.
     */
    public void setName(String name) {
        this.m_strName = name;
    }

    /**
     * Returns the email of the user.
     * @return the email of the user.
     */
    public String getEmail() {
        return m_strEmail;
    }

    /**
     * Modifies the email of the user.
     * @param email The email of the user.
     */
    public void setEmail(String email) {
        this.m_strEmail = email;
    }

    /**
     * Verifies the email of the user.
     * @param email The email to verify.
     * @return true if the email of the user is valid.
     */
    public boolean hasEmail(String email) {
        return this.m_strEmail.equals(email);
    }

    /**
     * Verifies the password of the user.
     * @param strPwd The password to verify.
     * @return true if the password of the user is valid.
     */
    public boolean hasPassword(String strPwd)
    {
        return this.m_strPassword.equals(strPwd);
    }

    /**
     * Verifies the role of the user.
     * @param oRole The role to verify.
     * @return true if the role of the user is valid.
     */
    public boolean hasRole(Role oRole) {
        return m_enumRole.equals(oRole);
    }

    /**
     * Returns the role of the user.
     * @return the role of the user.
     */
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

    /**
     * Compare two users.
     * @param o Ideally a user to compare.
     * @return Will return true if and only if the object provided is a user with the same email.
     */
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

    /**
     * Returns the textual description of the user in the format: role, name and email.
     * @return User features.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s - %s",m_enumRole.name() , this.m_strName, this.m_strEmail);
    }

}
