package com.example.clientarte;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/*  Fragment para seccion perfil */ 
public class AjustesFragment extends Fragment {
	private int j;
     
    public AjustesFragment(){}
    
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.xml.preferences, container, false);
          
        return rootView;
    }
}
