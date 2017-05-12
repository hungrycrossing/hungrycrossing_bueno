package com.example.usuario.proyectodam2;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RatingBar;

public class main_screen extends AppCompatActivity {
    final Context context = this;
    private Button btfiltres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        btfiltres=(Button) (findViewById(R.id.btnFiltros));


        btfiltres.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.filters_dialog);
                dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                ListView lv1 = (ListView) dialog.findViewById(R.id.lv1);
                ListView lv2 = (ListView) dialog.findViewById(R.id.lv2);
                RatingBar rb1=(RatingBar) dialog.findViewById(R.id.rBar1);


                Button dialogButton = (Button) dialog.findViewById(R.id.btFiltrarJa);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }


        });
    }
}
