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


public class Fragment_Login extends Fragment {
    private ImageView img1;
    private TextView tvusername,tvpssw,tverror;
    private Button btlogin,btsignup;
    private EditText etusername,etpssw;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btlogin=(Button)getView().findViewById(R.id.btnLogin);
        //btlogin.setOnClickListener();
        btsignup=(Button)getView().findViewById(R.id.btnRegister);
        //btnRegister.setOnClickListener(this);
        img1=(ImageView)getView().findViewById(R.id.imageView);
        tverror=(TextView)getView().findViewById(R.id.tvErrorLogin);
        tvusername=(TextView)getView().findViewById(R.id.tvPassword);
        tvpssw=(TextView)getView().findViewById(R.id.tvUsername);
        etpssw=(EditText)getView().findViewById(R.id.etPassword);
        etusername=(EditText)getView().findViewById(R.id.etUsername);
    }

    public Fragment_Login() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__login, container, false);
    }

}
