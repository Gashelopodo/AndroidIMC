package com.gashe.mimasacorporal.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.gashe.mimasacorporal.listeners.ListenerButton;
import com.gashe.mimasacorporal.R;
import com.gashe.mimasacorporal.classes.consultasDataBase;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //SqlScoutServer.create(this, getPackageName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Menu


        ListenerButton listenerButton = new ListenerButton(this);
        Button buttonLogin = (Button)findViewById(R.id.buttonLogin);
        Button buttonRegister = (Button)findViewById(R.id.buttonRegister);
        buttonLogin.setOnClickListener(listenerButton);
        buttonRegister.setOnClickListener(listenerButton);

        final SharedPreferences prefs = getSharedPreferences("pref", Context.MODE_PRIVATE);
        boolean login = prefs.getBoolean("login", false);
        int intentos = prefs.getInt("intentos", 0);

        if(login){
            Intent intent = new Intent(this, homeActivity.class);
            startActivity(intent);
            finishAffinity();
        }

        if(intentos > 0){
            SharedPreferences.Editor editor =  prefs.edit();
            editor.putInt("intentos", 0);
            editor.commit();
        }

        consultasDataBase query = new consultasDataBase(this, "imcDB", null, 1);
        //query.vaciarTabla();
        /*
        List<User> userList = query.mostrarUsers();
        for (User user : userList) {
            Log.d(getClass().getCanonicalName(), "USER = "+user.getUsuario()+" Pass: "+user.getPass());
        }
        */

    }

}
