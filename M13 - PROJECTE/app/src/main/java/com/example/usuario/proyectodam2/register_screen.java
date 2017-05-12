package com.example.usuario.proyectodam2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class register_screen extends AppCompatActivity implements View.OnClickListener {
    private Button btnSign;
    private EditText etLogin, etPass1,etPass2, etMail;
    private String login, pass1, pass2, mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        btnSign=(Button) (findViewById(R.id.btnSign));
        btnSign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btnSign)
        {

            pass1=etPass1.getText().toString();
            pass2=etPass2.getText().toString();
            login=etLogin.getText().toString();
            mail=etMail.getText().toString();

            if(pass1=="" || pass2=="" || login=="" || mail=="")
            {
                //mostreml el missatge de conforme es tenen que omplir tots els camps
            }
            else
                if(pass1 != pass2)
                {
                    //mostrem el missatge de que les dues contrasenyes no son iguals
                }
                else

                    {
                        
                    }
        }
    }
}
