package com.example.usuario.proyectodam2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.usuario.proyectodam2.main_screen.handler2;

/**
 * Created by Bernat on 18/05/2017.
 */
public class Search_Connection extends AsyncTask<View,Void,JSONObject> {
    private String nomRest,nom,imatge,ciutat2,imatgereal;
    private int state;
    private URL imageUrl=null;
    private Bitmap imagen;
    private JSONObject json=null;
    private ImageView imgRest;
    private ImageView imgRestProva=null;
    private String direccioimg="http://hungrycrossing.000webhostapp.com";
    private Context cont;
    private LinearLayout layoutimportant2;
    Bundle bundle=new Bundle();
    public Search_Connection(String rest,Context context){
        this.nomRest=rest;
        this.cont=context;
    }
    public JSONObject getjson()
    {
        return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        /*int i=0;
        try {

            for(i=0;i<jsonObject.length()-1;i++) {
                // json = jsonObject.getJSONObject("0");
                json=jsonObject.getJSONObject(""+i+"");
                nom = json.getString("Nom");
                imatge=json.getString("Imatge");
                imatgereal=direccioimg+imatge;
                ciutat2=json.getString("NomPob");
                addChild(nom,imatgereal,ciutat2);
                   } catch (JSONException e) {
            e.printStackTrace();

        }
            }*/
        try {
            state=jsonObject.getInt("estado");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        bundle.putInt("state",state);
            Message msg=new Message();
            msg.setData(bundle);
            handler2.sendMessage(msg);

    }

    private void addChild(String nom, String imatgereal, String ciutat3) {
        String nome,imatgee,city;
        nome=nom;
        imatgee=imatgereal;
        city=ciutat3;
        /*Connexio per obtenir imatge*/
        //HttpURLConnection conn=null;
        /*try {


            imageUrl=new URL(imatgee);
            conn=(HttpURLConnection)imageUrl.openConnection();
            conn.connect();

            InputStream in=new BufferedInputStream(conn.getInputStream());
            imagen= BitmapFactory.decodeStream(in);
            //imagen= BitmapFactory.decodeStream(conn.getInputStream());

            imgRest=new ImageView(cont);
            imgRest.setImageBitmap(imagen);
            imgRest.setPadding(10,10,10,10);

        } catch (Exception e) {
            e.printStackTrace();
        }*/


        /****************************/
       /* LinearLayout layout2=new LinearLayout(cont);
        layout2.setBackgroundResource(R.drawable.border);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        ImageView imatge3=new ImageView(cont);

        imatge3.setImageResource(R.drawable.foto_perfil_user);

        imatge3.setContentDescription(imatgee);
        imatge3.setTop(20);
        imatge3.setScaleX(0.5f);
        imatge3.setScaleY(0.5f);
        TextView tvnomRest=new TextView(cont);
        tvnomRest.setText(nome);
        tvnomRest.setPadding(10,10,10,10);
        TextView tvnomCiutat=new TextView(cont);
        tvnomCiutat.setText(city);
        tvnomCiutat.setPadding(10,10,10,10);

        layout2.addView(imatge3);
        layout2.addView(tvnomRest);
        layout2.addView(tvnomCiutat);


        layout2.setBackgroundColor(Color.rgb(255, 168, 43));
        layout2.setGravity(2);
        layout2.setMinimumWidth(700);
        layout2.setMinimumHeight(300);
        layout2.setBackgroundResource(R.drawable.border2);


        layout2.setClickable(true);//per accedir a la pagina del restaurnat individual

        layoutimportant2.addView(layout2);*/

    }
    @Override
    protected JSONObject doInBackground(View... view) {
        //layoutimportant2= (LinearLayout) view[0];

        URL url = null;

        try {
            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurant_Search.php?nom="+nomRest);
            HttpURLConnection urlConnection=null;
            urlConnection = (HttpURLConnection)url.openConnection();
            int status = urlConnection.getResponseCode();

            urlConnection.setRequestMethod("GET");//DUDA
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);

            //urlConnection.setDoOutput(true);

            urlConnection.connect();
            //status = urlConnection.getResponseCode();

            BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));

            char[] buffer = new char[1024];

            String jsonString = "";

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            json= new JSONObject(sb.toString());

            //System.out.println("JSON: " + jsonString);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return json;

    }



}
