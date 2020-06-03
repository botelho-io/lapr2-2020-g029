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

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ListTransactionTest{

    ListTransaction lt;
    RegistFreelancer rfre;
    PaymentDetails pd;
    Transaction trs;
    Freelancer fre;
    Task tsk;

    @BeforeEach
    void setUp() {
        AppPOE.restartInstance();
        App app = AppPOE.getInstance().getApp();
        Manager m = Organization.newManager("manager", "manager@mail.com", "password");
        Collaborator c = Organization.newCollaborator("colab", "colab@mail.com", "password");
        Organization org = app.getRegistOrganization().newOrganization("Org", m, c);
        RegistOrganization rorg = app.getRegistOrganization();
        rorg.add(org);
        rfre = app.getRegistFreelancer();
        fre = rfre.newFreelancer("André Botelho", Expertise.SENIOR.name(), "fre@mail.com", "7238648762", "6248736", "dshfsk", "Portugal");
        rfre.addFreelancer(fre);
        tsk = Transaction.newTask("id", "desc", 10, 10, "Example");
        lt = org.getListTransaction();
        pd = Transaction.newPaymentDetails(false);
        trs = ListTransaction.newTransaction(fre, tsk, pd);
        lt.add(trs);
        tsk = Transaction.newTask("id2", "desc2", 100, 5, "Example");
        pd = Transaction.newPaymentDetails(false);
        trs = ListTransaction.newTransaction(fre, tsk, pd);
        lt.add(trs);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void newTransaction() {
        Transaction expected = trs;
        Transaction result = ListTransaction.newTransaction(fre, tsk, pd);
        assertEquals(expected, result);
    }

    @Test
    void remove() {
        assertTrue(lt.remove(trs));
    }

    @Test
    void add() {
        tsk = Transaction.newTask("id3", "desc3", 100, 5, "Example");
        pd = Transaction.newPaymentDetails(true);
        trs = ListTransaction.newTransaction(fre, tsk, pd);
        assertTrue(lt.add(trs));
    }
}