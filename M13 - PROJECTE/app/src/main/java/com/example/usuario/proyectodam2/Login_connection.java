package com.example.usuario.proyectodam2;

import android.os.AsyncTask;
import android.util.JsonReader;
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
 * Created by Bernat on 12/05/2017.
 */
public class Login_connection extends AsyncTask<Void,Void,JSONObject>{
    String login;
    String password;
    private int state;
    public int status;
    public  Login_connection(String log, String pass)
    {
        login = log;
        password = pass;

    }
    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);

        super.onPostExecute(jsonObject);
        Log.d("Avis","Inici pst execute ");
        try {

            state=jsonObject.getInt("estado");

            login_screen.status=state;
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        JSONObject json=null;
        URL url = null;
        try {
            url = new URL("http://hungrycrossing.000webhostapp.com/ComprobarLogin.php?login=" + login + "&psw=" + password );
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
}
