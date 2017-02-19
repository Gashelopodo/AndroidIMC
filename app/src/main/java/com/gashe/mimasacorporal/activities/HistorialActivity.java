package com.gashe.mimasacorporal.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gashe.mimasacorporal.R;
import com.gashe.mimasacorporal.adapters.HistorialAdapter;
import com.gashe.mimasacorporal.classes.CompareDate;
import com.gashe.mimasacorporal.classes.CompareIMC;
import com.gashe.mimasacorporal.classes.Historial;
import com.gashe.mimasacorporal.classes.User;
import com.gashe.mimasacorporal.classes.consultasDataBase;

import java.util.Collections;
import java.util.List;

public class HistorialActivity extends AppCompatActivity {

    List<Historial> historialList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        SharedPreferences prefs = getSharedPreferences("pref", Context.MODE_PRIVATE);
        int userId = prefs.getInt("userId", 0);

        consultasDataBase query = new consultasDataBase(this, "imcDB", null, 1);
        historialList = query.mostrarHistorial(userId);

        ListView listView = (ListView) findViewById(R.id.myListView);
        if(historialList != null) {
            listView.setAdapter(new HistorialAdapter(this, historialList));
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE, 1, 1, "ORDENAR POR IMC");
        menu.add(Menu.NONE, 2, 1, "ORDENAR POR FECHA");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case 1:
                Collections.sort(historialList, new CompareIMC());
                break;
            case 2:
                Collections.sort(historialList, new CompareDate());
                break;
        }

        ListView listView = (ListView)findViewById(R.id.myListView);
        listView.setAdapter(new HistorialAdapter(this, historialList));

        return true;
    }


}
