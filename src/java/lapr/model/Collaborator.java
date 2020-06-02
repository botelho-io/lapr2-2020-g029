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

/**
 *
 * @author Universidade
 */
public class Collaborator extends User {

    /**
     * The name of the collaborator.
     */
    private String name;

    /**
     * The email of the collaborator.
     */

    private String email;

    /**
     * The passaword of the collaborator.
     */
    private String password;

    /**
     * Build an instance of collaborator receiving the name, email and password.
     *
     * @param name of the collaborator.
     * @param email of the collaborator.
     * @param password of the collaborator.
     * @param papeis role of the user.
     */
    public Collaborator (String name, String email, String password, PapelUtilizador[] papeis){
        super(name, email, password, papeis);
    }

    /**
     * Build an instance of collaborator receiving the name, email and password.
     *
     * @param name of the collaborator.
     * @param email of the collaborator.
     * @param password of the collaborator.
     */
    public Collaborator (String name, String email, String password){
        this(name, email, password, new PapelUtilizador[]{AppPOE.getInstance().getRole(Role.COLLABORATOR)});
    }

    /**
     * Returns the name of the collaborator.
     *
     * @return name of the collaborator.
     */
    public String getNome()
    {
        return this.name;
    }

    /**
     * Returns the email of the collaborator.
     *
     * @return email of the collaborator.
     */
    public String getEmail()
    {
        return this.email;
    }

    /**
     * Returns the password of the collaborator.
     *
     * @return password of the collaborator.
     */
    public String getPassword(){
        return this.password;
    }
}
