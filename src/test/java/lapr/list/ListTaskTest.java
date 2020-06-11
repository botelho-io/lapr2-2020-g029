package lapr.list;

import lapr.controller.AppPOE;
import lapr.model.*;
import lapr.utils.Expertise;
import lapr.utils.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ListTaskTest {

    @BeforeEach
    void setUp() throws IOException {
        AppPOE.restartInstance();
        App app = AppPOE.getInstance().getApp();
    }

    @Test
    void newTask() {
        Task[] tsklt = TestConstants.getTestTasks();
        Task task = tsklt [0];
        Task result = ListTask.newTask("TSK1", "A Test task 1", 10, 10, "TEST");
        assertEquals(task, result);
    }

    /**
    @Test
    void validatesTask() {
    }

    @Test
    void registTask() {
        assertFalse(lt.registTask(trs2)); // same id as trs1
        assertTrue(lt.registTask(trs3)); // unique id
        Task tsk = ListTask.newTask("id2", "desc4 :)", 10, 2, "Example2");
        Transaction task4 = ListTransaction.newTransaction(fre, tsk, LocalDate.now(), 1, "Good Job!");
        assertFalse(lt.registTask(trs3)); // Same ID as trs3
    }

    /**
    @Test
    void getUnexecutedTasks() {
    }*/
}