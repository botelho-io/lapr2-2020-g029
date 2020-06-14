/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a task an organization is trying to outsource to a freelancer.
 * @author André Botelho and Ricardo Moreira.
 */
public class Task implements Serializable {
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
    private double m_dDurationInHours;
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
    public Task(String id, String description, double durationInHours, double costPerHourOfJuniorEur, String category, Freelancer executor) {
        if ((id == null) || (description == null) || (category == null) || (id.isEmpty()))
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        this.m_strId = id;
        this.m_strDescription = description;
        this.m_dDurationInHours = durationInHours;
        this.m_dCostPerHourOfJuniorEur = costPerHourOfJuniorEur;
        this.m_strCategory = category;
        this.m_oExecutor = executor;
    }

    /**
     * Returns the freelancer that executed the task or null if the task has not been executed yet.
     * @return he freelancer that executed the task or null if the task has not been executed yet.
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
     * Returns the duration in hours it took to complete the task.
     * @return he duration in hours it took to complete the task.
     */
    public double getDurationInHours() {
        return m_dDurationInHours;
    }
    /**
     * @param durationInHours The new duration in hours it took to complete the task.
     */
    public void setDurationInHours(int durationInHours) {
        this.m_dDurationInHours = durationInHours;
    }
    /**
     * Returns the cost per hour a junior freelancer receives for this task.
     * @return he cost per hour a junior freelancer receives for this task.
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
     * Returns the id of the task.
     * @return he id of the task.
     */
    public String getId() {
        return m_strId;
    }
    /**
     * Returns a short description of the task.
     * @return  short description of the task.
     */
    public String getDescription() {
        return m_strDescription;
    }

    /**
     * Returns the category this task is in.
     * @return he category this task is in.
     */
    public String getCategory() {
        return this.m_strCategory;
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

    @Override
    public String toString() {
        return String.format("(%s) %s", m_strId, m_strDescription);
    }
}
