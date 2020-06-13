/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import lapr.controller.AppPOE;
import authorization.model.Role;

/**
 *
 * @author Universidade
 */
public class Administrator extends User {

    /**
     * Build an instance of administrator receiving the name, email and password.
     *
     * @param name of the admininstrator.
     * @param email of the admininstrator.
     * @param password of the admininstrator.
     */
    public Administrator (String name, String email, String password){
        super(name, email, password, Role.ADMINISTRATOR);
    }
}
