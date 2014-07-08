package com.example.clientarte;

import android.app.Fragment;
import android.os.Bundle;
<<<<<<< HEAD
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*  Fragment para seccion perfil */ 
public class AjustesFragment extends PreferenceFragment {
=======
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*  Fragment para seccion perfil */ 
public class AjustesFragment extends Fragment {
>>>>>>> refs/heads/master
     
    public AjustesFragment(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.xml.preferences, container, false);
          
        return rootView;
    }
}
