/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autorizacao.model;

import lapr.utils.Role;

import java.util.Objects;

/**
 *
 * @author paulomaio
 */
public class PapelUtilizador
{
    private Role m_oPapel;
    private String m_strDescricao;
    
    public PapelUtilizador(Role Papel)
    {
        if ( (Papel == null) )
            throw new IllegalArgumentException("O argumento não pode ser nulo ou vazio.");
        
        this.m_oPapel = Papel;
        this.m_strDescricao = Papel.name();
    }
    
    public PapelUtilizador(Role papel, String strDescricao)
    {
        if ( (papel == null) || (strDescricao == null) || (strDescricao.isEmpty()))
            throw new IllegalArgumentException("Nenhum dos argumentos não pode ser nulo ou vazio.");
        
        this.m_oPapel = papel;
        this.m_strDescricao = strDescricao;
    }
    
    public Role getPapel()
    {
        return this.m_oPapel;
    }
    
    public String getDescricao()
    {
        return this.m_strDescricao;
    }

    public boolean hasId(Role Id)
    {
        return this.m_oPapel.equals(Id);
    }
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.m_oPapel);
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
        PapelUtilizador obj = (PapelUtilizador) o;
        return Objects.equals(m_oPapel, obj.m_oPapel);
    }
    
    @Override
    public String toString()
    {
        return String.format("%s - %s", this.m_oPapel, this.m_strDescricao);
    }
}
