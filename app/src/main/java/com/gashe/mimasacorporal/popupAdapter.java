package com.gashe.mimasacorporal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Gashe on 19/1/17.
 */

public class popupAdapter extends BaseAdapter {

    private Context ct;

    public popupAdapter(Context ct) {
        this.ct = ct;
    }

    public int getCount() {
        return imagenes.length;
    }

    public Object getItem(int position) {
        return null;
    }


    public long getItemId(int position) {
        return 0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {


        View elemento = null;
        Activity activity = (Activity) ct;
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        elemento = layoutInflater.inflate(R.layout.row_popup, parent, false);
        ImageView imageView = (ImageView)elemento.findViewById(R.id.imageView);
        TextView textView = (TextView) elemento.findViewById(R.id.textView);
        imageView.setImageResource(imagenes[position]);
        textView.setText(textos[position]);

        return elemento;

    }


    private Integer[] imagenes = {
            R.drawable.fat, R.drawable.fat, R.drawable.fat, R.drawable.fat, R.drawable.fat
    };

    private String[] textos = {
            "IMC > 31 (Obeso)", "IMC > 25 y IMC < 31 (Sobrepeso)", "IMC > 18 y IMC < 25 (Normal)",
            "IMC > 16 y IMC < 18 (Bajo peso)", "IMC < 16 (Desnutrido)"
    };

}


