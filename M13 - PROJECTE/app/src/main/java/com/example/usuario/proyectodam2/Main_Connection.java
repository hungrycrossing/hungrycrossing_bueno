package com.example.usuario.proyectodam2;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by usuario on 15/05/2017.
 */

public class Main_Connection  extends AsyncTask<Void,Void,JSONObject> {
    private String ciutat, zona, esp1, esp2;
    private Float punts;
    public Main_Connection(String ciutat, String zona, String esp1, String esp2,Float punts)
    {
        this.ciutat=ciutat;
        this.zona=zona;
        this.esp1=esp1;
        this.esp2=esp2;
        this.punts=punts;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);

    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        JSONObject json=null;
        URL url = null;
        try {
            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat=" + ciutat + "&zona=" + zona +"&esp1="+esp1+"&esp2="+esp2+"&punts="+punts);
            HttpURLConnection urlConnection = null;
            urlConnection = (HttpURLConnection)url.openConnection();
            int status = urlConnection.getResponseCode();


            urlConnection.setRequestMethod("GET");//DUDA
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



        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        Log.d("Avis","Ha acabat de fer el do in background");
        return json;
    }
}
