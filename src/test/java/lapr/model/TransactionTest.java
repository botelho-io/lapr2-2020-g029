package lapr.model;

import lapr.api.PaymentAPI;
import lapr.controller.AppPOE;
import lapr.list.ListTask;
import lapr.list.ListTransaction;
import lapr.utils.Expertise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    Freelancer fre;
    Task tsk1;
    Task tsk2;
    Organization org;
    Transaction trs;

    @BeforeEach
    void setUp() throws IOException {
        tsk1 = ListTask.newTask("TSK1", "A Test task 1", 10, 10, "TEST");
        tsk2 = ListTask.newTask("TSK2", "A Test task 2", 10, 10, "TEST");
        fre = new Freelancer("FJ1", "Free Joe1", Expertise.JUNIOR, "fre1@dei.pt", "28739247891", "8937431", "Address1", "Germany");
        AppPOE.restartInstance();
        App app = AppPOE.getInstance().getApp();
        app.setAPIs(app.getEmailAPI(), app.getMonetaryConversionAPI(), new PaymentAPI() {
            @Override
            public boolean payTo(String freelancerId, String freelancerIBAN, String taskId, String taskDescription, Double eur, Double nativeCurrency) {
                return true;
            }
            @Override
            public void close() throws IOException {
            }
        }, app.getPswGeneratorAPI());
        Manager testMan = new Manager("Man Joe", "man@dei.pt", "password");
        Collaborator testCol = new Collaborator("Colab Joe", "colab@dei.pt", "password");
        org = app.getRegistOrganization().newOrganization("123", "DEFAULT", testMan, testCol);
        org.getListTask().registTask(tsk1);
        org.getListTask().registTask(tsk2);
        trs = ListTransaction.newTransaction("ID1", fre, tsk1, LocalDate.of(2020, 4, 3), 1.5, "...");
        org.getListTransaction().addTransaction(trs);
    }

    @Test
    void newTaskExecutionDetails() {
        TaskExecutionDetails expected = new TaskExecutionDetails(LocalDate.of(2020, 3, 3), 1.5, "Cool");
        TaskExecutionDetails actual = Transaction.newTaskExecutionDetails(LocalDate.of(2020, 3, 3), 1.5, "Cool");
        assertEquals(expected, actual);
    }

    @Test
    void newPaymentDetails() {
        PaymentDetails expected = new PaymentDetails();
        PaymentDetails actual = Transaction.newPaymentDetails(false);
        assertEquals(expected, actual);
    }

    @Test
    void makeBankTransfer() {
        assertTrue(trs.makeBankTransfer());
    }

    @Test
    void getAmount() {
        assertEquals(trs.getAmount(), 100.00);
    }

    @Test
    void getNativeAmount() {
        assertEquals(trs.getNativeAmount(), 100.00);
    }

    @Test
    void getFreelancer() {
        assertEquals(trs.getFreelancer(), fre);
    }

    @Test
    void getTask() {
        assertEquals(trs.getTask(), tsk1);
        assertNotEquals(trs.getTask(), tsk2);
    }

    @Test
    void getPaymentDetails() {
        PaymentDetails expected = new PaymentDetails();
        assertEquals(trs.getPaymentDetails(), expected);
        trs.makeBankTransfer();
        expected.setPayed(true);
        assertEquals(trs.getPaymentDetails(), expected);
    }

    @Test
    void getExecutionDetails() {
        TaskExecutionDetails expected = new TaskExecutionDetails(LocalDate.of(2020, 4, 3), 1.5, "...");
        assertEquals(trs.getExecutionDetails(), expected);
    }

    @Test
    void madeToAny() {
        Set<Freelancer> set = new HashSet<>();
        set.add(new Freelancer("FJ2", "Free Joe2", Expertise.JUNIOR, "fre2@dei.pt", "28739247892", "8937432", "Address2", "Germany"));
        set.add(new Freelancer("FJ3", "Free Joe3", Expertise.JUNIOR, "fre3@dei.pt", "28739247893", "8937433", "Address3", "Germany"));
        assertFalse(trs.madeToAny(set));
        set.add(fre);
        assertTrue(trs.madeToAny(set));
    }
}