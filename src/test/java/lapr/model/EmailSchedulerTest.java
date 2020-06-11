package lapr.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmailSchedulerTest {

    @Test
    void resetTime() {
    }

    @Test
    void scheduleNextYear() {
    }

    @Test

    void getNextDate() {
        Calendar t;
        // Wait until we aren't on the first second of the month...
        do {
            t = Calendar.getInstance();
        } while (   t.get(Calendar.DAY_OF_MONTH) == 1 &&
                t.get(Calendar.HOUR_OF_DAY) == 0 &&
                t.get(Calendar.MINUTE) == 0 );


        t.set(Calendar.DAY_OF_MONTH, 1);
        t.add(Calendar.MONTH, 1);
        t.set(Calendar.HOUR_OF_DAY, 0);
        t.set(Calendar.MINUTE, 0);
        t.set(Calendar.SECOND, 0);
        Date expected = t.getTime();

        Date observed = new EmailScheduler().getNextDate();

        assertEquals(expected, observed);
    }
}