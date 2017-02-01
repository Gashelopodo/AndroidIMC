package com.gashe.mimasacorporal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.prefs.Preferences;


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
        String nameActivity = activity.getLocalClassName();
        int idButton = view.getId();
        consultasDataBase query;
        TextView textView;
        String myUsuario;
        String myPass;
        User user;
        Intent intent;
        final SharedPreferences prefs = activity.getSharedPreferences("pref", Context.MODE_PRIVATE);


        // controlamos el botón

        switch (idButton){

            case R.id.buttonLogin:
                Log.d(getClass().getCanonicalName(), "entro en login");
                query = new consultasDataBase(ct, "imcDB", null, 1);
                textView = (TextView) activity.findViewById(R.id.myUser);
                myUsuario = textView.getText().toString();
                textView = (TextView) activity.findViewById(R.id.myPassword);
                myPass = textView.getText().toString();
                user = new User(myUsuario, myPass);
                boolean login;
                SharedPreferences.Editor editor = prefs.edit();
                if(query.checkLogin(user)){
                    editor.putBoolean("login", true);
                    editor.commit();
                    intent = new Intent(ct, homeActivity.class);
                    ct.startActivity(intent);
                    activity.finishAffinity();
                }else{
                    int intentos = prefs.getInt("intentos", 0);
                    editor.putInt("intentos", intentos+1);
                    editor.commit();
                    new AlertDialog.Builder(ct)
                        .setTitle(ct.getResources().getString(R.string.titleDialog))
                        .setMessage(ct.getResources().getString(R.string.error_login))
                        .setPositiveButton(ct.getResources().getString(R.string.buttonAceptar), null)
                        .show();
                    Log.d(getClass().getCanonicalName(), "INTENTOS: "+intentos);
                    if(intentos == 2){
                        activity.finishAffinity();
                    }
                }

                break;

            case R.id.buttonRegister:
                Log.d(getClass().getCanonicalName(), "entro en register");
                query = new consultasDataBase(ct, "imcDB", null, 1);
                textView = (TextView) activity.findViewById(R.id.myUser);
                myUsuario = textView.getText().toString();
                textView = (TextView) activity.findViewById(R.id.myPassword);
                myPass = textView.getText().toString();
                if (myUsuario.matches("") || myPass.matches("")) {
                    new AlertDialog.Builder(ct)
                        .setTitle(ct.getResources().getString(R.string.titleDialog))
                        .setMessage("Debes rellenar usuario y contraseña")
                        .setPositiveButton(ct.getResources().getString(R.string.buttonAceptar), null)
                        .show();
                }else{
                    user = new User(myUsuario, myPass);
                    query.registrarUser(user);
                    intent = new Intent(ct, homeActivity.class);
                    ct.startActivity(intent);
                }

                break;

            case R.id.buttonCalcularIMC:

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
                    intent = new Intent(ct, resultadoIMCActivity.class);
                    intent.putExtra("peso", peso);
                    intent.putExtra("altura", altura);
                    ct.startActivity(intent);
                }

                break;

            case R.id.masInfo:

                //GridView gridView = (GridView) activity.findViewById(R.id.popup_info);
                ViewGroup popup = (ViewGroup) activity.findViewById(R.id.popuplayout);
                LayoutInflater inflater = (LayoutInflater) activity.getLayoutInflater();
                View vistaInflada = inflater.inflate(R.layout.popup_info, popup);

                GridView gridView = (GridView) vistaInflada.findViewById(R.id.tablaComparativa);
                gridView.setAdapter(new popupAdapter(ct));

                ListenerButton listenerButton = new ListenerButton(ct);
                Button button = (Button) activity.findViewById(R.id.buttonVolver);
                button.setOnClickListener(listenerButton);

                break;

            case R.id.buttonVolver:

                LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.popuplayout);
                linearLayout.removeAllViews();

                break;

            case R.id.myButtonLogout:

                SharedPreferences.Editor editor_ =  prefs.edit();
                editor_.putBoolean("login", false);
                editor_.commit();

                intent = new Intent(ct, MainActivity.class);
                ct.startActivity(intent);
                activity.finishAffinity();

                break;

        }

    }
}
