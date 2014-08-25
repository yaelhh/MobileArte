package com.example.clientarte;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import backend.ComunidadBackend;
import backend.DatabaseHelper;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.java.Query;
import com.kinvey.java.core.KinveyClientCallback;

import dominio.Obra;
import dominio.Usuario;

public class ComunidadActivity extends ActionBarActivity {

	public static final String TAG = "ArteBackend";
	//private String appKey="kid_VT8_It3ePE";
	//private String appSecret="1b0fa51481984d2da5910f78a9d26ccc";
	private String mensaje;
	private Client mKinveyClient;
	private TextView tvBd1;
	private ViewGroup layout;
	private ScrollView scroll;
	private ListView lista;
	private static final int DLG_EXAMPLE1 = 0;
	private static final int TEXT_ID = 0;
	private static final int REQUEST_TEXT = 4;
	DatabaseHelper dh;
	private ImageButton btnAgregarComentario;
	private Obra obra;
	ArrayList<ComunidadBackend> listaComentarios= new ArrayList<ComunidadBackend>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado);
		setTitle("Comunidad");
		obra= new Obra();
		obra= getIntent().getParcelableExtra("obra");
		dh = new DatabaseHelper(getApplicationContext());
		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();

		//mKinveyClient = obj.getmKinveyClient(); 
		mKinveyClient = obj.captarUsuarioLogueado();
		//validarBoton(mKinveyClient, obj);

		//		if (!mKinveyClient.user().isUserLoggedIn()) {
		//			conectarBackend ();
		//		}
		if (obra == null){
			cargarDatos(mKinveyClient);
		}else{
			cargarDatosFiltrados(obra.getIdObra());
		}

		btnAgregarComentario= (ImageButton)findViewById(R.id.btnAgregarComentario);
		addListenerOnButton(obj);
	}

	public void addListenerOnButton( final ObjetosBackend obj) {

		btnAgregarComentario.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(validarBoton(mKinveyClient, obj)){
					if (!obj.captarUsuarioLogueado().user().getUsername().equalsIgnoreCase("adm")){
						if (obra != null){
							ingresarComentario(obra.getIdObra());	
						}
						if (obra == null){
							ingresarComentarioSinObra();
						}else{
							//							Toast t2 = Toast.makeText(obj, "No logueado", 8);
							//							t2.show();
						}
					}

				}
				String texto = "Si desea ingresar un comentario debe estar logueado. Gracias";
				Toast toast = Toast.makeText(ComunidadActivity.this, texto, Toast.LENGTH_LONG);
				toast.show();

			}
		});
	}


	public boolean validarBoton(Client c, ObjetosBackend o){
		boolean retorno = false;
		//if (obtenerUsuarioLogueado(c.user().getUsername()) == false){
		//if (c.user().getUsername().length()>12){
		mKinveyClient = o.captarUsuarioLogueado();
		if (!mKinveyClient.user().isUserLoggedIn()){
			mensajeConfirmacionLoguin();
			retorno = false;
		}	else{
			retorno = true;
		}
		return retorno;
	}

	public void mensajeConfirmacionLoguin(){
		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ComunidadActivity.this); 
		dialogo1.setTitle("Importante"); 
		dialogo1.setMessage("No esta logueado,¿Desea loguearse?"); 
		dialogo1.setCancelable(false); 
		dialogo1.setPositiveButton("Si", new DialogInterface.OnClickListener() { 
			public void onClick(DialogInterface dialogo1, int id) { 
				Intent intent = new Intent(ComunidadActivity.this, LoginActivity.class); 
				//startActivity(intent);
				ComunidadActivity.this.startActivityForResult(intent, REQUEST_TEXT);

			} 
		}); 
		dialogo1.setNegativeButton("No", new DialogInterface.OnClickListener() { 
			public void onClick(DialogInterface dialogo1, int id) { 
				cancelar(); 
			} 
		}); 
		dialogo1.show(); 


	}


	private boolean obtenerUsuarioLogueado(String username) {
		boolean retorno = false;
		ArrayList <Usuario> listaUsuarios = (ArrayList<Usuario>) dh.getAllTags();
		if(listaUsuarios.size() == 0){

			Log.e("largo lista de usuarios"+ listaUsuarios.size(), username);
			retorno = false;
		}else{
			for (int i = 0; i<listaUsuarios.size();i++){
				Usuario u = listaUsuarios.get(i);
				if (u.getLogueado()==1){
					retorno = true;
					break;
				}
			}

		}

		return retorno;
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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
			View rootView = inflater.inflate(R.layout.fragment_comunidad,
					container, false);
			return rootView;
		}
	}


	public void cargarDatos(Client mKinveyClient){
		Query myQuery = mKinveyClient.query();
		mKinveyClient.appData("Comunidad", ComunidadBackend.class).get(myQuery, new KinveyListCallback<ComunidadBackend>() {
			@Override
			public void onSuccess(ComunidadBackend[] resultadoconsulta) {
				if(resultadoconsulta.length>0){
					lista = (ListView) findViewById(R.id.ListView_listado);
					ArrayList<Lista_entrada> datos = new ArrayList<Lista_entrada>();  
					for (int i = 0; i < resultadoconsulta.length; i++) {
						datos.add(new Lista_entrada(R.drawable.user_icon,resultadoconsulta[i].getUsuario(),resultadoconsulta[i].getDescripcionComunidad()));
					}
					lista.setAdapter(new Lista_adaptador(ComunidadActivity.this, R.layout.activity_entradalv,datos) {

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

					lista.setOnItemClickListener(new OnItemClickListener() { 
						@Override
						public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
							Lista_entrada elegido = (Lista_entrada) pariente.getItemAtPosition(posicion); 

							CharSequence texto = "Seleccionado: " + elegido.get_textoDebajo();
							Toast toast = Toast.makeText(ComunidadActivity.this, texto, Toast.LENGTH_LONG);
							toast.show();
						}


					});
				}else{
					Toast.makeText(ComunidadActivity.this, "Se el primero en ingresar un comentario", Toast.LENGTH_LONG).show();

				}
			}

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}
		});



	}

	public void ingresarComentario(int idO) {
		//mKinveyClient = new Client.Builder(this.getApplicationContext()).build();
		if (!mKinveyClient.user().isUserLoggedIn()) {
			mostrarDialog();

		}else{
			Toast t=Toast.makeText(this,"ESTA LOGUEADO", Toast.LENGTH_SHORT);
			t.show();
			mostrarDialog();
		}


	}

	public void ingresarComentarioSinObra() {
		//mKinveyClient = new Client.Builder(this.getApplicationContext()).build();
		if (!mKinveyClient.user().isUserLoggedIn()) {
			mostrarDialog();

		}else{
			Toast t=Toast.makeText(this,"ESTA LOGUEADO", Toast.LENGTH_SHORT);
			t.show();
			mostrarDialog();
		}


	}


	public void mostrarDialog(){
		showDialog(DLG_EXAMPLE1);
	}

	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case DLG_EXAMPLE1:
			return createExampleDialog();
		default:
			return null;
		}
	}

	private Dialog createExampleDialog() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Compartir:");
		builder.setMessage("Ingrese su comentario:");
		builder.setIcon(R.drawable.butaca_roja);

		// Use an EditText view to get user input.
		final EditText input = new EditText(this);
		input.setId(TEXT_ID);
		builder.setView(input);

		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				String value = input.getText().toString();
				Log.d(TAG, "Usuario: " + value);
				guardarDatosComentarios(value);
				input.setText("");
				return;
			}
		});

		builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				input.setText("");
				return;
			}
		});

		return builder.create();
	}

	//	public void guardarDatosComentarios(String valor){
	//		//ObjetosBackend obb = ((ObjetosBackend) mKinveyClient).getmKinveyClient();
	//		Usuario u =	obtenerUsuarioLogueado();
	//		ComunidadBackend event = new ComunidadBackend();
	//		event.setDescripcionComunidad(valor);
	//		//event.setUsuario(mKinveyClient.user().getUsername().toString());
	//		event.setUsuario(u.getMiNombreUsuario());
	//		if (obra != null){
	//			String obr = obra.getIdObra()+"";
	//			if (obr == ""){
	//				event.setIdObra("");
	//			}else{
	//				event.setIdObra(obra.getIdObra()+"");	
	//			}
	//		}else{
	//			event.setIdObra("");
	//
	//		}
	//
	//
	//		//event.setIdObra(event);
	//
	//		AsyncAppData<ComunidadBackend> myevents = mKinveyClient.appData("Comunidad",ComunidadBackend.class);
	//		myevents.save(event, new KinveyClientCallback<ComunidadBackend>() {
	//
	//			@Override
	//			public void onFailure(Throwable e) {
	//				Log.e(TAG, "failed to save event data", e);
	//			}
	//			@Override
	//			public void onSuccess(ComunidadBackend arg0) {
	//
	//				actualizarDatos();
	//			}
	//		});
	//
	//	}

	public void guardarDatosComentarios(String valor){ 
		obtenerUsuarioLogueado();
		String u = mKinveyClient.user().getUsername().toString(); 
		ComunidadBackend event = new ComunidadBackend();
		event.setDescripcionComunidad(valor);
		event.setUsuario(u);
		if (obra != null){ 
			String obr = obra.getIdObra()+""; 
			if (obr == ""){
				event.setIdObra("");
			}else{
				event.setIdObra(obra.getIdObra()+"");

			}
		}else{
			event.setIdObra("");
		}
		AsyncAppData<ComunidadBackend> myevents = mKinveyClient.appData("Comunidad",ComunidadBackend.class);
		myevents.save(event, new KinveyClientCallback<ComunidadBackend>() {
			@Override
			public void onFailure(Throwable e) { 
				Log.e(TAG, "failed to save event data", e);
			}
			@Override
			public void onSuccess(ComunidadBackend arg0) { 
				actualizarDatos();
			}
		});
	}


	public Usuario obtenerUsuarioLogueado(){
		String estaLogueado = 1 + "";
		Usuario u = dh.obtenerUsuarioLogueado (estaLogueado);
		return u;
	}

	public void actualizarDatos(){ 
		if (obra!=null){
			cargarDatosFiltrados(obra.getIdObra());
		}else{
			cargarDatos(mKinveyClient);
		}
	}

	public void mensajeConfirmacion(){
		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this); 
		dialogo1.setTitle("Importante"); 
		dialogo1.setMessage("No esta logueado,¿Desea loguearse?"); 
		dialogo1.setCancelable(false); 
		dialogo1.setPositiveButton("Si", new DialogInterface.OnClickListener() { 
			public void onClick(DialogInterface dialogo1, int id) { 
				Intent intent = new Intent(ComunidadActivity.this, LoginActivity.class); 
				//startActivity(intent);
				ComunidadActivity.this.startActivityForResult(intent, REQUEST_TEXT);

			} 
		}); 
		dialogo1.setNegativeButton("No", new DialogInterface.OnClickListener() { 
			public void onClick(DialogInterface dialogo1, int id) { 
				cancelar(); 
			} 
		}); 
		dialogo1.show(); 


	}

	public void cancelar() {
		//finish();
		Toast t=Toast.makeText(this,"Presiono cancelar", Toast.LENGTH_SHORT);
		t.show();
	}

	public ArrayList crearListComunidad(){
		Query myQuery = mKinveyClient.query ();
		mKinveyClient.appData("Comunidad", ComunidadBackend.class).get(myQuery, new KinveyListCallback<ComunidadBackend>() {
			@Override
			public void onSuccess(ComunidadBackend[] resultadoconsulta) {
				for (int i = 0; i < resultadoconsulta.length; i++) {
					//Sala sala= new Sala(resultadoconsulta[i].getIdSala(),resultadoconsulta[i].getNombreSala(),resultadoconsulta[i].getCapacidad());
					ComunidadBackend cb = new ComunidadBackend(resultadoconsulta[i].getUsuario(),resultadoconsulta[i].getDescripcionComunidad(),resultadoconsulta[i].getIdObra());
					listaComentarios.add(cb);
				}
			}

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}
		});
		return listaComentarios;
	}

	private void cargarDatosFiltrados(int idObra) {
		String idO = idObra + "";
		Query query = mKinveyClient.query ();
		query.equals("idObra", idO);
		//query.regEx("name", searchBar.getText().toString());
		AsyncAppData<ComunidadBackend> searchedEvents = mKinveyClient.appData("Comunidad", ComunidadBackend.class);
		searchedEvents.get(query, new KinveyListCallback<ComunidadBackend>() {
			@Override
			public void onSuccess(ComunidadBackend[] resultadoconsulta) { 
				if(resultadoconsulta.length>0){
					lista = (ListView) findViewById(R.id.ListView_listado);
					ArrayList<Lista_entrada> datos = new ArrayList<Lista_entrada>();  
					for (int i = 0; i < resultadoconsulta.length; i++) {
						datos.add(new Lista_entrada(R.drawable.user_icon,resultadoconsulta[i].getUsuario(),resultadoconsulta[i].getDescripcionComunidad()));
					}
					lista.setAdapter(new Lista_adaptador(ComunidadActivity.this, R.layout.activity_entradalv,datos) {

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

					lista.setOnItemClickListener(new OnItemClickListener() { 
						@Override
						public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
							Lista_entrada elegido = (Lista_entrada) pariente.getItemAtPosition(posicion); 

							CharSequence texto = "Seleccionado: " + elegido.get_textoDebajo();
							Toast toast = Toast.makeText(ComunidadActivity.this, texto, Toast.LENGTH_LONG);
							toast.show();
						}


					});
				}else{
					Toast.makeText(ComunidadActivity.this, "Se el primero en ingresar un comentario", Toast.LENGTH_LONG).show();

				}
			}

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}
		});
	}




}