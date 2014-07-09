package com.example.clientarte;

import java.util.ArrayList;
import java.util.List;

import dominio.*;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class SectorAActivity extends Activity {
	ArrayList<Butaca> listButacas = new ArrayList<Butaca>();
	GridView gv;
	Sector SectorA;
	Butaca butaca;
	Sala sala;
	
	
	ImageAdapter IA= new ImageAdapter(SectorAActivity.this);
	Integer[] butacaXSector;
//	Sector miSector= new Sector(1, 25);
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sectora);
		gv= (GridView)findViewById(R.id.grdSectorA);
		SectorA = getIntent().getParcelableExtra("miSector");
				
		String mensaje = "Sector ID: " + Integer.toString((SectorA.getIdSector())) + ", " + "Cantidad de Butacas: " + Integer.toString(SectorA.getTotalButacas());
		mensaje = mensaje + ", " + "Cantidad en la lista de butacas: " + Integer.toString(SectorA.getListaButacas().size());
		Log.i("Segunda ventana - Contenido del sector", mensaje);
		//Mostrar id de butacas en el log
		for (int i = 0; i < SectorA.getTotalButacas(); i ++) {
			butaca = SectorA.getListaButacas().get(i);
			Log.i("Segunda ventana - Muestro ID butacas", "Butaca ID: " + Integer.toString(butaca.getIdButaca()));
		}
		listButacas= SectorA.getListaButacas();
		
		crearSector(gv,listButacas);
//
		seleccionarButaca();


	}

	public void crearSector (GridView grid, ArrayList<Butaca> listButacas){
		//Creamos el gridview
		ImageAdapter IA= new ImageAdapter(SectorAActivity.this);
		butacaXSector= new Integer[listButacas.size()];
		//Recorremos el gridview para ingresarle las imagenes, luego les cargaremos los objetos
		

		for(int i=0; i<listButacas.size(); i++){
			Log.e("Butaca"+i,"IdButaca "+ listButacas.get(i).getIdButaca());

			if (listButacas.get(i).getEstadoButaca()== false){

				butacaXSector[i]=R.drawable.butaca_roja;
//				Log.e("Butaca true" +i,butacaXSector[i].toString());
			}else{
//				Log.e("Butaca false "+i,"Butaca"+i);
//
				butacaXSector[i]=R.drawable.butaca_verde;
			}
					}
		IA.setmThumbIds(butacaXSector);
		grid.setAdapter(IA);

	}

	public void seleccionarButaca(){
		gv.setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent,
							android.view.View v, int position, long id) {
						if (listButacas.get(position).getEstadoButaca()== true){
							butacaXSector[position]=R.drawable.butaca_roja;
							listButacas.get(position).setEstadoButaca(false);
							
						}else{
							butacaXSector[position]=R.drawable.butaca_verde;
							listButacas.get(position).setEstadoButaca(true);
						}
						IA.setmThumbIds(butacaXSector);
						gv.setAdapter(IA);
					}



				});
	}
}

