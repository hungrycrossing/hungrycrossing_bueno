package com.example.usuario.proyectodam2;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by usuario on 13/05/2017.
 */

public class Register_connection extends AsyncTask<Void,Void,JSONObject> {
    int status, state;
    String mail,password,login,nom;

    public Register_connection(String mail, String pass, String login, String nom)
    {
        this.mail=mail;
        this.password=pass;
        this.login=login;
        this.nom=nom;
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        JSONObject json=null;


        URL url = null;
        try {
            url = new URL("http://hungrycrossing.000webhostapp.com/Inserir_Usuaris.php?mail=" + mail + "&pssw" + password +"&state=0&login="+login+"&nom="+nom );
            HttpURLConnection urlConnection = null;
            urlConnection = (HttpURLConnection)url.openConnection();
            status = urlConnection.getResponseCode();


            urlConnection.setRequestMethod("GET");//DUDA
            urlConnection.setReadTimeout(10000 );
            urlConnection.setConnectTimeout(15000 );

            //urlConnection.setDoOutput(true);

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
        Log.d("Avis","Ha acabat de fer el do in background");
        return json;

    }
    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        Log.d("Avis","Inici pst execute ");
        try {
            state=jsonObject.getInt("estado");

            login_screen.status=state;
        } catch (JSONException e) {
            e.printStackTrace();
        }    }
}
