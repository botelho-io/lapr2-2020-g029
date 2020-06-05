package lapr.controller;

import lapr.list.ListTransaction;
import lapr.model.*;

import java.time.LocalDate;
import java.util.List;

public class CreateTransactionController {
    App app;
    Organization org;
    ListTransaction ltr;
    Transaction tr;

    /**
     * Constructor.
     */
    public CreateTransactionController() {
        app = AppPOE.getInstance().getApp();
        String email = AppPOE.getInstance().getSessaoAtual().getEmailUtilizador();
        org = app.getRegistOrganization().getOrganizationByEmailUser(email);
    }

    /**
     * @return A list of the tasks in the organization of the current user that have not been executed.
     */
    public List<Task> getTasks() {
        return org.getListTask().getUnexecutedTasks();
    }

    /**
     * @return A list of the freelancers in the system.
     */
    public List<Freelancer> getFreelancers() {
        return app.getRegistFreelancer().getFreelancers();
    }

    /**
     * Creates a new transaction.
     * @param freelancer The freelancer who executed the task.
     * @param task The task that was executed.
     * @param endDate The date the task was executed.
     * @param daysDelay The days the task execution was delayed.
     * @param description A short description of the work.
     * @return True if the new transaction is valid, false otherwise.
     */
    public boolean newTransaction(Freelancer freelancer, Task task, LocalDate endDate, int daysDelay, String description) {
        ltr = org.getListTransaction();
        tr = ltr.newTransaction(freelancer, task, endDate, daysDelay, description);
        return ltr.validate(tr);
    }

    /**
     * Should be called only if newTransaction was successful.
     * @return True if the new task was added, false otherwise.
     */
    public boolean addTransaction() {
        if(tr == null) throw new IllegalStateException("Create Transaction Controller - Execution out of order");
        return ltr.addTransaction(tr);
    }

    /**
     * Should be called only if newTransaction was successful.
     * @return The amount in euros to pay for the transaction;
     */
    public double getAmount() {
        return tr.getAmount();
    }
}
