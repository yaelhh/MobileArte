package com.example.clientarte;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.kinvey.android.Client;
import com.kinvey.java.Query;
import com.kinvey.java.core.DownloaderProgressListener;
import com.kinvey.java.core.MediaHttpDownloader;

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
	public static final String TAG = "ArteBackend";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_programacion);
		cal = (CalendarView) findViewById(R.id.calendarView1);
		layout= (ViewGroup)findViewById(R.id.containerProgramacion);
		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		
		
		crearProgramacion(obj);


		//       addListenerOnButton(); 

		cal.setOnDateChangeListener(new OnDateChangeListener() {

			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,
					int dayOfMonth) {


				Toast.makeText(getBaseContext(),"Selected Date is\n\n"
						+dayOfMonth+" : "+month+" : "+year , 
						Toast.LENGTH_LONG).show();
			}
		});


	}
	//Funcion para crear activity en funcion de la lista de obras obtenidas
	public void crearProgramacion(ObjetosBackend obj){ 
//		obj=new ObjetosBackend(mKinveyClient);

		listObras=obj.getListObras();
		ArrayList<View> listView= new ArrayList<View>();
		//    recorro la lista de obras existentes y agrego un imagenbutton por cada obra
		for(int x=0; x<listObras.size();x++){
			miObra= (Obra) listObras.get(x);
			imageObra= new ImageButton(this);
			imageObra.setId(x);
			imageObra.setContentDescription(miObra.getNombre());
			Integer imagen= miObra.getListaImagenes()[0];
			imageObra.setBackgroundResource(imagen);
			imageObra.setPadding(10, 10, 10, 10);
			ingresoObra(imageObra); 
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
					Log.e("ProgramacionActivity - ingresoObra", Integer.toString(i));

					obraSeleccionada= (Obra) listObras.get(i);
					

					Intent intent = new Intent(ProgramacionActivity.this, ObraActivity.class);
					intent.putExtra("obra",obraSeleccionada); 
					startActivity(intent);
				}

			});
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

