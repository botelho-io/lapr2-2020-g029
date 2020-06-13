package lapr.utils;

import lapr.controller.AppPOE;
import lapr.lists.ListTask;
import lapr.lists.ListTransaction;
import lapr.model.*;
import lapr.lists.RegistFreelancer;

import java.time.LocalDate;

public abstract class TestConstants {
    public static final Freelancer[] testFreelancers;
    public static final Administrator testAdmin;
    public static final Manager testMan;
    public static final Collaborator testCol;
    public static final Organization testOrg;
    public static final Task[] testTasks;
    public static final Transaction[] testTransactions;
    static {
        final AppPOE poe = AppPOE.getInstance();
        final App app = poe.getApp();
        final RegistFreelancer rf = app.getRegistFreelancer();
        // Add freelancers
        testFreelancers = new Freelancer[]{
                new Freelancer("FJ1", "Free Joe1", Expertise.SENIOR, "fre1@dei.pt", "28739247891", "8937431", "Address1", "Germany"),
                new Freelancer("FJ2", "Free Joe2", Expertise.JUNIOR, "fre2@dei.pt", "28739247892", "8937432", "Address2", "Germany"),
                new Freelancer("FJ3", "Free Joe3", Expertise.SENIOR, "fre3@dei.pt", "28739247893", "8937433", "Address3", "Germany"),
                new Freelancer("FJ4", "Free Joe4", Expertise.JUNIOR, "fre4@dei.pt", "28739247894", "8937434", "Address4", "Germany")
        };

        // Add organization
        testAdmin = new Administrator("Admin Joe", "admin@dei.pt", "password");
        testMan = new Manager("Man Joe", "man@dei.pt", "password");
        testCol = new Collaborator("Colab Joe", "colab@dei.pt", "password");
        testOrg = app.getRegistOrganization().newOrganization("123", "DEFAULT", testMan, testCol);

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
                ListTransaction.newTransaction("TR1", testFreelancers[0], testTasks[3], LocalDate.now(), 0, "Good Job :)"),
                ListTransaction.newTransaction("TR2", testFreelancers[1], testTasks[4], LocalDate.now(), 1, "Good Job :)"),
                ListTransaction.newTransaction("TR3", testFreelancers[2], testTasks[5], LocalDate.now(), 9, "Good Job :)"),
                ListTransaction.newTransaction("TR4", testFreelancers[2], testTasks[6], LocalDate.now(), 0, "Good Job :)"),
                ListTransaction.newTransaction("TR5", testFreelancers[3], testTasks[7], LocalDate.now(), 3, "Good Job :)"),
                ListTransaction.newTransaction("TR6", testFreelancers[3], testTasks[8], LocalDate.now(), 4, "Good Job :)"),
                ListTransaction.newTransaction("TR7", testFreelancers[3], testTasks[9], LocalDate.now(), 4, "Good Job :)")
        };
    }

    public static void addTestOrganizationAndUsers() {
        AppPOE.getInstance().getApp().getAuthFacade().registerUser(testAdmin);
        AppPOE.getInstance().getApp().getRegistOrganization().registerOrganization(testOrg);
    }

    public static void addTestTasksAndOrg() {
        addTestOrganizationAndUsers();
        final ListTask lt = testOrg.getListTask();
        for(final Task t : testTasks)
            lt.registerTask(t);
    }

    public static void addTestFreelancers() {
        final AppPOE poe = AppPOE.getInstance();
        final App app = poe.getApp();
        final RegistFreelancer rf = app.getRegistFreelancer();
        for(final Freelancer f : testFreelancers)
            rf.registerFreelancer(f);
    }

    public static void addTestOrgTasksFreelancersAndTransactions() {
        addTestTasksAndOrg();
        addTestFreelancers();
        final ListTransaction lt = testOrg.getListTransaction();
        for (final Transaction t : testTransactions)
            lt.registerTransaction(t);
    }
}
