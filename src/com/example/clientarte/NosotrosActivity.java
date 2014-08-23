package com.example.clientarte;

import java.util.ArrayList; 
import java.util.List; 

import android.app.AlertDialog; 
import android.content.Context; 
import android.graphics.drawable.Drawable; 
import android.os.Bundle; 
import android.support.v4.app.Fragment; 
import android.support.v4.app.FragmentManager; 
import android.view.Menu; 
import android.widget.SearchView; 

import com.google.android.gms.maps.CameraUpdate; 
import com.google.android.gms.maps.CameraUpdateFactory; 
import com.google.android.gms.maps.GoogleMap; 
import com.google.android.gms.maps.MapFragment; 
import com.google.android.gms.maps.SupportMapFragment; 
import com.google.android.gms.maps.model.CameraPosition; 
import com.google.android.gms.maps.model.LatLng; 
import com.google.android.gms.maps.model.MarkerOptions; 






public class NosotrosActivity extends android.support.v4.app.FragmentActivity {
//public class NosotrosActivity extends  MapActivity implements OnQueryTextListener{

	private SearchView mSearchView;
//	private Fr mapView;
<<<<<<< HEAD
//	private MapController myMapController;
=======
	//private MapController myMapController;
>>>>>>> origin/develop
//	private MapView mapa = null;
//	public static FragmentManager fragmentManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nosotros);
//		fragmentManager = getSupportFragmentManager();
		GoogleMap mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		LatLng sobre = new LatLng(-34.904416, -56.198482);
		CameraPosition camPos = new CameraPosition.Builder()
		        .target(sobre)   //Centramos el mapa en Sodre
		        .zoom(17)         //Establecemos el zoom en 19
		        .bearing(20)      //Establecemos la orientación con el noreste arriba
		        .tilt(70)         //Bajamos el punto de vista de la cámara 70 grados
		        .build();
		 mapa.addMarker(new MarkerOptions()
	        .position(sobre)
	        .title("Teatro: Sodre"));
		CameraUpdate camUpd3 =
		    CameraUpdateFactory.newCameraPosition(camPos);
		 
		mapa.animateCamera(camUpd3);
//		CameraUpdate camUpd1 =CameraUpdateFactory.newLatLng(new LatLng(-34.904416, -56.198482));		 
//		mapa.moveCamera(camUpd1);
	
	}
	
//	@Override
//    protected boolean isRouteDisplayed() {
//    	return false;
//    }
	
	

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        
       
        return true;
    }

	

	
}
