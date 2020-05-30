/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model;

import autorizacao.model.PapelUtilizador;

import java.util.*;

/**
 *
 * @author Universidade
 */
public class User {
    private String name;
    private String email;
    private String password; // Não deveria guardar a password em "plain text"
    private Set<PapelUtilizador> m_lstPapeis = new HashSet<PapelUtilizador>();

    public User(String name, String email, String password, PapelUtilizador role) {
        this(name, email, password, new PapelUtilizador[]{role});
    }

    public User(String name, String email, String password, PapelUtilizador[] Roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.m_lstPapeis = new HashSet<PapelUtilizador>(Arrays.asList(Roles));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean hasEmail(String email)
    {
        return this.email.equals(email);
    }

    public boolean hasPassword(String strPwd)
    {
        return this.password.equals(strPwd);
    }

    public boolean addPapel(PapelUtilizador papel)
    {
        if (papel != null)
            return this.m_lstPapeis.add(papel);
        return false;
    }


    public boolean removePapel(PapelUtilizador papel)
    {
        if (papel != null)
            return this.m_lstPapeis.remove(papel);
        return false;
    }

    public boolean hasPapel(PapelUtilizador papel)
    {
        return this.m_lstPapeis.contains(papel);
    }

    public boolean hasPapel(String strPapel)
    {
        for(PapelUtilizador papel: this.m_lstPapeis)
        {
            if (papel.hasId(strPapel))
                return true;
        }
        return false;
    }

    public List<PapelUtilizador> getPapeis()
    {
        return new ArrayList<>(this.m_lstPapeis);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.email);
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
        return Objects.equals(email, obj.getEmail());
    }

    @Override
    public String toString()
    {
        return String.format("%s - %s", this.name, this.email);
    }

}
