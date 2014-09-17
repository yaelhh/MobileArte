package com.example.clientarte;

import java.util.ArrayList;















import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import backend.ComunidadBackend;
import backend.DatabaseHelper;
import backend.ObraBackend;
import backend.ObrasFavoritosBackend;
import backend.UsuarioBackend;

import com.google.android.gms.internal.bt;
import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyDeleteCallback;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.Query;
import com.kinvey.java.User;
import com.kinvey.java.core.KinveyClientCallback;
import com.kinvey.java.model.KinveyDeleteResponse;

import dominio.Funcion;
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
	private ImageButton btnCompraLibro;
	private int mascaras=0;

	//	private int requestCode = 1;
	//	private ListView lvObras;
	//	private DB_Obra dataSource;// = new DB_Obra(this);


	//final String[] from = { ObrasColumns.idObra, ObrasColumns.nombreObra, ObrasColumns.descripcionObra };
	//final int[] to = new int[] { R.id., R.id.apellidos, R.id.edad };

	/*DBAdapter dbAdapter;
	Boolean mBound;*/
	// Database Helper

	private ImageButton btnFavoritos;
	private ImageButton btnFavoritosSi;
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
		mascaras=getIntent().getExtras().getInt("mascaras");
		setTitle(obra.getNombre());
		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		kinveyClient = obj.captarUsuarioLogueado();
		btnComprar= (Button)findViewById(R.id.bttnComprar);
		btnComprar.getBackground().setColorFilter(new LightingColorFilter(330000 , 0xFFFFFF));
		btnCompraLibro=(ImageButton)findViewById(R.id.ImageBtnLibro);
		btnAgregarComentarioObra = (ImageButton)findViewById(R.id.imageButton2);
		btnVerVideo = (ImageButton)findViewById(R.id.imageButton3);
		btnCompartirFacebook = (ImageButton)findViewById(R.id.imageButton1);
		btnFavoritos = (ImageButton)findViewById(R.id.favorito);
		btnFavoritosSi = (ImageButton)findViewById(R.id.favoritoSi);
		//obtenerImagenObra(kinveyClient);
		validarFavorito(obra.getNombre());
		crearActivity();
		addListenerOnButton(obj);
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
		//Cargo descripción
		TextView textDescripcion= (TextView)findViewById(R.id.descObra);
		textDescripcion.setText(obra.getDescripcion());
		//Cargo funciones
		listFunciones= (Spinner) findViewById(R.id.listfechas);		
		ArrayList<Funcion>lista= obra.getListaFunciones();	
		//Creamos el adaptador
		ArrayAdapter<Funcion> spinner_adapter = new ArrayAdapter<Funcion>(this,android.R.layout.simple_spinner_item, lista);
		listFunciones.setAdapter(spinner_adapter);

	}

	public void addListenerOnButton(final ObjetosBackend obj){
		btnComprar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!obj.captarUsuarioLogueado().user().getUsername().equalsIgnoreCase("adm")){
					Intent intent = new Intent(ObraActivity.this, CompraActivity.class);
					intent.putExtra("obra",obra); 
					startActivity(intent);
				}else{
					Toast.makeText(ObraActivity.this,"Para realizar la compra debe estar logueado. Gracias!" , Toast.LENGTH_SHORT).show();
				}

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
				String videoId = obra.getNombre().toString();
				startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/results?search_query="+videoId.trim())));
				Log.i("Video", "Video Playing...."); 
			}
		});


		btnCompartirFacebook.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String titulo = obra.getNombre().toString().trim();
				String urlYT = "https://www.youtube.com/results?search_query=" +titulo;
				String cuerpoMensaje = "Me ha interesado: " + " "+  titulo +  " " + "ingresa a Art-e y opina!!" + " " + urlYT;
				Compartir(titulo, cuerpoMensaje);
			}

		});

		btnFavoritos.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try{
				if (!obj.captarUsuarioLogueado().user().getUsername().toString().equalsIgnoreCase("adm")){
					save(obra.getNombre());
					btnFavoritos.setVisibility(View.GONE);
					btnFavoritosSi.setVisibility(View.VISIBLE);
				}else{
					Toast.makeText(ObraActivity.this, "Debe estar logueado para marcar la obra como favorita. " , Toast.LENGTH_LONG).show();
				}
				}catch (Exception e){
					Log.e(TAG, "Entro  catch de boton favoritos" +obj.captarUsuarioLogueado().user().getUsername().toString());
					Toast.makeText(ObraActivity.this, "Debe estar logueado para marcar la obra como favorita. " , Toast.LENGTH_LONG).show();
				}
			}

		});

		btnFavoritosSi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try{
				if (!obj.captarUsuarioLogueado().user().getUsername().equalsIgnoreCase("adm")){
					borrarFavorito(obra.getNombre());
					btnFavoritos.setVisibility(View.VISIBLE);
					btnFavoritosSi.setVisibility(View.GONE);
				}else{
					Toast.makeText(ObraActivity.this, "Debe estar logueado para marcar la obra como favorita. " , Toast.LENGTH_LONG).show();
				}
				}catch (Exception e){
					Log.e(TAG, "Entro  catch de boton favoritos" +obj.captarUsuarioLogueado().user().getUsername().toString());
					Toast.makeText(ObraActivity.this, "Debe estar logueado para marcar la obra como favorita. " , Toast.LENGTH_LONG).show();
				}
			}

		});
		btnCompraLibro.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 Intent i = new Intent(Intent.ACTION_VIEW);
				 i.setData(Uri.parse("http://www.isadoralibros.com.uy/sitio/libros/buscar/q/"+obra.getNombre()));
				 startActivity(i);
			}

		});
	}

	public void validarFavorito(String nomO){
		try{
		String nomU = kinveyClient.user().getUsername().toString();
		Query query1 = kinveyClient.query ();
		Query query2 = kinveyClient.query ();
		query1.equals("nombreObra", String.valueOf(nomO));
		query1.equals("nombreUsuario", String.valueOf(nomU));
		
		if(!nomU.equalsIgnoreCase("adm")){
			AsyncAppData<ObrasFavoritosBackend> searchedEvents = kinveyClient.appData("ObrasFavoritos", ObrasFavoritosBackend.class);
			searchedEvents.get(query1.and(query2) , new KinveyListCallback<ObrasFavoritosBackend>() {
				@Override
				public void onSuccess(ObrasFavoritosBackend[] resultadoconsulta) { 
					for (int i = 0; i<resultadoconsulta.length; i++){
						if (resultadoconsulta.length>=1){
							btnFavoritos.setVisibility(View.GONE);
							btnFavoritosSi.setVisibility(View.VISIBLE);
						}	else{
							btnFavoritos.setVisibility(View.VISIBLE);
							btnFavoritosSi.setVisibility(View.GONE);
						}
					}

				}
				@Override
				public void onFailure(Throwable arg0) {
					// TODO Auto-generated method stub

				}
			});
		}else{
			//Toast.makeText(ObraActivity.this, "Debe estar logueado para marcar la obra como favorita. " , Toast.LENGTH_LONG).show();
		}
		}catch(Exception e){
			
		}

	}

	public void borrarFavorito (String nomObra){
		String nomU = kinveyClient.user().getUsername().toString();
		Query query1 = kinveyClient.query ();
		Query query2 = kinveyClient.query ();
		query1.equals("nombreObra", String.valueOf(nomObra));
		query1.equals("nombreUsuario", String.valueOf(nomU));
		final ObrasFavoritosBackend entity = new ObrasFavoritosBackend ();

		AsyncAppData<ObrasFavoritosBackend> searchedEvents = kinveyClient.appData("ObrasFavoritos", ObrasFavoritosBackend.class);
		searchedEvents.get(query1.and(query2) , new KinveyListCallback<ObrasFavoritosBackend>() {


			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}


			@Override
			public void onSuccess(ObrasFavoritosBackend[] arg0) {
				String idF = "";
				for (int i=0;i<arg0.length;i++){
					idF=arg0[i].getIdObraFavorito().toString();
				}
				eliminar(idF);

			}


		});


	}

	public void eliminar (String id){
		String eventId = id;
		AsyncAppData<ObrasFavoritosBackend> myevents = kinveyClient.appData("ObrasFavoritos", ObrasFavoritosBackend.class);
		myevents.delete(eventId, new KinveyDeleteCallback() {
			@Override
			public void onSuccess(KinveyDeleteResponse response) { 
				Log.e(TAG, "bORRO REGISTRO");
			}
			public void onFailure(Throwable error) {
			}
		});
	}




	public void save (String nomObra){
		String nomU = kinveyClient.user().getUsername().toString();
		final ObrasFavoritosBackend entity = new ObrasFavoritosBackend ();
		entity.put("nombreObra", nomObra);
		entity.put("nombreUsuario", nomU);
		kinveyClient.appData("ObrasFavoritos", ObrasFavoritosBackend.class).save(entity, new KinveyClientCallback<ObrasFavoritosBackend>() {


			@Override
			public void onSuccess(ObrasFavoritosBackend result) {

//				Toast.makeText(ObraActivity.this,"Revoluciones: " + result.getNombreObra()
//						+ "\nDescription: " , Toast.LENGTH_LONG).show();


			}

			@Override
			public void onFailure(Throwable error) {
				Log.e(TAG, "AppData.save Failure", error);
				Toast.makeText(ObraActivity.this, "Ha ocurrido un error " + error.getMessage(), Toast.LENGTH_LONG).show();
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
			mensaje = "Utilizando usuario implícito cacheado: " + kinveyClient.user().getId() + ".";
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

		// Establecemos un Listener para el evento de pulsación
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
//		.setMessage("¿Desea borrar esta obra?")
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

