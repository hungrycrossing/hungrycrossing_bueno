package com.example.usuario.proyectodam2;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class register_screen extends AppCompatActivity implements View.OnClickListener {
    private Button btnSign;
    private EditText etLogin, etPass1,etPass2, etMail, etCp, etNom;
    private String login, pass1, pass2, mail, cp,nom;
    private Register_connection register;
    private TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        btnSign=(Button) (findViewById(R.id.btnSign));
        btnSign.setOnClickListener(this);
<<<<<<< HEAD
        error=(TextView)findViewById(R.id.tvErrorReg);
        etLogin=(EditText)findViewById(R.id.etUsername);
        etPass1=(EditText)findViewById(R.id.etPassword);
        etPass2=(EditText)findViewById(R.id.etConfirmPass);
        etMail=(EditText)findViewById(R.id.etMail);
=======
        error=(TextView)(findViewById(R.id.tvErrorReg));

        etLogin=(EditText)(findViewById(R.id.etUsername));
        etPass1=(EditText)(findViewById(R.id.etPassword));
        etPass2=(EditText)(findViewById(R.id.etConfirmPass));
        etMail=(EditText)(findViewById(R.id.etMail));
        etCp=(EditText)(findViewById(R.id.etCP));
        etNom=(EditText)(findViewById(R.id.etName));
>>>>>>> origin/master
    }

    @Override
    public void onClick(View v) {
        if(v==btnSign)
        {

            int status;
            pass1=etPass1.getText().toString();
            pass2=etPass2.getText().toString();
            login=etLogin.getText().toString();
            mail=etMail.getText().toString();
            nom=etNom.getText().toString();
            cp=etCp.getText().toString();


            if(pass1=="" || pass2=="" || login=="" || mail=="" || cp=="" ||nom=="")
            {
                error.setText("You must fill all the fields");
                error.setVisibility(View.VISIBLE);
                //mostreml el missatge de conforme es tenen que omplir tots els camps
            }
            else
                if(pass1 == pass2)//si poso != va malament encara que es el que hauriam de posar
                {
                    error.setText("Passwords don't match");
                    error.setVisibility(View.VISIBLE);
                    //mostrem el missatge de que les dues contrasenyes no son iguals
                }
                else
                    if(cp.length()!=5)
                    {
                        error.setText("Write a correct Postal Code");
                        error.setVisibility(View.VISIBLE);
                        //mostrem el missatge de que les dues contrasenyes no son iguals
                    }
                else

                    {
                        register=new Register_connection(mail,pass1,login,nom,cp);
                        register.execute();
                        status=register.state;
                        if (status == 1)//cas correcte
                        {

                            final Dialog dialog = new Dialog(this);
                            dialog.setContentView(R.layout.mailenviat_dialog);
                            dialog.setTitle("");

                            // set the custom dialog components - text, image and button

                            Button ok = (Button) dialog.findViewById(R.id.btnOkmail);
                           // RatingBar rb1=(RatingBar) dialog.findViewById(R.id.rBar1);


                            //Button dialogButton = (Button) dialog.findViewById(R.id.btFiltrarJa);
                            // if button is clicked, close the custom dialog
                            ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    Intent login_screen = new Intent(getApplicationContext(), login_screen.class);
                                    startActivity(login_screen);
                                    finish();
                                }
                            });

                            dialog.show();
                            //se abriria un aviso conforome se ha enviado el mail y cuando le demos a aceptar nos llevaria a la
                            //pagina del login
                            //si no hemos confirmado el mail entonces cuando nos logueemos no nos dejará y saldra una ventana

                        } else if(status==2)//introduce user o psw que no tocan
                        {
                            error.setText("ERROR");
                            error.setVisibility(View.VISIBLE);
                        }
                    }
        }
    }
}
