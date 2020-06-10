/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autorizacao.model;

import lapr.model.User;
import lapr.utils.Role;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author paulomaio
 */
public class SessaoUtilizador implements Serializable
{
    private User m_oUtilizador = null;
    
    private SessaoUtilizador() {
    }
    
    public SessaoUtilizador(User oUtilizador) {
        if (oUtilizador == null)
            throw new IllegalArgumentException("Argumento não pode ser nulo.");
        this.m_oUtilizador = oUtilizador;
    }
    
    public void doLogout()
    {
        this.m_oUtilizador = null;
    }
    
    public boolean isLoggedIn()
    {
        return this.m_oUtilizador != null;
    }
    
    public boolean isLoggedInComPapel(Role oPapel)
    {
        if (isLoggedIn())
        {
            return this.m_oUtilizador.hasPapel(oPapel);
        }
        return false;
    }
    
    public String getNomeUtilizador()
    {
        if (isLoggedIn())
            return this.m_oUtilizador.getName();
        return null;
    }
    
    public String getEmailUtilizador()
    {
        if (isLoggedIn())
            return this.m_oUtilizador.getEmail();
        return null;
    }
    
    public List<UserRole> getPapeisUtilizador()
    {
        return this.m_oUtilizador.getPapeis();
    }
}
