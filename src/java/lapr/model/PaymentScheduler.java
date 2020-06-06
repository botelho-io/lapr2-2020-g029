/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import lapr.controller.MakePaymentTask;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * Class responsible for scheduling a task to automatically pay
 * the transactions of an organization.
 */
public class PaymentScheduler {
    /**
     * The day of the month the payments will be processed.
     */
    private int m_iDayMonth;
    /**
     * The time of the day the payments will be processed.
     */
    LocalTime m_oTimeOfDay;
    /**
     * The organization that has the unpaid transactions.
     */
    Organization m_oOrganization;
    /**
     * The current Timer being used.
     */
    private Timer m_oTimer;
    /**
     * Creates a new scheduler.
     * @param DayMonth The day of the month the payment are to be made.
     * @param TimeOfDay The time of day to make the payments.
     * @param Organization The organization to make payments on.
     */
    public PaymentScheduler(int DayMonth, LocalTime TimeOfDay, Organization Organization) {
        this.m_oOrganization = Organization;
        resetTime(DayMonth, TimeOfDay);
    }
    /**
     * Resets the time the payments are precessed.
     * @param DayMonth New day of the month to process payments.
     * @param TimeOfDay Time of the day to process payments.
     */
    public void resetTime(int DayMonth, LocalTime TimeOfDay) {
        // Cancel the previous tasks if they exist
        if(m_oTimer != null) {
            m_oTimer.cancel();
            m_oTimer.purge();
        }
        m_oTimer = new Timer();
        // Schedule next task
        this.m_iDayMonth = DayMonth;
        this.m_oTimeOfDay = TimeOfDay;
        scheduleNextMonth();
    }
    /**
     * Schedules the payments to be made on the next month.
     * @return
     */
    public void scheduleNextMonth() {
        m_oTimer.schedule(new MakePaymentTask(m_oOrganization, this), getNextDate());
    }
    /**
     * @return The date when the next automatic payments will be made.
     */
    public Date getNextDate() {
        // Find what time it is now
        Calendar cal = Calendar.getInstance();
        // Set that calendar to this month's day and time of payment
        // TODO: confirm with client what happens on invalid day of month.
        cal.set(Calendar.DAY_OF_MONTH, Math.min(cal.getActualMaximum(Calendar.DAY_OF_MONTH), m_iDayMonth));
        // Set time
        cal.set(Calendar.HOUR_OF_DAY, m_oTimeOfDay.getHour());
        cal.set(Calendar.MINUTE, m_oTimeOfDay.getMinute());
        cal.set(Calendar.SECOND, m_oTimeOfDay.getSecond());
        // Check if date has passed
        if(cal.after(Calendar.getInstance())) {
            // Date is not on this month, set for next month
            cal.add(Calendar.MONTH, 1);
        }
        return cal.getTime();
    }
}
