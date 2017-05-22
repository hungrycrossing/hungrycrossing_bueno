package com.example.usuario.proyectodam2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import static com.example.usuario.proyectodam2.main_screen.handler3;
/**
 * Created by usuario on 19/05/2017.
 */

public class descarregarimg_rest extends AsyncTask<View,Void,JSONObject> {
    String ruta, nom, imatge, imatgereal, ciutat2;

    JSONObject json, jsonObject;
    Bitmap imagen;
    Bitmap[] array=new Bitmap[100];
    Context cont;
    private String direccioimg="http://hungrycrossing.000webhostapp.com";

    Bundle bundle=new Bundle();
    ImageView imgRest;

    private LinearLayout layoutimportant2;
    public descarregarimg_rest(JSONObject json, Context cont)
    {
        this.jsonObject=json;
        this.cont=cont;
    }



    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    private void addChild(String nom, Bitmap imatgereal, String ciutat3) {
        String nome,city;
        Bitmap imatgee;
        nome=nom;
        imatgee=imatgereal;
        city=ciutat3;

        /*Connexio per obtenir imatge*/
       // HttpURLConnection conn=null;

           /* URL imageUrl;

            imageUrl=new URL(imatgee);
            conn=(HttpURLConnection)imageUrl.openConnection();
            conn.connect();

            InputStream in=new BufferedInputStream(conn.getInputStream());
            imagen= BitmapFactory.decodeStream(in);
           // imagen=*/
            imgRest=new ImageView(cont);
            imgRest.setImageBitmap(imatgee);
            imgRest.setPadding(10,10,10,10);
        imgRest.setScaleType(ImageView.ScaleType.CENTER_CROP);




        /****************************/

        LinearLayout layout2=new LinearLayout(cont);
        layout2.setBackgroundResource(R.drawable.border);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        /*ImageView imatge3=new ImageView(cont);

        imatge3.setImageResource(R.drawable.foto_perfil_user);

        imatge3.setContentDescription(imatgee);*/
        //imgRest.setTop(20);.
        //imgRest.setLeft(100);
        imgRest.setMaxWidth(200);
        imgRest.setMaxHeight(200);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(500, 500);
        imgRest.setLayoutParams(layoutParams);

        // imgRest.setScaleX(0.5f);
        //imgRest.setScaleY(200);
        TextView tvnomRest=new TextView(cont);
        tvnomRest.setText(nome);
        tvnomRest.setPadding(10,10,10,10);
        TextView tvnomCiutat=new TextView(cont);
        tvnomCiutat.setText(city);
        tvnomCiutat.setPadding(10,10,10,10);

        layout2.addView(imgRest);
        layout2.addView(tvnomRest);
        layout2.addView(tvnomCiutat);


        layout2.setBackgroundColor(Color.rgb(255, 168, 43));
        layout2.setGravity(2);
        layout2.setMinimumWidth(700);
        layout2.setMinimumHeight(300);
        layout2.setBackgroundResource(R.drawable.border2);


        layout2.setClickable(true);//per accedir a la pagina del restaurnat individual

        layoutimportant2.addView(layout2);

    }
    @Override
    protected JSONObject doInBackground(View... views) {
        layoutimportant2= (LinearLayout) views[0];
        HttpURLConnection conn=null;
        try {
            int i=0;


                for(i=0;i<jsonObject.length()-1;i++) {

                    // json = jsonObject.getJSONObject("0");
                    json=jsonObject.getJSONObject(""+i+"");
                    /*nom = json.getString("Nom");*/
                    imatge=json.getString("Imatge");
                    imatgereal=direccioimg+imatge;
                    URL imageUrl;

                    imageUrl=new URL(imatgereal);
                    conn=(HttpURLConnection)imageUrl.openConnection();
                    conn.connect();

                    InputStream in=new BufferedInputStream(conn.getInputStream());
                    imagen= BitmapFactory.decodeStream(in);
                    array[i]=imagen;
                   // ciutat2=json.getString("NomPob");
                    //addChild(nom,imatgereal,ciutat2);
                }
/*            URL imageUrl;
            imageUrl=new URL(ruta);
            conn=(HttpURLConnection)imageUrl.openConnection();
            conn.connect();

            /*InputStream in=new BufferedInputStream(conn.getInputStream());
            imagen= BitmapFactory.decodeStream(in);
            imagen= BitmapFactory.decodeStream(conn.getInputStream());*/

           /* bundle.putInt("state",1);

            Message msg=new Message();
            msg.setData(bundle);
            handlerdesc.sendMessage(msg);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        int i=0;


        for(i=0;i<jsonObject.length()-1;i++) {

            // json = jsonObject.getJSONObject("0");
            try {
                json=jsonObject.getJSONObject(""+i+"");

            nom = json.getString("Nom");

             ciutat2=json.getString("NomPob");
            addChild(nom,array[i],ciutat2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
       /* bundle.putInt("state",1);
        Message msg=new Message();
        msg.setData(bundle);
        main_screen.handler.sendMessage(msg);*/

    }
   /* public Bitmap getBitmap()
    {
        return imagen;
    }*/
}
