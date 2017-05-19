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
import static com.example.usuario.proyectodam2.main_screen.handler;


public class Main_Connection  extends AsyncTask<View,Void,JSONObject> {
    private String ciutat, zona, esp1, esp2,nom,imatge,ciutat2,imatgereal;
    private String direccioimg="http://hungrycrossing.000webhostapp.com";
    private Float puntos;
    private JSONObject json=null;
    private JSONArray jsonArray=null;
    private Handler handler= new Handler();
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

        int i=0;
        Log.d("Avis","Inici pst execute ");
        try {
            for(i=0;i<jsonObject.length()-1;i++) {
                // json = jsonObject.getJSONObject("0");
                json=jsonObject.getJSONObject(""+i+"");
                nom = json.getString("Nom");
                imatge=json.getString("Imatge");
                imatgereal=direccioimg+imatge;
                ciutat2=json.getString("NomPob");
                addChild(nom,imatgereal,ciutat2);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*LayoutInflater inflater = LayoutInflater.from(cont);
        int id = R.layout.fragment__main;
        layoutimportant = (RelativeLayout) inflater.inflate(id,null,false);
        LinearLayout layout2=new LinearLayout(cont);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        TextView tvnomRest=new TextView(cont);
        tvnomRest.setText(nom);
        TextView tvnomCiutat=new TextView(cont);
        tvnomRest.setText(ciutat2);
        layout2.addView(tvnomRest);
        layout2.addView(tvnomCiutat);
        layoutimportant.addView(layout2);*/



            /*state=jsonObject.getInt("estado");

            bundle.putInt("state",state);

            Message msg=new Message();
            msg.setData(bundle);
            main_screen.handler.sendMessage(msg);*/

    }

    private void addChild(String nom,String imatge,String ciutat) {
        String nome,imatgee,city;
        nome=nom;
        imatgee=imatge;
        city=ciutat;
        /*Connexio per obtenir imatge*/
        HttpURLConnection conn=null;
        try {


            imageUrl=new URL(imatgee);
            conn=(HttpURLConnection)imageUrl.openConnection();
            conn.connect();

            InputStream in=new BufferedInputStream(conn.getInputStream());
            imagen= BitmapFactory.decodeStream(in);
            //imagen= BitmapFactory.decodeStream(conn.getInputStream());

            imgRest=new ImageView(cont);
            imgRest.setImageBitmap(imagen);
            imgRest.setPadding(10,10,10,10);

        } catch (Exception e) {
            e.printStackTrace();
        }


        /****************************/
        LinearLayout layout2=new LinearLayout(cont);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        TextView tvnomRest=new TextView(cont);
        tvnomRest.setText(nome);
        tvnomRest.setPadding(10,10,10,10);
        TextView tvnomCiutat=new TextView(cont);
        tvnomCiutat.setText(city);
        tvnomCiutat.setPadding(10,10,10,10);


        layout2.addView(tvnomRest);
        layout2.addView(tvnomCiutat);
        layout2.addView(imgRest);
        layoutimportant.addView(layout2);


    }
    @Override
    protected JSONObject doInBackground(View... view) {
        layoutimportant= (LinearLayout) view[0];
        URL url = null;
        try {
            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            if(ciutat!=null)
            {
                if(zona!=null)
                {
                    if(esp1!=null)
                    {
                        if(puntos==0f) {
                            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat=" + ciutat + "&zona=" + zona +"&esp1="+esp1);
                        }
                        else
                        {
                            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat=" + ciutat + "&zona=" + zona +"&esp1="+esp1+"&valoracio="+puntos);
                        }
                    }
                    if(esp1==null)
                    {
                        if(puntos==0f) {
                            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat=" + ciutat + "&zona=" + zona);
                        }
                        else
                        {
                            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat=" + ciutat + "&zona=" + zona+"&valoracio="+puntos);
                        }
                    }
                }
                if(zona==null)
                {
                    if(esp1!=null)
                    {
                        if(puntos==0f) {
                            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat=" + ciutat+"&esp1="+esp1);
                        }
                        else
                        {
                            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat=" + ciutat+"&esp1="+esp1+"&valoracio="+puntos);
                        }
                    }
                    if(esp1==null)
                    {
                        if(puntos==0f) {
                            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat=" + ciutat);
                        }
                        else
                        {
                            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat=" + ciutat+"&valoracio="+puntos);
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
                            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?zona=" + zona +"&esp1="+esp1);
                        }
                        else
                        {
                            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?zona=" + zona +"&esp1="+esp1+"&valoracio="+puntos);
                        }
                    }
                    if(esp1==null)
                    {
                        if(puntos==0f) {
                            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?zona=" + zona);
                        }
                        else
                        {
                            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?zona=" + zona +"&valoracio="+puntos);
                        }
                    }
                }
                if(zona==null)
                {
                    if(esp1!=null)
                    {
                        if(puntos==0f) {
                            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?esp1="+esp1);

                        }
                        else
                        {
                            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?esp1="+esp1+"&valoracio="+puntos);

                        }
                    }
                    if(esp1==null)
                    {
                        if(puntos==0f) {
                            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php");
                        }
                        else
                        {
                            url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?valoracio="+puntos);
                        }
                    }
                }
            }
            //url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php?ciutat=" + ciutat + "&zona=" + zona +"&esp1="+esp1+"&esp2="+esp2+"&valoracio="+punts);
           //url = new URL("http://hungrycrossing.000webhostapp.com/Consultar_Restaurants.php");
            HttpURLConnection urlConnection = null;
            urlConnection = (HttpURLConnection)url.openConnection();
            //int status = urlConnection.getResponseCode();


            urlConnection.setRequestMethod("GET");
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

    public JSONObject getJson()
    {
        return json;
    }
}
