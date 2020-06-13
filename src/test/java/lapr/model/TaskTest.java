package lapr.model;

import lapr.controller.AppPOE;
import lapr.lists.ListTask;
import lapr.lists.ListTransaction;
import lapr.utils.Expertise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    Freelancer fre;
    Task tsk1;
    Task tsk2;
    Organization org;

    @BeforeEach
    void setUp() throws IOException {
        tsk1 = ListTask.newTask("TSK1", "A Test task 1", 10, 10, "TEST");
        tsk2 = ListTask.newTask("TSK2", "A Test task 2", 10, 10, "TEST");
        fre = new Freelancer("FJ1", "Free Joe1", Expertise.SENIOR, "fre1@dei.pt", "28739247891", "8937431", "Address1", "Germany");
        AppPOE.restartInstance();
        App app = AppPOE.getInstance().getApp();
        Manager testMan = new Manager("Man Joe", "man@dei.pt", "password");
        Collaborator testCol = new Collaborator("Colab Joe", "colab@dei.pt", "password");
        org = app.getRegistOrganization().newOrganization("123", "DEFAULT", testMan, testCol);
        org.getListTask().registerTask(tsk1);
        org.getListTask().registerTask(tsk2);
    }

    @Test
    void getExecutor() {
        org.getListTransaction().registerTransaction(ListTransaction.newTransaction("ID1", fre, tsk1, LocalDate.of(2020, 4, 3), 1.5, "..."));
        assertEquals(tsk1.getExecutor(), fre);
        assertNull(tsk2.getExecutor());
    }

    @Test
    void setExecutor() {
        tsk1.setExecutor(fre);
        assertEquals(tsk1.getExecutor(), fre);
        assertNull(tsk2.getExecutor());
    }

    @Test
    void getDurationInHours() {
        assertEquals(tsk1.getDurationInHours(), 10);
    }

    @Test
    void setDurationInHours() {
        tsk1.setDurationInHours(20);
        assertEquals(tsk1.getDurationInHours(), 20);
    }

    @Test
    void getCostPerHourOfJuniorEur() {
        assertEquals(tsk1.getCostPerHourOfJuniorEur(), 10);
    }

    @Test
    void setCostPerHourOfJuniorEur() {
        tsk1.setCostPerHourOfJuniorEur(20);
        assertEquals(tsk1.getCostPerHourOfJuniorEur(), 20);
    }

    @Test
    void getId() {
        assertEquals(tsk1.getId(), "TSK1");
    }

    @Test
    void getDescription() {
        assertEquals(tsk1.getDescription(), "A Test task 1");
    }

    @Test
    void getCategory() {
        assertEquals(tsk1.getCategory(), "TEST");
    }
}