package com.example.clientarte;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import dominio.Funcion;
import dominio.Obra;

/*  Fragment para seccion Programacion */ 
public class ProgramacionFragment extends Fragment implements OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener{
	CalendarView cal;
	private SearchView mSearchView;
	private ImageButton mObra;
	private ArrayList<Obra>listObras= new ArrayList<Obra>();
	private ArrayList<Funcion> listFunciones= new ArrayList<Funcion>();
	private ViewGroup layout;
	//	static ObjetosBackend obj;
	private ImageButton imageObra;
	private Obra miObra;
	private  Obra obraSeleccionada= new Obra();

	public static final String TAG = "ArteBackend";
	ArrayList<Obra> obrasDia= new ArrayList<Obra>();
	private SwipeRefreshLayout swipeLayouts;
	private  ListView miLista;

	View rootView;

     
    public ProgramacionFragment(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
       rootView = inflater.inflate(R.layout.activity_programacion, container, false);
		cal = (CalendarView) rootView.findViewById(R.id.calendarView1);

//       layout= (ViewGroup)rootView.findViewById(R.id.containerProgramacion);
       final ObjetosBackend obj= (ObjetosBackend) getActivity().getApplicationContext();
		listObras= obj.getListObras();

        
		//Obtenemos una referencia al viewgroup SwipeLayou
				swipeLayouts = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_contenedorDeObras);
				//Indicamos que listener recogerá la retrollamada (callback), en este caso, será el metodo OnRefresh de esta clase.
				swipeLayouts.setOnRefreshListener(this);
				//Podemos espeficar si queremos, un patron de colores diferente al patrón por defecto.
				(swipeLayouts).setColorScheme(android.R.color.holo_blue_bright,
						android.R.color.holo_green_light,
						android.R.color.holo_orange_light,
						android.R.color.holo_red_light);
				////		//Obtenemos una referencia a nuestra lista.
				//		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
				miLista = (ListView) rootView.findViewById(R.id.miListaObra);
				obtenerObraSegunFecha();
				return rootView;
    }
    	
	
    public void obtenerObraSegunFecha(){
		cal.setOnDateChangeListener(new OnDateChangeListener() {

			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,
					int dayOfMonth) {
				//				Log.e("Al principio",listObras.get(0).getNombre());
				if(obrasDia.size()>0){
					obrasDia= new ArrayList<Obra>();
					miLista.removeAllViews();			
				}

				String anio=String.valueOf(year);
				String mes=String.valueOf(month+1);
				String dia=String.valueOf(dayOfMonth);
				for(int x=0; x<listObras.size();x++){
					Obra o= listObras.get(x);
					for(int y=0;y<o.getListaFunciones().size();y++){
						String fechaActual=o.getListaFunciones().get(y).getFechaObra();
						String[] fecha = fechaActual.split("/");
						//					Log.e("Fuera if",listObras.get(x).getNombre());
						Log.e("fecha",fechaActual);
						if(dia.equalsIgnoreCase(fecha[0]) && mes.equalsIgnoreCase(fecha[1]) && anio.equalsIgnoreCase(fecha[2]) ){
							obrasDia.add(listObras.get(x));
							Log.e("Dentro if",listObras.get(x).getNombre());

						}
					}
				}

				Toast.makeText(getActivity().getBaseContext(),"Selected Date is\n\n"
						+dayOfMonth+" : "+(month+1) +" : "+year , 
						Toast.LENGTH_LONG).show();


				cargarDatos(obrasDia);
			}
			//				crearProgramacion(obrasDia);

		});


	}

	public void cargarDatos( ArrayList<Obra> lo){
		final ArrayList<Obra> listObras=lo;
		ArrayList<ListaEntradaObra> datos = new ArrayList<ListaEntradaObra>(); 
		for(int x=0;x<listObras.size();x++){
			Obra obraActual= listObras.get(x);
			datos.add(new ListaEntradaObra(obraActual.getListaImagenes()[0],obraActual.getNombre()));
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
		miLista.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				int filaSuperior = (
						miLista == null//Si la lista esta vacía ó
						|| miLista.getChildCount() == 0) ? 0 : miLista.getChildAt(0).getTop();//Estamos en el elemento superior
				swipeLayouts.setEnabled(filaSuperior >= 0); //Activamos o desactivamos el swipe layout segun corresponda
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
		listObras= obj.getListObras();


		Toast.makeText(getActivity(),"Cargando...", Toast.LENGTH_SHORT).show();

		swipeLayouts.setRefreshing(true);
		//Vamos a simular un refresco con un handle.
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				//Se supone que aqui hemos realizado las tareas necesarias de refresco, y que ya podemos ocultar la barra de progreso
				//				cargarDatos(listObras);
				obtenerObraSegunFecha();
				swipeLayouts.setRefreshing(false);
			}
		}, 3000);
	}


	//Funcion que al seleccionar una obra envie los datos a ObraActivity
	public void ingresoObra(ImageButton imageObra, Obra obra){
		final Obra obraSeleccionada=obra;
		imageObra.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				int i= v.getId(); 
				Log.e("ProgramacionActivity - ingresoObra", Integer.toString(i));

				//					obraSeleccionada= (Obra) listObras.get(i);


				Intent intent = new Intent(getActivity(), ObraActivity.class);
				intent.putExtra("obra",obraSeleccionada); 
				startActivity(intent);
			}
	});
}
     
   	
   	@Override
   	public boolean onOptionsItemSelected(MenuItem item) {
   	    // Handle presses on the action bar items
   	    switch (item.getItemId()) {
   	        case R.id.action_notification:
   	            //composeMessage();
   	            return true;
   	        default:
   	            return super.onOptionsItemSelected(item);
   	    }
   	}
   	
   	
//   	public void addListenerOnButton() {
//   		 
//   		mObra.setOnClickListener(new OnClickListener() {
//    
//   			@Override
//   			public void onClick(View v) {
//   				Intent intent = new Intent(getActivity(), ObraActivity.class);
//   				startActivity(intent);
//   			}
//
//   		});
//   }
   	public static class PlaceholderFragment extends Fragment {

   		public PlaceholderFragment() {
   		}

   		@Override
   		public View onCreateView(LayoutInflater inflater, ViewGroup container,
   				Bundle savedInstanceState) {
   			View rootView = inflater.inflate(R.layout.fragment_programacion, container, false);
   			return rootView;
   		}
   	}


	@Override
	public boolean onQueryTextChange(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}