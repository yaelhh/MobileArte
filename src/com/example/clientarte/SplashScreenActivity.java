package com.example.clientarte;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.java.core.DownloaderProgressListener;
import com.kinvey.java.core.MediaHttpDownloader;
import com.kinvey.java.model.FileMetaData;

public class SplashScreenActivity extends Activity {

	// Set the duration of the splash screen
	private static final long SPLASH_SCREEN_DELAY = 15000;
	public static final String TAG = "kid_VT8_It3ePE";
	private String appKey="1b0fa51481984d2da5910f78a9d26ccc";
	private String appSecret="ad60af2bc83d4899aef2f1dffc6529e1";
	private String mensaje;
	private Client mKinveyClient;
	static final String FILENAME = "usuario_1.jpg";


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
//		try {
//			//download(getTarget());
//		//	descargarImag(getTarget());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	

	private File getTarget() {
	        return(new File(this.getFilesDir(), SplashScreenActivity.FILENAME));
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
	 
	 
	
	//private void descargarIma(File target) throws IOException {
//	private void descargarImag(File target) throws FileNotFoundException {
//		FileOutputStream fos= new FileOutputStream(target);
//		// call kinvey specific task to perform download
//		final FileMetaData meta = new FileMetaData(SplashScreenActivity.FILENAME);
//		meta.setId(SplashScreenActivity.FILENAME);
//		mKinveyClient.file().downloadWithTTL(meta.getId(), 1200000, fos, new DownloaderProgressListener() {
//
//			@Override
//			public void progressChanged(MediaHttpDownloader downloader) throws IOException {
//				// Log.i(KitchenSink.TAG, "progress updated: "+downloader.getDownloadState());
//				final String state = new String(downloader.getDownloadState().toString());
//
//				//				 getSherlockActivity().runOnUiThread(new Runnable() {
//				//					 @Override
//				//					 public void run() {
//				//						 tProgress.setText((tProgress.getText() == null) ? state
//				//								 : tProgress.getText() + "\n" + state);
//				//					 }
//				//				 });
//
//
//				@Override
//				public void onSuccess(Void arg0) {
//					try {
//						FileOutputStream fStream = getApplicationContext().openFileOutput(meta.getId(), Context.MODE_PRIVATE);
//						ByteArrayOutputStream bos = arg0.getFile("image").getOutput();
//						bos.writeTo(fStream);
//						bos.flush();
//						fStream.flush();
//						bos.close();
//						fStream.close();
//					} catch (Exception ex) {}
//				}
//				@Override
//				public void onFailure(Throwable arg0) {
//					// TODO Auto-generated method stub
//
//				}
//			}
//		});	
//
//	}	 

//	 public void descargarImag() throws FileNotFoundException{
//		 FileMetaData meta = new FileMetaData(SplashScreenActivity.FILENAME);
//		 meta.setId(SplashScreenActivity.FILENAME);
//		 FileOutputStream fos= new FileOutputStream("usuario_1.jpg");
//		 mKinveyClient.file().download(meta, fos, new DownloaderProgressListener() {
//			 @Override
//			 public void onSuccess(Void result) {
//				 Log.i(TAG, "successfully downloaded file");
//			 }
//			 @Override
//			 public void onFailure(Throwable error) {
//				 Log.e(TAG, "failed to downloaded file.", error);
//			 }
//			 @Override
//			 public void progressChanged(MediaHttpDownloader downloader) throws IOException {
//				 Log.i(TAG, "progress updated: "+downloader.getDownloadState());
//				 // any updates to UI widgets must be done on the UI thread
//			 }
//		 });	
//
//
//	 }


}
