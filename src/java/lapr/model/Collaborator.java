/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import authorization.model.Role;

/**
 *
 * @author Universidade
 */
public class Collaborator extends User {
    /**
     * Build an instance of collaborator receiving the name, email and password.
     *
     * @param name of the collaborator.
     * @param email of the collaborator.
     * @param password of the collaborator.
     */
    public Collaborator (String name, String email, String password) {
        super(name, email, password, Role.COLLABORATOR);
    }
}
