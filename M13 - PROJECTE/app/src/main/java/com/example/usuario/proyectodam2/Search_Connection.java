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
import java.net.URLConnection;

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
       /* try {
            state=jsonObject.getInt("estado");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        bundle.putInt("state",1);
            Message msg=new Message();
            msg.setData(bundle);
            handler2.sendMessage(msg);

    }

    @Override
    protected JSONObject doInBackground(View... view) {
        //layoutimportant2= (LinearLayout) view[0];

        URL url = null;

        try {
            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurant_Search.php?nom="+nomRest);
            HttpURLConnection urlConnection=null;
            urlConnection = (HttpURLConnection)url.openConnection();
            //int status = urlConnection.getResponseCode();

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
            urlConnection.disconnect();
            //System.out.println("JSON: " + jsonString);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return json;

    }



}
