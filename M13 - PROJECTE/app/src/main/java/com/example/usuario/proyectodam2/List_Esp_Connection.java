package com.example.usuario.proyectodam2;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import com.example.usuario.proyectodam2.main_screen;

import static com.example.usuario.proyectodam2.main_screen.handler;
import static com.example.usuario.proyectodam2.main_screen.handler6;
/**
 * Created by Bernat on 24/05/2017.
 */
public class List_Esp_Connection extends AsyncTask<View,Void,JSONObject> {
    public String[] especialitats=new String[100];
    JSONObject json, jsonObject;
    Bundle bundle=new Bundle();

    public List_Esp_Connection(){

    }

    @Override
    protected JSONObject doInBackground(View... view) {
        URL url = null;
        try {
            url = new URL("http://hungrycrossing.000webhostapp.com/Retorna_Especialitat.php");
            HttpURLConnection urlConnection = null;
            urlConnection = (HttpURLConnection)url.openConnection();
            int status = urlConnection.getResponseCode();


            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 );
            urlConnection.setConnectTimeout(15000 );

            //urlConnection.setDoOutput(true);

            urlConnection.connect();

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
            System.out.println("JSON: " + jsonString);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        try {

            for(int i=0;i<jsonObject.length()-1;i++)
            {
                json=jsonObject.getJSONObject(""+i+"");


                especialitats[i]=json.get("Nom").toString();
                main_screen.especialitats[i]=especialitats[i];
            }
            bundle.putInt("state",1);
            Log.d("Avis","FI pst execute ");
            Message msg=new Message();
            msg.setData(bundle);
            handler6.sendMessage(msg);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //main_screen.handler4.sendEmptyMessage(1);

    }

}