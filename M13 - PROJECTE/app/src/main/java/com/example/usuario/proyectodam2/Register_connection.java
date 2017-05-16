package com.example.usuario.proyectodam2;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import static com.example.usuario.proyectodam2.register_screen.handler;


/**
 * Created by usuario on 13/05/2017.
 */

public class Register_connection extends AsyncTask<Void,Void,JSONObject> {
    private int state;
    private String mail,password,login,nom,cp;
    Bundle bundle=new Bundle();

    public Register_connection(String mail, String pass, String login, String nom, String cp)
    {
        this.mail=mail;
        this.password=pass;
        this.login=login;
        this.nom=nom;
        this.cp=cp;
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        JSONObject json=null;
        URL url;
        try {
            url = new URL("http://hungrycrossing.000webhostapp.com/Inserir_Usuaris.php?mail=" + mail + "&pssw=" + password +"&state=0&login="+login+"&nom="+nom+"&CP="+cp+"&datanaix=1990-10-10");
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
    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        Log.d("Avis","Inici pst execute ");
        try {
            state=jsonObject.getInt("estado");

            bundle.putInt("state",state);
            Message msg=new Message();
            msg.setData(bundle);
            handler.sendMessage(msg);

        } catch (JSONException e) {
            e.printStackTrace();
        }    }
}
