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

    public CreateTransactionController() {
        app = AppPOE.getInstance().getApp();
        String email = AppPOE.getInstance().getSessaoAtual().getEmailUtilizador();
        org = app.getRegistOrganization().getOrganizationByEmailUser(email);
    }

    public List<Task> getTasks() {
        return org.getListTask().getUnexecutedTasks();
    }

    public List<Freelancer> getFreelancers() {
        return app.getRegistFreelancer().getFreelancers();
    }

    public boolean newTransaction(Freelancer freelancer, Task task, LocalDate endDate, int daysDelay, String description) {
        ltr = org.getListTransaction();
        tr = ltr.newTransaction(freelancer, task, endDate, daysDelay, description);
        return ltr.validate(tr);
    }

    public boolean addTransaction() {
        if(tr == null) return false;
        return ltr.addTransaction(tr);
    }

    public double getAmount() {
        return tr.getAmount();
    }
}
