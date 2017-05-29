package com.example.usuario.proyectodam2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
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
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class main_screen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //final Context context = this;
    String zona, esp, nomRest,ciutat;
    private int i = 0;
    public static String[] ciutats = new String[100];
    public static String[] zonass = new String[100];
    public static String[] especialitats = new String[100];
    private EditText etSearch;
    public static Handler handler, handler2, handler3, handler4,handler5,handler6;
    ArrayList<localizaciones> cord = new ArrayList<>();
    ArrayList<Double> lats = new ArrayList<>();
    ArrayList<Double> longs = new ArrayList<>();
    ArrayList<String> noms = new ArrayList<>();


    public Context context, context2;
    public LinearLayout linear, linear2;
    private JSONObject json,json2;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        String[] values = {"My Profile", "Settings", "Log out"};
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(LTRadapter);
        spinner.setOnItemSelectedListener(this);
        /*






        */
        linear = (LinearLayout) findViewById(R.id.layoutImportant);

        context = this;

        context2 = this;
        etSearch = (EditText) (findViewById(R.id.etSearch));



        // si li donc al botó filtrar del main screen
        Button btfiltres = (Button) (findViewById(R.id.btnFiltros));
        btfiltres.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                List_Connection list = new List_Connection();
                list.execute();
                List_Zona_Connection list2=new List_Zona_Connection();
                list2.execute();
                List_Esp_Connection list3=new List_Esp_Connection();
                list3.execute();
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.filters_dialog);
                dialog.setTitle("Title...");
                final Spinner spCiutats=(Spinner)dialog.findViewById(R.id.sp3);
                final Spinner spZonas = (Spinner) dialog.findViewById(R.id.sp1);
                final Spinner spEsp = (Spinner) dialog.findViewById(R.id.sp2);
                //final ListView lvCiutats = (ListView) dialog.findViewById(R.id.lv3);
                /*final Spinner spCiutats = (Spinner) findViewById(R.id.spCiutats);

                final Spinner spEspecialitats = (Spinner) findViewById(R.id.spEspecialitats);*/
                final RatingBar rb1 = (RatingBar) dialog.findViewById(R.id.rBar1);
                final List<String> ciudades = new ArrayList<String>();
                final List<String> zonas = new ArrayList<String>();
                final List<String> especialidades = new ArrayList<String>();
                handler4 = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if (msg.getData().getInt("state") == 1) {

                            while (ciutats[i] != null) {
                                ciudades.add(ciutats[i]);
                                i++;
                            }

                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                                    context,
                                    android.R.layout.simple_spinner_item,
                                    ciudades);
                            //arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);


                            spCiutats.setAdapter(arrayAdapter);


                        } else {
                            Log.d("Avis", "No ha entrar al if de omplir llistes");
                        }
                    }
                };
                handler5 = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        i=0;
                        if (msg.getData().getInt("state") == 1) {

                            while (zonass[i] != null) {
                                zonas.add(zonass[i]);
                                i++;
                            }

                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                                    context,
                                    android.R.layout.simple_spinner_item,
                                    zonas);
                            //arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);


                            spZonas.setAdapter(arrayAdapter);


                        } else {
                            Log.d("Avis", "No ha entrar al if de omplir llistes");
                        }
                    }
                };

                handler6 = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        i=0;
                        if (msg.getData().getInt("state") == 1) {

                            while (especialitats[i] != null) {
                                especialidades.add(especialitats[i]);
                                i++;
                            }

                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                                    context,
                                    android.R.layout.simple_spinner_item,
                                    especialidades);
                            //arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);


                            spEsp.setAdapter(arrayAdapter);


                        } else {
                            Log.d("Avis", "No ha entrar al if de omplir llistes");
                        }
                    }
                };


                // set the custom dialog components - text, image and button
               spCiutats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                       ciutat=(String)   spCiutats.getItemAtPosition(i);
                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> parent) {
                        ciutat=null;
                   }
               });
                spZonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        zona=(String)   spZonas.getItemAtPosition(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        zona=null;
                    }
                });
                spEsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        esp=(String)   spEsp.getItemAtPosition(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        esp=null;
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
                        float[] punts = new float[]{rb1.getRating()};
                        //IGUAL ES EL CONTEXT QUE ES CONTEXT 2
                        final Main_Connection connection = new Main_Connection(ciutat, zona, esp, null, punts[0], context);
                        //Main_Connection connection=new Main_Connection("Martorelles","Montanya","Xines","Hola",punts[0]);
                        connection.execute(linear);
                        handler = new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                super.handleMessage(msg);
                                if (msg.getData().getInt("state") == 1) {

                                    // JSONObject json=connection.getJson();
                                    json2 = connection.getJson();
                                    final descarregarimg_rest desc = new descarregarimg_rest(json2, context2);
                                    desc.execute(linear);
                                    cord = desc.coord;

                                } else {

                                }

                            }
                        };
                        //J
                        //JSONObject json = connection.getJson();
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
                nomRest = etSearch.getText().toString();
                linear.removeAllViews();
                final Search_Connection connection2 = new Search_Connection(nomRest, context2);

                connection2.execute(linear);
                handler2 = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if (msg.getData().getInt("state") == 1) {
                            //Intent main_screen = new Intent(getApplicationContext(), main_screen.class);
                            //startActivity(main_screen);
                            //mostrem la llista de restaurants
                            json = connection2.getjson();
                            final descarregarimg_rest desc = new descarregarimg_rest(json, context2);
                            desc.execute(linear);
                            cord = desc.coord;
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
                for (int x = 0; x < cord.size(); x++) {
                    lats.add(cord.get(x).lat);
                    longs.add(cord.get(x).lon);
                    noms.add(cord.get(x).nom);
                }
                Intent rut = new Intent(getApplicationContext(), ruta_screen.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("la", (Serializable) lats);
                bundle.putSerializable("lo", (Serializable) longs);
                bundle.putSerializable("no", (Serializable) noms);


                rut.putExtras(bundle);
                startActivity(rut);
                //ruta.coord=coord;

            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getSelectedItem().toString()) {
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "main_screen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.usuario.proyectodam2/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "main_screen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.usuario.proyectodam2/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
