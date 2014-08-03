package com.example.clientarte;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import backend.ComunidadBackend;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.Query;
import com.kinvey.java.User;
import com.kinvey.java.core.DownloaderProgressListener;
import com.kinvey.java.core.KinveyClientCallback;
import com.kinvey.java.core.MediaHttpDownloader;
import com.kinvey.java.core.MediaHttpUploader;
import com.kinvey.java.model.FileMetaData;

public class ComunidadActivity extends ActionBarActivity {
	
	public static final String TAG = "ArteBackend";
	private String appKey="kid_VT8_It3ePE";
	private String appSecret="1b0fa51481984d2da5910f78a9d26ccc";
	private String mensaje;
	private Client mKinveyClient;
	private TextView tvBd1;
	private ViewGroup layout;
	private ScrollView scroll;
	private ListView lista;
    private static final int DLG_EXAMPLE1 = 0;
    private static final int TEXT_ID = 0;
    private static final int REQUEST_TEXT = 4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_listado);
		setContentView(R.layout.activity_listado);
		//layout= (ViewGroup)findViewById(R.id.containerComunidad);
		//scroll = (ScrollView)findViewById(R.id.ScrollViewComunidad);


		//		if (savedInstanceState == null) {
		//			getSupportFragmentManager().beginTransaction()
		//			.add(R.id.container, new PlaceholderFragment()).commit();
		//		}

		//Conexión de la APP a Kinvey
//		mKinveyClient = new Client.Builder(this.getApplicationContext()).build();
//		mKinveyClient.ping(new KinveyPingCallback() {
//			public void onFailure(Throwable t) {
//				Log.e("Probando Kinvey Connection", "Kinvey Ping Failed", t);
//			}
//			public void onSuccess(Boolean b) {
//				Log.d("Probando Kinvey Connection", "Kinvey Ping Success");
//			}
//		});
		//mKinveyClient = new Client.Builder(this.getApplicationContext()).build();
				conectarBackend();
				cargarDatos();
<<<<<<< HEAD
				
=======
//				agregarComentarios();
>>>>>>> develop
//		mostrarImagen();


	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_comunidad,
					container, false);
			return rootView;
		}
	}
	
	
	/*
	 * PARTE DEL BACKEND
	 *
	 * 
	 * 
	 * 
	 * */
	public void conectarBackend (){
		mKinveyClient = new Client.Builder(appKey, appSecret, this).build();
		mKinveyClient.ping(new KinveyPingCallback() {
			public void onFailure(Throwable t) {
				Log.e("Probando Kinvey Connection", "Kinvey Ping Failed", t);
			}
			public void onSuccess(Boolean b) {
				Log.d("Probando Kinvey Connection", "Kinvey Ping Success");
			}
		});
		//mKinveyClient.user().login("nlema", "nlema", new KinveyUserCallback() {
		if (!mKinveyClient.user().isUserLoggedIn()) {
			mKinveyClient.user().login(new KinveyUserCallback() {
				public void onFailure(Throwable error) {
					mensaje = "Error al realizar el login.";
					Log.e("Realizando Kinvey Login", mensaje, error);
				}
				@Override
				public void onSuccess(User u) {
					mensaje = "Bienvenido usuario: " + u.getId() + ".";
					Log.d("Realizando Kinvey Login", mensaje);
				}
			});
		} else {
			mensaje = "Utilizando usuario implícito cacheado: " + mKinveyClient.user().getId() + ".";
			Log.d("Realizando Kinvey Login", mensaje);
		}

	}	
	

//	  //Recuperar una COMUNIDAD
//  	public void recuperarComunidad (View view) {
	//obrapepe
	//Query myQuery = kinveyClient.query();
//          //appData es la interface para guardar y recuperar entidades 
//  		kinveyClient.appData("Comunidad", ComunidadBackend.class).getEntity("1", new KinveyClientCallback<ComunidadBackend>() {
//              public void onSuccess(ComunidadBackend result) {
//                  mensaje = "Comunidad id: " + result.getIdComunidad() + ", Descripcion: " + result.getDescripcionComunidad();
//                  Log.d(TAG + "- recuperarComunidad", mensaje);
//              }
//              @Override
//              public void onFailure(Throwable error) {
//                  Log.e(TAG + "- recuperarComunidad", "Falla en AppData.getEntity", error);
//              }
//			
//          });
//      }
  	
//  	//Recuperar todas las comunidades
//  	public void recuperarComunidades(View view) {
//  		Query myQuery = kinveyClient.query();
//  		kinveyClient.appData("Comunidad", ComunidadBackend.class).get(myQuery, new KinveyListCallback<ComunidadBackend>() {
//  			@Override
//  			public void onSuccess(ComunidadBackend[] resultadoconsulta) {
//  				//for (Sala sala : result) {
//  				for (int i = 0; i < resultadoconsulta.length; i++) {
//  					mensaje = "Comunidad id: " + resultadoconsulta[i].getIdComunidad() + ", Descripcion: " + resultadoconsulta[i].getDescripcionComunidad();
//  					Log.d(TAG + "- recuperarComunidades", mensaje);
//  				}
//  			}
//  			@Override
//  			public void onFailure(Throwable error) {
//  				Log.e(TAG, "AppData.get by Query Failure", error);
//  			}
//  		});
//  	}
    
	
//	public void a(){
//		Query myQuery = kinveyClient.query();
//		kinveyClient.appData("Comunidad", ComunidadBackend.class).get(myQuery, new KinveyListCallback<ComunidadBackend>() {
//	    
//			@Override
//			public void onSuccess(ComunidadBackend[] resultadoconsulta) {
//				lista = (ListView) findViewById(R.id.ListView_listado);
//				lista.setAdapter(new Lista_adaptador(ComunidadActivity.this, R.layout.activity_entradalv, resultadoconsulta){
//
//					public void onEntrada(Lista_entrada entrada, View view) {
//						if (entrada != null) {
//							TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior); 
//							if (texto_superior_entrada != null) 
//								texto_superior_entrada.setText(((Lista_entrada) entrada).get_textoEncima()); 
//
////							TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior); 
////							if (texto_inferior_entrada != null)
////								texto_inferior_entrada.setText(((Lista_entrada) entrada).get_textoDebajo()); 
//
////							ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen); 
////							if (imagen_entrada != null)
////								imagen_entrada.setImageResource(((Lista_entrada) entrada).get_idImagen());
//						}
//					}
//
//					@Override
//					public void onEntrada(Object entrada, View view) {
//						// TODO Auto-generated method stub
//						
//					}
//
//
//
//				}); 
//			}
//				@Override
//				public void onFailure(Throwable error) {
//					Log.e(TAG, "AppData.get by Query Failure", error);
//				}
//			});
//
//		}
//	
	public void cargarDatos(){
		Query myQuery = mKinveyClient.query();
		mKinveyClient.appData("Comunidad", ComunidadBackend.class).get(myQuery, new KinveyListCallback<ComunidadBackend>() {
			@Override
			public void onSuccess(ComunidadBackend[] resultadoconsulta) {
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
				//for (Sala sala : result) {
				// int[] to = new int[] { R.id.TextView01 };
				//				for (int i = 0; i < resultadoconsulta.length; i++) {
				//					mensaje = "Comunidad id: " + resultadoconsulta[i].getIdComunidad() + ", Descripcion: " + resultadoconsulta[i].getDescripcionComunidad();
				//					tvBd1 = new TextView(ComunidadActivity.this);
				//					tvBd1.setId(i);
				//					tvBd1.setText(mensaje);
				//					tvBd1.setPadding(10, 10, 10, 10);
				//					tvBd1.setWidth(470);
				//					tvBd1.setHeight(140);
				//					//scroll.addView(layout);
				//					layout.addView(tvBd1);
				//				}
			}

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}
		});



	}
	
	
	
	public void ingresarComentario(View view) {
		mKinveyClient = new Client.Builder(this.getApplicationContext()).build();
		if (!mKinveyClient.user().isUserLoggedIn()) {
			mostrarDialog();
//			PerfilActivity.this.startActivity(new Intent(PerfilActivity.this, 
//                    LoginActivity.class));
//			PerfilActivity.this.finish();
		}else{
//			if (mKinveyClient.user().isUserLoggedIn()) {
//			mensajeConfirmacionDesloguear();
			//desloguearUsuario();
			Toast t=Toast.makeText(this,"Presiono Cancelar", Toast.LENGTH_SHORT);
			t.show();
		}
		

	}
	
	
//	public void mostrarDialog(){
//		showDialog(DLG_EXAMPLE1);
//	}
	
	public void mostrarDialog(){
		
			mensajeConfirmacion();
//			PerfilActivity.this.startActivity(new Intent(PerfilActivity.this, 
//                    LoginActivity.class));
//			PerfilActivity.this.finish();
		
//			if (mKinveyClient.user().isUserLoggedIn()) {
//			mensajeConfirmacionDesloguear();
			//desloguearUsuario();
		
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
                return;
            }
        });
 
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
 
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        
        return builder.create();
    }
	
	public void guardarDatosComentarios(String valor){
		ComunidadBackend event = new ComunidadBackend();
		event.setDescripcionComunidad(valor);
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
	
	
			
	}

	


//	private void mostrarImagen() throws FileNotFoundException {
//		FileOutputStream fos= new FileOutputStream("mug.png");
//		Query q = kinveyClient.query();
//		q.equals("FileName", 3600);
//		kinveyClient.file().download("mug.png", fos, new DownloaderProgressListener() {
//			@Override
//			public void onSuccess(Void result) {
//				Log.i(TAG, "successfully downloaded file");
//			}
//			@Override
//			public void onFailure(Throwable error) {
//				Log.e(TAG, "failed to downloaded file.", error);
//			}
//			@Override
//			public void progressChanged(MediaHttpDownloader downloader) throws IOException {
//				Log.i(TAG, "progress updated: "+downloader.getDownloadState());
//				// any updates to UI widgets must be done on the UI thread
//			}
//		});
//	}
		

		
    	
    	
    	


