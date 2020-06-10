/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autorizacao.model;

import lapr.utils.Role;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author paulomaio
 */
public class RegistUserRoles implements Serializable {

    private Set<UserRole> m_lstPapeis = new HashSet<UserRole>();
    public UserRole novoPapelUtilizador(Role Papel)
    {
        return new UserRole(Papel);
    }
    public UserRole novoPapelUtilizador(Role papel, String strDescricao)
    {
        return new UserRole(papel,strDescricao);
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
    
    public UserRole procuraPapel(Role papel)
    {
        for(UserRole p: this.m_lstPapeis)
        {
            if(p.hasId(papel))
                return p;
        }
        return null;
    }
    
    public boolean hasPapel(Role oPapel)
    {
        UserRole papel = procuraPapel(oPapel);
        if (papel != null)
            return this.m_lstPapeis.contains(papel);
        return false;
    }
    
    public boolean hasPapel(UserRole papel)
    {
        return this.m_lstPapeis.contains(papel);
    }
}
