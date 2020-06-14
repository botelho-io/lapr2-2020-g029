package lapr.controller;

import lapr.lists.ListTransaction;
import lapr.model.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Class that is responsible for creating a new transaction and adding to tha transaction list of the organization.
 * @author André Botelho and Ricardo Moreira.
 */
public class CreateTransactionController {
    /**
     * Instance of the app.
     */
    App app;
    /**
     * Instance of Organization of the current user.
     */
    Organization org;
    /**
     * A list of transactions of current organization.
     */
    ListTransaction ltr;
    /**
     * Instance of transaction to be added to the list of transactions.
     */
    Transaction tr;

    /**
     * Constructor.
     */
    public CreateTransactionController() {
        app = AppPOE.getInstance().getApp();
        String email = AppPOE.getInstance().getApp().getAuthFacade().getCurrentSession().getEmailUser();
        org = app.getRegistOrganization().getOrganizationByEmailUser(email);
    }

    /**
     * Returns a list of the tasks in the organization of the current user that have not been executed.
     * @return  list of the tasks in the organization of the current user that have not been executed.
     */
    public List<Task> getTasks() {
        return org.getListTask().getUnexecutedTasks();
    }

    /**
     * Returns a list of the freelancers in the system.
     * @return  list of the freelancers in the system.
     */
    public Collection<Freelancer> getFreelancers() {
        return app.getRegistFreelancer().getFreelancers();
    }

    /**
     * Creates a new transaction.
     * @param id The ID of the transaction.
     * @param freelancer The freelancer who executed the task.
     * @param task The task that was executed.
     * @param endDate The date the task was executed.
     * @param hoursDelay The days the task execution was delayed.
     * @param description A short description of the work.
     * @return True if the new transaction is valid, false otherwise.
     */
    public boolean newTransaction(String id, Freelancer freelancer, Task task, LocalDate endDate, double hoursDelay, String description) {
        ltr = org.getListTransaction();
        tr = ListTransaction.newTransaction(id, freelancer, task, endDate, hoursDelay, description);
        return ltr.validate(tr);
    }

    /**
     * Should be called only if newTransaction was successful.
     * @return True if the new task was added, false otherwise.
     */
    public boolean addTransaction() {
        if(tr == null) throw new IllegalStateException("Create Transaction Controller - Execution out of order");
        return ltr.registerTransaction(tr);
    }

    /**
     * Should be called only if newTransaction was successful.
     * @return The amount in euros to pay for the transaction;
     */
    public double getAmount() {
        return tr.getAmount();
    }
}
