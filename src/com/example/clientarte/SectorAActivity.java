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
	int PrecioTotal;
	ArrayList<Butaca> lstButaca=new ArrayList<Butaca>();

	// Sector miSector= new Sector(1, 25);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sectora);
		gv = (GridView) findViewById(R.id.grdSectorA);
		Bundle extras = getIntent().getExtras();
		cantEntradas= extras.getInt("cantEntrada");
		SectorA = getIntent().getParcelableExtra("sector");
		PrecioTotal= cantEntradas*SectorA.getPrecioSector();
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
			if (listButacas.get(i).getEstadoButaca()==1) {
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

				switch (listButacas.get(position).getEstadoButaca()){
				case 0:
					if (cantEntradas==0 || cantSeleccionadas < cantEntradas) {
						butacaXSector[position] = R.drawable.butaca_amarilla;
						listButacas.get(position).setEstadoButaca(2);
						cantSeleccionadas++;
						lstButaca.add(listButacas.get(position));
						
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
		intent.putExtra("precioTotal", PrecioTotal);
		intent.putExtra("ButacasSeleccionadas", lstButaca);
        setResult( Activity.RESULT_OK, intent );
        SectorAActivity.this.finish();
//		finish();
	}
}
