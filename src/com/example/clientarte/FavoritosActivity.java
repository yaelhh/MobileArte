package com.example.clientarte;

import java.util.ArrayList;

import dominio.Obra;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class FavoritosActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener {
	
	private ArrayList<Obra>listaObras= new ArrayList<Obra>();
	private ViewGroup layout;
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
				cargarDatosFavoritos(listaObras);
	}	
	
	
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}
	


	public void cargarDatosFavoritos( ArrayList<Obra> lo){
		final ArrayList<Obra> listObras=lo;
		ArrayList<ListaEntradaObra> datos = new ArrayList<ListaEntradaObra>(); 
		for(int x=0;x<listObras.size();x++){
			Obra obraActual= listObras.get(x);
			datos.add(new ListaEntradaObra(R.drawable.carmen_1,obraActual.getNombre()));
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
				Intent intent = new Intent(FavoritosActivity.this, ObraActivity.class);
				intent.putExtra("obra",obraSeleccionada); 
				startActivity(intent);
			}


		});
	}

}
