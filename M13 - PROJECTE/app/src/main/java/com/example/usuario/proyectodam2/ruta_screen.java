package com.example.usuario.proyectodam2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class ruta_screen extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    public ArrayList<Double> lats;
    public ArrayList<Double>longs;
    private ArrayList<String> noms;
    /*public ruta_screen(ArrayList<localizaciones> loc)
    {
        coord=loc;
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        lats=(ArrayList<Double>) bundle.getSerializable("la");
        longs=(ArrayList<Double>) bundle.getSerializable("lo");
        noms=(ArrayList<String>) bundle.getSerializable("no");


        setContentView(R.layout.activity_ruta_screen);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        LocationManager gestorLoc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED)
            gestorLoc.requestLocationUpdates(gestorLoc.GPS_PROVIDER, 1000,2 , this);
        else
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 4);
        Location l;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        else
        {
            l = gestorLoc.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        //coord= (ArrayList<localizaciones>) getIntent().getExtras().getSerializable("coords");

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        UiSettings settings = mMap.getUiSettings();
        settings.setZoomControlsEnabled(true); // permitir hacer zoom
        punterosMapa punts= new punterosMapa(lats,longs,noms);
        punts.execute(mMap);
      // CameraPosition cameraPosition = new CameraPosition.Builder()
                //.target(lats.get(0),longs.get(0))      // Sets the center of the map to Mountain View
        //        .zoom(20);              // Sets the zoom
               // .bearing(90)                // Sets the orientation of the camera to east
                //.tilt(30)                   // Sets the tilt of the camera to 30 degrees
                //.build();                   // Creates a CameraPosition from the builder
       // mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lats.get(0), longs.get(0));
        //mMap.addMarker(new MarkerOptions().position(sydney).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lats.get(0), longs.get(0)), 16.0f));
        Polyline line= mMap.addPolyline(new PolylineOptions()
            .add(new LatLng(lats.get(0),longs.get(0)),new  LatLng(lats.get(1),longs.get(1)))
                // LatLng(lats.get(2),longs.get(2),  LatLng(lats.get(3),longs.get(3),  LatLng(lats.get(4),longs.get(4)))
                .width(5)
                .color(Color.CYAN));

    }

    @Override
    public void onLocationChanged(Location location) {
        Polyline line= mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(lats.get(0),longs.get(0)),new  LatLng(lats.get(1),longs.get(1)))
                // LatLng(lats.get(2),longs.get(2),  LatLng(lats.get(3),longs.get(3),  LatLng(lats.get(4),longs.get(4)))
                .width(5)
                .color(Color.CYAN));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
