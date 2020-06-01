/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

/**
 *
 * @author Universidade
 */
public class Organization {

    private String name;
    private Manager manager;
    private Colaborator colaborator;

    public Organization(String name, Manager manager, Colaborator colaborator) {
        if ((name == null) || (manager == null) || (colaborator == null))
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
            this.name = name;
            if(!validatesColaborator(colaborator))
                throw new IllegalArgumentException("Colaborator is invalid");
            if(!validatesManager(manager))
                throw new IllegalArgumentException("Manager is invalid");
            this.setManager(manager);
            this.setColaborator(colaborator);
    }

    public static Colaborator newColaborator (String name, String email, String password) {
        return new Colaborator(name,email,password);
    }

    public static Manager newManager (String name, String email, String password) {
        return new Manager(name,email,password);
    }

    public static boolean validatesColaborator(Colaborator colaborator) {
        //TODO: Validate colaborator.
        return true;
    }

    private void setColaborator(Colaborator colaborator) {
        this.colaborator = colaborator;
    }

    public static boolean validatesManager(Manager manager) {
        //TODO: Validate manager.
        return true;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

}