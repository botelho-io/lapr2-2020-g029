package lapr.controller;

import lapr.list.ListTask;
import lapr.list.ListTransaction;
import lapr.model.*;
import lapr.regist.RegistFreelancer;
import lapr.regist.RegistOrganization;
import lapr.utils.Expertise;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class MakePaymentTaskTest {

    MakePaymentTask pt;
    ListTransaction lt;

    @BeforeEach
    void setUp() throws IOException {
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
        Task tsk = ListTask.newTask("id", "desc", 10, 10, "Example");
        lt = org.getListTransaction();
        Transaction trs = ListTransaction.newTransaction(fre, tsk, LocalDate.now(), 1, "Good Job!");
        lt.addTransaction(trs);
        tsk = ListTask.newTask("id2", "desc2", 100, 5, "Example");
        trs = ListTransaction.newTransaction(fre, tsk, LocalDate.now(), 3650, "A little late...");
        lt.addTransaction(trs);
        //PaymentScheduler ps = org.newPaymentScheduler(40, LocalTime.now());
        //pt = new MakePaymentTask(org, ps);
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