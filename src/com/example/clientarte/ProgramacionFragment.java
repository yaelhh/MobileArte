package com.example.clientarte;

import java.util.ArrayList;

import dominio.Funcion;
import dominio.Obra;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.CalendarView.OnDateChangeListener;

/*  Fragment para seccion Programacion */ 
public class ProgramacionFragment extends Fragment {
	CalendarView cal;
	private SearchView mSearchView;
	private ImageButton mObra;
	private ArrayList<Obra>listObras= new ArrayList<Obra>();
	private ArrayList<Funcion> listFunciones= new ArrayList<Funcion>();
	private ViewGroup layout;
	private Objetos obj;
	private ImageButton imageObra;
	private Obra miObra;
	private  Obra obraSeleccionada= new Obra();
	View rootView;

     
    public ProgramacionFragment(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
       rootView = inflater.inflate(R.layout.activity_programacion, container, false);
//       layout= (ViewGroup)rootView.findViewById(R.id.containerProgramacion);
        
        cal = (CalendarView)rootView.findViewById(R.id.calendarView1);
        
        cal.setOnDateChangeListener(new OnDateChangeListener() {
			
		@Override
		public void onSelectedDayChange(CalendarView view, int year, int month,
				int dayOfMonth) {
			
			
			Toast.makeText(getActivity().getBaseContext(),"Selected Date is\n\n"
				+dayOfMonth+" : "+month+" : "+year , 
				Toast.LENGTH_LONG).show();
			
		}
	});
//       mObra= (ImageButton)rootView.findViewById(R.id.GalleryObra);
//        addListenerOnButton();
        crearProgramacion();
        return rootView;
    }
    	
	//Funcion para crear activity en funcion de la lista de obras obtenidas
	public void crearProgramacion(){ 
		obj=new Objetos();

		listObras=obj.getListObra();
		ArrayList<View> listView= new ArrayList<View>();
		//    recorro la lista de obras existentes y agrego un imagenbutton por cada obra
		for(int x=0; x<listObras.size();x++){
			miObra= (Obra) listObras.get(x);
			imageObra= new ImageButton(getActivity());
			imageObra.setId(x);
			imageObra.setContentDescription(miObra.getNombre());
			Integer imagen= miObra.getListaImagenes()[0];
			imageObra.setBackgroundResource(imagen);
			imageObra.setPadding(10, 10, 10, 10);
			ingresoObra(imageObra); 
			//        imageObra.setOnClickListener(new OnClickListener() {
			//    	   
			//			@Override
			//			public void onClick(View v) {
			//				Intent intent = new Intent(ProgramacionActivity.this, ObraActivity.class);
			//				intent.putExtra("obra",miObra); 
			//				startActivity(intent);
			//			}
			//
			//		});
			//Le agrego al layout el imageButton creado
			layout.addView(imageObra, 470, 140);
		}   
	}

	//Funcion que al seleccionar una obra envie los datos a ObraActivity
	public void ingresoObra(ImageButton imageObra){
		  
		   imageObra.setOnClickListener(new OnClickListener() {
	    	   
				@Override
				public void onClick(View v) {
					int i= v.getId(); 
					Log.d("ProgramacionActivity - ingresoObra", Integer.toString(i));

					obraSeleccionada= (Obra) listObras.get(i);

					Intent intent = new Intent(getActivity(), ObraActivity.class);
					intent.putExtra("obra",obraSeleccionada); 
					startActivity(intent);
				}

			});
	    }
	
     
   	
   	@Override
   	public boolean onOptionsItemSelected(MenuItem item) {
   	    // Handle presses on the action bar items
   	    switch (item.getItemId()) {
   	        case R.id.action_notification:
   	            //composeMessage();
   	            return true;
   	        default:
   	            return super.onOptionsItemSelected(item);
   	    }
   	}
   	
   	
//   	public void addListenerOnButton() {
//   		 
//   		mObra.setOnClickListener(new OnClickListener() {
//    
//   			@Override
//   			public void onClick(View v) {
//   				Intent intent = new Intent(getActivity(), ObraActivity.class);
//   				startActivity(intent);
//   			}
//
//   		});
//   }
   	public static class PlaceholderFragment extends Fragment {

   		public PlaceholderFragment() {
   		}

   		@Override
   		public View onCreateView(LayoutInflater inflater, ViewGroup container,
   				Bundle savedInstanceState) {
   			View rootView = inflater.inflate(R.layout.fragment_programacion, container, false);
   			return rootView;
   		}
   	}
}