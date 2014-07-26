import java.util.ArrayList;
import java.util.HashMap;

import android.app.Application;
import android.util.Log;
import android.widget.TextView;
import dominio.*;

import com.example.clientarte.ComunidadActivity;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.User;
import com.kinvey.android.AsyncAppData;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.java.Query;
import com.kinvey.java.core.DownloaderProgressListener;
import com.kinvey.java.core.KinveyClientCallback;
import com.kinvey.java.core.MediaHttpDownloader;
import com.kinvey.java.model.FileMetaData;





import dominio.Butaca;
import dominio.Funcion;
import dominio.Obra;
import dominio.Sala;
import dominio.Sector;


public class ObjetosKinvey extends Application {
	//Creo listas 
	
		private ArrayList<Funcion> listaFunciones;
		private ArrayList<Sala> listaSalas;
		private ArrayList<Sector> listaSectores;
		//	private ArrayList<Comodidad> listaComodidades;
		private ArrayList<Obra> listaObra;
		private ArrayList<Butaca> listabutacas;
		ArrayList<Butaca>listabutacas2;
		ArrayList<Butaca> listabutacas3;
		ArrayList<Butaca> listabutacas4;
		
		private Funcion funcionA;
		private Funcion funcionB;
		private Obra obra1;
		private Obra obra2;
		private Sala sala1;
		private Sala sala2;
		private Sector sector1;
		private Sector sector2;
		private Sector sector3;
		private Sector sector4;

		private Butaca miButaca;
		private HashMap<String, Boolean> hashComodidades = new HashMap <String, Boolean> ();
		
		//creo objetos de kinvey
		public static final String TAG = "ArteBackend";
		private String appKey="kid_VT8_It3ePE";
		private String appSecret="1b0fa51481984d2da5910f78a9d26ccc";
		private String mensaje;
		private Client mKinveyClient;
		private TextView tvBd1;
		public static final String AUTHTOKEN_TYPE = ".clientarte";
		public static final String ACCOUNT_TYPE = ".clientarte";
		public static final String LOGIN_TYPE_KEY = "loginType";
		private Client service;
		
		
		//Constructor de clase
		public void ObjetosKinvey(){
			ArrayList listaImagenes= new ArrayList();
			ArrayList<Funcion> listaFunciones= new ArrayList<Funcion>();
			ArrayList<Sala> listaSalas= new ArrayList<Sala>();
			ArrayList<Sector> listaSectores= new ArrayList<Sector>();
			//	private ArrayList<Comodidad> listaComodidades;
			ArrayList<Obra> listaObra= new ArrayList<Obra>();
			ArrayList<Butaca> listabutacas= new ArrayList<Butaca>();
			ArrayList<Butaca>listabutacas2 = new ArrayList<Butaca>();
			ArrayList<Butaca> listabutacas3 = new ArrayList<Butaca>();
			conectarBackend();
			cargarDatosObra();
			
			
		}
		
		//Funcion para conectarse al backend
	public void conectarBackend (){
//		kinveyClient = new Client.Builder(appKey, appSecret, this).build();
		mKinveyClient = new Client.Builder(this.getApplicationContext()).build();
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


	public void cargarDatosObra(){
		Query myQuery = mKinveyClient.query();
		mKinveyClient.appData("Funciones", Funcion.class).get(myQuery, new KinveyListCallback<Funcion>(){
			
				@Override
				public void onSuccess(Funcion[] arg0) {
					
					
				}
				 @Override
		            public void onFailure(Throwable error) {
		                Log.e(TAG, "AppData.get by Query Failure", error);
		            }
		    }); 
		mKinveyClient.appData("Obras", Obra.class).get(myQuery, new KinveyListCallback<Obra>(){
			 public void onSuccess(Obra[] resultadoconsulta) {
	                //for (Sala sala : result) {
	       		// int[] to = new int[] { R.id.TextView01 };
	            	
	            	
	            }
	            @Override
	            public void onFailure(Throwable error) {
	                Log.e(TAG, "AppData.get by Query Failure", error);
	            }
		    }); 
	        
		}
		
				
	
	
}
