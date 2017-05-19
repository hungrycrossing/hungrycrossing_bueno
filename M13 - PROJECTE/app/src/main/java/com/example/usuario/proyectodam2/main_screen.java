package com.example.usuario.proyectodam2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import org.json.JSONObject;

public class main_screen extends AppCompatActivity {
    final Context context = this;
    String zona,esp,nomRest;
    private EditText etSearch;
    public static Handler handler,handler2;
    Search_Connection connection2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        etSearch=(EditText)(findViewById(R.id.etSearch));
        // si li donc al botó filtrar del main screen
        Button btfiltres = (Button) (findViewById(R.id.btnFiltros));
        btfiltres.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.filters_dialog);
                dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                final ListView lvZonas = (ListView) dialog.findViewById(R.id.lv1);
                final ListView lvEspecialitats = (ListView) dialog.findViewById(R.id.lv2);
                final RatingBar rb1=(RatingBar) dialog.findViewById(R.id.rBar1);


                lvEspecialitats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        esp=(String)   lvEspecialitats.getItemAtPosition(i);

                    }
                });
                lvZonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        zona=(String)   lvZonas.getItemAtPosition(i);

                    }
                });
                Button dialogButton = (Button) dialog.findViewById(R.id.btFiltrarJa);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //primero de toddo llamaré a la clase para conectarse con el php de filtros el cual me
                        //va a devolver un json con la lista de restaurantes segun los filtros que yo le indique
                        float[] punts = new float[]{ rb1.getRating() };
                        Main_Connection connection=new Main_Connection(null,zona,esp,null,punts[0]);
                        //Main_Connection connection=new Main_Connection("Martorelles","Montanya","Xines","Hola",punts[0]);
                        connection.execute();
                        handler=new Handler(){
                            @Override
                            public void handleMessage(Message msg) {
                                super.handleMessage(msg);
                                if(msg.getData().getInt("state")==1)
                                {
                                    //JSONObject json=connection.getJson();
                                }
                                else
                                {
                                }

                            }
                        };
                        //J
                        JSONObject json=connection.getJson();
                        dialog.dismiss();


                    }
                });

                dialog.show();
            }


        });
        //si li donc al botó search del main screen
        Button btSearch = (Button) (findViewById(R.id.btSearch));
        btSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                handler2=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if(msg.getData().getInt("state")==1)
                        {
                            //Intent main_screen = new Intent(getApplicationContext(), main_screen.class);
                            //startActivity(main_screen);
                            //mostrem la llista de restaurants
                        }
                        //else
                            //error.setVisibility(View.VISIBLE);
                    }
                };



                nomRest=etSearch.getText().toString();

                connection2 = new Search_Connection(nomRest);

                connection2.execute();

            }
        });

    }
}
