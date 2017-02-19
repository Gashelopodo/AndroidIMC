package com.gashe.mimasacorporal.classes;

import android.app.Activity;
import android.content.Context;

import com.gashe.mimasacorporal.R;

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

    public String codifica (String cadena)
    {
        String cad_codificada = "";
        String aux = "";
        int i_aux = 0 ;
        char c_aux = 0;

        for(int i  = 0; i < cadena.length(); i++)
        {
            i_aux = (int) cadena.charAt(i);
            c_aux = (char) ((i_aux + 9) - i);
            aux = aux + c_aux;
        }

        for (int i = aux.length() - 1; i > -1; i--)
        {
            c_aux = aux.charAt(i);
            cad_codificada = cad_codificada + c_aux;
        }

        return cad_codificada;
    }


    public String decodifica (String cadena)
    {
        String cad_decodificada = "";
        String aux = "";
        int i_aux = 0;
        char c_aux = 0;

        for (int i = cadena.length() - 1; i > -1; i--)
        {
            c_aux = cadena.charAt(i);
            aux = aux + c_aux;
        }


        for(int i  = 0; i < aux.length(); i++)
        {
            i_aux = (int) aux.charAt(i);
            c_aux = (char) ((i_aux + i) - 9);
            cad_decodificada = cad_decodificada + c_aux;
        }



        return cad_decodificada;
    }


}
