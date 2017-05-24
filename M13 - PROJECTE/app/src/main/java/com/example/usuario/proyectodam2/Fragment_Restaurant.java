package com.example.usuario.proyectodam2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Restaurant extends Fragment {
    private Button btSend;
    private EditText etComentario;
    private TextView tvNomRest,tvZona,tvPoblacio,tvDireccio,tvComentario,tvRating,tvOpiniones,tvOpinion;
    private ImageView imgRest;
    private RatingBar rBar;

    public Fragment_Restaurant() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__restaurant, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btSend=(Button)getView().findViewById(R.id.btSend);
        imgRest=(ImageView)getView().findViewById(R.id.imgRest);
        rBar=(RatingBar) getView().findViewById(R.id.rBar1);
        tvNomRest=(TextView)getView().findViewById(R.id.tvNomRest);
        tvZona=(TextView)getView().findViewById(R.id.tvZona);
        tvPoblacio=(TextView)getView().findViewById(R.id.tvPoblacio);
        tvDireccio=(TextView)getView().findViewById(R.id.tvDireccio);
        tvComentario=(TextView)getView().findViewById(R.id.tvComentario);
        tvRating=(TextView)getView().findViewById(R.id.tvRating);
        tvOpiniones=(TextView)getView().findViewById(R.id.tvOpiniones);
       // tvOpinion=(TextView)getView().findViewById(R.id.tvOpinion);
        etComentario=(EditText)getView().findViewById(R.id.etComentario);
    }


}
