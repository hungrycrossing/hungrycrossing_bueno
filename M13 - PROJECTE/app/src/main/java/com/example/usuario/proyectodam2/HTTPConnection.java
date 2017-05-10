package com.example.usuario.proyectodam2;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Bernat on 09/05/2017.
 */
public class HTTPConnection extends AsyncTask<Void,Void,JSONObject> {
    String login;
    String password;
    private int status;
    public  HTTPConnection(String log, String pass)
    {
        login = log;
        password = pass;

    }



    @Override
    protected JSONObject doInBackground(Void... params) {
        JSONObject json=null;
        URL url = null;
        try {
            url = new URL("http://hungrycrossing.000webhostapp/ComprobarLogin.php?nombre=" + login + "&pass=" + password );
            HttpURLConnection urlConnection = null;
            urlConnection = (HttpURLConnection)url.openConnection();
            status = urlConnection.getResponseCode();


            urlConnection.setRequestMethod("GET");//DUDA
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);

            urlConnection.setDoOutput(true);

            urlConnection.connect();

            BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));

            char[] buffer = new char[1024];

            String jsonString = new String();

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            br.close();

            json= new JSONObject(sb.toString());

            System.out.println("JSON: " + jsonString);



        } catch (MalformedURLException e) {
            e.printStackTrace();
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
            jsonObject.getString("estado");
        } catch (JSONException e) {
            e.printStackTrace();

        }
        //es retorna el jsonObject de forma estatica (no tinc ni P idea)

    }

}
