/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import lapr.controller.MakePaymentTask;
import lapr.controller.SendEmailTask;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * Class responsible for scheduling the e-mails to be automatically sent to the freelancer.
 */
public class EmailScheduler {
    /**
     * The current Timer being used.
     */
    private Timer m_oTimer;

    /**
     * Creates a new scheduler.
     */
    public EmailScheduler() {
        resetTime();
    }

    /**
     * Resets the time the e-mails are sent.
     */
    public void resetTime() {
        // Cancel the previous tasks if they exist
        if(m_oTimer != null) {
            m_oTimer.cancel();
            m_oTimer.purge();
        }
        m_oTimer = new Timer();
        // Schedule next task
        scheduleNextYear();
    }

    /**
     * Schedules the e-mails to be sent on the next year.
     */
    public void scheduleNextYear() {
        m_oTimer.schedule(new SendEmailTask(this), getNextDate());
    }

    /**
     * @return The date when the next automatic e-mails will be sent.
     */
    public Date getNextDate() {
        // Find what time it is now
        Calendar cal = Calendar.getInstance();
        // Set that calendar to end of year
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        // Set time
        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        // Check if date has passed
        if(cal.after(Calendar.getInstance())) {
            // Date has passed, send next years date.
            cal.add(Calendar.YEAR, 1);
        }
        return cal.getTime();
    }
}
