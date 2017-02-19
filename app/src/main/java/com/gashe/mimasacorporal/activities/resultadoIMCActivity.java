package com.gashe.mimasacorporal.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.gashe.mimasacorporal.classes.Historial;
import com.gashe.mimasacorporal.classes.consultasDataBase;
import com.gashe.mimasacorporal.listeners.ListenerButton;
import com.gashe.mimasacorporal.R;
import com.gashe.mimasacorporal.classes.Utils;

import java.util.prefs.Preferences;

public class resultadoIMCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_imc);

        // recogemos datos de actividad anterior
        Intent intent = getIntent();
        String peso = intent.getStringExtra("peso");
        String altura = intent.getStringExtra("altura");

        // calculamos IMC y vemos tipo
        Utils utils = new Utils();
        String[] IMC = utils.calculoIMC(peso, altura, this);


        // guardamos registro en bbdd
        consultasDataBase query = new consultasDataBase(this, "imcDB", null, 1);
        SharedPreferences prefs = getSharedPreferences("pref", Context.MODE_PRIVATE);
        int userId = prefs.getInt("userId", 0);
        Historial historial = new Historial(userId, Float.parseFloat(IMC[0]));
        query.registrarImc(historial);

        // informamos del guardado
        View view = findViewById(R.id.activity_resultado_imc);
        Snackbar snackbar = Snackbar.make(view, "Guardado registro IMC", Snackbar.LENGTH_LONG);
        snackbar.show();

        // pintamos valores
        TextView calculoIMC = (TextView)findViewById(R.id.resultadoIMC);
        calculoIMC.setText(IMC[0]);
        TextView calculoIMCtexto = (TextView)findViewById(R.id.resultadoTexto);
        calculoIMCtexto.setText(IMC[1]);

        // escuchamos boton mas info
        ListenerButton listenerButton = new ListenerButton(this);
        TextView button = (TextView)findViewById(R.id.masInfo);
        button.setOnClickListener(listenerButton);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE, 1, 1, "+ Info");
        menu.add(Menu.NONE, 2, 1, "Historial IMC");
        menu.add(Menu.NONE, 9, 1, "Salir");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        final SharedPreferences prefs = getSharedPreferences("pref", Context.MODE_PRIVATE);
        Intent intent;
        switch (item.getItemId()){
            case 1:
                intent = new Intent(this, WebViewActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, HistorialActivity.class);
                startActivity(intent);
                break;
            case 9:
                AlertDialog ad = new AlertDialog.Builder(this).create();
                ad.setTitle("Salir");
                ad.setMessage("¿Seguro que quieres salir?");
                ad.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                ad.setButton(AlertDialog.BUTTON_POSITIVE, "Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences.Editor editor_ =  prefs.edit();
                        editor_.putBoolean("login", false);
                        editor_.commit();

                        Intent intent = new Intent(resultadoIMCActivity.this, MainActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    }
                });
                ad.show();
                break;
        }

        return true;
    }

}
