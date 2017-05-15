package com.example.usuario.proyectodam2;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by usuario on 13/05/2017.
 */

public class enviarMail extends AsyncTask<Void,Void,JSONObject> {
    String mail, nom;
    int status;

    public enviarMail(String mail, String nom) {
        this.mail = mail;
        this.nom = nom;
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        URL url = null;
        try {
            url = new URL("http://hungrycrossing.000webhostapp.com/mail.php?mail=" + mail + "&nom=" + nom);
            HttpURLConnection urlConnection = null;
            urlConnection = (HttpURLConnection) url.openConnection();
            status = urlConnection.getResponseCode();


            urlConnection.setRequestMethod("GET");//DUDA
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
