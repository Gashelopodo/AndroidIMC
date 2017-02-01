package com.gashe.mimasacorporal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

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
}
