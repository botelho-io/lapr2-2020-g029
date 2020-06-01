/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import lapr.list.ListTransaction;

/**
 * Represents ans organazation seeking freelancers to complete tasks.
 */
public class Organization {

    /**
     * Name of the organization.
     */
    private String m_strName;
    /**
     * Responsible for making payments on unpaid transactions.
     */
    PaymentScheduler m_oScheduler;
    /**
     * A list transactions.
     */
    ListTransaction m_oListTransaction;
    /**
     * @return The list transactions the organization made.
     */
    public ListTransaction getListTransaction() {
        return m_oListTransaction;
    }

    private Manager manager;
    private Collaborator collaborator;

    public Organization(String name, Manager manager, Collaborator collaborator) {
        if ((name == null) || (manager == null) || (collaborator == null))
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
            this.m_strName = name;
            if(!validatesCollaborator(collaborator))
                throw new IllegalArgumentException("Collaborator is invalid");
            if(!validatesManager(manager))
                throw new IllegalArgumentException("Manager is invalid");
            this.setManager(manager);
            this.setCollaborator(collaborator);
    }

    public static Collaborator newCollaborator (String name, String email, String password) {
        return new Collaborator(name,email,password);
    }

    public static Manager newManager (String name, String email, String password) {
        return new Manager(name,email,password);
    }

    public static boolean validatesCollaborator(Collaborator collaborator) {
        //TODO: Validate collaborator.
        return true;
    }

    private void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    public static boolean validatesManager(Manager manager) {
        //TODO: Validate manager.
        return true;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}