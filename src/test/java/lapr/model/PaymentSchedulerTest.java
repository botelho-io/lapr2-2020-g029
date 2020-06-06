package lapr.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PaymentSchedulerTest {

    PaymentScheduler ps;

    @BeforeEach
    void setUp() {

        ps = new PaymentScheduler(18, LocalTime.MIDNIGHT, null);
    }

    @Test
    void getNextDate() {
        assertEquals(getNextDateTested().toString(), ps.getNextDate().toString());
    }

    public static Date getNextDateTested() {
        // Find what time it is now
        Calendar cal = Calendar.getInstance();
        // Set that calendar to this month's day and time of payment
        cal.set(Calendar.DAY_OF_MONTH, 18);
        // Set time
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        // Check if date has passed
        if(cal.after(Calendar.getInstance())) {
            // Date is not on this month, set for next month
            cal.add(Calendar.MONTH, 1);
        }
        return cal.getTime();
    }
}