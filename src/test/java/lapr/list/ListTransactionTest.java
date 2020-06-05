package lapr.list;

import lapr.controller.AppPOE;
import lapr.controller.MakePaymentTask;
import lapr.model.*;
import lapr.regist.RegistFreelancer;
import lapr.regist.RegistOrganization;
import lapr.utils.Expertise;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ListTransactionTest{

    ListTransaction lt;
    RegistFreelancer rfre;
    Transaction trs1;
    Transaction trs2;
    Transaction trs3;
    Task tsk1;
    Freelancer fre;

    @BeforeEach
    void setUp() {
        AppPOE.restartInstance();
        App app = AppPOE.getInstance().getApp();
        Manager m = Organization.newManager("manager", "manager@mail.com", "password");
        Collaborator c = Organization.newCollaborator("colab", "colab@mail.com", "password");
        Organization org = app.getRegistOrganization().newOrganization("Org", m, c);
        //RegistOrganization rorg = app.getRegistOrganization();
        //rorg.add(org);
        rfre = app.getRegistFreelancer();
        tsk1 = ListTask.newTask("id1", "desc1", 10, 10, "Example");
        Task tsk2 = ListTask.newTask("id2", "desc2", 100, 5, "Example");
        lt = org.getListTransaction();
        fre = rfre.newFreelancer("André Botelho", Expertise.SENIOR.name(), "fre@mail.com", "7238648762", "6248736", "dshfsk", "Portugal");
        trs1 = ListTransaction.newTransaction(fre, tsk1, LocalDate.now(), 1, "Good Job!");
        trs2 = ListTransaction.newTransaction(fre, tsk1, LocalDate.now(), 2, "Bad Job!");
        trs3 = ListTransaction.newTransaction(fre, tsk2, LocalDate.now(), 2, "Bad Job!");
        lt.addTransaction(trs1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void newTransaction() {
        Transaction expected = trs1;
        Transaction result = ListTransaction.newTransaction(fre, tsk1, LocalDate.now(), 1, "Good Job!");
        assertEquals(expected, result);
    }

    @Test
    void remove() {
        assertTrue(lt.remove(trs1));
        assertFalse(lt.remove(trs2));
    }

    @Test
    void add() {
        assertFalse(lt.addTransaction(trs2)); // same id as trs1
        assertTrue(lt.addTransaction(trs3)); // unique id
        Task tsk = ListTask.newTask("id2", "desc4 :)", 10, 2, "Example2");
        Transaction trs4 = ListTransaction.newTransaction(fre, tsk, LocalDate.now(), 1, "Good Job!");
        assertFalse(lt.addTransaction(trs3)); // Same ID as trs3
    }

    @Test
    void Equals() {
        assertEquals(trs1, trs2);
        assertNotEquals(trs1, trs3);
    }
}