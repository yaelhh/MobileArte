package com.example.clientarte;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CompraActivity extends ActionBarActivity {

	TextView lblPulsado;
	GridView grdOpciones;
	
	
	ArrayAdapter<String> adaptador;
	ImageButton butaca;
	private ArrayList<ImageButton> listaButacas = new ArrayList<ImageButton>();
	Integer[] butacaXSector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compra);

//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.activity_Compra, new PlaceholderFragment()).commit();
//		}
		ImageAdapter IA= new ImageAdapter(this);
		GridView gridview = (GridView) findViewById(R.id.gridview);
		butacaXSector= IA.getmThumbIds();
		Integer cantButaca=25;
		Log.e("but"+butacaXSector,"IA"+IA);
		
		for(int i=1; i<=cantButaca; i++){
		butacaXSector[i]=R.drawable.butaca_roja;
		Log.e("butacaXSector"+butacaXSector[i], "cant butaca"+i);
		}
		IA.setmThumbIds(butacaXSector);
		gridview.setAdapter(IA);
		
		
		//cargarCombos();
		//crearMatriz ();
	}
	
	public void crearMatriz (){
		
		//butaca= (ImageButton)findViewById(R.id.imageButaca);
		int text=0;
		String [] datos= new String[25];
		for(int i=0; i<=25; i++)
			datos[i-1]="Datos"+i;
			text++;
//		listaButacas.add(butaca);
		
		
		ArrayAdapter<ImageButton> adaptador =new ArrayAdapter<ImageButton>(this, text,listaButacas);
		final GridView grdOpciones = (GridView)findViewById(R.id.GridOpciones);
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
