package com.example.androidapptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.content.Intent;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button bConn,bAnnuler;
    EditText edIDT,edMDP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bConn = (Button)findViewById(R.id.button);
        edIDT = (EditText)findViewById(R.id.editText);
        edMDP = (EditText)findViewById(R.id.editText2);
        final Intent intent = new Intent().setClass(this, Main2Activity.class);

        bAnnuler = (Button)findViewById(R.id.button2);

        bConn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edIDT.getText().toString().equals("admin") &&
                        edMDP.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),"bienn !!!",Toast.LENGTH_SHORT).show();

                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "mot de passe ou identifiant faux ",Toast.LENGTH_SHORT).show();
                }
            }
        });

        bAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}