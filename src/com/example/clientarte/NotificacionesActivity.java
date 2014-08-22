package com.example.clientarte;

import java.lang.reflect.Array;
import java.util.ArrayList;

import backend.ComunidadBackend;
import backend.DatabaseHelper;
import backend.NotificacionesBackend;
import backend.ObraBackend;
import backend.UsuarioBackend;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.Query;
import com.kinvey.java.User;
import com.kinvey.java.core.KinveyClientCallback;

import dominio.Notificaciones;
import dominio.Obra;
import dominio.Usuario;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import backend.NotificacionesBackend;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.java.Query;

public class NotificacionesActivity extends Activity{
	
	private Client mKinveyClient;
	private ImageButton btnBorrar;
	ListView listView;
	ArrayList<String> arrayList = new ArrayList<String>();
	ArrayAdapter<String> adaptador;
	View rootView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notificaciones);

		//Instanciamos el listView
		listView = (ListView) findViewById(R.id.listViewNotificaciones);

		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		mKinveyClient = obj.captarUsuarioLogueado();
		//cargarDatosNotificaciones(mKinveyClient);
		cargarDatos(mKinveyClient);

		//Instanciamos el adaptador, le pasamos el arraylist y al listview la pasamos nuestro adapter como adaptador de contenido
		adaptador = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, arrayList);
		listView.setAdapter(adaptador);
		
//		//Deslizar item para borrarlo
//				SwipeListViewTouchListener touchListener =new SwipeListViewTouchListener(listView,new SwipeListViewTouchListener.OnSwipeCallback() {
//					@Override
//					public void onSwipeLeft(ListView listView, int [] reverseSortedPositions) {
//						//Aqui ponemos lo que hara el programa cuando deslizamos un item ha la izquierda
//						arrayList.remove(reverseSortedPositions[0]);
//						adaptador.notifyDataSetChanged();
//					}
//
//					@Override
//					public void onSwipeRight(ListView listView, int [] reverseSortedPositions) {
//						//Aqui ponemos lo que hara el programa cuando deslizamos un item ha la derecha
//						//arrayList.remove(reverseSortedPositions[0]);
//						
//						arrayList.remove(reverseSortedPositions[0]);
//						adaptador.notifyDataSetChanged();
//					}
//				},true, false);
//				
//				//Escuchadores del listView
//				listView.setOnTouchListener(touchListener);
//				listView.setOnScrollListener(touchListener.makeScrollListener());	
		mostrarMensaje(listView);
	}
	
	
	private void cargarDatosNotificaciones(Client mKinveyClient2) {
		Query myQuery = mKinveyClient.query();
		mKinveyClient.appData("NotificacionesUsuario", NotificacionesBackend.class).get(myQuery, new KinveyListCallback<NotificacionesBackend>() {
			@Override
			public void onSuccess(NotificacionesBackend[] resultadoconsulta) {
				//listView = (ListView) rootView.findViewById(R.id.ListView_listado);
				listView = (ListView) findViewById(R.id.listViewNotificaciones);
				ArrayList<Lista_entrada> datos = new ArrayList<Lista_entrada>();  
				for (int i = 0; i < resultadoconsulta.length; i++) {
					datos.add(new Lista_entrada(R.drawable.ic_action_event,resultadoconsulta[i].getIdUsuario(),
							resultadoconsulta[i].getTipo(),resultadoconsulta[i].getTitulo(),
							resultadoconsulta[i].getTexto()));

				}
				listView.setAdapter(new Lista_adaptador(NotificacionesActivity.class, R.layout.activity_notificaciones,datos) {

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

//				listView.setOnItemClickListener(new OnItemClickListener() { 
//					@Override
//					public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
//						Lista_entrada elegido = (Lista_entrada) pariente.getItemAtPosition(posicion); 
//
//						CharSequence texto = "Seleccionado: " + elegido.get_textoDebajo();
//						Toast toast = Toast.makeText(NotificacionesActivity.class, texto, Toast.LENGTH_LONG);
//						toast.show();
//					}
//
//
//				});
			}

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}
		});

		
	}
	
	
//	public void cargarDatos(Client k){
//		String idUsuario = k.user().getUsername() + "";
//		Query query = k.query ();
//		query.equals("idUsuario", idUsuario);
//		
//		AsyncAppData<NotificacionesBackend> searchedEvents = k.appData("NotificacionesUsuario", NotificacionesBackend.class);
//		searchedEvents.get(query, new KinveyListCallback<NotificacionesBackend>() {
//			@Override
//			public void onSuccess(NotificacionesBackend[] resultadoconsulta) { 
//				listView = (ListView) findViewById(R.id.listViewNotificaciones);
//				ArrayList<Lista_entrada> datos = new ArrayList<Lista_entrada>();  
//				for (int i = 0; i < resultadoconsulta.length; i++) {
//					datos.add(new Lista_entrada(R.drawable.user_icon,resultadoconsulta[i].getIdUsuario(),resultadoconsulta[i].getTipo(), resultadoconsulta[i].getTexto()));
//				}
//				listView.setAdapter(new Lista_adaptador(NotificacionesActivity.this, R.layout.activity_entradalv,datos) {
//
//					@Override
//					public void onEntrada(Object entrada, View view) {
//						if (entrada != null) {
//							TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior); 
//							if (texto_superior_entrada != null) 
//								texto_superior_entrada.setText(((Lista_entrada) entrada).get_textoEncima()); 
//
//							TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior); 
//							if (texto_inferior_entrada != null)
//								texto_inferior_entrada.setText(((Lista_entrada) entrada).get_textoDebajo()); 
//
//							ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen); 
//							if (imagen_entrada != null)
//								imagen_entrada.setImageResource(((Lista_entrada) entrada).get_idImagen());
//						}
//
//					}
//				});
//
//				listView.setOnItemClickListener(new OnItemClickListener() { 
//					@Override
//					public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
//						Lista_entrada elegido = (Lista_entrada) pariente.getItemAtPosition(posicion); 
//
//						CharSequence texto = "Seleccionado: " + elegido.get_textoDebajo();
//						Toast toast = Toast.makeText(NotificacionesActivity.this, texto, Toast.LENGTH_LONG);
//						toast.show();
//					}
//
//
//				});
//
//			}
//
//			@Override
//			public void onFailure(Throwable arg0) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//
//	}	
	public void cargarDatos(Client k){
		String idUsuario = k.user().getUsername() + "";
		Query query = k.query ();
		query.equals("idUsuario", idUsuario);
		
		AsyncAppData<NotificacionesBackend> searchedEvents = k.appData("NotificacionesUsuario", NotificacionesBackend.class);
		searchedEvents.get(query, new KinveyListCallback<NotificacionesBackend>() {
			@Override
			public void onSuccess(NotificacionesBackend[] resultadoconsulta) { 
				listView = (ListView) findViewById(R.id.listViewNotificaciones);
				final ArrayList<Lista_entrada> datos = new ArrayList<Lista_entrada>();  
				for (int i = 0; i < resultadoconsulta.length; i++) {
					//datos.add(new Lista_entrada(R.drawable.user_icon,resultadoconsulta[i].getIdUsuario(),resultadoconsulta[i].getTipo()));
					datos.add(new Lista_entrada(R.drawable.user_icon,resultadoconsulta[i].getTipo(),resultadoconsulta[i].getTexto()));
				}
				listView.setAdapter(new Lista_adaptador(NotificacionesActivity.this, R.layout.activity_entradalv,datos) {

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

				listView.setOnItemClickListener(new OnItemClickListener() { 
					@Override
					public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
						Lista_entrada elegido = (Lista_entrada) pariente.getItemAtPosition(posicion); 

						CharSequence texto = elegido.get_textoDebajo();
						Toast toast = Toast.makeText(NotificacionesActivity.this, texto, Toast.LENGTH_LONG);
						toast.show();
					}


				});

				//Deslizar item para borrarlo
				SwipeListViewTouchListener touchListener =new SwipeListViewTouchListener(listView,new SwipeListViewTouchListener.OnSwipeCallback() {
					@Override
					public void onSwipeLeft(ListView listView, int [] reverseSortedPositions) {
						//Aqui ponemos lo que hara el programa cuando deslizamos un item ha la izquierda
						datos.remove(reverseSortedPositions[0]);
						adaptador.notifyDataSetChanged();
					}

					@Override
					public void onSwipeRight(ListView listView, int [] reverseSortedPositions) {
						//Aqui ponemos lo que hara el programa cuando deslizamos un item ha la derecha
						//arrayList.remove(reverseSortedPositions[0]);
						
						datos.remove(reverseSortedPositions[0]);
						adaptador.notifyDataSetChanged();
						eliminarBackend(datos);
					}
				},true, false);
				
				//Escuchadores del listView
				listView.setOnTouchListener(touchListener);
				listView.setOnScrollListener(touchListener.makeScrollListener());	
				
				mostrarMensaje(listView);
				
			
			}
			
			
			
			

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}
		});

	}	
	
	
	public void mostrarMensaje(ListView l){
		
		if (l.getCount()<=0){
			Toast tIO = Toast.makeText(NotificacionesActivity.this,"Usted no cuenta con notificaciones para consultar. Gracias", Toast.LENGTH_SHORT);
			tIO.show();
		}
	}
	
	public void eliminarBackend(ArrayList <Lista_entrada> a){
		
		for (int i = 0; i<a.size(); i++){
			String id = a.get(i).get_textoDebajo();
			Log.e("Dato seleccionado", id);		
		}
	}
	
	
}










	
//	public void prueba(Client k){
//		String idUsuario = k.user().getUsername() + "";
//		Query query = k.query ();
//		query.equals("idUsuario", idUsuario);
//		
//		AsyncAppData<NotificacionesBackend> searchedEvents = k.appData("NotificacionesUsuario", NotificacionesBackend.class);
//		searchedEvents.get(query, new KinveyListCallback<NotificacionesBackend>() {
//			@Override
//			public void onSuccess(NotificacionesBackend[] resultadoconsulta) { 
//				//ArrayList<NotificacionesBackend>lista = new ArrayList<NotificacionesBackend>();
////				listView = (ListView) findViewById(R.id.listViewNotificaciones);
////				ArrayList<Lista_entrada> datos = new ArrayList<Lista_entrada>();  
////				for (int i = 0; i < resultadoconsulta.length; i++) {
////					//datos.add(new Lista_entrada(R.drawable.user_icon,resultadoconsulta[i].getUsuario(),resultadoconsulta[i].getDescripcionComunidad()));
////					datos.add(new NotificacionesBackend(R.drawable.user_icon,resultadoconsulta[i].getIdUsuario(),
////							resultadoconsulta[i].getTipo(),resultadoconsulta[i].getTitulo(),
////							resultadoconsulta[i].getTexto()));
//				ArrayList<NotificacionesBackend>lista = new ArrayList<NotificacionesBackend>();
//				lista.add(new NotificacionesBackend(R.drawable.user_icon,resultadoconsulta[i].getIdUsuario(),
//						resultadoconsulta[i].getTipo(),resultadoconsulta[i].getTitulo(),
//						resultadoconsulta[i].getTexto()));
//				}
//			list
//				li.setAdapter(new Lista_adaptador(ComunidadActivity.this, R.layout.activity_entradalv,datos) {
//
//					@Override
//					public void onEntrada(Object entrada, View view) {
//						if (entrada != null) {
//							TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior); 
//							if (texto_superior_entrada != null) 
//								texto_superior_entrada.setText(((Lista_entrada) entrada).get_textoEncima()); 
//
//							TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior); 
//							if (texto_inferior_entrada != null)
//								texto_inferior_entrada.setText(((Lista_entrada) entrada).get_textoDebajo()); 
//
//							ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen); 
//							if (imagen_entrada != null)
//								imagen_entrada.setImageResource(((Lista_entrada) entrada).get_idImagen());
//						}
//
//					}
//				});
//
//				lista.setOnItemClickListener(new OnItemClickListener() { 
//					@Override
//					public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
//						Lista_entrada elegido = (Lista_entrada) pariente.getItemAtPosition(posicion); 
//
//						CharSequence texto = "Seleccionado: " + elegido.get_textoDebajo();
//						Toast toast = Toast.makeText(ComunidadActivity.this, texto, Toast.LENGTH_LONG);
//						toast.show();
//					}
//
//
//				});
//
//
//	}

	