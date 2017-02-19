package com.gashe.mimasacorporal.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.gashe.mimasacorporal.listeners.ListenerButton;
import com.gashe.mimasacorporal.R;

public class homeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ListenerButton listenerButton = new ListenerButton(this);
        Button button = (Button)findViewById(R.id.buttonCalcularIMC);
        button.setOnClickListener(listenerButton);
        Button buttonLogin = (Button)findViewById(R.id.myButtonLogout);
        buttonLogin.setOnClickListener(listenerButton);

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

                        Intent intent = new Intent(homeActivity.this, MainActivity.class);
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
