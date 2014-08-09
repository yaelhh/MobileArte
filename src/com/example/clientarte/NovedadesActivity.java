package com.example.clientarte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import dominio.Funcion;
import dominio.Obra;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NovedadesActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener {
	private ArrayList<Obra>listaObras= new ArrayList<Obra>();
	private ViewGroup layout;
	//	static ObjetosBackend obj;
	private ImageButton imageObra;
	private Obra miObra;
	private  Obra obraSeleccionada= new Obra();
	private SwipeRefreshLayout swipeLayout;
	private  ListView miLista;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_novedades);
		//Obtenemos una referencia al viewgroup SwipeLayou
		swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
		//Indicamos que listener recogerá la retrollamada (callback), en este caso, será el metodo OnRefresh de esta clase.
		swipeLayout.setOnRefreshListener(this);
		//Podemos espeficar si queremos, un patron de colores diferente al patrón por defecto.
		(swipeLayout).setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);
		//Obtenemos una referencia a nuestra lista.
		//		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		miLista = (ListView) findViewById(R.id.mi_lista);
		Bundle extras = getIntent().getExtras();
		if(extras!=null){
			listaObras= extras.getParcelableArrayList("listObras");
		}else{
			final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
			listaObras= obj.getListObras();
		}
		cargarDatos(listaObras);
	}

	public void cargarDatos( ArrayList<Obra> lo){
		final ArrayList<Obra> listObras=lo;
		ArrayList<ListaEntradaObra> datos = new ArrayList<ListaEntradaObra>(); 
		for(int x=0;x<listObras.size();x++){
			Obra obraActual= listObras.get(x);
			datos.add(new ListaEntradaObra(obraActual.getListaImagenes()[0],obraActual.getNombre()));
		}
		miLista.setAdapter(new Lista_adaptador(this, R.layout.entrada_obras, datos){
			@Override
			public void onEntrada(Object entrada, View view) {

				TextView tituloObra = (TextView) view.findViewById(R.id.textTituloNovObras); 
				tituloObra.setText(((ListaEntradaObra) entrada).getTituloObra()); 
				ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageNovedadesObras); 
				imagen_entrada.setBackgroundResource(((ListaEntradaObra) entrada).getImagenObra());
			}
		});

		/*Aquí esta el truco para que el refresco de una lista funcione correctamente.
		45
		 El problema del layout SwipeToRefresh es que deteca cuando hacemos scroll hacia arriba, e interpreta que debe invocar
		46
		 al callback para ejecutar el refresco. Por lo tanto, con la implementacion basica lo que obtenemos es una lista en la que no
		47
		 se puede hacer scroll hacia arriba ya que cada vez que lo intentamos, se ejecuta el callback. Por lo tanto lo que tenemos que hacer
		48
		 es comprobar que estamos en la parte superior de la lista, y solo en ese caso invocar el callback.
		49
		 */
		miLista.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				int filaSuperior = (
						miLista == null//Si la lista esta vacía ó
						|| miLista.getChildCount() == 0) ? 0 : miLista.getChildAt(0).getTop();//Estamos en el elemento superior
				swipeLayout.setEnabled(filaSuperior >= 0); //Activamos o desactivamos el swipe layout segun corresponda
			}
		});

		miLista.setOnItemClickListener(new OnItemClickListener() { 
			@Override
			public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
				ListaEntradaObra elegido = (ListaEntradaObra) pariente.getItemAtPosition(posicion); 

				obraSeleccionada= (Obra) listObras.get(posicion);
				Intent intent = new Intent(NovedadesActivity.this, ObraActivity.class);
				intent.putExtra("obra",obraSeleccionada); 
				startActivity(intent);
			}


		});
	}
	@Override
	public void onRefresh() {
		//Aqui ejecutamos el codigo necesario para refrescar nuestra interfaz grafica.
		//Antes de ejecutarlo, indicamos al swipe layout que muestre la barra indeterminada de progreso.
		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		obj.traerDatos();
		//		ArrayList<Obra> lista=obj.getListObras();

		listaObras.clear();
		listaObras= obj.getListObras();
		Toast.makeText(this,"Cargando...", Toast.LENGTH_SHORT).show();

		swipeLayout.setRefreshing(true);
		//Vamos a simular un refresco con un handle.
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				//Se supone que aqui hemos realizado las tareas necesarias de refresco, y que ya podemos ocultar la barra de progreso
				cargarDatos(listaObras);
				swipeLayout.setRefreshing(false);
			}
		}, 3000);
	}






	//	//Funcion para crear activity en funcion de la lista de obras obtenidas
	//	public void crearProgramacion(ObjetosBackend obj){ 
	//
	//		listObras=obj.getListObras();
	//
	//		ArrayList<View> listView= new ArrayList<View>();
	//		//    recorro la lista de obras existentes y agrego un imagenbutton por cada obra
	//		for(int x=0; x<listObras.size();x++){
	//			Log.e("En crearProgramacion",listObras.get(x).getNombre());
	//			miObra= (Obra) listObras.get(x);
	//			imageObra= new ImageButton(this);
	//			imageObra.setId(x);
	//			imageObra.setContentDescription(miObra.getNombre());
	//			Integer imagen= miObra.getListaImagenes()[0];
	//			imageObra.setBackgroundResource(imagen);
	//			imageObra.setPadding(10, 30, 10, 10);
	//			ingresoObra(imageObra); 
	//			//Le agrego al layout el imageButton creado
	//			layout.addView(imageObra, 470, 140);
	//		}   
	//	}
	//
	//	//Funcion que al seleccionar una obra envie los datos a ObraActivity
	//	public void ingresoObra(ImageButton imageObra){
	//		imageObra.setOnClickListener(new OnClickListener() {
	//
	//			@Override
	//			public void onClick(View v) {
	//
	//				int i= v.getId(); 
	//				Log.e("ProgramacionActivity - ingresoObra", Integer.toString(i));
	//				obraSeleccionada= (Obra) listObras.get(i);
	//				Intent intent = new Intent(NovedadesActivity.this, ObraActivity.class);
	//				intent.putExtra("obra",obraSeleccionada); 
	//				startActivity(intent);
	//			}
	//
	//		});
	//	}		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent intent = new Intent(this, Settings.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
			/*case R.id.action_settings:
        Toast.makeText(this, "Reloading parkings...", Toast.LENGTH_LONG)
            .show();
        refresh();
        break;*/

			/*default:
        break;*/
		}
		return false;
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
			View rootView = inflater.inflate(R.layout.fragment_novedades, container,
					false);
			return rootView;
		}
	}




}
