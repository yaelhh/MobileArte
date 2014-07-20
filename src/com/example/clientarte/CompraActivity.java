package com.example.clientarte;

import java.util.ArrayList;
import java.util.HashMap;

import dominio.*;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CompraActivity extends Activity {

	TextView lblPulsado;
	GridView grdOpciones;
	Object click_item;
	GridView gridviewSectorA;
	GridView gridviewSectorB;

	Button bttnSectorA;
	Button bttnSectorB;
	Button bttnSectorC;
	Button btnComprar;
	Dialog customDialog;

	ArrayAdapter<String> adaptador;
	ImageButton butaca;
	Sector sector;
	Integer cantButaca;
	ArrayList<Butaca> listabutacas;
	ArrayList<Sector> listSectores= new ArrayList<Sector>();
	private HashMap<String, Boolean> hashComodidades = new HashMap <String, Boolean> ();	
	private Sala sala;
	private Butaca miButaca;
	private Objetos obj;
	private Obra obra;
	private Sector sectorA;
	private Sector sectorB;
	private Sector sectorC;
	private EditText cantEntradas;
	private Usuario usuario;
	private int cant;
	private Boolean yaSeleccionada;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compra);
		sala=new Sala();
		obj= new Objetos();
		sala= obj.getListSalas().get(0);
		obra= new Obra();
		obra= getIntent().getParcelableExtra("obra");
		TextView titulo= (TextView)findViewById(R.id.idTitulo);
		titulo.setText(obra.getNombre());
		Spinner sprHorario= (Spinner)findViewById(R.id.spinner_horario);
		ArrayList<Funcion>lista= obra.getListaFunciones();	
		//Creamos el adaptador
        ArrayAdapter<Funcion> spinner_adapter = new ArrayAdapter<Funcion>(this,android.R.layout.simple_spinner_item, lista);
        sprHorario.setAdapter(spinner_adapter);
        TextView txtSala= (TextView)findViewById(R.id.sala);
        txtSala.setText(sala.getNombreSala());
        cantEntradas=(EditText)findViewById(R.id.idCantidadEntradas);
//        cant=cantEntradas.getText().toString();
        usuario= new Usuario();
       
		//El cantButaca va a ser de acuerdo al sector que estemos creando, aca lo inicializamos asi
		bttnSectorA=(Button)findViewById(R.id.sectorA);
		bttnSectorB=(Button)findViewById(R.id.sectorB);
		bttnSectorC=(Button)findViewById(R.id.sectorC);
		btnComprar=(Button)findViewById(R.id.button_comprar);
		sectorA= new Sector();
		sectorA=sala.getListaSectores().get(0);
		sectorB= new Sector();
		sectorB=sala.getListaSectores().get(1);
		sectorC= new Sector();
		sectorC=sala.getListaSectores().get(2);
		bttnSectorA.setText("Precio "+ sectorA.getPrecioSector());
		bttnSectorB.setText("Precio "+ sectorB.getPrecioSector());
		bttnSectorC.setText("Precio "+ sectorC.getPrecioSector());
//		bttnSectorA.setOnClickListener((OnClickListener) entrarSector());		

//		crearObjetos();
//		addListenerOnButton();



	}
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		yaSeleccionada= data.getBooleanExtra("yaSeleccionada",false);
		Log.e("yaSeleciconada",""+yaSeleccionada);
	}
//  	private void crearObjetos() {
//  		//Crear sector
//  		sector = new Sector();
//		sector.setIdSector(1); 
//		sector.setTotalButacas(24);
//		
//		//Crear las butacas del sector 
//		listabutacas = new ArrayList<Butaca>();
//		for (int i = 0; i < sector.getTotalButacas(); i ++) {
//			miButaca = new Butaca();
//			miButaca.setIdButaca(i);
//			miButaca.setEstadoButaca(true);
//			listabutacas.add(miButaca);
//			//Log.d("Creación de la lista de butacas", "ID Butaca: " + Integer.toString(butaca.getIdButaca()));
//		}
//		//Agrego la lista de butacas al sector
//		sector.setListaButacas(listabutacas);
//		
//
//		miSala= new Sala(1, "Nombre Sala",listSectores,hashComodidades, 200);
//		listSectores.add(miSector);
//		miSala.setListaSectores(listSectores);
////		Log.e("Mi sala 1",Integer.toString(miSala.getIdSala()));		
//	}
	
	public void entrarSector(View v){
		cantEntradas=(EditText)findViewById(R.id.idCantidadEntradas);
		String s=cantEntradas.getText().toString();
		if(!s.equals("")){
			cant= Integer.parseInt(cantEntradas.getText().toString());
			Intent intent = new Intent(CompraActivity.this, SectorAActivity.class);
			intent.putExtra("cantEntrada", cant);
			Log.e("v.getId "+ v.getId(), " A "+R.id.sectorA +" B "+R.id.sectorB +" C "+R.id.sectorC );
		switch (v.getId()){
		case R.id.sectorA:
			intent.putExtra("sector",sectorA );
			break;
		case R.id.sectorB:
			intent.putExtra("sector",sectorB );	
			break;
		case R.id.sectorC:
			intent.putExtra("sector",sectorC );
			break;
		}	
		startActivity(intent);
			
		}else{
            Toast.makeText(this,"Debe seleccionar la cantidad de entradas a seleccionar" , Toast.LENGTH_SHORT).show();
		}
		
		
		}
		
	
	

	public void addListenerOnButton() {
//		 final boolean bol = false;
		

		bttnSectorA.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CompraActivity.this, SectorAActivity.class);
				cantEntradas=(EditText)findViewById(R.id.idCantidadEntradas);
//				cant= cantEntradas.getText().toString();
				intent.putExtra("cantEntrada",cant );
				intent.putExtra("sector",sectorA ); 
				startActivity(intent);				    
	        }
			
		});
		bttnSectorB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CompraActivity.this, SectorAActivity.class);
				intent.putExtra("cantEntrada", cantEntradas.getText().toString());
				intent.putExtra("sector", sectorB); 
				startActivity(intent);				    
	        }
			
		});
		bttnSectorC.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CompraActivity.this, SectorAActivity.class);
				intent.putExtra("cantEntrada", cantEntradas.getText().toString());
				intent.putExtra("sector",sectorC); 
				startActivity(intent);				    
	        }
			
		});
		btnComprar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(usuario.getLogueado()){
					Intent intent = new Intent(CompraActivity.this, SectorAActivity.class);
					intent.putExtra("usuario",usuario);			
					startActivity(intent);
				}else{
					Intent intent = new Intent(CompraActivity.this, LoginActivity.class);
					
					startActivity(intent);

				}		

				
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
