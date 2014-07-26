package com.example.clientarte;

import java.util.ArrayList;

import dominio.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

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

	// Sector miSector= new Sector(1, 25);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sectora);
		gv = (GridView) findViewById(R.id.grdSectorA);
		Bundle extras = getIntent().getExtras();
		cantEntradas= extras.getInt("cantEntrada");
		SectorA = getIntent().getParcelableExtra("sector");
		gv.setNumColumns(SectorA.getLinea());
		btnOk=(Button)findViewById(R.id.bttnOk);
		btnOk.setClickable(false);

		// Mostrar id de butacas en el log
		for (int i = 0; i < SectorA.getTotalButacas(); i++) {
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
		twEntradas.setText("Eligió comprar "+ cantEntradas + " entradas");
		

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
			if (listButacas.get(i).getEstadoButaca() == false) {
				butacaXSector[i] = R.drawable.butaca_roja;
				contOcupada++;

				// Log.e("Butaca true" +i,butacaXSector[i].toString());
			} else {
				// Log.e("Butaca false "+i,"Butaca"+i);
				butacaXSector[i] = R.drawable.butaca_verde;
				contLibre++;
			}
		}
		IA.setmThumbIds(butacaXSector);
		grid.setAdapter(IA);

	}

	public void seleccionarButaca() {
		gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, android.view.View v,
					int position, long id) {


				if (listButacas.get(position).getEstadoButaca() == true) {
					if (cantEntradas==0 || cantSeleccionadas < cantEntradas) {
						butacaXSector[position] = R.drawable.butaca_roja;
						listButacas.get(position).setEstadoButaca(false);
						cantSeleccionadas++;
					} else {
						Toast.makeText(SectorAActivity.this,"Ya seleccionó todas ls entradas elegidas" , Toast.LENGTH_SHORT).show();

					}

				} else {
					butacaXSector[position] = R.drawable.butaca_verde;
					listButacas.get(position).setEstadoButaca(true);
					cantSeleccionadas--;
				}

				IA.setmThumbIds(butacaXSector);
				gv.setAdapter(IA);
				if(cantSeleccionadas==cantEntradas){
					btnOk.setClickable(true);
				}

			}

		});
	}
	public void butacasElegidas(View v){
		Intent intent = new Intent();
//		intent.putExtra("sectorElegido",SectorA );
		intent.putExtra("yaSeleccionadas", true);
        setResult( Activity.RESULT_OK, intent );
        SectorAActivity.this.finish();
//		finish();
	}
}
