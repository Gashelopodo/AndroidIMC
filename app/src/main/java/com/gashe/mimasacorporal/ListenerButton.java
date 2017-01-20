package com.gashe.mimasacorporal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Created by Gashe on 18/12/16.
 */

public class ListenerButton implements View.OnClickListener {

    Context ct;
    String button;

    public ListenerButton(Context context, String button){
        this.ct = context;
        this.button = button;
    }

    @Override
    public void onClick(View view) {

        Activity activity = (Activity)ct;
        String nameActivity = activity.getLocalClassName();

        // vemos de que actividad procede el botón
        if(button == "buttonCalculo") {

            String peso = ((EditText) activity.findViewById(R.id.inputPeso)).getText().toString();
            String altura = ((EditText) activity.findViewById(R.id.inputAltura)).getText().toString();

            // comprobamos que hay datos
            if (peso.matches("") || altura.matches("")) {
                Log.d(getClass().getCanonicalName(), "No has introducido nada");
                new AlertDialog.Builder(ct)
                        .setTitle(ct.getResources().getString(R.string.titleDialog))
                        .setMessage(ct.getResources().getString(R.string.messageDialog))
                        .setPositiveButton(ct.getResources().getString(R.string.buttonAceptar), null)
                        .show();
            } else {
                // intent y pasamos valores a nueva actividad
                Intent intent = new Intent(ct, resultadoIMCActivity.class);
                intent.putExtra("peso", peso);
                intent.putExtra("altura", altura);
                ct.startActivity(intent);
            }
        }else if(button == "info"){

            //GridView gridView = (GridView) activity.findViewById(R.id.popup_info);
            ViewGroup popup = (ViewGroup) activity.findViewById(R.id.popuplayout);
            LayoutInflater inflater = (LayoutInflater) activity.getLayoutInflater();
            View vistaInflada = inflater.inflate(R.layout.popup_info, popup);

            GridView gridView = (GridView) vistaInflada.findViewById(R.id.tablaComparativa);
            gridView.setAdapter(new popupAdapter(ct));

            ListenerButton listenerButton = new ListenerButton(ct, "volver");
            Button button = (Button) activity.findViewById(R.id.buttonVolver);
            button.setOnClickListener(listenerButton);


        }else if(button == "volver"){
            LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.popuplayout);
            linearLayout.removeAllViews();
        }

    }
}
