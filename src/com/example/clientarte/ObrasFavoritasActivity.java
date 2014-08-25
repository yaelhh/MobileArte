package com.example.clientarte;

import java.util.ArrayList;

import backend.ObraBackend;
import backend.ObrasFavoritosBackend;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.java.Query;
import com.kinvey.java.query.AbstractQuery.SortOrder;

import dominio.Obra;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Build;

public class ObrasFavoritasActivity extends ActionBarActivity {
	private ArrayList<Obra>listaObras= new ArrayList<Obra>();
	private ViewGroup layout;
	//	static ObjetosBackend obj;
	private ImageButton imageObra;
	private Obra miObra;
	private  Obra obraSeleccionada= new Obra();
	private SwipeRefreshLayout swipeLayout;
	private  ListView miLista;
	private int mascaras=0;
	private Client mKinveyClient;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notificaciones);
		setTitle("Favoritos");
		miLista = (ListView) findViewById(R.id.listViewNotificaciones);
		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		cargarDatos(obj);
	}


	public void cargarDatos(final ObjetosBackend obj){
		mKinveyClient=obj.getmKinveyClient();
		listaObras=obj.getListObras();
		String nombre=mKinveyClient.user().getUsername().toString();
		Query query = mKinveyClient.query();
		query.equals("nombreUsuario", nombre);
		mKinveyClient.appData("ObrasFavoritos", ObrasFavoritosBackend.class).get(query, new KinveyListCallback<ObrasFavoritosBackend>() {
			@Override
			public void onSuccess(ObrasFavoritosBackend[] resultadoconsulta) {
				ArrayList<Lista_entrada> datos = new ArrayList<Lista_entrada>(); 
				for (int i = 0; i < resultadoconsulta.length; i++) {
					for(int x=0;x<listaObras.size();x++){
						Obra obraActual=listaObras.get(x);
						if(resultadoconsulta[i].getNombreObra().equalsIgnoreCase(listaObras.get(x).getNombre())){
							datos.add(new Lista_entrada(obraActual.getListaImagenes()[0],obraActual.getNombre(),obraActual.getDescripcion()));
						
						}
					}
				}
				miLista.setAdapter(new Lista_adaptador(getApplicationContext(), R.layout.activity_entradalv,datos) {

					@Override
					public void onEntrada(Object entrada, View view) {
						if (entrada != null) {
							TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior); 
							if (texto_superior_entrada != null) 
								texto_superior_entrada.setText(((Lista_entrada) entrada).get_textoEncima()); 

							TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior); 
							if (texto_inferior_entrada != null)
								texto_inferior_entrada.setText(((Lista_entrada) entrada).get_textoDebajo()); 

							ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen); 
							if (imagen_entrada != null)
								imagen_entrada.setImageResource(((Lista_entrada) entrada).get_idImagen());
						}

					}
				});


//				miLista.setOnItemClickListener(new OnItemClickListener() { 
//					@Override
//					public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
//						ListaEntradaObra elegido = (ListaEntradaObra) pariente.getItemAtPosition(posicion); 
//						
//					}
//
//
//				});

				Log.e("ObrasFavoritos","Listas de obras cargadas");
			}
			@Override
			public void onFailure(Throwable arg0) {
				Toast.makeText(getApplicationContext(), "Ups.. no nos pudimos conectar con la base de datos, asegurece de estar logueado y tener conexión a internet", Toast.LENGTH_LONG).show();


			}
		});
	
	
	}

@Override
public boolean onCreateOptionsMenu(Menu menu) {

	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.obras_favoritas, menu);
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_obras_favoritas,
				container, false);
		return rootView;
	}
}

}
