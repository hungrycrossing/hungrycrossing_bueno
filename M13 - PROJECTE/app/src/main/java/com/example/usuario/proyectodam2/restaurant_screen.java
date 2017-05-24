package com.example.usuario.proyectodam2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.FileInputStream;
import java.util.Objects;

public  class restaurant_screen extends AppCompatActivity {
    Bundle b = new Bundle();
    String nomRest,nomCiutat,user;
    Bitmap imatge;
    public static float puntuacio;
    public static String zona,direccio,opinioUser,titolOpinio,direccio2;
    public static String[] opiniones=new String[100];
    private Button btSend;
    private EditText etComentario;
    private TextView tvNomRest,tvZona,tvDireccio,tvPoblacio,tvComentario,tvRating,tvOpiniones,tvOpinion;
    private ImageView imgRest;
    private RatingBar rBar;
    private TextView error;
    public static Handler handler;
    private LinearLayout linOpin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_screen);

        btSend=(Button)findViewById(R.id.btSend);
        imgRest=(ImageView)findViewById(R.id.imgRest);
        rBar=(RatingBar)findViewById(R.id.rBar1);
        tvNomRest=(TextView)findViewById(R.id.tvNomRest);
        tvZona=(TextView)findViewById(R.id.tvZona);
        tvDireccio=(TextView)findViewById(R.id.tvDireccio);
        tvPoblacio=(TextView)findViewById(R.id.tvPoblacio);
        tvComentario=(TextView)findViewById(R.id.tvComentario);
        tvRating=(TextView)findViewById(R.id.tvRating);
        tvOpiniones=(TextView)findViewById(R.id.tvOpiniones);
        error=(TextView)findViewById(R.id.tvErrorComentari);
        etComentario=(EditText)findViewById(R.id.etComentario);
        linOpin=(LinearLayout) findViewById(R.id.LayoutOpinions);
        b=getIntent().getExtras();
        nomRest=b.getString("nomRest");
        nomCiutat=b.getString("nomCiutat");
        direccio2=tvDireccio.getText().toString();
        //imatge=b.getParcelable("img");
        Bitmap bmp = null;
        String filename = getIntent().getStringExtra("img");
        try {
            FileInputStream is = this.openFileInput(filename);
            imatge = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Restaurant_Individual restconnection=new Restaurant_Individual(nomRest,nomCiutat);

        restconnection.execute();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                addChild(nomRest,nomCiutat,zona,direccio,imatge,opiniones);



            }
        };



        //********************************PROBES*******************************/
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opinioUser=etComentario.getText().toString();
                puntuacio=rBar.getRating();
                titolOpinio="";
                if(Objects.equals(opinioUser,""))
                {
                    error.setVisibility(View.VISIBLE);
                }
                else
                {
                    if(Objects.equals(user,null) || Objects.equals(user,"") )
                    {
                        user="a";
                        Comentari_Connection coment = new Comentari_Connection(opinioUser,direccio,user,puntuacio,titolOpinio);
                        coment.execute();
                        finish();
                        startActivity(getIntent());
                    }
                    else
                    {
                        Comentari_Connection coment = new Comentari_Connection(opinioUser,direccio,user,puntuacio,titolOpinio);
                        coment.execute();

                    }
                }

            }
        });





        //********************************FI PROBES****************************/
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent main_screen=  new Intent(this, main_screen.class);
        this.startActivity(main_screen);
        finish();
    }
    private void addChild(String nomRest, String nomCiutat, String zona, String direccio,Bitmap imatge,String[] opiniones) {
        String var2,var3,var4;
        int i=0;
        var2=tvDireccio.getText().toString();
        var3=tvPoblacio.getText().toString();
        var4=tvZona.getText().toString();
        tvNomRest.setText(nomRest);
        tvNomRest.setTextSize(20f);
        tvPoblacio.setText(var3+nomCiutat);
        tvZona.setText(var4+zona);
        tvDireccio.setText(var2+direccio);
        tvDireccio.setPadding(8,0,0,0);
        imgRest.setImageBitmap(imatge);
        imgRest.setMaxHeight(160);
        imgRest.setMaxWidth(150);
        imgRest.setScaleType(ImageView.ScaleType.CENTER_CROP);
        while(opiniones[i]!=null)
        {
            TextView tv=new TextView(this);
            tv.setText(opiniones[i]);
            linOpin.addView(tv);
            i++;
        }




    }
}
