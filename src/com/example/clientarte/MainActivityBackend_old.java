package com.example.clientarte;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import backend.SalaBackend;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.Query;
import com.kinvey.java.User;
import com.kinvey.java.core.KinveyClientCallback;

public class MainActivityBackend_old extends ActionBarActivity {

	public Client mKinveyClient;
	public static final String TAG = "ArteBackend";
	private String mensaje;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_backend);
	
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		//Conexión de la APP a Kinvey
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
	
	//@Override
	protected void onDestroy () {
		super.onDestroy();
		mKinveyClient.user().logout().execute();
		mensaje = "Hasta luego.";
       Log.d("Realizando Kinvey Logout", mensaje);
	}
	
	//Recuperar una sala
	public void recuperarSala (View view) {
        //appData es la interface para guardar y recuperar entidades 
        mKinveyClient.appData("Sala", SalaBackend.class).getEntity("1", new KinveyClientCallback<SalaBackend>() {
            @Override
            public void onSuccess(SalaBackend result) {
                mensaje = "Sala id: " + result.getIdSala() + ", Nombre: " + result.getNombreSala();
                Log.d(TAG + "- recuperarSala", mensaje);
            }
            @Override
            public void onFailure(Throwable error) {
                Log.e(TAG + "- recuperarSala", "Falla en AppData.getEntity", error);
            }
        });
    }

	//Recuperar todas las salas
    public void recuperarSalas(View view) {
        Query myQuery = mKinveyClient.query();
        mKinveyClient.appData("Sala", SalaBackend.class).get(myQuery, new KinveyListCallback<SalaBackend>() {
            @Override
            public void onSuccess(SalaBackend[] resultadoconsulta) {
                //for (Sala sala : result) {
            	for (int i = 0; i < resultadoconsulta.length; i++) {
                	mensaje = "Sala id: " + resultadoconsulta[i].getIdSala() + ", Nombre: " + resultadoconsulta[i].getNombreSala();
                	Log.d(TAG + "- recuperarSalas", mensaje);
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
