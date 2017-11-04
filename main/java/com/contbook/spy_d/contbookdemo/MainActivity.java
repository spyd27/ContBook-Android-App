package com.contbook.spy_d.contbookdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button BtnRegister;
    private Button BtnLogin;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnRegister=  (Button) findViewById(R.id.BtnRegister);
        BtnLogin=  (Button) findViewById(R.id.BtnLogin);

        BtnRegister.setOnClickListener(this);
        BtnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == BtnRegister) {
            Intent i = new Intent(this, SignupActivity.class);
            startActivity(i);
        }
        if (v == BtnLogin) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    }
}
