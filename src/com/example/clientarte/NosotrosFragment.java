package com.example.clientarte;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*  Fragment para seccion Nosotros */ 
public class NosotrosFragment extends android.support.v4.app.Fragment {
     
    public NosotrosFragment(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.activity_nosotros, container, false);
        GoogleMap mapa = ((SupportMapFragment) MainActivity.fragmentManager.findFragmentById(R.id.map)).getMap();
		LatLng sobre = new LatLng(-34.904416, -56.198482);
		CameraPosition camPos = new CameraPosition.Builder()
		        .target(sobre)   //Centramos el mapa en Sodre
		        .zoom(17)         //Establecemos el zoom en 19
		        .bearing(10)      //Establecemos la orientación con el noreste arriba
		        .tilt(70)         //Bajamos el punto de vista de la cámara 70 grados
		        .build();
		 mapa.addMarker(new MarkerOptions()
	        .position(sobre)
	        .title("Teatro: Sodre"));
		CameraUpdate camUpd3 =
		    CameraUpdateFactory.newCameraPosition(camPos);
		 
		mapa.animateCamera(camUpd3);
        return rootView;
    }
}