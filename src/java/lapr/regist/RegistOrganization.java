package lapr.regist;

import autorizacao.AutorizacaoFacade;
import lapr.controller.AppPOE;
import lapr.model.Collaborator;
import lapr.model.Manager;
import lapr.model.Organization;

import java.util.HashSet;
import java.util.Set;

public class RegistOrganization {

    /**
     * A list of organizations.
     */
    private Set<Organization> m_lstOrganizacoes;


    public RegistOrganization() {
        this(new HashSet<>());
    }

    public RegistOrganization(Set<Organization> m_lstOrganizacoes) {
        this.m_lstOrganizacoes = m_lstOrganizacoes;
    }

    /**
     * Build a new instance of organization receiving the name, manager and collaborator.
     *
     * @param name of the collaborator.
     * @param manager of the organization
     * @param collaborator  of the organization.
     */
    public Organization newOrganization(String name, Manager manager, Collaborator collaborator) {
        return new Organization(name, manager, collaborator);
    }

    /**
     * Add a new organization to the organization list if valid.
     *
     * @param organization to get added.
     * @return list with the organization added.
     */
    public boolean add(Organization organization) {
        if(!validateOrganization(organization))
            throw new IllegalArgumentException("Organization is invalid.");
        return m_lstOrganizacoes.add(organization);
    }

    /**
     * Validates organization.
     * @param organizacation to get validated .
     * @return true if valid.
     */
    public boolean validateOrganization(Organization organizacation) {
        return !m_lstOrganizacoes.contains(organizacation);
    }

    /**
     * Returns the organization a user is affiliated with.
     * @param email The email of the user.
     * @return The organization the user is affiliated with if it exists, null otherwise.
     */
    public Organization getOrganizationByEmailUser(String email) {
        for (Organization org : this.m_lstOrganizacoes) {
            if(org.getCollaborator().getEmail().equals(email) || org.getManager().getEmail().equals(email))
                return org;
        }
        return null;
    }
}
