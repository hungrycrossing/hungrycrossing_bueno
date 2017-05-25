package com.example.usuario.proyectodam2;

import android.graphics.Color;
import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by usuario on 23/05/2017.
 */

public class punterosMapa  extends AsyncTask<GoogleMap,Void,JSONArray> {
    ArrayList<Double> latitud, longitud;
    Double lat, lon;
    String name;
    ArrayList<String> nom;
    GoogleMap map;
    ArrayList<Marker> markers= new ArrayList<Marker>();
    ArrayList<LatLng> points= new ArrayList<>();
    LatLng position;



    public punterosMapa(ArrayList<Double> la, ArrayList<Double> lo, ArrayList<String> no)
    {
        nom=no;
        latitud=la;
        longitud=lo;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);
        for (int x = 0; x < latitud.size(); x++) {
            if(x<=4)
            {
            lat=latitud.get(x);
            lon=longitud.get(x);
            name=nom.get(x);
                position= new LatLng(lat,lon);

                Marker marcador = map.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lon))
                    //   .draggable(true) //permite que se pueda arrastrar el marcador
                    .title(name) //titulo del marcador
                    //configurar el color del marker
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))

            );
                marcador.showInfoWindow(); //mostraremos la informacion del marker por defecto

                markers.add(marcador);

                points.add(position);//guardem la posicio del marker en un array de punts per a dibuixar les linies entre ells

        }}
        /*if(latitud.size()==2)
        {
            Polyline line= map.addPolyline(new PolylineOptions()
                    .add(new LatLng(latitud.get(0),longitud.get(0)),new  LatLng(latitud.get(1),longitud.get(1)))
                    // LatLng(lats.get(2),longs.get(2),  LatLng(lats.get(3),longs.get(3),  LatLng(lats.get(4),longs.get(4)))
                    .width(5)
                    .color(Color.CYAN));
        }
        else
        if(latitud.size()==3)
        {
            Polyline line= map.addPolyline(new PolylineOptions()
                    .add(new LatLng(latitud.get(0),longitud.get(0)),new  LatLng(latitud.get(1),longitud.get(1)),new  LatLng(latitud.get(2),longitud.get(2)))
                    // LatLng(lats.get(2),longs.get(2),  LatLng(lats.get(3),longs.get(3),  LatLng(lats.get(4),longs.get(4)))
                    .width(5)
                    .color(Color.CYAN));
        }*/
    }

    @Override
    protected JSONArray doInBackground(GoogleMap... googleMaps) {
        map = googleMaps[0];

        return null;
    }
}
