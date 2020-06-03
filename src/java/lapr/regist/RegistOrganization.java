package lapr.regist;

import autorizacao.AutorizacaoFacade;
import lapr.model.Collaborator;
import lapr.model.Manager;
import lapr.model.Organization;

import java.util.HashSet;
import java.util.Set;

public class RegistOrganization {
    private Set<Organization> m_lstOrganizacoes;

    public RegistOrganization() {
        this(new HashSet<>());
    }

    public RegistOrganization(Set<Organization> m_lstOrganizacoes) {
        this.m_lstOrganizacoes = m_lstOrganizacoes;
    }

    public Organization newOrganization(String name, Manager manager, Collaborator collaborator) {
        return new Organization(name, manager, collaborator);
    }

    public boolean add(Organization organization) {
        if(!validateOrganization(organization))
            throw new IllegalArgumentException("Organization is invalid.");
        return m_lstOrganizacoes.add(organization);
    }

    public boolean validateOrganization(Organization organizacation) {
        return !m_lstOrganizacoes.contains(organizacation);
    }
}
