package com.example.clientarte;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.User;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
public class LoginActivity_OLD extends ActionBarActivity {
	
	public static final String TAG = "ArteBackend";
	private String appKey="kid_VT8_It3ePE";
	private String appSecret="1b0fa51481984d2da5910f78a9d26ccc";
	private String mensaje;
	private Client kinveyClient;
	private EditText username;
    private EditText password;
    private Button login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		conectarBackend();
//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.activity_login, new PlaceholderFragment()).commit();
//		}
		
		//setHasOptionsMenu(true);
		//getClient().user().isUserLoggedIn())
        if (kinveyClient.user().isUserLoggedIn()){
//        	Log.i(StatusShare.TAG, "logged in: " + getClient().user().getUsername());
//        	Toast.makeText(getSherlockActivity(), "Logging in", Toast.LENGTH_SHORT);
          //s  loggedIn();

        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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


	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_login,
					container, false);
			return rootView;
		}
	}

}
