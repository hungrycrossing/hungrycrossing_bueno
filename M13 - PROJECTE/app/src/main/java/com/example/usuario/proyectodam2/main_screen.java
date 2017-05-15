package com.example.usuario.proyectodam2;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RatingBar;

public class main_screen extends AppCompatActivity {
    final Context context = this;
    String zona,esp;
    Main_Connection connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

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
                        connection=new Main_Connection(null,zona,esp,null,punts[0]);
                        connection.execute();
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }


        });
    }
}
