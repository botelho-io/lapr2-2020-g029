/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import autorizacao.model.UserRole;
import lapr.utils.Role;
import java.util.*;

/**
 * Represents a user that can login into the system.
 */
public class User {
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
    private Set<UserRole> m_lstPapeis = new HashSet<UserRole>();

    public User(String name, String email, String password, UserRole role) {
        this(name, email, password, new UserRole[]{role});
    }

    public User(String name, String email, String password, UserRole[] Roles) {
        this.m_strName = name;
        this.m_strEmail = email;
        this.m_strPassword = password;
        this.m_lstPapeis = new HashSet<UserRole>(Arrays.asList(Roles));
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

    public String getM_strPassword() {
        return m_strPassword;
    }

    public void setM_strPassword(String m_strPassword) {
        this.m_strPassword = m_strPassword;
    }

    public boolean hasEmail(String email)
    {
        return this.m_strEmail.equals(email);
    }

    public boolean hasPassword(String strPwd)
    {
        return this.m_strPassword.equals(strPwd);
    }

    public boolean addPapel(UserRole papel)
    {
        if (papel != null)
            return this.m_lstPapeis.add(papel);
        return false;
    }


    public boolean removePapel(UserRole papel)
    {
        if (papel != null)
            return this.m_lstPapeis.remove(papel);
        return false;
    }

    public boolean hasPapel(UserRole papel)
    {
        return this.m_lstPapeis.contains(papel);
    }

    public boolean hasPapel(Role oPapel)
    {
        for(UserRole papel: this.m_lstPapeis)
        {
            if (papel.hasId(oPapel))
                return true;
        }
        return false;
    }

    public List<UserRole> getPapeis()
    {
        return new ArrayList<>(this.m_lstPapeis);
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
