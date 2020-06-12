package lapr.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskExecutionDetailsTest {

    @Test
    void getHoursDelay() {
        TaskExecutionDetails details = new TaskExecutionDetails(LocalDate.of(2020, 3, 3), 1.5, "Cool");
        assertEquals(details.getHoursDelay(), 1.5);
    }

    @Test
    void getEndDate() {
        TaskExecutionDetails details = new TaskExecutionDetails(LocalDate.of(2020, 3, 3), 1.5, "Cool");
        assertEquals(details.getEndDate(), LocalDate.of(2020, 3, 3));
    }

    @Test
    void getDescription() {
        TaskExecutionDetails details = new TaskExecutionDetails(LocalDate.of(2020, 3, 3), 1.5, "Cool");
        assertEquals(details.getDescription(), "Cool");
    }
}