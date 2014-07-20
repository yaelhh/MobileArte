package com.example.clientarte;

import java.io.IOException;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.User;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import backend.DatabaseHelper;
import dominio.Obra;




public class ObraActivity_Old extends ActionBarActivity {
	
	public static final String TAG = "ArteBackend";
	private String appKey="kid_VT8_It3ePE";
	private String appSecret="1b0fa51481984d2da5910f78a9d26ccc";
	private String mensaje;
	private Client kinveyClient;
	
//	private int requestCode = 1;
//	private ListView lvObras;
//	private DB_Obra dataSource;// = new DB_Obra(this);
	
	//final String[] from = { ObrasColumns.idObra, ObrasColumns.nombreObra, ObrasColumns.descripcionObra };
	//final int[] to = new int[] { R.id., R.id.apellidos, R.id.edad };

	/*DBAdapter dbAdapter;
	Boolean mBound;*/
	// Database Helper
    DatabaseHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_obra);
		
		DatabaseHelper myDbHelper = new DatabaseHelper(this);
		myDbHelper = new DatabaseHelper(this);
		conectarBackend();
		
		try {
			myDbHelper.createDataBase();
			
		} catch (IOException ioe) {

			throw new Error("Unable to create database");

		}
		
		Obra miObra = new Obra ("Redemption", "Jason Statham");
		Obra miObra2 = new Obra ("La Madrastra", "Victoria Ruffo");
		Obra miObra3 = new Obra ("The Wolf of Wall Street", "Leonardo DiCaprio");
		Obra miObra4 = new Obra ("X-Men", "Hugh Jackman");
		myDbHelper.createObra(miObra);
		myDbHelper.createObra(miObra2);
		myDbHelper.createObra(miObra3);
		myDbHelper.createObra(miObra4);
	
		
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
	}

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

