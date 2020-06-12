/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

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
    private double m_dDelayHours;
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
    public TaskExecutionDetails(LocalDate endDate, double hoursDelay, String description) {
        if(endDate == null) throw new IllegalArgumentException("Task Execution - End Date cannot be null");
        if(description == null || (description = description.trim()).isEmpty()) throw new IllegalArgumentException("Task Execution - Description cannot be empty");
        this.m_oEndDate = endDate;
        this.m_dDelayHours = hoursDelay;
        this.m_strDescription = description;
    }

    /**
     * @return The number of hours the execution was delayed.
     */
    public double getHoursDelay() {
        return m_dDelayHours;
    }

    /**
     * @return The date the task was executed.
     */
    public LocalDate getEndDate() {
        return m_oEndDate;
    }

    /**
     * @return A textual description of the quality of the work done by the freelancer.
     */
    public String getDescription() {
        return this.m_strDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskExecutionDetails)) return false;
        TaskExecutionDetails that = (TaskExecutionDetails) o;
        return Double.compare(that.m_dDelayHours, m_dDelayHours) == 0 &&
                m_oEndDate.equals(that.m_oEndDate) &&
                m_strDescription.equals(that.m_strDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_oEndDate, m_dDelayHours, m_strDescription);
    }
}
