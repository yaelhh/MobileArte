package com.example.clientarte;

import com.example.clientarte.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class CompraActivity extends ActionBarActivity {

	TextView lblPulsado;
	GridView grdOpciones;
	String[] datos;
	ArrayAdapter<String> adaptador;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compra);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.activity_Compra, new PlaceholderFragment()).commit();
		}
		
		//cargarCombos();
		crearMatriz ();
	}
	
	public void crearMatriz (){
		
		String[][] datos = new String[25][15];
		//...
		for(int i=1; i<=25; i++)
			for(int x=1; x<=25; x++)
		        datos[i] [x]= "Dato " + datos[i][x];
		 
		ArrayAdapter<String> adaptador =
		        new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		 
		grdOpciones = (GridView)findViewById(R.id.GridOpciones);
		 
		grdOpciones.setAdapter(adaptador);
	}
	
	public void cargarCombos (){
		/*Spinner spinner_sala = (Spinner) findViewById(R.id.spinner_sala);
		ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this, R.array.salas , android.R.layout.simple_spinner_item);
		spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spinner_sala.setAdapter(spinner_adapter);
		
		Spinner spinner_horarios = (Spinner) findViewById(R.id.spinner_horario);
		ArrayAdapter spinner_adapterH = ArrayAdapter.createFromResource( this, R.array.horarios , android.R.layout.simple_spinner_item);
		spinner_adapterH.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spinner_horarios.setAdapter(spinner_adapterH);*/
		
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
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_compra,
					container, false);
			return rootView;
		}
	}

}
