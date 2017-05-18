package com.example.usuario.proyectodam2;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;

import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import static com.example.usuario.proyectodam2.Register_connection.handlermail;


/**
 * Created by usuario on 13/05/2017.
 */

public class enviarMail extends AsyncTask<Void,Void,JSONObject> {
    private String mail, login;
    int status;
    Bundle bundle=new Bundle();

    public enviarMail(String mail, String login) {
        this.mail = mail;
        this.login = login;
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        URL url;
        try {
                url = new URL("http://hungrycrossing.000webhostapp.com/mail.php?mail=" + mail + "&login=" + login);
            HttpURLConnection urlConnection;
            urlConnection = (HttpURLConnection) url.openConnection();
            status = urlConnection.getResponseCode();


            urlConnection.setRequestMethod("GET");//DUDA
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);

            bundle.putInt("state",1);
            Message msg=new Message();
            msg.setData(bundle);
            handlermail.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        /*bundle.putInt("state", 1);
        Message msg = new Message();
        msg.setData(bundle);
        handlermail.sendMessage(msg);*/
    }
}
