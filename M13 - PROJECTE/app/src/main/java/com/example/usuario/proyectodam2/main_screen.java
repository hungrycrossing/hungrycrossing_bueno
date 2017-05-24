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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class main_screen extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    //final Context context = this;
    String zona,esp,nomRest;
    private EditText etSearch;
    public static Handler handler,handler2, handler3;
    ArrayList<localizaciones> cord=new ArrayList<>();
    ArrayList<Double> lats=new ArrayList<>();
    ArrayList<Double> longs=new ArrayList<>();
    ArrayList<String> noms=new ArrayList<>();
    public Context context,context2;
    public LinearLayout linear,linear2;
    private JSONObject json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        String [] values = {"My Profile","Settings","Log out"};
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(LTRadapter);
        spinner.setOnItemSelectedListener(this);

        linear=(LinearLayout) findViewById(R.id.layoutImportant);
        //linear2=(LinearLayout) findViewById(R.id.layoutImportant);
        context=this;
        context2=this;
        etSearch=(EditText)(findViewById(R.id.etSearch));

     /*  final Main_Connection conCreate=new Main_Connection(null,null,null,null,0.0f,context);
        conCreate.execute();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.getData().getInt("state")==1)
                {
                    JSONObject jsoncreate=conCreate.getJson();
                    final descarregarimg_rest desc=new descarregarimg_rest(json,context2);
                    desc.execute(linear);
                }
                else
                {

                }

            }
        };*/

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
                        linear.removeAllViews();
                        //primero de toddo llamaré a la clase para conectarse con el php de filtros el cual me
                        //va a devolver un json con la lista de restaurantes segun los filtros que yo le indique
                        float[] punts = new float[]{ rb1.getRating() };
                        final Main_Connection connection=new Main_Connection(null,zona,esp,null,punts[0],context);
                        //Main_Connection connection=new Main_Connection("Martorelles","Montanya","Xines","Hola",punts[0]);
                        connection.execute(linear);
                        handler=new Handler(){
                            @Override
                            public void handleMessage(Message msg) {
                                super.handleMessage(msg);
                                if(msg.getData().getInt("state")==1)
                                {

                                   // JSONObject json=connection.getJson();
                                    json=connection.getJson();
                                    final descarregarimg_rest desc=new descarregarimg_rest(json,context2);
                                    desc.execute(linear);
                                    cord=desc.coord;

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
                nomRest=etSearch.getText().toString();
                linear.removeAllViews();
                final Search_Connection connection2 = new Search_Connection(nomRest,context2);

                connection2.execute(linear);
                handler2=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if(msg.getData().getInt("state")==1)
                        {
                            //Intent main_screen = new Intent(getApplicationContext(), main_screen.class);
                            //startActivity(main_screen);
                            //mostrem la llista de restaurants
                            json=connection2.getjson();
                            final descarregarimg_rest desc=new descarregarimg_rest(json,context2);
                            desc.execute(linear);
                           cord=desc.coord;
                        }
                        //else
                            //error.setVisibility(View.VISIBLE);
                    }


                };
            }
        });
        Button btRuta = (Button) (findViewById(R.id.btnRuta));
        btRuta.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
               // ruta_screen ruta=new ruta_screen(coord);

                //bundle.putArrayLis
                //ruta_screen ruta=new ruta_screen();
                //ruta.coord=coord;
                for(int x=0;x<cord.size();x++) {
                    lats.add(cord.get(x).lat);
                    longs.add(cord.get(x).lon);
                    noms.add(cord.get(x).nom);
                }
                Intent rut = new Intent(getApplicationContext(), ruta_screen.class);
                Bundle bundle=new Bundle();

                bundle.putSerializable("la", (Serializable) lats);
                bundle.putSerializable("lo", (Serializable) longs);
                bundle.putSerializable("no", (Serializable) noms);


                rut.putExtras(bundle);
                startActivity(rut);
                //ruta.coord=coord;

            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getSelectedItem().toString()) {
            case ("My Profile"):
                break;
            case ("Settings"):
                break;
            case ("Log out"):
                Intent login_screen = new Intent(getApplicationContext(), login_screen.class);
                startActivity(login_screen);

                this.finish();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public Context getActivity() {
        return this;
    }

}
