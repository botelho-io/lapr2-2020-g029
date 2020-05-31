/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autorizacao.model;

import lapr.utils.Role;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author paulomaio
 */
public class RegistoPapeisUtilizador
{
    private Set<PapelUtilizador> m_lstPapeis = new HashSet<PapelUtilizador>();
    
    public PapelUtilizador novoPapelUtilizador(Role Papel)
    {
        return new PapelUtilizador(Papel);
    }
    
    public PapelUtilizador novoPapelUtilizador(Role papel, String strDescricao)
    {
        return new PapelUtilizador(papel,strDescricao);
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
    
    public PapelUtilizador procuraPapel(Role papel)
    {
        for(PapelUtilizador p: this.m_lstPapeis)
        {
            if(p.hasId(papel))
                return p;
        }
        return null;
    }
    
    public boolean hasPapel(Role oPapel)
    {
        PapelUtilizador papel = procuraPapel(oPapel);
        if (papel != null)
            return this.m_lstPapeis.contains(papel);
        return false;
    }
    
    public boolean hasPapel(PapelUtilizador papel)
    {
        return this.m_lstPapeis.contains(papel);
    }
}
