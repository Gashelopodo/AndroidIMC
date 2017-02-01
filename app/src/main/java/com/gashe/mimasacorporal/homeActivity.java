package com.gashe.mimasacorporal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

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
}
