package com.example.clientarte;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import backend.SalaBackend;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.android.callback.KinveyUserDeleteCallback;
import com.kinvey.java.User;
import com.kinvey.java.core.KinveyClientCallback;

import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AccountAuthenticatorActivity {
	
	//public static final String TAG = LoginActivity.class.getSimpleName();
	public static final String TAG = "ArteBackend";
	private EditText emailUsuario;
	private EditText passUsuario;
	private String appKey="kid_VT8_It3ePE";
	private String appSecret="1b0fa51481984d2da5910f78a9d26ccc";
	private String mensaje;
	
	
	private Client mKinveyClient;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

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
		emailUsuario = (EditText)findViewById(R.id.txtMailUsuarioLogin);
		passUsuario = (EditText)findViewById(R.id.txtPassLogin);


	}
	
	//@Override
		public void cerrar (View view) {
			super.onDestroy();
			mKinveyClient.user().logout().execute();
			mensaje = "Hasta luego.";
	       Log.d("Realizando Kinvey Logout", mensaje);
		}
		
		//Registro usuario
		public void registroUsuario (View view) {
//			emailUsuario = (EditText) findViewById(R.id.etNombreUsuario);
//			password = (EditText) findViewById(R.id.etPassUsuario);
			final String usuario = emailUsuario.getText().toString();
			String password = passUsuario.getText().toString();
			//Crear nuevo usuario
			//if (emailValidator(usuario)){
			mKinveyClient.user().create(usuario, password, new KinveyUserCallback() {
				public void onFailure(Throwable error) {
					//onFailura se ejecuta si el usuario existe
					//Se avisa al usuario por pantalla del error
					mensaje = "El usuario: " + usuario + ", ya se encuentra registrado";
					Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
					//Se graba registro en el log de errores
					Log.e("Realizando registro de usuario en Kinvey", mensaje, error);
				}
				@Override
				public void onSuccess(User u) {
					mensaje = "Se ha realizado el alta del usuario: " + u.getUsername() + ".";
					Log.d("Realizando registro de usuario en Kinvey", mensaje);
				}
			});
//			}else{
//				mensaje = "El email ingresado no es correcto, revise formato";
//				Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
//			}
		}
	
		//Login
		public void login (View view) {
			//			usuario = (EditText) findViewById(R.id.etNombreUsuario);
			//			password = (EditText) findViewById(R.id.etPassUsuario);
			//Verificar si el usuario está "logeado"
			final String usuario = emailUsuario.getText().toString();
			String password = passUsuario.getText().toString();
			if (!mKinveyClient.user().isUserLoggedIn()) {
				//Si no está "logeado" se realiza el login
				//if (emailValidator(usuario)){
				mKinveyClient.user().login(usuario, password, new KinveyUserCallback() {
					public void onFailure(Throwable error) {
						mensaje = "Error al realizar el login.";
						Log.e("Realizando Kinvey Login", mensaje, error);
					}
					@Override
					public void onSuccess(User u) {
						//Se muestra mensaje de bienvenida por pantalla
						mensaje = "Bienvenido usuario: " + u.getUsername() + ".";
						Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
						//Se graba registro en el log
						Log.d("Realizando Kinvey Login", mensaje);
						Intent intent = new Intent();
						intent.putExtra("username",mKinveyClient.user().getUsername());
						//intent.putExtra("logueado", true);
						       setResult( Activity.RESULT_OK, intent );
						       LoginActivity.this.finish();
						//salirLoguin();
					}
				});
			} else {
				mensaje = "Utilizando usuario cacheado: " + mKinveyClient.user().getUsername() + ".";
				Log.d("Realizando Kinvey Login", mensaje);
			}
			//			}else{
			//				mensaje = "El email ingresado no es correcto, revise formato";
			//				Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
			//			}
		}
		
		public void salirLoguin(){

			finish();

		}
		
		//Recuperar una sala
		public void recuperarSala (View view) {
	        //appData es la interface para guardar y recuperar entidades 
			//Se buscan los datos de la sala 1
	        mKinveyClient.appData("Sala", SalaBackend.class).getEntity("1", new KinveyClientCallback<SalaBackend>() {
	            @Override
	            public void onSuccess(SalaBackend result) {
	            	//Se muestra por pantalla los datos de la sala
	                mensaje = "Sala id: " + result.getIdSala() + ", Nombre: " + result.getNombreSala();
					Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
	                //Se graba registro en el log
					Log.d(TAG + "- recuperarSala", mensaje);
	            }
	            @Override
	            public void onFailure(Throwable error) {
	            	//Se avisa por pantalla que el usuario tiene que estar logeado
	            	mensaje = "Para acceder a los datos de la sala, debe estar logeado en la aplicación";
	            	Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
	                //Se graba registro en el log
	            	Log.e(TAG + "- recuperarSala", "Falla en AppData.getEntity", error);
	            }
				
	        });
	    }
		
		
		public boolean emailValidator(String email) {
		    Pattern pattern;
		    Matcher matcher;
		    final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		    pattern = Pattern.compile(EMAIL_PATTERN);
		    matcher = pattern.matcher(email);
		    return matcher.matches();
		}
		
		
	
	
	
	
	

}
