package com.example.clientarte;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*  Fragment para seccion Nosotros */ 
public class ComunidadFragment extends Fragment {
     
    public ComunidadFragment(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.activity_comunidad, container, false);
          
        return rootView;
    }
}