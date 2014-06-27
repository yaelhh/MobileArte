package com.example.clientarte;

import java.util.ArrayList;
import java.util.List;









import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
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
	Dialog customDialog;

	ArrayAdapter<String> adaptador;
	ImageButton butaca;
	private ArrayList<ImageButton> listaButacas = new ArrayList<ImageButton>();
	Integer cantButaca;

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

		addListenerOnButton();



	}
    
     


	public void addListenerOnButton() {

		bttnSectorA.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentManager fragmentManager = getFragmentManager();
			    SectoraDialog dialogo = new SectoraDialog();
			    dialogo.show(fragmentManager, "tagAlerta");
			    
			    GridView grid=(GridView)v.findViewById(R.id.grdSectorA);
			    cantButaca=25;
			    crearSector(grid,cantButaca,dialogo);
			}
		});
	}


	public void crearSector (GridView grid,Integer cantButaca,SectoraDialog dialogo){
		//Creamos el gridview
		ImageAdapter IA= new ImageAdapter(dialogo.getActivity());	
		Integer[] butacaXSector=new Integer[25];
		//Recorremos el gridview para ingresarle las imagenes, luego les cargaremos los objetos
		for(int i=0; i<cantButaca; i++){
			butacaXSector[i]=R.drawable.butaca_roja;
		}
		IA.setmThumbIds(butacaXSector);
		grid.setAdapter(IA);
	
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
	
//		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_compra,
					container, false);
			return rootView;
		}
	}

	

}
