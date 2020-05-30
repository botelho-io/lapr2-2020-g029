/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.lapr2.t4j.autorizacao.model;

import java.util.Objects;

/**
 *
 * @author paulomaio
 */
public class PapelUtilizador
{
    private String m_strPapel;
    private String m_strDescricao;
    
    public PapelUtilizador(String strPapel)
    {
        if ( (strPapel == null) || (strPapel.isEmpty()))
            throw new IllegalArgumentException("O argumento não pode ser nulo ou vazio.");
        
        this.m_strPapel = strPapel;
        this.m_strDescricao = strPapel;
    }
    
    public PapelUtilizador(String strPapel, String strDescricao)
    {
        if ( (strPapel == null) || (strDescricao == null) || (strPapel.isEmpty())|| (strDescricao.isEmpty()))
            throw new IllegalArgumentException("Nenhum dos argumentos não pode ser nulo ou vazio.");
        
        this.m_strPapel = strPapel;
        this.m_strDescricao = strDescricao;
    }
    
    public String getPapel()
    {
        return this.m_strPapel;
    }
    
    public String getDescricao()
    {
        return this.m_strDescricao;
    }

    public boolean hasId(String strId)
    {
        return this.m_strPapel.equals(strId);
    }
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.m_strPapel);
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
        return Objects.equals(m_strPapel, obj.m_strPapel);
    }
    
    @Override
    public String toString()
    {
        return String.format("%s - %s", this.m_strPapel, this.m_strDescricao);
    }
}
