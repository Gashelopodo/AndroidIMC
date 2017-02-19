package com.gashe.mimasacorporal.classes;

import java.util.Comparator;

/**
 * Created by Gashe on 19/2/17.
 */

public class CompareIMC implements Comparator<Historial> {


    @Override
    public int compare(Historial historial, Historial historial1) {
        float resta = historial.getImc()-historial1.getImc();
        return Math.round(resta);
    }
}
