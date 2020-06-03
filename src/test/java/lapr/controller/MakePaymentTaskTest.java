package lapr.controller;

import lapr.list.ListTransaction;
import lapr.model.*;
import lapr.regist.RegistFreelancer;
import lapr.regist.RegistOrganization;
import lapr.utils.Expertise;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MakePaymentTaskTest {

    MakePaymentTask pt;
    ListTransaction lt;

    @BeforeEach
    void setUp() {
        AppPOE.restartInstance();
        App app = AppPOE.getInstance().getApp();
        Manager m = Organization.newManager("manager", "manager@mail.com", "password");
        Collaborator c = Organization.newCollaborator("colab", "colab@mail.com", "password");
        Organization org = app.getRegistOrganization().newOrganization("Org", m, c);
        RegistOrganization rorg = app.getRegistOrganization();
        rorg.add(org);
        RegistFreelancer rfre = app.getRegistFreelancer();
        Freelancer fre = rfre.newFreelancer("André Botelho", Expertise.SENIOR.name(), "fre@mail.com", "7238648762", "6248736", "dshfsk", "Portugal");
        rfre.addFreelancer(fre);
        Task tsk = Transaction.newTask("id", "desc", 10, 10, "Example");
        lt = org.getListTransaction();
        PaymentDetails pd = Transaction.newPaymentDetails(false);
        Transaction trs = ListTransaction.newTransaction(fre, tsk, pd);
        lt.add(trs);
        tsk = Transaction.newTask("id2", "desc2", 100, 5, "Example");
        pd = Transaction.newPaymentDetails(false);
        trs = ListTransaction.newTransaction(fre, tsk, pd);
        lt.add(trs);
        PaymentScheduler ps = org.newPaymentScheduler(40, LocalTime.now());
        pt = new MakePaymentTask(org, ps);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void run() {
        pt.run();
        for(Transaction t : lt) {
            assertTrue(t.getPaymentDetails().isPayed());
        }
    }
}