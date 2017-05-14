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
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Register extends Fragment {
    private Button btRegister;
    private ImageView imgLogo;
    private TextView tvJoin,tvUsername,tvPssw,tvPssw2,tvEmail,tvError;
    private EditText etUsername,etPssw,etPssw2,etEmail;

    public Fragment_Register() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btRegister=(Button)getView().findViewById(R.id.btnRegister);
        imgLogo=(ImageView)getView().findViewById(R.id.imageView);
        tvJoin=(TextView)getView().findViewById(R.id.tvJoin);
        tvUsername=(TextView)getView().findViewById(R.id.tvUsername);
        tvPssw=(TextView)getView().findViewById(R.id.tvPassword);
        tvPssw2=(TextView)getView().findViewById(R.id.tvConfirmpass);
        tvEmail=(TextView)getView().findViewById(R.id.tvEmail);
        tvError=(TextView)getView().findViewById(R.id.tvErrorReg);
        etUsername=(EditText)getView().findViewById(R.id.etUsername);
        etPssw=(EditText)getView().findViewById(R.id.etPassword);
        etPssw2=(EditText)getView().findViewById(R.id.etConfirmPass);
        etEmail=(EditText)getView().findViewById(R.id.etMail);
    }

}
