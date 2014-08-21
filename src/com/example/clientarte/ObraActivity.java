package com.example.clientarte;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import android.app.ActionBar.LayoutParams;
import android.app.ProgressDialog;
import android.content.Intent;

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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import backend.DatabaseHelper;
import dominio.Funcion;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import backend.DatabaseHelper;
import backend.ObraBackend;
import backend.ObrasImagen;
import backend.SalaBackend;
import dominio.Obra;




public class ObraActivity extends ActionBarActivity {


	//	private int requestCode = 1;
	//	private ListView lvObras;
	//	private DB_Obra dataSource;// = new DB_Obra(this);
	private Obra obra;
	private Button btnComprar;
	public static final String TAG = "ArteBackend";
	private String appKey="kid_VT8_It3ePE";
	private String appSecret="1b0fa51481984d2da5910f78a9d26ccc";
	private String mensaje;
	private Client kinveyClient;
	private static final int REQUEST_TEXT = 3;
	private ImageButton btnAgregarComentarioObra;
	private ImageButton btnVerVideo;
	private ImageButton btnCompartirFacebook;
	
	//	private int requestCode = 1;
	//	private ListView lvObras;
	//	private DB_Obra dataSource;// = new DB_Obra(this);


	//final String[] from = { ObrasColumns.idObra, ObrasColumns.nombreObra, ObrasColumns.descripcionObra };
	//final int[] to = new int[] { R.id., R.id.apellidos, R.id.edad };

	/*DBAdapter dbAdapter;
	Boolean mBound;*/
	// Database Helper
	DatabaseHelper db;
	Spinner listFunciones;
	Spinner listHorarios;
	itemAdapter IA;
	//android:onClick="referenciarIngresarComentario"
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_obra);

		//		DatabaseHelper myDbHelper = new DatabaseHelper(this);
		//		myDbHelper = new DatabaseHelper(this);
		obra= new Obra();
		obra= getIntent().getParcelableExtra("obra");
		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		kinveyClient = obj.captarUsuarioLogueado();
		btnComprar= (Button)findViewById(R.id.bttnComprar);
		btnAgregarComentarioObra = (ImageButton)findViewById(R.id.imageButton2);
		btnVerVideo = (ImageButton)findViewById(R.id.imageButton3);
		btnCompartirFacebook = (ImageButton)findViewById(R.id.imageButton1);
		//obtenerImagenObra(kinveyClient);
		crearActivity();
		addListenerOnButton();
	}		
	
	public void referenciarIngresarComentario (View view){
		Intent intent = new Intent(ObraActivity.this, ComunidadActivity.class); 
		ObraActivity.this.startActivityForResult(intent, REQUEST_TEXT);
	}

	//Funcion donde se crea el activity en funcion a la obra que se obtenga
	public void crearActivity(){
		//Cargo galeria de imagenes
		Gallery galleryObra= (Gallery)findViewById(R.id.galleryObra);
		galleryObra.setContentDescription(obra.getNombre());
		GalleryImageAdapter IA= new GalleryImageAdapter(ObraActivity.this);
		//		int[] imagenList= new int[obra.getListaImagenes().length];
		IA.setmImageIds(obra.getListaImagenes());
		galleryObra.setAdapter(IA);
		//Cargo titulo
		TextView titulo=(TextView)findViewById(R.id.titulo);
		titulo.setText(obra.getNombre());
		//Cargo descripci�n
		TextView textDescripcion= (TextView)findViewById(R.id.descObra);
		textDescripcion.setText(obra.getDescripcion());
		//Cargo funciones
		listFunciones= (Spinner) findViewById(R.id.listfechas);		
		ArrayList<Funcion>lista= obra.getListaFunciones();	
		//Creamos el adaptador
		ArrayAdapter<Funcion> spinner_adapter = new ArrayAdapter<Funcion>(this,android.R.layout.simple_spinner_item, lista);
		listFunciones.setAdapter(spinner_adapter);

	}

	public void addListenerOnButton(){
		btnComprar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ObraActivity.this, CompraActivity.class);
				intent.putExtra("obra",obra); 
				startActivity(intent);
			}

		});
		
		btnAgregarComentarioObra.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ObraActivity.this, ComunidadActivity.class);
				intent.putExtra("obra",obra); 
				startActivity(intent);
			}

		});
		
		btnVerVideo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ObraActivity.this, VideoActivity.class);
				intent.putExtra("obra",obra); 
				startActivity(intent);
			}

		});
	
	
	btnCompartirFacebook.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) {
			 
			String titulo = obra.getNombre().toString();
			String urlYT = "https://www.youtube.com/results?search_query=" +titulo.trim();
			String cuerpoMensaje = "Me ha interesado: " + " "+  titulo +  " " + "ingresa a Art-e y opina!!" + " " + urlYT;
			Compartir(titulo, cuerpoMensaje);
		}

	});
}
	
	
	public void Compartir(String titulo,String cuerpoMensaje ){
		   Intent intentCompartir = new Intent(Intent.ACTION_SEND);
		   intentCompartir.setType("text/plain");
		   intentCompartir.putExtra(Intent.EXTRA_SUBJECT, titulo);
		   intentCompartir.putExtra(Intent.EXTRA_TEXT, cuerpoMensaje);
		   intentCompartir.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		   //startActivity(Intent.createChooser(intentCompartir ,  titulo));
		   this.startActivity(Intent.createChooser(intentCompartir,  titulo ));
	}




//		listFunciones.setAdapter(new itemAdapter(this, R.layout.activity_obra, obra.getListaFunciones()) {
//		IA= new itemAdapter(this, R.layout.activity_obra, obra.getListaFunciones()) {
//
//			@Override
//			public void onEntrada(Object entrada, View view) {
//				if (entrada != null) {
//					TextView texto_superior_entrada = (TextView) view.findViewById(R.id.labelHorario); 
//					if (texto_superior_entrada != null) 
//						texto_superior_entrada.setText("Hola"); 
//				}
//
//			}
//		};



//			@Override
//			public void onEntrada(Object entrada, View view) {
//				// TODO Auto-generated method stub
//				
//			 }
//		}
//	});
//		
//		
//		
//		
//
//			@Override
//			public void onEntrada(Object entrada, View view) {
//		        if (entrada != null) {
//		            TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior); 
//		            if (texto_superior_entrada != null) 
//		            	texto_superior_entrada.setText(((Lista_entrada) entrada).get_textoEncima()); 
//
//		            TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior); 
//		            if (texto_inferior_entrada != null)
//		            	texto_inferior_entrada.setText(((Lista_entrada) entrada).get_textoDebajo()); 
//
//		            ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen); 
//		            if (imagen_entrada != null)
//		            	imagen_entrada.setImageResource(((Lista_entrada) entrada).get_idImagen());
//		        }
//			}
//		});
//			

//}
//listFunciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//	@Override
//	public void onItemClick(AdapterView adapter, View view, int position, long arg) {
//
//		// Sets the visibility of the indeterminate progress bar in the
//		// title
//		setProgressBarIndeterminateVisibility(true);
//
//		// Show progress dialog
//		       Di progressDialog = ProgressDialog.show(ObraActivity.this, "ProgressDialog", "Loading!");
//		 
//		        // Tells JavaScript to open windows automatically.
//		        webView.getSettings().setJavaScriptEnabled(true);
//		 
//		        // Sets our custom WebViewClient.
//		        webView.setWebViewClient(new myWebClient());
//		 
//		        // Loads the given URL
//		        Item item = (Item) listView.getAdapter().getItem(position);
//		            webView.loadUrl(item.getUrl());
//	}
//});
//}


//		DatabaseHelper myDbHelper = new DatabaseHelper(this);
//		myDbHelper = new DatabaseHelper(this);


//		try {
//			myDbHelper.createDataBase();
//			
//		} catch (IOException ioe) {
//
//			throw new Error("Unable to create database");
//
//		}
//		
//		Obra miObra = new Obra ("Redemption", "Jason Statham");
//		Obra miObra2 = new Obra ("La Madrastra", "Victoria Ruffo");
//		Obra miObra3 = new Obra ("The Wolf of Wall Street", "Leonardo DiCaprio");
//		Obra miObra4 = new Obra ("X-Men", "Hugh Jackman");
//		myDbHelper.createObra(miObra);
//		myDbHelper.createObra(miObra2);
//		myDbHelper.createObra(miObra3);
//		myDbHelper.createObra(miObra4);


		

public void conectarBackend (){
	kinveyClient = new Client.Builder(appKey, appSecret, this).build();
	kinveyClient.ping(new KinveyPingCallback() {
		public void onFailure(Throwable t) {
			Log.e("Probando Kinvey Connection", "Kinvey Ping Failed", t);
		}
		public void onSuccess(Boolean b) {
			Log.d("Probando Kinvey Connection", "Kinvey Ping Success");
		}
	});
	//mKinveyClient.user().login("nlema", "nlema", new KinveyUserCallback() {
	if (!kinveyClient.user().isUserLoggedIn()) {
		kinveyClient.user().login(new KinveyUserCallback() {
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
		mensaje = "Utilizando usuario impl�cito cacheado: " + kinveyClient.user().getId() + ".";
		Log.d("Realizando Kinvey Login", mensaje);
	}

}

//Recuperar una obra
public void recuperarObra (View view) {
	//appData es la interface para guardar y recuperar entidades 
	kinveyClient.appData("Obra", ObraBackend.class).getEntity("01", new KinveyClientCallback<ObraBackend>() {
		@Override
		public void onSuccess(ObraBackend result) {
			mensaje = "Obra id: " + result.getIdObra() + ", Nombre: " + result.getNombreObras()+ ", Descripcion: " + result.getDescripcipnObras();
			Log.d(TAG + "- recuperarObra", mensaje);
		}
		@Override
		public void onFailure(Throwable error) {
			Log.e(TAG + "- recuperarObra", "Falla en AppData.getEntity", error);
		}

	});
}

//Recuperar todas las obras
public void recuperarObras(View view) {
	Query myQuery = kinveyClient.query();
	kinveyClient.appData("Obra", ObraBackend.class).get(myQuery, new KinveyListCallback<ObraBackend>() {
		@Override
		public void onSuccess(ObraBackend[] resultadoconsulta) {
			//for (Sala sala : result) {
			for (int i = 0; i < resultadoconsulta.length; i++) {
				mensaje = "Obra id: " + resultadoconsulta[i].getIdObra() + ", Nombre: " + resultadoconsulta[i].getNombreObras() + ", Descripcion: " + resultadoconsulta[i].getDescripcipnObras();
				Log.d(TAG + "- recuperarObras", mensaje);
			}
		}
		@Override
		public void onFailure(Throwable error) {
			Log.e(TAG, "AppData.get by Query Failure", error);
		}
	});
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
	getMenuInflater().inflate(R.menu.main_backend, menu);
	return true;
}
	
	
//		try {
//	        FileOutputStream fStream = getApplicationContext().openFileOutput("image.png", Context.MODE_PRIVATE);
//	        ByteArrayOutputStream bos = result.getFile("image").getOutput();
//	        bos.writeTo(fStream);
//	        bos.flush();
//	        fStream.flush();
//	        bos.close();
//	        fStream.close();
//	    } catch (Exception ex) {}
	

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
		View rootView = inflater.inflate(R.layout.fragment_main_backend, container,
				false);
		return rootView;
	}
}

}	
//		db = new DatabaseHelper(getApplicationContext());
//		Obra tag1 = new Obra("Shopping");
//        Obra tag2 = new Obra("Important");
//long tag1_id = db.createObra(tag1);
//long tag2_id = db.createObra(tag2);



//		MySQLiteOpenHelper db = new MySQLiteOpenHelper(this);
//		System.out.println(db.TABLA_OBRAS.length());
//		System.out.println(db.TABLA_OBRAS);
// Instanciamos NotasDataSource para
// poder realizar acciones con la base de datos
//dataSource = new DB_Obra(this);
/*	dataSource.open();
		//dataSource.close();

		// Instanciamos los elementos
		lvObras = (ListView) findViewById(R.id.listViewexp);

		// Cargamos la lista de notas disponibles
		List<Obra> listaObras = dataSource.getAllObras();
		ArrayAdapter<Obra> adapter = new ArrayAdapter<Obra>(this, android.R.layout.simple_list_item_1, listaObras);

		// Establecemos el adapter
		lvObras.setAdapter(adapter);

		// Establecemos un Listener para el evento de pulsaci�n
		//lvObras.setOnItemClickListener(this);
		lvObras.setOnItemClickListener((OnItemClickListener) this);
 */


/*public void agregarObra(View v) {
		Intent i = new Intent(this, NuevaNotaActivity.class);
		startActivityForResult(i, requestCode);
	}*/

//	public void onItemClick(final AdapterView<?> adapterView, View view, final int position, long id) {
//		// TODO Auto-generated method stub
//		AlertDialog.Builder builder = new AlertDialog.Builder(this)
//		.setTitle("Borrar obra")
//		.setMessage("�Desea borrar esta obra?")
//		.setPositiveButton("Aceptar",
//				new DialogInterface.OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface arg0, int arg1) {
//				// TODO Auto-generated method stub
//				Obra obra = (Obra) adapterView
//						.getItemAtPosition(position);
//				dataSource.borrarObra(obra);
//
//				// Refrescamos la lista
//				refrescarLista();
//			}
//		})
//
//		.setNegativeButton("Cancelar",
//				new DialogInterface.OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog,
//					int which) {
//				// TODO Auto-generated method stub
//				return;
//			}
//		});
//		builder.show();
//	}

//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		// TODO Auto-generated method stub
//		Log.d("Result", "Se ejecuta onActivityResult");
//		super.onActivityResult(requestCode, resultCode, data);
//		if (requestCode == this.requestCode && resultCode == RESULT_OK) {
//			// Actualizar el Adapter
//			dataSource.open();
//			refrescarLista();
//		}
//	}

//	private void refrescarLista() {
//		List<Obra> listaObras = dataSource.getAllObras();
//		ArrayAdapter<Obra> adapter = new ArrayAdapter<Obra>(this,
//				android.R.layout.simple_list_item_1, listaObras);
//		lvObras.setAdapter(adapter);



/*protected void onPause() {
		// TODO Auto-generated method stub
		dataSource.close();
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		dataSource.open();
		super.onResume();
	}*/
//}









//////RESPALDO DE LA CLASE OBRA
/*public class ObraActivity extends ActionBarActivity {

	private Fachada fachada = Fachada.getInstance();
	private ArrayList listaObras = fachada.retornarObras();
	//private ArrayList listaFunciones = fachada.retornarFuncionesObra("prueba");

	SparseArray<GrupoDeItems> grupos = new SparseArray<GrupoDeItems>();
	ListView listView ;
	ImageView selectedImage;  
	private Obra obra;
    private Integer[] mImageIds = {
               R.drawable.logo_app,
               R.drawable.novedades,
               R.drawable.nosotros,

       };

    @Override
	protected void onCreate(Bundle savedInstanceState) {

    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_obra);
        obra= getIntent().getParcelableExtra("obra");
        Log.e("idObra","-"+obra.getIdObra());
        Log.e("nombreObra","-"+obra.getNombre());
        Log.e("descripcionObra","-"+obra.getDescripcion());
        for(int x=0; x<obra.getListaImagenes().length;x++){
        	Log.e("idObra","-"+obra.getListaImagenes()[x]);
        }

        Gallery gallery = (Gallery) findViewById(R.id.gallery);
        selectedImage=(ImageView)findViewById(R.id.iconCompra);
        gallery.setSpacing(1);
        gallery.setAdapter(new GalleryImageAdapter(this));
        TextView titulo=(TextView)findViewById(R.id.titulo);
        titulo.setText(obra.getNombre());
         // clicklistener for Gallery
        gallery.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(ObraActivity.this, "Your selected position = " + position, Toast.LENGTH_SHORT).show();
                // show the selected Image
                selectedImage.setImageResource(mImageIds[position]);
                addListenerOnButton();
            }
        });

        crearDatos();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listViewexp);
        Adaptador adapter = new Adaptador(this, grupos);
        listView.setAdapter(adapter);




    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
     /*
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
      case R.id.action_settings:
              Intent intent = new Intent(this, Settings.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
              startActivity(intent);
              break;
      case R.id.action_settings:
        Toast.makeText(this, "Reloading parkings...", Toast.LENGTH_LONG)
            .show();
        refresh();
        break;*/

/*default:
        break;*/
// }
//return false;
//}

/*public void crearDatos() {
        GrupoDeItems grupo0 = new GrupoDeItems("Lunes 16/06");
        grupo0.children.add("20:00 - Sala 1");
        grupo0.children.add("22:00 - Sala 1");
        grupos.append(0, grupo0);
        GrupoDeItems grupo1 = new GrupoDeItems("Lunes 23/06");
        grupo1.children.add("22:00 - Sala 2");
        grupo1.children.add("00:00 - Sala 2");
        grupo1.children.add("01:30 - Sala 3");
        grupos.append(1, grupo1);
        GrupoDeItems grupo2 = new GrupoDeItems("Lunes 30/06");
        grupo2.children.add("19:00 - Sala 5");
        grupo2.children.add("23:00 - Sala 5");
        //grupo2.children.add("20:00 - Sala 1");
        grupos.append(2, grupo2);
     }

    public void addListenerOnButton() {

    	selectedImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ObraActivity.this, CompraActivity.class);
				startActivity(intent);
			}

		});
}
<<<<<<< HEAD

}
=======

}*/

