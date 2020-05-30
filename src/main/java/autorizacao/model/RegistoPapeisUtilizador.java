/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autorizacao.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author paulomaio
 */
public class RegistoPapeisUtilizador
{
    private Set<PapelUtilizador> m_lstPapeis = new HashSet<PapelUtilizador>();
    
    public PapelUtilizador novoPapelUtilizador(String strPapel)
    {
        return new PapelUtilizador(strPapel);
    }
    
    public PapelUtilizador novoPapelUtilizador(String strPapel, String strDescricao)
    {
        return new PapelUtilizador(strPapel,strDescricao);
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
    
    public PapelUtilizador procuraPapel(String strPapel)
    {
        for(PapelUtilizador p: this.m_lstPapeis)
        {
            if(p.hasId(strPapel))
                return p;
        }
        return null;
    }
    
    public boolean hasPapel(String strPapel)
    {
        PapelUtilizador papel = procuraPapel(strPapel);
        if (papel != null)
            return this.m_lstPapeis.contains(papel);
        return false;
    }
    
    public boolean hasPapel(PapelUtilizador papel)
    {
        return this.m_lstPapeis.contains(papel);
    }
}
