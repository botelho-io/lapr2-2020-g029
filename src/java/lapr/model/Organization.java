/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import autorizacao.AutorizacaoFacade;
import lapr.list.ListTransaction;
import lapr.list.ListTask;

import java.util.HashSet;
import java.util.Set;

import java.time.LocalTime;

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
     * Creates a new payment scheduler.
     * @param DayMonth The day of the month the payment are to be made.
     * @param TimeOfDay The time of day to make the payments.
     * @return The new payment scheduler
     */
    public PaymentScheduler newPaymentScheduler(int DayMonth, LocalTime TimeOfDay) {
        if(m_oScheduler == null)
            return new PaymentScheduler(DayMonth, TimeOfDay, this);
        else {
            m_oScheduler.resetTime(DayMonth, TimeOfDay);
            return m_oScheduler;
        }
    }
    /**
     * @return The list transactions the organization made.
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
     * @return The manager of the organization.
     */
    public Manager getManager() {
        return m_oManager;
    }

    /**
     * @return The collaborator of the organization.
     */
    public Collaborator getCollaborator() {
        return m_oCollaborator;
    }

    /**
     * An AutorizacaoFacade instance.
     */
    private final AutorizacaoFacade m_oAutorizacao = null;

    /**
     * Setting task list.
     */
    private Set<Task> m_lstTarefas = new HashSet<Task>();

    /**
     * Task list of the organization.
     */
    private ListTask m_oListTask;

    /**
     * @return Task list of the organization.
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
     */
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
            this.m_oListTransaction = new ListTransaction();
            this.m_oListTask = new ListTask();
    }
    /**
     * Build a new instance of collaborator receiving the name, email and password.
     *
     * @param name of the collaborator.
     * @param email of the collaborator.
     * @param password of the collaborator.
     */
    public static Collaborator newCollaborator (String name, String email, String password) {
        return new Collaborator(name,email,password);
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
     * @param collaborator of the organization.
     * @return true if valid.
     */
    public static boolean validatesCollaborator(Collaborator collaborator) {
        //boolean bRet = true;

        //if (this.m_oAutorizacao.existeUtilizador(collaborator.getEmail()))
          //  bRet = false;
        return true;
    }

    /**
     * Modifies collaborator of the organization.
     *
     * @param collaborator of the organization.
     */
    private void setCollaborator(Collaborator collaborator) {
        this.m_oCollaborator = collaborator;
    }

    /**
     * Validates manager of the organization.
     *
     * @param manager of the organization.
     * @return true if valid.
     */
    public static boolean validatesManager(Manager manager) {
        //boolean bRet = true;

        //if (this.m_oAutorizacaom_oAutorizacao.existeUtilizador(manager.getEmail()))
          //  bRet = false;

        return true;
    }

    /**
     * Modifies manager of the organization.
     *
     * @param manager of the organization.
     */
    public void setManager(Manager manager) {
        this.m_oManager = manager;
    }
}