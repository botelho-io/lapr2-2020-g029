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
public class Colaborator extends User {

    /**
     * The name of the colaborator.
     */
    private String name;

    /**
     * The email of the colaborator.
     */

    private String email;

    /**
     * The passaword of the colaborator.
     */
    private String password;

    /**
     * Build an instance of colaborator receiving the name, email and password.
     *
     * @param name of the colaborator.
     * @param email of the colaborator.
     * @param password of the colaborator.
     * @param papeis role of the user.
     */
    public Colaborator (String name, String email, String password, PapelUtilizador[] papeis){
        super(name, email, password, papeis);
    }

    /**
     * Build an instance of colaborator receiving the name, email and password.
     *
     * @param name of the colaborator.
     * @param email of the colaborator.
     * @param password of the colaborator.
     */
    public Colaborator (String name, String email, String password){
        this(name, email, password, new PapelUtilizador[]{AppPOE.getInstance().getRole(Role.COLLABORATOR)});
    }

    /**
     * Returns the name of the colaborator.
     *
     * @return name of the colaborator.
     */
    public String getNome()
    {
        return this.name;
    }

    /**
     * Returns the email of the colaborator.
     *
     * @return email of the colaborator.
     */
    public String getEmail()
    {
        return this.email;
    }

    /**
     * Returns the password of the colaborator.
     *
     * @return password of the colaborator.
     */
    public String getPassword(){
        return this.password;
    }
}
