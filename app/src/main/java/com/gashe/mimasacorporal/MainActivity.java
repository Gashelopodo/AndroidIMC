package com.gashe.mimasacorporal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListenerButton listenerButton = new ListenerButton(this, "buttonCalculo");
        Button button = (Button)findViewById(R.id.buttonCalcularIMC);
        button.setOnClickListener(listenerButton);


    }
}
