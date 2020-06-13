/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import authorization.AuthFacade;
import lapr.controller.AppPOE;
import lapr.lists.ListTask;
import lapr.lists.ListTransaction;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import java.time.LocalTime;

/**
 * Represents ans organazation seeking freelancers to complete tasks.
 */
public class Organization implements Serializable {

    /**
     * The International Bank Account Number of the organization.
     */
    private final String m_strIban;
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
     * Sets a new schedule for making payments.
     * @param dayMonth The day of the month the payment are to be made.
     * @param timeOfDay The time of day to make the payments.
     * @return True if the schedule is valid, false otherwise.
     */
    public boolean setPaymentScheduler(int dayMonth, LocalTime timeOfDay) {
        if(!validatesPaymentScheduler(dayMonth, timeOfDay))
            return false;
        else {
            if (m_oScheduler == null)
                m_oScheduler = new PaymentScheduler(dayMonth, timeOfDay, this);
            else
                m_oScheduler.resetTime(dayMonth, timeOfDay);
            return true;
        }
    }


    /**
     * Returns the list transactions the organization made.
     * @return he list transactions the organization made.
     */
    public ListTransaction getListTransaction() {
        return m_oListTransaction;
    }

    /**
     * The manager of the organization.
     */
    private Manager m_oManager;

    /**
     * The collaborator of the organization.
     */
    private Collaborator m_oCollaborator;

    /**
     * Returns the manager of the organization.
     * @return he manager of the organization.
     */
    public Manager getManager() {
        return m_oManager;
    }

    /**
     * Returns the collaborator of the organization.
     * @return he collaborator of the organization.
     */
    public Collaborator getCollaborator() {
        return m_oCollaborator;
    }

    /**
     * An AutorizacaoFacade instance.
     */
    private final AuthFacade m_oAutorizacao = null;

    /**
     * Setting task list.
     */
    private Set<Task> m_lstTarefas = new HashSet<Task>();

    /**
     * Task list of the organization.
     */
    private ListTask m_oListTask;

    /**
     * Returns task list of the organization.
     * @return ask list of the organization.
     */
    public ListTask getListTask() {
        return m_oListTask;
    }

    /**
     * Build an instance of organization receiving the name, manager and collaborator.
     *
     * @param name of the collaborator.
     * @param manager of the organization
     * @param collaborator  of the organization.
     * @param iban The IBAN of the organization.
     */
    public Organization(String iban, String name, Manager manager, Collaborator collaborator) {
        if ((name == null) || (manager == null) || (collaborator == null) || (iban == null))
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        this.m_strName = name;
        this.m_strIban = iban;
        if(!validatesCollaborator(collaborator))
            throw new IllegalStateException("Organization - Collaborator is not valid because it already exists.");
        if(!validatesManager(manager))
            throw new IllegalStateException("Organization - Manager is not valid because it already exists.");
        if(!this.setManager(manager))
            throw new IllegalStateException("Organization - Manager cannot be added into the system because it already exists.");
        if(!this.setCollaborator(collaborator))
            throw new IllegalStateException("Organization - Collaborator cannot be added into the system because it already exists.");
        this.m_oListTransaction = new ListTransaction();
        this.m_oListTask = new ListTask();
    }
    /**
     * Build a new instance of collaborator receiving the name, email and password.
     * @param name of the collaborator.
     * @param email of the collaborator.
     * @param password of the collaborator.
     * @return The new collaborator.
     */
    public static Collaborator newCollaborator (String name, String email, String password) {
        return new Collaborator(name,email,password);
    }

    /**
     * Build a new instance of manager receiving the name, email and password.
     * @param name of the manager.
     * @param email of the manager.
     * @param password of the manager.
     * @return The new manager.
     */
    public static Manager newManager (String name, String email, String password) {
        return new Manager(name,email,password);
    }

    /**
     * Validates collaborator of the organization.
     *
     * @param collaborator of the organization.
     * @return true if valid.
     */
    public static boolean validatesCollaborator(Collaborator collaborator) {
        return !AppPOE.getInstance().getApp().getAuthFacade().hasUser(collaborator);
    }

    /**
     * Modifies collaborator of the organization.
     * @param collaborator of the organization.
     * @return True if the collaborator was set, false otherwise.
     */
    private boolean setCollaborator(Collaborator collaborator) {
        if(m_oCollaborator != null || !validatesCollaborator(collaborator)) return false;
        this.m_oCollaborator = collaborator;
        return true;
    }

    /**
     * Validates manager of the organization.
     *
     * @param manager of the organization.
     * @return true if valid.
     */
    public static boolean validatesManager(Manager manager) {
        return !AppPOE.getInstance().getApp().getAuthFacade().hasUser(manager);
    }

    /**
     * Modifies manager of the organization.
     * @param manager of the organization.
     * @return True iff the manager was set, false otherwise.
     */
    public boolean setManager(Manager manager) {
        if(m_oManager != null || !validatesManager(manager)) return false;
        this.m_oManager = manager;
        return true;
    }

    /**
     * Checks if an organization is able to be added into the system
     * @return True if the organization can be added into the system; False otherwise.
     */
    public boolean validateOrganization() {
        return  validatesCollaborator(getCollaborator()) &&
                validatesManager(getManager());
    }


    /**
     * Validates the schedule
     * @param dayMonth The day of the month the payment are to be made.
     * @param timeOfDay The time of day to make the payments.
     * @return True if the schedule is valid, false otherwise.
     */
    public static boolean validatesPaymentScheduler(int dayMonth, LocalTime timeOfDay) {
        return dayMonth >= 1 && dayMonth <= 28;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return m_strIban.equals(that.m_strIban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_strIban);
    }
}