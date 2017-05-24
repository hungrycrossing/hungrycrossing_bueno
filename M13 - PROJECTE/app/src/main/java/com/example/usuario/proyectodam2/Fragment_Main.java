package com.example.usuario.proyectodam2;


import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class Fragment_Main extends Fragment {
    private ImageView imgPerfil;
    private Button btFiltres,btSearch;
    private EditText etSearch;
    private TextView tvInfo;
    public Fragment_Main() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imgPerfil=(ImageView)getView().findViewById(R.id.imageView);
        btFiltres=(Button)getView().findViewById(R.id.btnFiltros);
        btSearch=(Button)getView().findViewById(R.id.btSearch);
        etSearch=(EditText)getView().findViewById(R.id.etSearch);
        //tvInfo=(TextView)getView().findViewById(R.id.tvInfo);
    }

}
