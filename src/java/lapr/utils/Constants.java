package lapr.utils;

import autorizacao.model.UserRole;
import lapr.controller.AppPOE;
import lapr.list.ListTask;
import lapr.list.ListTransaction;
import lapr.model.*;
import lapr.regist.RegistFreelancer;

import java.time.LocalDate;

public abstract class Constants {
    public static final String PATH_PARAMS = "config.properties";

    private static final Freelancer[] testFreelancers;
    private static final Administrator testAdmin;
    private static final Manager testMan;
    private static final Collaborator testCol;
    private static final Organization testOrg;
    private static final Task[] testTasks;
    private static final Transaction[] testTransactions;

    static {
        final AppPOE poe = AppPOE.getInstance();
        final App app = poe.getApp();
        final RegistFreelancer rf = app.getRegistFreelancer();
        // Add freelancers
        testFreelancers = new Freelancer[]{
                rf.newFreelancer("Free Joe1", Expertise.SENIOR, "fre1@dei.pt", "28739247891", "8937431", "Address1", "Germany"),
                rf.newFreelancer("Free Joe2", Expertise.JUNIOR, "fre2@dei.pt", "28739247892", "8937432", "Address2", "Germany"),
                rf.newFreelancer("Free Joe3", Expertise.SENIOR, "fre3@dei.pt", "28739247893", "8937433", "Address3", "Germany"),
                rf.newFreelancer("Free Joe4", Expertise.JUNIOR, "fre4@dei.pt", "28739247894", "8937434", "Address4", "Germany")
        };

        // Add organization
        testAdmin = new Administrator("Admin Joe", "admin@dei.pt", "password");
        testMan = new Manager("Man Joe", "man@dei.pt", "password");
        testCol = new Collaborator("Colab Joe", "colab@dei.pt", "password");
        testOrg = app.getRegistOrganization().newOrganization("DEFAULT", testMan, testCol);

        // Add tasks
        testTasks = new Task[]{
                ListTask.newTask("TSK1", "A Test task 1", 10, 10, "TEST"),
                ListTask.newTask("TSK2", "A Test task 2", 10, 10, "TEST"),
                ListTask.newTask("TSK3", "A Test task 3", 10, 10, "TEST"),
                ListTask.newTask("TSK4", "A Test task 4", 10, 10, "TEST"),
                ListTask.newTask("TSK5", "A Test task 5", 10, 10, "TEST"),
                ListTask.newTask("TSK6", "A Test task 6", 10, 10, "TEST"),
                ListTask.newTask("TSK7", "A Test task 7", 10, 10, "TEST"),
                ListTask.newTask("TSK8", "A Test task 8", 10, 10, "TEST"),
                ListTask.newTask("TSK9", "A Test task 9", 10, 10, "TEST"),
                ListTask.newTask("TSK10", "A Test task 10", 1, 1, "TEST")
        };

        // Add transactions
        testTransactions = new Transaction[]{
                ListTransaction.newTransaction(testFreelancers[0], testTasks[3], LocalDate.now(), 0, "Good Job :)"),
                ListTransaction.newTransaction(testFreelancers[1], testTasks[4], LocalDate.now(), 1, "Good Job :)"),
                ListTransaction.newTransaction(testFreelancers[2], testTasks[5], LocalDate.now(), 9, "Good Job :)"),
                ListTransaction.newTransaction(testFreelancers[2], testTasks[6], LocalDate.now(), 0, "Good Job :)"),
                ListTransaction.newTransaction(testFreelancers[3], testTasks[7], LocalDate.now(), 3, "Good Job :)"),
                ListTransaction.newTransaction(testFreelancers[3], testTasks[8], LocalDate.now(), 4, "Good Job :)"),
                ListTransaction.newTransaction(testFreelancers[3], testTasks[9], LocalDate.now(), 4, "Good Job :)")
        };
    }

    public static void addTestOrganizationAndUsers() {
        AppPOE.getInstance().getAuthFacade().registUser(testAdmin);
        AppPOE.getInstance().getApp().getRegistOrganization().add(testOrg);
    }

    public static void addTestTasksAndOrg() {
        addTestOrganizationAndUsers();
        final ListTask lt = testOrg.getListTask();
        for(final Task t : testTasks)
            lt.registTask(t);
    }

    public static void addTestFreelancers() {
        final AppPOE poe = AppPOE.getInstance();
        final App app = poe.getApp();
        final RegistFreelancer rf = app.getRegistFreelancer();
        for(final Freelancer f : testFreelancers)
            rf.addFreelancer(f);
    }

    public static void addTestOrgTasksFreelancersAndTransactions() {
        addTestTasksAndOrg();
        addTestFreelancers();
        final ListTransaction lt = testOrg.getListTransaction();
        for (final Transaction t : testTransactions)
            lt.addTransaction(t);
    }
}
