package com.example.clientarte;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import dominio.Obra;

/*  Fragment para seccion perfil */ 
public class NovedadesFragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener{
	
	private ArrayList<Obra>listaObras= new ArrayList<Obra>();
	private ViewGroup layout;
	//	static ObjetosBackend obj;
	private ImageButton imageObra;
	private Obra miObra;
	private  Obra obraSeleccionada= new Obra();
	private SwipeRefreshLayout swipeLayout;
	private  ListView miLista;
     
    public NovedadesFragment(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.activity_novedades, container, false);
        swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
		//Indicamos que listener recogerá la retrollamada (callback), en este caso, será el metodo OnRefresh de esta clase.
		swipeLayout.setOnRefreshListener(this);
		//Podemos espeficar si queremos, un patron de colores diferente al patrón por defecto.
		(swipeLayout).setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);
		//Obtenemos una referencia a nuestra lista.
		//		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		miLista = (ListView) rootView.findViewById(R.id.mi_lista);
		Bundle extras = getActivity().getIntent().getExtras();
		if(extras!=null){
			listaObras= extras.getParcelableArrayList("listObras");
		}else{
			final ObjetosBackend obj= (ObjetosBackend) getActivity().getApplicationContext();
			listaObras= obj.getListObras();
		}
		cargarDatos(listaObras);

          
        return rootView;
    }

    public void cargarDatos( ArrayList<Obra> lo){
		final ArrayList<Obra> listObras=lo;
		ArrayList<ListaEntradaObra> datos = new ArrayList<ListaEntradaObra>(); 
		for(int x=0;x<listObras.size();x++){
			Obra obraActual= listObras.get(x);
//			datos.add(new ListaEntradaObra(obraActual.getListaImagenes()[0],obraActual.getNombre()));
		}
		miLista.setAdapter(new Lista_adaptador(getActivity(), R.layout.entrada_obras, datos){
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
				Intent intent = new Intent(getActivity(), ObraActivity.class);
				intent.putExtra("obra",obraSeleccionada); 
				startActivity(intent);
			}


		});
	}
	@Override
	public void onRefresh() {
		//Aqui ejecutamos el codigo necesario para refrescar nuestra interfaz grafica.
		//Antes de ejecutarlo, indicamos al swipe layout que muestre la barra indeterminada de progreso.
		final ObjetosBackend obj= (ObjetosBackend) getActivity().getApplicationContext();
		obj.traerDatos();
		//		ArrayList<Obra> lista=obj.getListObras();

		listaObras.clear();
		listaObras= obj.getListObras();
		Toast.makeText(getActivity(),"Cargando...", Toast.LENGTH_SHORT).show();

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

}