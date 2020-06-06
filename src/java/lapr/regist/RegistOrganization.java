package lapr.regist;

import autorizacao.AuthFacade;
import lapr.controller.AppPOE;
import lapr.model.*;

import java.util.*;

public class RegistOrganization implements Iterable<Organization> {

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
     * @param name of the collaborator.
     * @param manager of the organization
     * @param collaborator  of the organization.
     * @return The new organization.
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

        final AuthFacade au = AppPOE.getInstance().getAuthFacade();
        boolean success =  m_lstOrganizacoes.add(organization);
        success = success && au.registUser(organization.getCollaborator());
        success = success && au.registUser(organization.getManager());
        return success;
    }

    /**
     * Validates organization.
     * @param organizacation Organization to validate.
     * @return true if valid.
     */
    public boolean validateOrganization(Organization organizacation) {
        return  organizacation.validateOrganization() &&
                !m_lstOrganizacoes.contains(organizacation);
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

    /**
     * @return An iterator to all organizations.
     */
    @Override
    public Iterator<Organization> iterator() {
        return this.m_lstOrganizacoes.iterator();
    }

    /**
     * Group the transaction by the freelancer that executed them on all organizations.
     * @return A map that makes a freelancer correspond to a list of their executed transactions.
     */
    public Map<Freelancer, List<Transaction>> getGroupedTransactions() {
        final Map<Freelancer, List<Transaction>> fre_trs = new HashMap<>();
        for (final Organization org : this) {
            final Map<Freelancer, List<Transaction>> map = org.getListTransaction().getGroupedTransactions();
            for(final Freelancer fre : map.keySet()) {
                final List<Transaction> lt = fre_trs.get(fre);
                final List<Transaction> val = map.get(fre);
                if(lt != null) lt.addAll(val);
                else fre_trs.put(fre, val);
            }
        }
        return  fre_trs;
    }
    /**
     * Group the transaction by the freelancer that executed them.
     * @param year The year of the transactions.
     * @return A map that makes a freelancer correspond to a list of their executed transactions in the system
     * on the year specified.
     */
    public Map<Freelancer, List<Transaction>> getGroupedTransactionsInYear(final int year) {
        Map<Freelancer, List<Transaction>> fre_trs = new HashMap<>();
        for (final Organization org : this) {
            final Map<Freelancer, List<Transaction>> map = org.getListTransaction().getGroupedTransactionsInYear(year);
            for(final Freelancer fre : map.keySet()) {
                final List<Transaction> lt = fre_trs.get(fre);
                final List<Transaction> val = map.get(fre);
                if(lt != null) lt.addAll(val);
                else  fre_trs.put(fre, val);
            }
        }
        return  fre_trs;
    }
}
