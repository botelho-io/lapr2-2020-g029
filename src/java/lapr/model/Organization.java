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
     * A list of unpaid transactions.
     */
    ListTransaction m_oListTransaction;

    /**
     * @return The list of unpaid transactions the organization needs to pay.
     */
    public ListTransaction getListTransaction() {
        return m_oListTransaction;
    }

    /**
     * An manager of the organization.
     */
    private Manager manager;

    /**
     * A collaborator of the organization.
     */
    private Colaborator colaborator;

    /**
     * Build an instance of organization receiving the name, manager and collaborator.
     *
     * @param name of the colaborator.
     * @param manager of the organization
     * @param colaborator  of the organization.
     */
    public Organization(String name, Manager manager, Colaborator colaborator) {
        if ((name == null) || (manager == null) || (colaborator == null))
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
            this.m_strName = name;
            if(!validatesColaborator(colaborator))
                throw new IllegalArgumentException("Colaborator is invalid");
            if(!validatesManager(manager))
                throw new IllegalArgumentException("Manager is invalid");
            this.setManager(manager);
            this.setColaborator(colaborator);
    }

    /**
     * Build a new instance of colaborator receiving the name, email and password.
     *
     * @param name of the colaborator.
     * @param email of the colaborator.
     * @param password of the colaborator.
     */
    public static Colaborator newColaborator (String name, String email, String password) {
        return new Colaborator(name,email,password);
    }

    /**
     * Build a new instance of manager receiving the name, email and password.
     *
     * @param name of the manager.
     * @param email of the manager.
     * @param password of the manager.
     */
    public static Manager newManager (String name, String email, String password) {
        return new Manager(name,email,password);
    }

    /**
     * Validates collaborator of the organization.
     *
     * @param colaborator of the organization.
     * @return true if valid.
     */
    public static boolean validatesColaborator(Colaborator colaborator) {
        //TODO: Validate colaborator.
        return true;
    }

    /**
     * Modifies collaborator of the organization.
     *
     * @param colaborator of the organization.
     */
    private void setColaborator(Colaborator colaborator) {
        this.colaborator = colaborator;
    }

    /**
     * Validates manager of the organization.
     *
     * @param manager of the organization.
     * @return true if valid.
     */
    public static boolean validatesManager(Manager manager) {
        //TODO: Validate manager.
        return true;
    }

    /**
     * Modifies manager of the organization.
     *
     * @param manager of the organization.
     */
    public void setManager(Manager manager) {
        this.manager = manager;
    }
}