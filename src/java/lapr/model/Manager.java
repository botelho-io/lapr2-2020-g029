/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import autorizacao.model.UserRole;
import lapr.utils.Role;

import static lapr.controller.AppPOE.*;

/**
 *
 * @author Universidade
 */
public class Manager extends User {
    /**
     * Build an instance of manager receiving the name, email and password.
     *
     * @param name of the manager.
     * @param email of the manager.
     * @param password of the manager.
     * @param papeis role of the user.
     */
    public Manager (String name, String email, String password, UserRole[] papeis){
        super(name, email, password, papeis);
    }

    /**
     * Build an instance of manager receiving the name, email and password.
     *
     * @param name of the manager.
     * @param email of the manager.
     * @param password of the manager.
     */
    public Manager (String name, String email, String password) {
        this(name, email, password, new UserRole[]{getInstance().getApp().getAuthFacade().getRole(Role.MANAGER)});
    }
}
