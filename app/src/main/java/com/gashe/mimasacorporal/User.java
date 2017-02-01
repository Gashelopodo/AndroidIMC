package com.gashe.mimasacorporal;

/**
 * Created by Gashe on 30/1/17.
 */

public class User {

    private String usuario;
    private String pass;

    public User(String usuario, String pass) {
        this.usuario = usuario;
        this.pass = pass;
    }

    public User(String usuario){
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
