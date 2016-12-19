package com.gashe.mimasacorporal;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Gashe on 18/12/16.
 */

public class Utils {


    public String[] calculoIMC(String p, String a, Context context){

        float peso = Float.parseFloat(p);
        float altura = Float.parseFloat(a);
        float calculoIMC = 0;
        String[] IMC;
        String resultado;

        altura = (altura/100)*(altura/100);
        calculoIMC = peso/altura;

        if(calculoIMC < 16) resultado = context.getResources().getString(R.string.desnutricion);
        else if(calculoIMC >= 16 && calculoIMC < 18) resultado = context.getResources().getString(R.string.bajo_peso);
        else if(calculoIMC >= 18 && calculoIMC < 25) resultado = context.getResources().getString(R.string.peso_normal);
        else if(calculoIMC >= 25 && calculoIMC < 31) resultado = context.getResources().getString(R.string.sobrepeso);
        else resultado = context.getResources().getString(R.string.obeso);

        IMC = new String[]{Float.toString(calculoIMC), resultado };

        return IMC;

    }


}
