package lapr.model;

import lapr.controller.AppPOE;
import lapr.utils.TestConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PaymentSchedulerTest {
    @Test
    void getNextDate() {
        Calendar t;
        // Wait until we aren't on the first millisecond of the month...
        do {
            t = Calendar.getInstance();
        } while (   t.get(Calendar.DAY_OF_MONTH) == 1 &&
                    t.get(Calendar.HOUR_OF_DAY) == 0 &&
                    t.get(Calendar.MINUTE) == 0 &&
                    t.get(Calendar.MILLISECOND) == 0 );


        t.set(Calendar.DAY_OF_MONTH, 1);
        t.add(Calendar.MONTH, 1);
        t.set(Calendar.HOUR_OF_DAY, 0);
        t.set(Calendar.MINUTE, 0);
        t.set(Calendar.SECOND, 0);
        t.set(Calendar.MILLISECOND, 0);
        Date expected = t.getTime();

        Date observed = new PaymentScheduler(1, LocalTime.of(0, 0), null).getNextDate();

        assertEquals(expected, observed);
    }


    @Test
    void resetTime() throws IOException {
        Manager testMan = new Manager("Man Joe", "man@dei.pt", "password");
        Collaborator testCol = new Collaborator("Colab Joe", "colab@dei.pt", "password");
        AppPOE.restartInstance();
        Organization org = AppPOE.getInstance().getApp().getRegistOrganization().newOrganization("123", "DEFAULT", testMan, testCol);
        PaymentScheduler ps = new PaymentScheduler(1, LocalTime.of(0,0), org);
        PaymentScheduler ps2 = new PaymentScheduler(2, LocalTime.of(3,6), org);
        ps2.resetTime(1, LocalTime.of(0, 0));
        Date expected = ps.getNextDate();
        Date actual = ps2.getNextDate();
        assertEquals(expected, actual);
    }
}