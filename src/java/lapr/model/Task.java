/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import java.util.Objects;

/**
 * Represents a task an organization is trying to outsource to a freelancer.
 */
public class Task {
    /**
     * The id of the task.
     */
    private String m_strId;
    /**
     * A short description of the task.
     */
    private String m_strDescription;
    /**
     * The duration it took to complete the task.
     */
    private int m_iDurationInHours;
    /**
     * The cost per hour a junior freelancer receives for this task.
     */
    private double m_dCostPerHourOfJuniorEur;
    /**
     * The category this task is in.
     */
    private String m_strCategory;
    /**
     * The Freelancer that executed the task.
     */
    private Freelancer m_oExecutor;

    /**
     * Constructor.
     * @param id The id of the task.
     * @param description A short description of the task.
     * @param durationInHours The duration it took to complete the task.
     * @param costPerHourOfJuniorEur The cost per hour a junior freelancer receives for this task.
     * @param category The category this task is in.
     * @param executor The Freelancer that executed the task.
     */
    public Task(String id, String description, int durationInHours, double costPerHourOfJuniorEur, String category, Freelancer executor) {
        if ((id == null) || (description == null) || (category == null) || (id.isEmpty()))
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        this.m_strId = id;
        this.m_strDescription = description;
        this.m_iDurationInHours = durationInHours;
        this.m_dCostPerHourOfJuniorEur = costPerHourOfJuniorEur;
        this.m_strCategory = category;
        this.m_oExecutor = executor;
    }

    /**
     * @return The freelancer that executed the task or null if the task has not been executed yet.
     */
    public Freelancer getExecutor() {
        return m_oExecutor;
    }
    /**
     * Set the task as having been executed by a freelancer.
     * @param executor The Freelancer that executed the task.
     */
    public void setExecutor(Freelancer executor) {
        this.m_oExecutor = executor;
    }
    /**
     * @return The duration in hours it took to complete the task.
     */
    public int getDurationInHours() {
        return m_iDurationInHours;
    }
    /**
     * @param durationInHours The new duration in hours it took to complete the task.
     */
    public void setDurationInHours(int durationInHours) {
        this.m_iDurationInHours = durationInHours;
    }
    /**
     * @return The cost per hour a junior freelancer receives for this task.
     */
    public double getCostPerHourOfJuniorEur() {
        return m_dCostPerHourOfJuniorEur;
    }
    /**
     * @param costPerHourOfJuniorEur The new cost per hour a junior freelancer receives for this task.
     */
    public void setCostPerHourOfJuniorEur(double costPerHourOfJuniorEur) {
        this.m_dCostPerHourOfJuniorEur = costPerHourOfJuniorEur;
    }
    /**
     * @return The id of the task.
     */
    public String getId() {
        return m_strId;
    }
    /**
     * @return A short description of the task.
     */
    public String getM_strDescription() {
        return m_strDescription;
    }

    /**
     * Compare two tasks.
     * @param o Ideally a task to compare.
     * @return Will return true if and only if the object provided is a task with the same ID.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return m_strId.equals(task.m_strId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_strId);
    }
}
