/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import autorizacao.model.PapelUtilizador;
import lapr.controller.AppPOE;
import lapr.utils.Constants;
import lapr.utils.Role;

import static lapr.controller.AppPOE.*;

/**
 *
 * @author Universidade
 */
public class Manager extends User {

    /**
     * The name of the manager.
     */
    private String name;

    /**
     * The email of the manager.
     */
    private String email;

    /**
     * The passaword of the manager.
     */
    private String password;

    /**
     * Build an instance of manager receiving the name, email and password.
     *
     * @param name of the manager.
     * @param email of the manager.
     * @param password of the manager.
     * @param papeis role of the user.
     */
    public Manager (String name, String email, String password, PapelUtilizador[] papeis){
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
        this(name, email, password, new PapelUtilizador[]{getInstance().getRole(Role.MANAGER)});
    }

    /**
     * Returns the name of the manager.
     *
     * @return name of the manager.
     */
    public String getNome()
    {
        return this.name;
    }

    /**
     * Returns the email of the manager.
     *
     * @return email of the manager.
     */
    public String getEmail()
    {
        return this.email;
    }

    /**
     * Returns the password of the manager.
     *
     * @return password of the manager.
     */
    public String getPassword(){
        return this.password;
    }
}
