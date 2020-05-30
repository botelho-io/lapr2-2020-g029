/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autorizacao.model;

import lapr.model.User;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author paulomaio
 */
public class RegistoUtilizadores
{
    private Set<User> m_lstUtilizadores = new HashSet<>();
    
    public boolean addUtilizador(User utlz)
    {

        if (utlz != null)
            return this.m_lstUtilizadores.add(utlz);
        return false;
    }
    
    public boolean removeUtilizador(User utlz)
    {
        if (utlz != null)
            return this.m_lstUtilizadores.remove(utlz);
        return false;
    }
    
    public User procuraUtilizador(String email)
    {
        for(User utlz: this.m_lstUtilizadores)
        {
            if(utlz.hasEmail(email))
                return utlz;
        }
        return null;
    }
    
    public boolean hasUtilizador(String email)
    {
        User utlz = procuraUtilizador(email);
        if (utlz != null)
            return this.m_lstUtilizadores.contains(utlz);
        return false;
    }
    
    public boolean hasUtilizador(User utlz)
    {
        return this.m_lstUtilizadores.contains(utlz);
    }

    public User newUser(String strNome, String strEmail, String strPassword) {
        return null;
    }
}

