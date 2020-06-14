/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import authorization.model.Role;

/**
 * Represents a manager of the organization.
 * @author André Botelho and Ricardo Moreira.
 */
public class Manager extends User {
    /**
     * Build an instance of manager receiving the name, email and password.
     *
     * @param name of the manager.
     * @param email of the manager.
     * @param password of the manager.
     */
    public Manager (String name, String email, String password) {
        super(name, email, password, Role.MANAGER);
    }
}
