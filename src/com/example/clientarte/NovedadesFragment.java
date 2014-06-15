package com.example.clientarte;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*  Fragment para seccion perfil */ 
public class NovedadesFragment extends Fragment {
     
    public NovedadesFragment(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.activity_novedades, container, false);
          
        return rootView;
    }
}