package com.example.clientarte;

import java.util.ArrayList;

import backend.ComunidadBackend;
import backend.DatabaseHelper;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.Query;
import com.kinvey.java.User;
import com.kinvey.java.core.KinveyClientCallback;

import dominio.Usuario;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.sax.RootElement;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/*  Fragment para seccion Nosotros */ 
public class ComunidadFragment extends Fragment {
	
	public static final String TAG = "ArteBackend";
	private String mensaje;
	private Client mKinveyClient;
	private TextView tvBd1;
	private ViewGroup layout;
	private ScrollView scroll;
	private ListView lista;
	private Button btnAgregarComentario;

	View rootView;
	DatabaseHelper dh;
	
    public ComunidadFragment(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	rootView = inflater.inflate(R.layout.activity_listado, container, false);
        dh = new DatabaseHelper(getActivity().getApplicationContext());
      
      //Conexión de la APP a Kinvey
        	final ObjetosBackend obj= (ObjetosBackend) getActivity().getApplicationContext();
      		mKinveyClient = new Client.Builder(getActivity().getApplicationContext()).build();
      		mKinveyClient.ping(new KinveyPingCallback() {
      			public void onFailure(Throwable t) {
      				Log.e("Probando Kinvey Connection", "Kinvey Ping Failed", t);
      			}
      			public void onSuccess(Boolean b) {
      				Log.d("Probando Kinvey Connection", "Kinvey Ping Success");
      			}
      		});
      		
      		//btnAgregarComentario = (Button)rootView.findViewById(R.id.btnAgregarComentario);
      		btnAgregarComentario= (Button)rootView.findViewById(R.id.btnAgregarComentario);
    		addListenerOnButton(obj);
      		cargarDatos();
      				
          
        return rootView;
    }
    
    public void addListenerOnButton(ObjetosBackend obj) {
    	mKinveyClient = obj.getmKinveyClient();
    	btnAgregarComentario.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(getActivity(), ProgramacionActivity.class);
//				startActivity(intent);
				if (buscarUsuarioLogueado()){
					agregarComentarios();
				}else{
					mensaje = "Bienvenido usuario: " + mKinveyClient.user().getUsername() + ".";
					Toast.makeText(getActivity().getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
				}
				
			}

		});
    }
    
	public void cargarDatos(){
		Query myQuery = mKinveyClient.query();
		mKinveyClient.appData("Comunidad", ComunidadBackend.class).get(myQuery, new KinveyListCallback<ComunidadBackend>() {
			@Override
			public void onSuccess(ComunidadBackend[] resultadoconsulta) {
				lista = (ListView) rootView.findViewById(R.id.ListView_listado);
				ArrayList<Lista_entrada> datos = new ArrayList<Lista_entrada>();  
				for (int i = 0; i < resultadoconsulta.length; i++) {
					datos.add(new Lista_entrada(R.drawable.user_icon,resultadoconsulta[i].getUsuario(),resultadoconsulta[i].getDescripcionComunidad()));

				}
				lista.setAdapter(new Lista_adaptador(getActivity(), R.layout.activity_entradalv,datos) {

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
						Toast toast = Toast.makeText(getActivity(), texto, Toast.LENGTH_LONG);
						toast.show();
					}


				});
			}

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}
		});



	}
	
	private void agregarComentarios() {
		ComunidadBackend event = new ComunidadBackend();
		event.setDescripcionComunidad("Launch Party");
		//event.setAddress("Kinvey HQ");
		AsyncAppData<ComunidadBackend> myevents = mKinveyClient.appData("Comunidad",ComunidadBackend.class);
		myevents.save(event, new KinveyClientCallback<ComunidadBackend>() {
		  @Override
		  public void onFailure(Throwable e) {
		      Log.e(TAG, "failed to save event data", e);
		  }
		@Override
		public void onSuccess(ComunidadBackend arg0) {
			 
			
		}
		});
		
	}
	
	public boolean buscarUsuarioLogueado (){
		String nombreUsuario = mKinveyClient.user().getUsername().toString();
		Usuario u = dh.getTodo(nombreUsuario);
		boolean resultado = false;
		if (u.getMiNombreUsuario()!= "" && u.getLogueado() == 1){
			resultado = true;
			
		}else{
			 resultado = false;
		}
		return resultado;
	}
	
}