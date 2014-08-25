package com.example.clientarte;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import backend.ButacaFuncionSectorBackend;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.java.Query;

import dominio.Butaca;
import dominio.Funcion;
import dominio.Sala;
import dominio.Sector;

public class SectorAActivity extends Activity {
	ArrayList<Butaca> listButacas = new ArrayList<Butaca>();
	GridView gv;
	Sector SectorA;
	Butaca butaca;
	Sala sala;

	ImageAdapter IA = new ImageAdapter(SectorAActivity.this);
	Integer[] butacaXSector;
	TextView twCantButacaTotal;
	TextView twCantButacaLibre;
	TextView twCantButacaOcupada;
	TextView twEntradas;
	int contLibre;
	int contOcupada;
	int cantEntradas=1;
	int cantSeleccionadas=0;
	Button btnOk;
	int PrecioTotal;
	ArrayList<Butaca> lstButaca=new ArrayList<Butaca>();
	private Client mKinveyClient;
	private Funcion funcion;
	private ProgressBar bar;


	// Sector miSector= new Sector(1, 25);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sectora);
		gv = (GridView) findViewById(R.id.grdSectorA);
		Bundle extras = getIntent().getExtras();
		cantEntradas= extras.getInt("cantEntrada");
		SectorA = getIntent().getParcelableExtra("sector");
		funcion=getIntent().getParcelableExtra("funcion");
		setTitle("Sector elegido");
		//		PrecioTotal= cantEntradas*SectorA.getPrecioSector();
		gv.setNumColumns(SectorA.getLinea());
		btnOk=(Button)findViewById(R.id.bttnOk);
		//		btnOk.setClickable(false);
		bar=(ProgressBar)findViewById(R.id.progress_bar_sector);
		 bar.setIndeterminate(true);
        bar.setVisibility(View.GONE);

		// Mostrar id de butacas en el log
		for (int i = 0; i < SectorA.getTotalButacas()-1; i++) {
			butaca = SectorA.getListaButacas().get(i);
		}
		listButacas = SectorA.getListaButacas();
		crearSector(gv, listButacas);
		twCantButacaTotal = (TextView) findViewById(R.id.twTotal);
		twCantButacaTotal.setText(" Total butacas en sector "+ SectorA.getTotalButacas()+" ");
		twCantButacaLibre = (TextView) findViewById(R.id.twLibre);
		twCantButacaLibre.setText(" " + contLibre);
		twCantButacaOcupada = (TextView) findViewById(R.id.twOcupada);
		twCantButacaOcupada.setText("  " + contOcupada);
		twEntradas= (TextView)findViewById(R.id.twEntradas);
		twEntradas.setText("Le quedan por elegir "+ cantEntradas + " butacas");
		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		mKinveyClient=obj.getmKinveyClient();


		seleccionarButaca();
	}

	public void crearSector(GridView grid, ArrayList<Butaca> listButacas) {
		// Creamos el gridview
		ImageAdapter IA = new ImageAdapter(SectorAActivity.this);
		butacaXSector = new Integer[listButacas.size()];

		// Recorremos el gridview para ingresarle las imagenes, luego les
		// cargaremos los objetos
		for (int i = 0; i < listButacas.size(); i++) {
			// Log.e("Butaca"+i,"IdButaca "+ listButacas.get(i).getIdButaca());
			switch (listButacas.get(i).getEstadoButaca()){
			case 0:
				butacaXSector[i] = R.drawable.butaca_verde;
				contLibre++;
				break;
			case 1:	
				butacaXSector[i] = R.drawable.butaca_roja;
				contOcupada++;
				break;
			case 2:
				butacaXSector[i] = R.drawable.butaca_amarilla;
				cantSeleccionadas++;
				break;
			}
		}
		IA.setmThumbIds(butacaXSector);
		grid.setAdapter(IA);

	}

	public void seleccionarButaca() {
		gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, android.view.View v,
					int position, long id) {

				switch (listButacas.get(position).getEstadoButaca()){
				case 0:
					if (cantSeleccionadas < cantEntradas) {
						VerificarButaca(position);
						//						butacaXSector[position] = R.drawable.butaca_amarilla;
						//						listButacas.get(position).setEstadoButaca(2);
						//						PrecioTotal+=SectorA.getPrecioSector();
						//						cantSeleccionadas++;
						//						lstButaca.add(listButacas.get(position));


					} else {
						Toast.makeText(SectorAActivity.this,"Ya seleccionó todas las entradas elegidas" , Toast.LENGTH_SHORT).show();

					}
					break;
				case 1:
					break;
				case 2:	
					butacaXSector[position] = R.drawable.butaca_verde;
					listButacas.get(position).setEstadoButaca(0);
					lstButaca.remove(listButacas.get(position));
					PrecioTotal-=SectorA.getPrecioSector();
					cantSeleccionadas--;
					IA.setmThumbIds(butacaXSector);
					gv.setAdapter(IA);
				}

				
				//				if(cantSeleccionadas==cantEntradas){
				//					btnOk.setClickable(true);
				//				}

			}

		});
	}

	public void VerificarButaca(final int position){
		bar.setVisibility(View.VISIBLE);
		Query query1 = mKinveyClient.query ();
		Query query2 = mKinveyClient.query ();
		query1.equals("idButaca", String.valueOf(listButacas.get(position).getIdButaca()));
		query2.equals("idFuncion", String.valueOf(funcion.getIdFuncion()));
		
		AsyncAppData<ButacaFuncionSectorBackend> searchedEvents = mKinveyClient.appData("ButacaFuncionSector", ButacaFuncionSectorBackend.class);
		searchedEvents.get(query1.and(query2), new KinveyListCallback<ButacaFuncionSectorBackend>(){			
			@Override
			public void onSuccess(ButacaFuncionSectorBackend[] result) {
				Log.e("Entre al onSuccess", result.length+"es la cantidad del resultado q trajo");
//				for(int x=0; x<result.length;x++){
					if(result[0].getEstadoButaca().equalsIgnoreCase("0")){
						Log.e("Encontre ButacaFuncionSectorBackend ",result[0].getIdButaca()+"--"+result[0].getEstadoButaca());

						butacaXSector[position] = R.drawable.butaca_amarilla;
						listButacas.get(position).setEstadoButaca(2);
						PrecioTotal+=SectorA.getPrecioSector();
						cantSeleccionadas++;
						lstButaca.add(listButacas.get(position));
						IA.setmThumbIds(butacaXSector);
						gv.setAdapter(IA);
						bar.setVisibility(View.GONE);
					}else{
						Toast.makeText(SectorAActivity.this,"Ups.. alguien le ganó de mano, y compró esta butaca por favor selecione una nueva" , Toast.LENGTH_SHORT).show();
						butacaXSector[position] = R.drawable.butaca_roja;
						listButacas.get(position).setEstadoButaca(1);
						IA.setmThumbIds(butacaXSector);
						gv.setAdapter(IA);
						bar.setVisibility(View.GONE);

						
					}

//				}
			}

			@Override
			public void onFailure(Throwable error) {
				Toast.makeText(SectorAActivity.this,"Ha ocurrido un error y no se pudo conectar para verificar que la butaca este en este momento disponible, verifique su conexión a internet" , Toast.LENGTH_SHORT).show();
				bar.setVisibility(View.GONE);
			}


		});
	}



public void butacasElegidas(View v){
	SectorA.setListaButacas(listButacas);
	Intent intent = new Intent();
	intent.putExtra("sectorElegido",SectorA );
	intent.putExtra("yaSeleccionadas", true);
	intent.putExtra("precioTotal", PrecioTotal);
	intent.putExtra("ButacasSeleccionadas", lstButaca);
	intent.putExtra("cantSeleccionadas", cantSeleccionadas);
	setResult( Activity.RESULT_OK, intent );
	SectorAActivity.this.finish();
	//		finish();
}
}
