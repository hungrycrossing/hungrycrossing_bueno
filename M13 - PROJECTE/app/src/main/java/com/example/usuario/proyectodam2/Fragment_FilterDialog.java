package com.example.usuario.proyectodam2;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;


public class Fragment_FilterDialog extends DialogFragment {
    private TextView tvTitol,tvZona,tvEspecialitats,tvRating;
    private ListView lvZona,lvEspecialitats;
    private RatingBar rbar;
    private Button btFiltre;

    public Fragment_FilterDialog() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btFiltre=(Button)getView().findViewById(R.id.btFiltrarJa);
        rbar=(RatingBar)getView().findViewById(R.id.rBar1);
        lvZona=(ListView)getView().findViewById(R.id.lv1);
        lvEspecialitats=(ListView)getView().findViewById(R.id.lv2);
        tvTitol=(TextView)getView().findViewById(R.id.titol);
        tvZona=(TextView)getView().findViewById(R.id.zona);
        tvEspecialitats=(TextView)getView().findViewById(R.id.tipos);
        tvRating=(TextView)getView().findViewById(R.id.precio);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__filter_dialog, container, false);
    }

}
