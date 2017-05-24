package com.example.usuario.proyectodam2;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Bernat on 23/05/2017.
 */
public class Comentari_Connection extends AsyncTask<Void,Void,JSONObject> {
    private String comentari,direccio2,usuari,titolOpinio;
    private Float puntuacio;
    private int state;
    public Comentari_Connection(String opinioUser, String direccio, String user, float puntuacio, String titol){
        this.comentari=opinioUser;
        this.direccio2=direccio;
        this.usuari=user;
        this.titolOpinio=titol;
        this.puntuacio=puntuacio;
    }
    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        JSONObject json=null;
        URL url;
        try {
            url = new URL("http://hungrycrossing.000webhostapp.com/Inserir_Opinio.php?opinio="+comentari+"&direccio="+direccio2+"&login="+usuari+"&titol="+titolOpinio+"&puntuacio="+puntuacio);

            HttpURLConnection urlConnection = null;
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
}
