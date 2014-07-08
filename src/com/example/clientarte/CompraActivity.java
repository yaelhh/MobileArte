package com.example.clientarte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




















import dominio.Butaca;
import dominio.Comodidad;
import dominio.Sala;
import dominio.Sector;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;

public class CompraActivity extends Activity {

	TextView lblPulsado;
	GridView grdOpciones;
	Object click_item;
	GridView gridviewSectorA;
	GridView gridviewSectorB;

	ImageButton bttnSectorA;
	ImageButton bttnSectorB;
	ImageButton bttnSectorC;
	Button btnComprar;
	Dialog customDialog;

	ArrayAdapter<String> adaptador;
	ImageButton butaca;
	Sector sector;
	Integer cantButaca;
	ArrayList<Butaca> listabutacas;
	ArrayList<Sector> listSectores= new ArrayList<Sector>();
	private HashMap<String, Boolean> hashComodidades = new HashMap <String, Boolean> ();
	Sector miSector;
	Sala miSala;
	Butaca miButaca;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compra);
		
				
//				if (savedInstanceState == null) {
//					getSupportFragmentManager().beginTransaction()
//							.add(R.id.activity_Compra, new PlaceholderFragment()).commit();
//				}

		//El cantButaca va a ser de acuerdo al sector que estemos creando, aca lo inicializamos asi
		//Integer[] butacaXSector=new Integer[25];		
//		Integer cantButacaSectorA=25;
		bttnSectorA=(ImageButton)findViewById(R.id.sectorA);
		bttnSectorB=(ImageButton)findViewById(R.id.sectorB);
		bttnSectorC=(ImageButton)findViewById(R.id.sectorC);
		btnComprar=(Button)findViewById(R.id.button_comprar);
		
		crearObjetos();
		addListenerOnButton();



	}
    
  	private void crearObjetos() {
  		//Crear sector
  		sector = new Sector();
		sector.setIdSector(1); 
		sector.setTotalButacas(24);
		
		//Crear las butacas del sector 
		listabutacas = new ArrayList<Butaca>();
		for (int i = 0; i < sector.getTotalButacas(); i ++) {
			miButaca = new Butaca();
			miButaca.setIdButaca(i);
			miButaca.setEstadoButaca(true);
			listabutacas.add(miButaca);
			//Log.d("Creación de la lista de butacas", "ID Butaca: " + Integer.toString(butaca.getIdButaca()));
		}
		//Agrego la lista de butacas al sector
		sector.setListaButacas(listabutacas);
		

		miSala= new Sala(1, "Nombre Sala",listSectores,hashComodidades, 200);
		listSectores.add(miSector);
		miSala.setListaSectores(listSectores);
//		Log.e("Mi sala 1",Integer.toString(miSala.getIdSala()));		
	}

	public void addListenerOnButton() {
//		 final boolean bol = false;

		bttnSectorA.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CompraActivity.this, SectorAActivity.class);
				intent.putExtra("sector", sector); 
				startActivity(intent);				    
	        }
			
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compra, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class sectorA extends Fragment {
	
		public sectorA() {
		}
	
//		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_sectora,	container, false);
			return rootView;
		}
	}

	

}
