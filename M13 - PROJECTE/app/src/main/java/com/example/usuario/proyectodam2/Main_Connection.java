package com.example.usuario.proyectodam2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static com.example.usuario.proyectodam2.main_screen.handler;


public class Main_Connection  extends AsyncTask<View,Void,JSONObject> {
    private String ciutat, zona, esp1, esp2,nom,imatge,ciutat2,imatgereal;
    private String direccioimg="http://hungrycrossing.000webhostapp.com";
    private Float puntos;
    private JSONObject json=null;
    private JSONArray jsonArray=null;
    private int state;
    private URL imageUrl=null;
    private Bitmap imagen;
    private ImageView imgRest;
    private Context cont;
    private LinearLayout layoutimportant;
    Bundle bundle=new Bundle();

    public Main_Connection(String ciutat, String zona, String esp1, String esp2, Float punts, Context context)
    {
        this.ciutat=ciutat;
        this.zona=zona;
        this.esp1=esp1;
        this.esp2=esp2;
        this.puntos=punts;
        this.cont=context;
        //if(this.puntos==0)this.puntos=null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);

       /* try {
            state=jsonObject.getInt("estado");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        bundle.putInt("state",1);
        Log.d("Avis","FI pst execute ");
            Message msg=new Message();
            msg.setData(bundle);
            handler.sendMessage(msg);

    }
    @Override
    protected JSONObject doInBackground(View... view) {
        //layoutimportant= (LinearLayout) view[0];
        URL url = null;
        /*try {
            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/
        try {
            if(ciutat!=null)
            {
                if(zona!=null)
                {
                    if(esp1!=null)
                    {
                        if(puntos==0f) {

                            String url2="http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat="+URLEncoder.encode(ciutat,"UTF-8")+"&zona="+URLEncoder.encode(zona,"UTF-8")+"&esp1="+URLEncoder.encode(esp1,"UTF-8");
                            url = new URL(url2);
                        }
                        else
                        {
                            String url2="http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat="+URLEncoder.encode(ciutat,"UTF-8")+"&zona="+URLEncoder.encode(zona,"UTF-8")+"&esp1="+URLEncoder.encode(esp1,"UTF-8")+"&valoracio="+puntos;
                            url = new URL(url2);
                        }
                    }
                    if(esp1==null)
                    {
                        if(puntos==0f) {
                            String url2="http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat=" +URLEncoder.encode(ciutat,"UTF-8")+ "&zona=" + URLEncoder.encode(zona,"UTF-8");
                            url = new URL(url2);
                        }
                        else
                        {
                            String url2="http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat=" + URLEncoder.encode(ciutat,"UTF-8") + "&zona=" + URLEncoder.encode(zona,"UTF-8")+"&valoracio="+puntos;
                            url = new URL(url2);
                        }
                    }
                }
                if(zona==null)
                {
                    if(esp1!=null)
                    {
                        if(puntos==0f) {
                            String url2="http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat=" + URLEncoder.encode(ciutat,"UTF-8")+"&esp1="+URLEncoder.encode(esp1,"UTF-8");
                            url = new URL(url2);
                        }
                        else
                        {
                            String url2="http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat=" +  URLEncoder.encode(ciutat,"UTF-8")+"&esp1="+URLEncoder.encode(esp1,"UTF-8")+"&valoracio="+puntos;
                            url = new URL(url2);
                        }
                    }
                    if(esp1==null)
                    {
                        if(puntos==0f) {
                            String url2="http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat=" + URLEncoder.encode(ciutat,"UTF-8");
                            url = new URL(url2);
                        }
                        else
                        {
                            String url2="http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat=" + URLEncoder.encode(ciutat,"UTF-8")+"&valoracio="+puntos;
                            url = new URL(url2);
                        }
                    }
                }
            }
            if(ciutat==null)
            {
                if(zona!=null)
                {
                    if(esp1!=null)
                    {
                        if(puntos==0f) {
                            String url2="http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?zona=" +  URLEncoder.encode(zona,"UTF-8") +"&esp1="+ URLEncoder.encode(esp1,"UTF-8");
                            url = new URL(url2);
                        }
                        else
                        {
                            String url2="http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?zona=" + URLEncoder.encode(zona,"UTF-8") +"&esp1="+URLEncoder.encode(esp1,"UTF-8")+"&valoracio="+puntos;
                            url = new URL(url2);
                        }
                    }
                    if(esp1==null)
                    {
                        if(puntos==0f) {
                            String url2="http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?zona=" + URLEncoder.encode(zona,"UTF-8");
                            url = new URL(url2);
                        }
                        else
                        {
                            String url2="http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?zona=" + URLEncoder.encode(zona,"UTF-8") +"&valoracio="+puntos;
                            url = new URL(url2);
                        }
                    }
                }
                if(zona==null)
                {
                    if(esp1!=null)
                    {
                        if(puntos==0f) {
                            String url2="http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?esp1="+URLEncoder.encode(esp1,"UTF-8");
                            url = new URL(url2);

                        }
                        else
                        {
                            String url2="http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?esp1="+URLEncoder.encode(esp1,"UTF-8")+"&valoracio="+puntos;
                            url = new URL(url2);

                        }
                    }
                    if(esp1==null)
                    {
                        if(puntos==0f) {
                            String url2="http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php";
                            url = new URL(url2);
                        }
                        else
                        {
                            String url2="http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?valoracio="+puntos;
                            url = new URL(url2);
                        }
                    }
                }
            }

           // url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat=Granollers&zona=Centre ciutat&esp1=Cuina Catalana");
           //url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php");

            HttpURLConnection urlConnection = null;
            urlConnection = (HttpURLConnection)url.openConnection();
            //int status = urlConnection.getResponseCode();


            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 );
            urlConnection.setConnectTimeout(15000 );

            //urlConnection.setDoOutput(true);

            urlConnection.connect();//COMENTAAAAAAAAAAAAAAAAAAT

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
            urlConnection.disconnect();
           // System.out.println("JSON: " + jsonString);


        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        Log.d("Avis","Ha acabat de fer el do in background");
        return json;
    }

    public JSONObject getJson()
    {
        return json;
    }
}
