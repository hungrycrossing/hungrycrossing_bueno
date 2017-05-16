package com.example.usuario.proyectodam2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class login_screen extends AppCompatActivity implements View.OnClickListener{
    private Button btLog, btnRegister;
    private EditText etUsername;
    private EditText etPssw;
    private String pass,username;
    TextView error;
    public static int status;
    HTTPConnection connection;
    public static Handler handler;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_screen);
            btLog=(Button) (findViewById(R.id.btnLogin));
            btLog.setOnClickListener(this);
            btnRegister=(Button) (findViewById(R.id.btnRegister));
            btnRegister.setOnClickListener(this);

            etUsername=(EditText)(findViewById(R.id.etUsernameLogin));
            etPssw=(EditText)(findViewById(R.id.etPasswordLogin));
            error=(TextView)(findViewById(R.id.tvErrorLogin));

        }

        @Override
        public void onClick(View v) {
            if(v==btLog) {
                handler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if(msg.getData().getInt("state")==1)
                        {
                            Intent main_screen = new Intent(getApplicationContext(), main_screen.class);
                            startActivity(main_screen);
                            //pasem a la pagina principal
                        }
                        else
                            error.setVisibility(View.VISIBLE);
                    }
                };

                pass = etPssw.getText().toString();
                username = etUsername.getText().toString();
                    connection = new HTTPConnection(username, pass);

                    connection.execute();

                  //  ssadfd();

                    }






                if (v == btnRegister) //lleva al activity de registrar
                {
                    Intent register_screen = new Intent(getApplicationContext(), register_screen.class);
                    startActivity(register_screen);
                }

        }

        private void ssadfd()
        {
            if(connection.getStatus()== AsyncTask.Status.FINISHED)
            {
                status=connection.getState();   //Amb aixo bloquejo la pantalla fins que l'httpconnection retorni el reusltat

                if (status == 1)//cas correcte
                {
                    Intent main_screen = new Intent(getApplicationContext(), main_screen.class);
                    startActivity(main_screen);
                    //pasem a la pagina principal


                } else //introduce user o psw que no tocan
                {
                    error.setVisibility(View.VISIBLE);
                }
            }
        }
}
