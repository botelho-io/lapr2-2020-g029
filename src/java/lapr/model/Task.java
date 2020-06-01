/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

/**
 * Represents a task an organization is trying to outsource to a freelancer.
 */
public class Task {
    /**
     * The id of the task.
     */
    private String id;
    /**
     * A short description of the task.
     */
    private String description;
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
    private String category;
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
        return id;
    }
    /**
     * @return A short description of the task.
     */
    public String getDescription() {
        return description;
    }
}
