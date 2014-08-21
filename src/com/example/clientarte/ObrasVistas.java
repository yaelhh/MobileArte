package com.example.clientarte;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import backend.CompraBackend;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.java.Query;

import dominio.Compra;
import dominio.Funcion;
import dominio.Obra;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ObrasVistas extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener{

	private SwipeRefreshLayout swipeLayout;
	private  ListView miLista;
	private Client mKinveyClient;
	private Compra compra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prox_obras);


		//Obtenemos una referencia al viewgroup SwipeLayou
		swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container_prox_obras);
		//Indicamos que listener recogerá la retrollamada (callback), en este caso, será el metodo OnRefresh de esta clase.
		swipeLayout.setOnRefreshListener(this);
		//Podemos espeficar si queremos, un patron de colores diferente al patrón por defecto.
		(swipeLayout).setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);
		//Obtenemos una referencia a nuestra lista.
		//		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		miLista = (ListView) findViewById(R.id.lista_prox_obras);


		cargarDatos();
	}

	public void cargarDatos(){
		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		mKinveyClient= obj.captarUsuarioLogueado();
		Query nquery = mKinveyClient.query ();
		final ArrayList<Obra> lObra= obj.getListObras();
		ArrayList<Funcion> lFuncion= obj.getListaFuncion();
		String xo=obj.captarUsuarioLogueado().user().getUsername();
		nquery.equals("idUsuario", obj.captarUsuarioLogueado().user().getUsername());
		//			nquery.addSort("idSector", SortOrder.DESC);
		AsyncAppData<CompraBackend> searchedEvents = mKinveyClient.appData("Compra", CompraBackend.class);
		searchedEvents.get(nquery, new KinveyListCallback<CompraBackend>() {
			@Override
			public void onSuccess(CompraBackend[] resultadoconsulta) { 
				if(resultadoconsulta.length>0){
				Log.e("Traigo","traigo ls compras");
				ArrayList<ListaEntradaCompra> datos = new ArrayList<ListaEntradaCompra>(); 
				for(int x=0;x<resultadoconsulta.length;x++){
					//	Obra obraActual= listObras.get(x);
					CompraBackend datoActual=resultadoconsulta[x];
					for(int j=0;j<lObra.size();j++){
						if(datoActual.getIdObra()==lObra.get(j).getIdObra()){
							Obra obra=lObra.get(j);
							for(int y=0;y<obra.getListaFunciones().size();y++){
								Calendar c = Calendar.getInstance();
								SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
								String fechaActual = df1.format(c.getTime());
								Date fechaHoy = df1.parse(fechaActual , new ParsePosition(0));
								Date fechaFuncion=df1.parse(obra.getListaFunciones().get(y).getFechaObra() , new ParsePosition(0));
								if(obra.getListaFunciones().get(y).getIdFuncion()==datoActual.getIdFuncion() && fechaFuncion.before(fechaHoy) ){
									compra=new Compra(datoActual.getId(),obra,datoActual.isPago(),datoActual.getPrecioTotal(),obra.getListaFunciones().get(y)); 
									datos.add(new ListaEntradaCompra(R.drawable.qrcodigo,obra.getNombre(),obra.getListaFunciones().get(y).toString(),datoActual.getPrecioTotal(),datoActual.isPago(),compra));
									break;
								}
							}
						}
					}

				}
				//					datos.add(new ListaEntradaCompra(R.drawable.qrcodigo,resultadoconsulta[x]));
				if(datos.size()==0){
					Toast.makeText(getApplicationContext(),"Usted por el momento no ha visto obras", Toast.LENGTH_SHORT).show();

				}
				Lista_adaptador l=new Lista_adaptador(getApplicationContext(),R.layout.entrada_compra, datos) {
					
					@Override
					public void onEntrada(Object entrada, View view) {
						// TODO Auto-generated method stub
						TextView tituloObra = (TextView) view.findViewById(R.id.textTituloObra); 
						tituloObra.setText(((ListaEntradaCompra) entrada).getTituloObra()); 
						ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageQr); 
						//						imagen_entrada.setBackgroundResource(((ListaEntradaObra) entrada).getImagenObra());
						TextView funcion=(TextView)view.findViewById(R.id.textFuncion);
						funcion.setText("Funcion: "+((ListaEntradaCompra) entrada).getFuncion());
						TextView precio=(TextView)view.findViewById(R.id.textPrecio);
						precio.setText("Precio: $"+String.valueOf(((ListaEntradaCompra) entrada).getPrecio()));
						
					}
				};
				miLista.setAdapter(l);
					

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
						ListaEntradaCompra elegido = (ListaEntradaCompra) pariente.getItemAtPosition(posicion); 
						
						
					}


				});
				}else{
					Toast.makeText(getApplicationContext(),"Usted por el momento no ha visto obras", Toast.LENGTH_SHORT).show();

				}
			}

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Ha ocurrido un error, asegurece de estar logueado y tener conexión a internet", Toast.LENGTH_SHORT).show();

			}
		});
	}

	@Override
	public void onRefresh() {
		//Aqui ejecutamos el codigo necesario para refrescar nuestra interfaz grafica.
		//Antes de ejecutarlo, indicamos al swipe layout que muestre la barra indeterminada de progreso.
		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		Toast.makeText(this,"Cargando...", Toast.LENGTH_SHORT).show();

		swipeLayout.setRefreshing(true);
		//Vamos a simular un refresco con un handle.
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				//Se supone que aqui hemos realizado las tareas necesarias de refresco, y que ya podemos ocultar la barra de progreso
				cargarDatos();
				swipeLayout.setRefreshing(false);
			}
		}, 3000);
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
			View rootView = inflater.inflate(R.layout.fragment_prox_obras,
					container, false);
			return rootView;
		}
	}

}
