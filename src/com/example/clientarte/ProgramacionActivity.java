package com.example.clientarte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.kinvey.android.Client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;
import dominio.Funcion;
import dominio.Obra;




public class ProgramacionActivity extends Activity implements OnQueryTextListener{         
	CalendarView cal;
	private SearchView mSearchView;
	private ImageButton mObra;
	private ArrayList<Obra>listObras= new ArrayList<Obra>();
	private ArrayList<Funcion> listFunciones= new ArrayList<Funcion>();
	private ViewGroup layout;
//	static ObjetosBackend obj;
	private ImageButton imageObra;
	private Obra miObra;
	private  Obra obraSeleccionada= new Obra();
	private HashMap<Obra, Funcion> obraSegunFuncion;
	ArrayList<Obra> obrasDia= new ArrayList<Obra>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_programacion);
		cal = (CalendarView) findViewById(R.id.calendarView1);
		layout= (ViewGroup)findViewById(R.id.contenedorDeObras);
		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		listObras= obj.getListObras();
//		obraSegunFuncion=obj.getobraSegunFuncion();
		
//		crearProgramacion(obj);


		//       addListenerOnButton(); 

		cal.setOnDateChangeListener(new OnDateChangeListener() {

			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,
					int dayOfMonth) {
//				Log.e("Al principio",listObras.get(0).getNombre());
				if(obrasDia.size()>0){
					obrasDia= new ArrayList<Obra>();
					layout.removeAllViews();			
				}
				
				String anio=String.valueOf(year);
				String mes=String.valueOf(month+1);
				String dia=String.valueOf(dayOfMonth);
			for(int x=0; x<listObras.size();x++){
				Obra o= listObras.get(x);
				for(int y=0;y<o.getListaFunciones().size();y++){
					String fechaActual=o.getListaFunciones().get(y).getFechaObra();
					String[] fecha = fechaActual.split("/");
//					Log.e("Fuera if",listObras.get(x).getNombre());
					Log.e("fecha",fechaActual);
					if(dia.equalsIgnoreCase(fecha[0]) && mes.equalsIgnoreCase(fecha[1]) && anio.equalsIgnoreCase(fecha[2]) ){
						obrasDia.add(listObras.get(x));
						Log.e("Dentro if",listObras.get(x).getNombre());
						
					}
				}
			}
				
				Toast.makeText(getBaseContext(),"Selected Date is\n\n"
						+dayOfMonth+" : "+(month+1) +" : "+year , 
						Toast.LENGTH_LONG).show();
				crearProgramacion(obrasDia);
			}
		});


	}
	

	//Funcion que al seleccionar una obra envie los datos a ObraActivity
	public void ingresoObra(ImageButton imageObra, Obra obra){
		final Obra obraSeleccionada=obra;
		   imageObra.setOnClickListener(new OnClickListener() {
	    	   
				@Override
				public void onClick(View v) {
					
					int i= v.getId(); 
					Log.e("ProgramacionActivity - ingresoObra", Integer.toString(i));

//					obraSeleccionada= (Obra) listObras.get(i);
					

					Intent intent = new Intent(ProgramacionActivity.this, ObraActivity.class);
					intent.putExtra("obra",obraSeleccionada); 
					startActivity(intent);
				}

			});
	    }
	
	//Funcion para crear activity en funcion de la lista de obras obtenidas
		public void crearProgramacion(ArrayList<Obra> lObra){ 
//			obj=new ObjetosBackend(mKinveyClient);

//			listObras=obj.getListObras();
			ArrayList<View> listView= new ArrayList<View>();
			//    recorro la lista de obras existentes y agrego un imagenbutton por cada obra
			for(int x=0; x<lObra.size();x++){
				Log.e("En crearProgramacion",lObra.get(x).getNombre());
				miObra= (Obra) lObra.get(x);
				imageObra= new ImageButton(this);
				imageObra.setId(x);
				imageObra.setContentDescription(miObra.getNombre());
				Integer imagen= miObra.getListaImagenes()[0];
				imageObra.setBackgroundResource(imagen);
				imageObra.setPadding(10, 10, 10, 10);
				ingresoObra(imageObra,miObra); 
				//Le agrego al layout el imageButton creado
				layout.addView(imageObra, 470, 140);
			}   
		}
	
	@Override
	public boolean onQueryTextChange(String newText) {

		Toast.makeText(this, newText, Toast.LENGTH_SHORT).show();

		return false;
	}
	@Override
	public boolean onQueryTextSubmit(String text) {

		Toast.makeText(this, "Searching for " + text, Toast.LENGTH_LONG).show();

		return false;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		MenuItem searchItem = menu.findItem(R.id.action_search);
		mSearchView = (SearchView) searchItem.getActionView();
		mSearchView.setQueryHint("Search…");
		mSearchView.setOnQueryTextListener(this);

		return true;
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


	//	public void addListenerOnButton() {
	//		 
	//		mObra.setOnClickListener(new OnClickListener() {
	// 
	//			@Override
	//			public void onClick(View v) {
	//				Intent intent = new Intent(ProgramacionActivity.this, ObraActivity.class);
	//				startActivity(intent);
	//			}
	//
	//		});
	//}
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

