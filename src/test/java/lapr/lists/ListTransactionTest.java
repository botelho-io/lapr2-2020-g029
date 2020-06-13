package lapr.lists;

import lapr.api.EmailAPI;
import lapr.controller.AppPOE;
import lapr.model.*;
import lapr.utils.Expertise;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListTransactionTest{

    ListTransaction lt;
    RegistFreelancer rfre;
    Transaction trs1; // in lt
    Transaction trs2; // same as trs1
    Transaction trs3;
    Task tsk1;
    Freelancer fre1; // of trs1 and trs2
    Freelancer fre3; // of trs3

    @BeforeEach
    void setUp() throws IOException {
        AppPOE.restartInstance();
        App app = AppPOE.getInstance().getApp();
        Manager m = Organization.newManager("manager", "manager@mail.com", "password");
        Collaborator c = Organization.newCollaborator("colab", "colab@mail.com", "password");
        Organization org = app.getRegistOrganization().newOrganization("123", "Org", m, c);
        //RegistOrganization rorg = app.getRegistOrganization();
        //rorg.add(org);
        rfre = app.getRegistFreelancer();
        tsk1 = ListTask.newTask("id1", "desc1", 10, 10, "Example");
        Task tsk2 = ListTask.newTask("id2", "desc2", 100, 5, "Example");
        lt = org.getListTransaction();
        fre1 = rfre.newFreelancer("André Botelho", Expertise.SENIOR.name(), "fre@mail.com", "7238648762", "6248736", "dshfsk", "Portugal");
        fre3 = rfre.newFreelancer("Ricardo Moreira", Expertise.JUNIOR.name(), "fre3@mail.com", "72386487623", "62487363", "dshfsk", "Portugal");
        trs1 = ListTransaction.newTransaction("ID1", fre1, tsk1, LocalDate.now(), 1, "Good Job!");
        trs2 = ListTransaction.newTransaction("ID1", fre1, tsk1, LocalDate.now(), 2, "Bad Job!");
        trs3 = ListTransaction.newTransaction("ID3", fre3, tsk2, LocalDate.now(), 2, "Bad Job!");
        lt.registerTransaction(trs1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void newTransaction() {
        Transaction expected = trs1;
        Transaction result = ListTransaction.newTransaction("ID1", fre1, tsk1, LocalDate.now(), 1, "Good Job!");
        assertEquals(expected, result);
    }

    @Test
    void remove() {
        assertTrue(lt.remove(trs1));
        assertFalse(lt.remove(trs2));
    }

    @Test
    void add() {
        assertFalse(lt.registerTransaction(trs2)); // same id as trs1
        assertTrue(lt.registerTransaction(trs3)); // unique id
        assertFalse(lt.registerTransaction(trs3)); // Same ID as trs3
    }

    @Test
    void Equals() {
        assertEquals(trs1, trs2);
        assertNotEquals(trs1, trs3);
    }

    @Test
    void emailAboutPayment() {
        App app = AppPOE.getInstance().getApp();
        app.setAPIs(new EmailAPI() {
            @Override public boolean sendEmail(String address, String message) {
                return true;
            }
            @Override public void close() throws IOException {
            }
        }, app.getMonetaryConversionAPI(), app.getPaymentAPI(), app.getPswGeneratorAPI());
        assertTrue(lt.emailAboutPayment());
        app.setAPIs(new EmailAPI() {
            @Override public boolean sendEmail(String address, String message) {
                return false;
            }
            @Override public void close() throws IOException {
            }
        }, app.getMonetaryConversionAPI(), app.getPaymentAPI(), app.getPswGeneratorAPI());
        assertFalse(lt.emailAboutPayment());
    }

    @Test
    void getFreelancersOfAllTransactions() {
        Collection<Freelancer> r = lt.getFreelancersOfAllTransactions();
        assertEquals(1, r.size());
        assertTrue(r.contains(fre1));
        lt.registerTransaction(trs3);
        r = lt.getFreelancersOfAllTransactions();
        assertEquals(2, r.size());
        assertTrue(r.contains(fre1));
        assertTrue(r.contains(fre3));
    }
}