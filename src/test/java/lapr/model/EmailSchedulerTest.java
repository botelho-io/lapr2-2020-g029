package lapr.model;

import lapr.controller.AppPOE;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmailSchedulerTest {


    @Test

    void getNextDate() {
        Calendar t;
        // Wait until we aren't on the first second of the month...
        do {
            t = Calendar.getInstance();
        } while (   t.get(Calendar.MONTH) == Calendar.DECEMBER  &&
                    t.get(Calendar.DAY_OF_MONTH) == t.getActualMaximum(Calendar.DAY_OF_MONTH) &&
                    t.get(Calendar.HOUR_OF_DAY) == 10 &&
                    t.get(Calendar.MINUTE) == 0 );


        t.set(Calendar.DAY_OF_MONTH, t.getActualMaximum(Calendar.DAY_OF_MONTH)+1);
        t.set(Calendar.MONTH, Calendar.DECEMBER);
        t.set(Calendar.HOUR_OF_DAY, 10);
        t.set(Calendar.MINUTE, 0);
        t.set(Calendar.SECOND, 0);
        t.set(Calendar.MILLISECOND, 0);

        Date expected = t.getTime();

        Date observed = new EmailScheduler().getNextDate();

        assertEquals(expected, observed);
    }
}