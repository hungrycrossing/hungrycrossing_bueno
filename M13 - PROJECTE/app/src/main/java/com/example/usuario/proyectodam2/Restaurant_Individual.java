package com.example.usuario.proyectodam2;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.example.usuario.proyectodam2.restaurant_screen;
/**
 * Created by Bernat on 23/05/2017.
 */
public class Restaurant_Individual extends AsyncTask<View,Void,JSONObject> {
    public String nomRest,imatge,ciutat,zona,opinio,direccio;
    public String[] opiniones=new String[100];
    private String direccioimg="http://hungrycrossing.000webhostapp.com";
    private Context context;
    private JSONObject json=null;
    private JSONArray json2=null;
    private RelativeLayout relative;

    private TextView tvNomRest,tvCiutat,tvZona,tvDireccio;
    private ImageView imageRest;

    public Restaurant_Individual(String nom,String city)
    {
        this.nomRest=nom;
        this.ciutat=city;
    }
    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);

        try {

            for(int i=0;i<jsonObject.length()-1;i++)
            {
                json=jsonObject.getJSONObject(""+i+"");
                nomRest = json.getString("Nom");
                //imatge=json.getString("Imatge");
                //imatge=direccioimg+imatge;
                zona=json.getString("NomZona");
                direccio=json.getString("Direccio");

                opiniones[i]=json.get("Opinio").toString();
                restaurant_screen.opiniones[i]=opiniones[i];
            }
            restaurant_screen.zona=zona;
            restaurant_screen.direccio=direccio;

            //addChild(nomRest,imatge,zona,direccio);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        restaurant_screen.handler.sendEmptyMessage(1);


    }

    @Override
    protected JSONObject doInBackground(View... params) {
        // relative=(RelativeLayout)params[0];
        URL url = null;

        try {
            url = new URL("http://hungrycrossing.000webhostapp.com/RestaurantSeleccionat.php?Nom="+nomRest);
            HttpURLConnection urlConnection=null;
            urlConnection = (HttpURLConnection)url.openConnection();
            int status = urlConnection.getResponseCode();

            urlConnection.setRequestMethod("GET");//DUDA
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);

            //urlConnection.setDoOutput(true);

            urlConnection.connect();
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

            //System.out.println("JSON: " + jsonString);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public String[] getOpinions()
    {
        return opiniones;
    }
    public String getZona()
    {
        return zona;
    }
    public String getDireccio()
    {
        return direccio;
    }
}
