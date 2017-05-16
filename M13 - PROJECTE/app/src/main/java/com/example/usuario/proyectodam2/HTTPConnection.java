package com.example.usuario.proyectodam2;

import android.app.Activity;
import android.app.Notification;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import static com.example.usuario.proyectodam2.login_screen.handler;
import com.example.usuario.proyectodam2.login_screen.*;
import static android.os.Build.VERSION_CODES.N;

/**
 * Created by Bernat on 09/05/2017.
 */
public class HTTPConnection extends AsyncTask<Void,Void,JSONObject> {
    String login;
    String password;
    private int state;
    public int status;
    private Context context;
    Bundle bundle= new Bundle();
    //public static Handler handler=login_screen.handler;


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
            url = new URL("http://hungrycrossing.000webhostapp.com/ComprobarLogin.php?login=" + login + "&psw=" + password );
            HttpURLConnection urlConnection = null;
            urlConnection = (HttpURLConnection)url.openConnection();
            status = urlConnection.getResponseCode();


            urlConnection.setRequestMethod("GET");//DUDA
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);

            //urlConnection.setDoOutput(true);

            urlConnection.connect();

            BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));

            char[] buffer = new char[1024];

            String jsonString = "";

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
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
        //es retorna el jsonObject de forma estatica (no tinc ni P idea)
    }
public int getState()
{
    return state;
}

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
