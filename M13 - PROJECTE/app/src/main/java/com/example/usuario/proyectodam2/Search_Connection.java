package com.example.usuario.proyectodam2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.usuario.proyectodam2.login_screen.handler;
import static com.example.usuario.proyectodam2.main_screen.handler2;
/**
 * Created by Bernat on 18/05/2017.
 */
public class Search_Connection extends AsyncTask<Void,Void,JSONObject> {
    private String nomRest;
    private int state;
    Bundle bundle=new Bundle();
    public Search_Connection(String rest){
        this.nomRest=rest;
    }


    @Override
    protected JSONObject doInBackground(Void... params) {
        JSONObject json=null;
        URL url = null;

        try {
            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurant_Search.php?nom="+nomRest);
            HttpURLConnection urlConnection;
            urlConnection = (HttpURLConnection)url.openConnection();


            urlConnection.setRequestMethod("GET");//DUDA
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);

            //urlConnection.setDoOutput(true);

            //urlConnection.connect();
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

            System.out.println("JSON: " + jsonString);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return json;

    }


    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        try {
            state=jsonObject.getInt("estado");

            bundle.putInt("state",state);
            Message msg=new Message();
            msg.setData(bundle);
            handler.sendMessage(msg);
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }
}
