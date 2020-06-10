/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Universidade
 */
public class TaskExecutionDetails implements Serializable {
    /**
     * The date the task ended.
     */
    private LocalDate m_oEndDate;
    /**
     * The delay the freelancer took to execute the task.
     */
    private int m_iDayHours;
    /**
     * A textual description of the quality of the work done by the freelancer.
     */
    private String m_strDescription;

    /**
     * Constructor.
     * @param endDate The date the task ended.
     * @param hoursDelay The delay the freelancer took to execute the task. (in hours)
     * @param description A textual description of the quality of the work done by the freelancer.
     */
    public TaskExecutionDetails(LocalDate endDate, int hoursDelay, String description) {
        if(endDate == null) throw new IllegalArgumentException("Task Execution - End Date cannot be null");
        if(description == null || (description = description.trim()).isEmpty()) throw new IllegalArgumentException("Task Execution - Description cannot be empty");
        this.m_oEndDate = endDate;
        this.m_iDayHours = hoursDelay;
        this.m_strDescription = description;
    }

    /**
     * @return The number of hours the execution was delayed.
     */
    public int getHoursDelay() {
        return m_iDayHours;
    }

    /**
     * @return The date the task was executed.
     */
    public LocalDate getEndDate() {
        return m_oEndDate;
    }
}
