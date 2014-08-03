package com.example.clientarte;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.User;

public class SplashScreenActivity extends Activity {

	// Set the duration of the splash screen
	private static final long SPLASH_SCREEN_DELAY = 15000;
	public static final String TAG = "kid_VT8_It3ePE";
	private String appKey="1b0fa51481984d2da5910f78a9d26ccc";
	private String appSecret="ad60af2bc83d4899aef2f1dffc6529e1";
	private String mensaje;
	private Client mKinveyClient;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//DB_Obra o = new DB_Obra();
		//o.crearObra();
		super.onCreate(savedInstanceState);

		// Set portrait orientation
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// Hide title bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_splash_screen);

		TimerTask task = new TimerTask() {
			@Override
			public void run() {

				// Start the next activity
				Intent mainIntent = new Intent().setClass(
						SplashScreenActivity.this, MainActivity.class);
						startActivity(mainIntent);

				// Close the activity so the user won't able to go back this
				// activity pressing Back button
				finish();
			}
		};

		// Simulate a long loading process on application startup.
		Timer timer = new Timer();
		timer.schedule(task, SPLASH_SCREEN_DELAY);

		        
		//conectarBackend();
		//mKinveyClient = new Client.Builder(this.getApplicationContext()).build();
		mKinveyClient = new Client.Builder(getApplicationContext()).build();
		mKinveyClient.ping(new KinveyPingCallback() { 


			public void onFailure(Throwable t) {
				Log.e("Probando Kinvey Connection", "Kinvey Ping Failed", t); 
			}

			public void onSuccess(Boolean b) { 
				Log.d("Probando Kinvey Connection", "Kinvey Ping Success"); 
			}
		});
		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		obj.inicialize(mKinveyClient); 
		obj.traerDatos();
	}
	
	
//	public void conectarBackend (){ 
//
//		mKinveyClient = new Client.Builder(appKey, appSecret, this).build();
//		mKinveyClient.ping(new KinveyPingCallback() { 
//			public void onFailure(Throwable t) {
//				Log.e("Probando Kinvey Connection", "Kinvey Ping Failed", t); 
//			}
//			public void onSuccess(Boolean b) { 
//				Log.d("Probando Kinvey Connection", "Kinvey Ping Success");
//			}
//		});
//		//mKinveyClient.user().login("nlema", "nlema", new KinveyUserCallback() { 
//		if (!mKinveyClient.user().isUserLoggedIn()) { 
//			mKinveyClient.user().login(new KinveyUserCallback() { 
//				public void onFailure(Throwable error) { 
//					mensaje = "Error al realizar el login.";
//					Log.e("Realizando Kinvey Login", mensaje, error); 
//				}
//				@Override
//				public void onSuccess(User u) {
//					mensaje = "Bienvenido usuario: " + u.getId() + "."; 
//					Log.d("Realizando Kinvey Login", mensaje);
//				}
//			});
//		} else { 
//			mensaje = "Utilizando usuario implícito cacheado: " + mKinveyClient.user().getId() + ".";
//			Log.d("Realizando Kinvey Login", mensaje); 
//		}
//	}
}
