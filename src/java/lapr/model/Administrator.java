/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import lapr.controller.AppPOE;
import authorization.model.Role;

/**
 * Represents an administrator of the organization.
 * @author André Botelho and Ricardo Moreira.
 */
public class Administrator extends User {

    /**
     * Build an instance of administrator receiving the name, email and password.
     *
     * @param name name of the administrator.
     * @param email email of the administrator.
     * @param password password of the administrator.
     */
    public Administrator (String name, String email, String password){
        super(name, email, password, Role.ADMINISTRATOR);
    }
}
