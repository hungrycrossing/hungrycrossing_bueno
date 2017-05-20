package com.example.usuario.proyectodam2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class loading_screen extends AppCompatActivity implements View.OnClickListener{
    private Button btLog;
    private EditText etUsername;
    private EditText etPssw;
    private String pass,username;
    private int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_screen);
        Intent login_screen= new  Intent(getApplicationContext(), login_screen.class);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(login_screen);
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}
