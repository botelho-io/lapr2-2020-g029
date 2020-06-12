package lapr.regist;

import lapr.controller.AppPOE;
import lapr.list.ListTask;
import lapr.list.ListTransaction;
import lapr.model.*;
import lapr.utils.Expertise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegistOrganizationTest {

    Freelancer[] testFreelancers;
    Administrator testAdmin;
    Manager testMan;
    Collaborator testCol;
    Organization testOrg;
    Task[] testTasks;
    Transaction[] testTransactions;

    @BeforeEach
    void setUp() throws IOException {
        AppPOE.restartInstance();
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
                ListTransaction.newTransaction("ID1", testFreelancers[0], testTasks[3], LocalDate.of(2021, 1, 5), 0, "Good Job :)"),
                ListTransaction.newTransaction("ID2", testFreelancers[1], testTasks[4], LocalDate.of(2021, 2, 4), 1, "Good Job :)"),
                ListTransaction.newTransaction("ID3", testFreelancers[2], testTasks[5], LocalDate.of(2021, 3, 3), 9, "Good Job :)"),
                ListTransaction.newTransaction("ID4", testFreelancers[2], testTasks[6], LocalDate.of(2020, 4, 2), 0, "Good Job :)"),
                ListTransaction.newTransaction("ID5", testFreelancers[3], testTasks[7], LocalDate.of(2020, 5, 1), 3, "Good Job :)"),
                ListTransaction.newTransaction("ID6", testFreelancers[3], testTasks[8], LocalDate.of(2020, 6, 9), 4, "Good Job :)"),
                ListTransaction.newTransaction("ID7", testFreelancers[3], testTasks[9], LocalDate.of(2020, 7, 8), 4, "Good Job :)")
        };
        for (Transaction t : testTransactions) {
            testOrg.getListTransaction().addTransaction(t);
        }
    }

    @Test
    void add() {
        RegistOrganization ro = AppPOE.getInstance().getApp().getRegistOrganization();
        assertTrue(ro.add(testOrg));
        try {
            ro.add(testOrg);
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void validateOrganization() {
        RegistOrganization ro = AppPOE.getInstance().getApp().getRegistOrganization();
        assertTrue(ro.validateOrganization(testOrg));
        assertTrue(ro.add(testOrg));
        assertFalse(ro.validateOrganization(testOrg));
    }

    @Test
    void getOrganizationByEmailUser() {
        RegistOrganization ro = AppPOE.getInstance().getApp().getRegistOrganization();
        ro.add(testOrg);
        assertNull(ro.getOrganizationByEmailUser(testAdmin.getEmail()));
        assertEquals(ro.getOrganizationByEmailUser(testMan.getEmail()), testOrg);
        assertEquals(ro.getOrganizationByEmailUser(testCol.getEmail()), testOrg);
    }

    @Test
    void getGroupedTransactions() {
        RegistOrganization ro = AppPOE.getInstance().getApp().getRegistOrganization();
        ro.add(testOrg);
        assertEquals(ro.getGroupedTransactions().keySet().size(), 4);
    }

    @Test
    void getGroupedTransactionsInYear() {
        RegistOrganization ro = AppPOE.getInstance().getApp().getRegistOrganization();
        ro.add(testOrg);
        assertEquals(ro.getGroupedTransactionsInYear(2021).keySet().size(), 3);
        assertEquals(ro.getGroupedTransactionsInYear(2020).keySet().size(), 2);
    }

    @Test
    void getTransactionsOfFreelancers() {
        RegistOrganization ro = AppPOE.getInstance().getApp().getRegistOrganization();
        ro.add(testOrg);
        Set<Freelancer> fres = new HashSet<>();

        fres.add(testFreelancers[0]);
        Collection<Transaction> tr = ro.getTransactionsOfFreelancers(fres);
        assertEquals(tr.size(), 1);

        fres.add(testFreelancers[1]);
        tr = ro.getTransactionsOfFreelancers(fres);
        assertEquals(tr.size(), 2);

        fres.add(testFreelancers[2]);
        tr = ro.getTransactionsOfFreelancers(fres);
        assertEquals(tr.size(), 4);

        fres.add(testFreelancers[3]);
        tr = ro.getTransactionsOfFreelancers(fres);
        assertEquals(tr.size(), 7);

        fres.add(null);
        tr = ro.getTransactionsOfFreelancers(fres);
        assertEquals(tr.size(), 7);
    }
}