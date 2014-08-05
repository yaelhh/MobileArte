package com.example.clientarte;


/*import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;*/


import backend.UsuarioBackend;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyPingCallback;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;



public class PerfilActivity extends Activity {

	private Button btnCuenta;
	private Button btnProxObras;
	private Button btnObrasVistas;
	private Button btnMascaras;
	private Button btnCanjeMasc;
	private Button btnRegistrar;
	private Button btnLoguear;

	private String mensaje;
	private Client mKinveyClient;
	private ProgressDialog mProgressDialog = null;
	private static final int REQUEST_TEXT = 2;
<<<<<<< HEAD

=======
	//ObjetosBackend obj;
	
>>>>>>> develop
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil);
<<<<<<< HEAD
		btnCuenta= (Button)findViewById(R.id.btnCuenta);
		btnRegistrar= (Button)findViewById(R.id.registrarUsuarioPerfil);
		btnLoguear= (Button)findViewById(R.id.LoguearDesloguear);
		addListenerOnButton();
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

		//final UsuarioBackend obj= (UsuarioBackend)getApplicationContext();

=======
		
		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		btnCuenta= (Button)findViewById(R.id.btnCuenta);
		btnRegistrar= (Button)findViewById(R.id.registrarUsuarioPerfil);
		btnLoguear= (Button)findViewById(R.id.LoguearDesloguear);
		addListenerOnButton(obj);
>>>>>>> develop
	}

	

	public void desloguearUsuario () {
		super.onDestroy();
		mKinveyClient.user().logout().execute();
		onCreateDialog();
		//mensaje = "Cerrando Sesion";
		//Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
	}

	protected Dialog onCreateDialog() {
		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage("Cerrando sesion");
		dialog.setIndeterminate(true);

		mProgressDialog = dialog;
		return dialog;		
	}

	public void mensajeConfirmacion(){
		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this); 
		dialogo1.setTitle("Importante"); 
		dialogo1.setMessage("No esta logueado,¿Desea loguearse?"); 
		dialogo1.setCancelable(false); 
		dialogo1.setPositiveButton("Si", new DialogInterface.OnClickListener() { 
			public void onClick(DialogInterface dialogo1, int id) { 
				Intent intent = new Intent(PerfilActivity.this, LoginActivity.class); 
				//startActivity(intent);
				PerfilActivity.this.startActivityForResult(intent, REQUEST_TEXT);

			} 
		}); 
		dialogo1.setNegativeButton("No", new DialogInterface.OnClickListener() { 
			public void onClick(DialogInterface dialogo1, int id) { 
				cancelar(); 
			} 
		}); 
		dialogo1.show(); 


	}

	public void mensajeConfirmacionDesloguear(){
		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this); 
		dialogo1.setTitle("Importante"); 
		dialogo1.setMessage("Usted esta logueado,¿Desea desloguearse?"); 
		dialogo1.setCancelable(false); 
		dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() { 
			public void onClick(DialogInterface dialogo1, int id) { 
				//				Intent intent = new Intent(PerfilActivity.this, LoginActivity.class); 
				//				startActivity(intent); 
				desloguearUsuario ();

			} 
		}); 
		dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() { 
			public void onClick(DialogInterface dialogo1, int id) { 
				cancelar(); 
			} 
		}); 
		dialogo1.show(); 


	}

	public void aceptar() { 
		Toast t=Toast.makeText(this,"Bienvenido a probar el programa.", Toast.LENGTH_SHORT);
		t.show();
	}

	public void cancelar() {
		//finish();
		Toast t=Toast.makeText(this,"Presiono cancelar", Toast.LENGTH_SHORT);
		t.show();
	}



<<<<<<< HEAD
	public void addListenerOnButton() {
=======
	public void addListenerOnButton( ObjetosBackend obj) {
>>>>>>> develop

		btnCuenta.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}

		});

		btnProxObras.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PerfilActivity.this, ProgramacionActivity.class);
				startActivity(intent);
			}

		});
		btnRegistrar.setOnClickListener(new OnClickListener() {

<<<<<<< HEAD
			@Override
			public void onClick(View v) {
		Toast t3=Toast.makeText(PerfilActivity.this,"Usuario Logueado" + mKinveyClient.user().getUsername(), Toast.LENGTH_SHORT);
		t3.show();
	}
=======
			
			public void onClick(View v) {
				//Toast t3=Toast.makeText(PerfilActivity.this,"Usuario Logueado" + mKinveyClient.user().getUsername(), Toast.LENGTH_SHORT);
				//Toast t3=Toast.makeText(PerfilActivity.this,"Usuario Logueado" + obj.getmKinveyClient().user().getUsername().toString(), Toast.LENGTH_SHORT);
				//t3.show();
			}
>>>>>>> develop

		});
		
		btnCuenta.setOnClickListener(new OnClickListener() {	 
			@Override
			public void onClick(View v) {
				mKinveyClient = new Client.Builder(PerfilActivity.this.getApplicationContext()).build();
				if (!mKinveyClient.user().isUserLoggedIn()) {
					mensajeConfirmacion();
					//			PerfilActivity.this.startActivity(new Intent(PerfilActivity.this, 
					//                    LoginActivity.class));
					//			PerfilActivity.this.finish();
				}else{
					if (mKinveyClient.user().isUserLoggedIn()) {
						mensajeConfirmacionDesloguear();
						//desloguearUsuario();
					}
				}
			}
		});

		btnLoguear.setOnClickListener(new OnClickListener() {	 
			@Override
			public void onClick(View v) {
				mKinveyClient = new Client.Builder(PerfilActivity.this.getApplicationContext()).build();
				//mKinveyClient = new Client.Builder(this.getApplicationContext()).build();
				if (!mKinveyClient.user().isUserLoggedIn()) {
					mensajeConfirmacion();
					
				}else{
					if (mKinveyClient.user().isUserLoggedIn()) {
						mensajeConfirmacionDesloguear();
					}
				}
			}
		});

		
	}
	@Override 
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		View view = null;
		if (requestCode == 2) { 
			if (resultCode == RESULT_OK) { 
				//BUSCO USUARIO SEGUN NOMBRE DE USUARIO KINVEY LOGUEADO
				String userNameLogueado = data.getStringExtra("username");
				Toast t=Toast.makeText(this,"Me traigo datos" + userNameLogueado, Toast.LENGTH_SHORT);
				t.show();
				mKinveyClient.user().setUsername(userNameLogueado);
				Toast t2=Toast.makeText(this,"Usuario Logueado" + mKinveyClient.user().getUsername(), Toast.LENGTH_SHORT);
				t2.show();

			} else if (resultCode == RESULT_CANCELED) {  
			} 
		}
	}


//	public void registrarUsuarioPrueba (View view){
//		Toast t3=Toast.makeText(this,"Usuario Logueado" + mKinveyClient.user().getUsername(), Toast.LENGTH_SHORT);
//		t3.show();
//	}
<<<<<<< HEAD
}
=======
}
>>>>>>> develop
