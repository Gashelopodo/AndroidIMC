package com.gashe.mimasacorporal.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gashe.mimasacorporal.R;
import com.gashe.mimasacorporal.classes.Historial;

import java.util.List;

/**
 * Created by Gashe on 19/2/17.
 */

public class HistorialAdapter extends BaseAdapter {


    private Context context;
    private List<Historial> historialList;

    public HistorialAdapter(Context context, List<Historial> historialList) {
        this.context = context;
        this.historialList = historialList;
    }

    @Override
    public int getCount() {
        return historialList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View elemento;
        Activity activity = (Activity)context;
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        elemento = layoutInflater.inflate(R.layout.row_historial, viewGroup, false);

        TextView myIMC = (TextView)elemento.findViewById(R.id.myTextIMC);
        TextView myFecha = (TextView)elemento.findViewById(R.id.myTextDate);

        myIMC.setText(Float.toString(historialList.get(i).getImc()));
        myFecha.setText(historialList.get(i).getDate());

        return elemento;
    }
}
