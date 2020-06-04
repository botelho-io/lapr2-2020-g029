/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import java.time.LocalDate;

/**
 *
 * @author Universidade
 */
public class TaskExecutionDetails {
    /**
     * The date the task ended.
     */
    private LocalDate m_oEndDate;
    /**
     * The delay the freelancer took to execute the task.
     */
    private int m_iDaysDelay;
    /**
     * A textual description of the quality of the work done by the freelancer.
     */
    private String m_strDescription;

    /**
     * Constructor.
     * @param endDate The date the task ended.
     * @param daysDelay The delay the freelancer took to execute the task.
     * @param description A textual description of the quality of the work done by the freelancer.
     */
    public TaskExecutionDetails(LocalDate endDate, int daysDelay, String description) {
        this.m_oEndDate = endDate;
        this.m_iDaysDelay = daysDelay;
        this.m_strDescription = description;
    }
}
