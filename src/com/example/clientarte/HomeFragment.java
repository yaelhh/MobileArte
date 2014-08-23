package com.example.clientarte;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;



/*  Fragment para seccion perfil */ 
public class HomeFragment extends android.support.v4.app.Fragment {
	private ImageButton botonprogramacion, botonComunidad, botonNosotros, botonNovedades;
	private SearchView mSearchView;
	Fragment fragment = null;

     
    public HomeFragment(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.activity_principal, container, false);
          
        botonprogramacion = (ImageButton)rootView.findViewById(R.id.imageProgramacion);
		botonComunidad= (ImageButton)rootView.findViewById(R.id.ImageComunidad);

		botonNosotros=(ImageButton)rootView.findViewById(R.id.ImageNosotros);
		botonNovedades =(ImageButton)rootView.findViewById(R.id.ImageNovedades);
		
		addListenerOnButton();
		return rootView;
    }
    
    public void addListenerOnButton() {
    	
		botonprogramacion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			Intent intent = new Intent(getActivity(), ProgramacionActivity.class);
				startActivity(intent);
				
		}

		});
		botonComunidad.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), ComunidadActivity.class);
				startActivity(intent);
			}	
		
		});
		
		botonNosotros.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), NosotrosActivity.class);
				startActivity(intent);
			}
			
		});
		botonNovedades.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), NovedadesActivity.class);
				startActivity(intent);
			}
			
		});
}
}