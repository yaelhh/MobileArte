package com.example.clientarte;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import android.accounts.AccountAuthenticatorActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import backend.DatabaseHelper;
import backend.SalaBackend;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.User;
import com.kinvey.java.core.KinveyClientCallback;

public class LoginActivity extends AccountAuthenticatorActivity {
	
	//public static final String TAG = LoginActivity.class.getSimpleName();
	public static final String TAG = "ArteBackend";
	private EditText emailUsuario;
	private EditText passUsuario;
	private String appKey="kid_VT8_It3ePE";
	private String appSecret="1b0fa51481984d2da5910f78a9d26ccc";
	private String mensaje;
	DatabaseHelper dh;
	private Button btnLogin;
	
	private Client mKinveyClient;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		dh = new DatabaseHelper(getApplicationContext());
		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		setTitle("Login");
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
		mKinveyClient = obj.captarUsuarioLogueado();
		emailUsuario = (EditText)findViewById(R.id.txtMailUsuarioLogin);
		passUsuario = (EditText)findViewById(R.id.txtPassLogin);
		
		btnLogin= (Button)findViewById(R.id.buttonLogin);
		btnLogin.setOnClickListener(new OnClickListener() {  
			@Override
			public void onClick(View v) {
				login(obj);
			}
		});



	}
	
	//@Override
		public void cerrar (View view) {
			super.onDestroy();
			modificarEstadoDeslogueado(mKinveyClient.user().getUsername());
			mKinveyClient.user().logout().execute();
			mensaje = "Hasta luego.";
	       Log.d("Realizando Kinvey Logout", mensaje);
		}
		
		//Registro usuario
		public void registroUsuario (View view) {
			LoginActivity.this.startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class));
            LoginActivity.this.finish();
		}
		
	
		//Login
		public void login (final ObjetosBackend obj) {
			final String usuario = emailUsuario.getText().toString();
			final String password = passUsuario.getText().toString();
			
			if (!mKinveyClient.user().isUserLoggedIn()) {
				//if (emailValidator(usuario)){
				mKinveyClient.user().login(usuario, password, new KinveyUserCallback() {
				//obj.captarUsuarioLogueado().user().login(usuario, password, new KinveyUserCallback() {
					public void onFailure(Throwable error) {
						mensaje = "Error al realizar el login.";
						Log.e("Realizando Kinvey Login", mensaje, error);
						Toast.makeText(getApplicationContext(),"Nombre de usuario o contraseña incorrecta" , Toast.LENGTH_LONG).show();
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
						obj.cargarUsuarioLogueado(usuario);
						modificarEstado(mKinveyClient.user().getUsername().toString());
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
		
		public void modificarEstado (String nombreUsuario){
			int i = dh.actualizarUsuarioLogueado(nombreUsuario);
			String men = i + "";
			Log.d("Los campos afectados fueron" + men, men );
		}
		
		public void modificarEstadoDeslogueado (String nombreUsuario){
//			int i = dh.actualizarUsuarioLogueadoDeslogueado(nombreUsuario);
//			String men = i + "";
//			Log.d("Los campos afectados fueron" + men, men );
			dh.actualizarUsuarioLogueadoDeslogueado(nombreUsuario);
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
