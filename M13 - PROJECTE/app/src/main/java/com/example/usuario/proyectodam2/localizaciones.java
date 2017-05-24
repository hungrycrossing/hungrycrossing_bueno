package com.example.usuario.proyectodam2;

/**
 * Created by usuario on 23/05/2017.
 */

public class localizaciones {
    public String nom;
    public Double lat, lon;
    public localizaciones(String nomRest, Double lat, Double lon)
    {
        nom=nomRest;
        this.lat=lat;
        this.lon=lon;
    }
}
