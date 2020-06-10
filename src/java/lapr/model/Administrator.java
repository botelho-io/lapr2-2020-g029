/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import authorization.model.UserRole;
import lapr.controller.AppPOE;
import lapr.utils.Role;

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
     * @param papeis role of the user.
     */
    public Administrator (String name, String email, String password, UserRole[] papeis){
        super(name, email, password, papeis);
    }

    /**
     * Build an instance of administrator receiving the name, email and password.
     *
     * @param name of the admininstrator.
     * @param email of the admininstrator.
     * @param password of the admininstrator.
     */
    public Administrator (String name, String email, String password){
        this(name, email, password, new UserRole[]{AppPOE.getInstance().getApp().getAuthFacade().getRole(Role.ADMINISTRATOR)});
    }
}
