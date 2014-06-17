package com.example.clientarte;

import com.example.clientarte.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*  Fragment para seccion Nosotros */ 
public class NosotrosFragment extends Fragment {
     
    public NosotrosFragment(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.activity_nosotros, container, false);
          
        return rootView;
    }
}