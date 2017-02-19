package com.gashe.mimasacorporal.classes;

import java.util.Date;

/**
 * Created by Gashe on 19/2/17.
 */

public class Historial {

    private int userId;
    private float imc;
    private String date;

    public Historial(int userId, float imc, String date) {
        this.userId = userId;
        this.imc = imc;
        this.date = date;
    }

    public Historial(int userId, float imc) {
        this.userId = userId;
        this.imc = imc;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getImc() {
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
