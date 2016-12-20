package com.gashe.mimasacorporal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Created by Gashe on 18/12/16.
 */

public class ListenerButton implements View.OnClickListener {

    Context ct;

    public ListenerButton(Context context){
        this.ct = context;
    }

    @Override
    public void onClick(View view) {

        Activity activity = (Activity)ct;
        String peso = ((EditText)activity.findViewById(R.id.inputPeso)).getText().toString();
        String altura = ((EditText)activity.findViewById(R.id.inputAltura)).getText().toString();


        // comprobamos que hay datos
        if(peso.matches("") || altura.matches("")){
            Log.d(getClass().getCanonicalName(), "No has introducido nada");
            new AlertDialog.Builder(ct)
                .setTitle(ct.getResources().getString(R.string.titleDialog))
                .setMessage(ct.getResources().getString(R.string.messageDialog))
                .setPositiveButton(ct.getResources().getString(R.string.buttonAceptar), null)
                .show();
        }else{
            // intent y pasamos valores a nueva actividad
            Intent intent = new Intent(ct, resultadoIMCActivity.class);
            intent.putExtra("peso", peso);
            intent.putExtra("altura", altura);
            ct.startActivity(intent);
        }

    }
}
