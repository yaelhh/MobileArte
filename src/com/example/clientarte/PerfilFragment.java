package com.example.clientarte;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import backend.DatabaseHelper;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyPingCallback;

/*  Fragment para seccion perfil */ 
public class PerfilFragment extends Fragment {
	//private Button btnCuenta;
	private Button btnProxObras;
	private Button btnObrasVistas;
	private Button btnMascaras;
	private Button btnCanjeMasc;
	private Button btnRegistrar;
	private Button btnLoguear;
	private Button btnEditarUsuario;

	
	private String mensaje;
	private Client mKinveyClient;
	private ProgressDialog mProgressDialog = null;
	private static final int REQUEST_TEXT = 2;
	
    public PerfilFragment(){}
	DatabaseHelper dh;
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.activity_perfil, container, false);
        dh = new DatabaseHelper(getActivity().getApplicationContext());
		final ObjetosBackend obj= (ObjetosBackend) getActivity().getApplicationContext();
      //Conexión de la APP a Kinvey
        	mKinveyClient = new Client.Builder(getActivity().getApplicationContext()).build();
      		mKinveyClient.ping(new KinveyPingCallback() {
      			public void onFailure(Throwable t) {
      				Log.e("Probando Kinvey Connection", "Kinvey Ping Failed", t);
      			}
      			public void onSuccess(Boolean b) {
      				Log.d("Probando Kinvey Connection", "Kinvey Ping Success");
      			}
      		});
      		//btnCuenta= (Button)rootView.findViewById(R.id.btnCuenta);
    		btnRegistrar= (Button)rootView.findViewById(R.id.registrarUsuarioPerfil);
    		btnLoguear= (Button)rootView.findViewById(R.id.LoguearDesloguear);
    		btnProxObras = (Button)rootView.findViewById(R.id.btnPrxObras);
<<<<<<< HEAD
    		btnObrasVistas= (Button)rootView.findViewById(R.id.BtnObrasVistas);
    		mKinveyClient = obj.captarUsuarioLogueado();
=======
    		btnEditarUsuario= (Button)rootView.findViewById(R.id.btnCuenta);
    		
    		//mKinveyClient = obj.captarUsuarioLogueado();
>>>>>>> develop
    		addListenerOnButton(obj);

        return rootView;
    }
    
//    public void validarUsuarioOpcion(View view){
//    	mKinveyClient = new Client.Builder(getActivity().getApplicationContext()).build();
//    	//mKinveyClient = new Client.Builder(this.getApplicationContext()).build();
//    	if (!mKinveyClient.user().isUserLoggedIn()) {
//    		mensajeConfirmacion();
//    		//			PerfilActivity.this.startActivity(new Intent(PerfilActivity.this, 
//    		//                    LoginActivity.class));
//    		//			PerfilActivity.this.finish();
//    	}else{
//    		if (mKinveyClient.user().isUserLoggedIn()) {
//    			mensajeConfirmacionDesloguear();
//    			//desloguearUsuario();
//    		}
//    	}
//    }
    
//    public void desloguearUsuario () {
//
//      		btnCuenta=(Button)rootView.findViewById(R.id.btnCuenta);
//      		btnCuenta.setOnClickListener(new OnClickListener() {
//      			 
//    			@Override
//    			public void onClick(View v) {
//    				mKinveyClient = new Client.Builder(getActivity().getApplicationContext()).build();
//    				//mKinveyClient = new Client.Builder(this.getApplicationContext()).build();
//    				if (!mKinveyClient.user().isUserLoggedIn()) {
//    					mensajeConfirmacion();
////    					PerfilActivity.this.startActivity(new Intent(PerfilActivity.this, 
////    		                    LoginActivity.class));
////    					PerfilActivity.this.finish();
//    				}else{
//    					if (mKinveyClient.user().isUserLoggedIn()) {
//    					mensajeConfirmacionDesloguear();
//    					//desloguearUsuario();
//    				}
//    				}
//
//  			}
//
//    		});
//        return rootView;
//    }
    
//    public void validarUsuarioOpcion(View view){
//		mKinveyClient = new Client.Builder(getActivity().getApplicationContext()).build();
//		//mKinveyClient = new Client.Builder(this.getApplicationContext()).build();
//		if (!mKinveyClient.user().isUserLoggedIn()) {
//			mensajeConfirmacion();
////			PerfilActivity.this.startActivity(new Intent(PerfilActivity.this, 
////                    LoginActivity.class));
////			PerfilActivity.this.finish();
//		}else{
//			if (mKinveyClient.user().isUserLoggedIn()) {
//			mensajeConfirmacionDesloguear();
//			//desloguearUsuario();
//		}
//		}
//	}

    		
    

<<<<<<< HEAD
	public void desloguearUsuario (ObjetosBackend obj) {
		//super.onDestroy();
		mKinveyClient=obj.captarUsuarioLogueado();
=======
<<<<<<< 8a56b019027e0b215327bd641bb423760733d5ce
    public void desloguearUsuario (ObjetosBackend obj) {
		//super.onDestroy();
		try{
		mKinveyClient = obj.captarUsuarioLogueado();
=======
	public void desloguearUsuario (ObjetosBackend obj) {
		//super.onDestroy();
		mKinveyClient=obj.captarUsuarioLogueado();
>>>>>>> 16c003e6586b220188a279caa9534e0a96fef792
>>>>>>> develop
		String usuarioLog = mKinveyClient.user().getUsername().toString();
		modificarEstadoDeslogueado(usuarioLog);
		onCreateDialog();
		mKinveyClient.user().logout().execute();
		}catch (Exception io){
			Toast tIO = Toast.makeText(getActivity(),"Ha ocurrido un error al desloguear usuario. ", Toast.LENGTH_SHORT);
			tIO.show();
		}
	}
	
	public void modificarEstadoDeslogueado (String nombreUsuario){
		//int i = dh.actualizarUsuarioLogueadoDeslogueado(nombreUsuario);
		dh.actualizarUsuarioLogueadoDeslogueado(nombreUsuario);
//		String men = i + "";
//		Log.d("Los campos afectados fueron" + men, men );
	}
	
	
	protected Dialog onCreateDialog() {
		final ProgressDialog dialog = new ProgressDialog(getActivity());
		dialog.setMessage("Cerrando sesion");
		dialog.setIndeterminate(true);
		
		mProgressDialog = dialog;
		return dialog;		
	}
	
	public void mensajeConfirmacion(){
		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getActivity()); 
		dialogo1.setTitle("Importante"); 
		dialogo1.setMessage("No esta logueado,¿Desea loguearse?"); 
		dialogo1.setCancelable(false); 
		dialogo1.setPositiveButton("Si", new DialogInterface.OnClickListener() { 
			public void onClick(DialogInterface dialogo1, int id) { 
				Intent intent = new Intent(getActivity(), LoginActivity.class); 
				//startActivity(intent);
				getActivity().startActivityForResult(intent, REQUEST_TEXT);

			} 
		}); 
		dialogo1.setNegativeButton("No", new DialogInterface.OnClickListener() { 
			public void onClick(DialogInterface dialogo1, int id) { 
				cancelar(); 
			} 
		}); 
		dialogo1.show(); 


	}
	
	public void mensajeConfirmacionDesloguear(final ObjetosBackend obj){
		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getActivity()); 
		dialogo1.setTitle("Importante"); 
		dialogo1.setMessage("Usted esta logueado,¿Desea desloguearse?"); 
		dialogo1.setCancelable(false); 
		dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() { 
			public void onClick(DialogInterface dialogo1, int id) { 
//				Intent intent = new Intent(PerfilActivity.this, LoginActivity.class); 
//				startActivity(intent); 
<<<<<<< HEAD
				
=======
<<<<<<< 8a56b019027e0b215327bd641bb423760733d5ce
=======
				
>>>>>>> 16c003e6586b220188a279caa9534e0a96fef792
>>>>>>> develop
				desloguearUsuario (obj);

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
		Toast t=Toast.makeText(getActivity(),"Bienvenido a probar el programa.", Toast.LENGTH_SHORT);
		t.show();
	}

	public void cancelar() {
		//finish();
		Toast t=Toast.makeText(getActivity(),"Presiono cancelar", Toast.LENGTH_SHORT);
		t.show();
	}

    
    @Override 
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	View view = null;
		if (requestCode == 2) { 
			if (resultCode == getActivity().RESULT_OK) { 
				//BUSCO USUARIO SEGUN NOMBRE DE USUARIO KINVEY LOGUEADO
				String userNameLogueado = data.getStringExtra("username");
				Toast t=Toast.makeText(getActivity(),"Me traigo datos" + userNameLogueado, Toast.LENGTH_SHORT);
				t.show();
				mKinveyClient.user().setUsername(userNameLogueado);
				Toast t2=Toast.makeText(getActivity(),"Usuario Logueado" + mKinveyClient.user().getUsername(), Toast.LENGTH_SHORT);
				t2.show();
				
			} else if (resultCode == getActivity().RESULT_CANCELED) {  
			} 
		}
	}
    
    
    public void addListenerOnButton(final ObjetosBackend obj) {

		btnProxObras.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), ProxObrasActivity.class);
				startActivity(intent);
			}

		});
		btnRegistrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Toast t3=Toast.makeText(getActivity(),"Usuario logueado AHORA" + obj.captarUsuarioLogueado().user().getUsername(), Toast.LENGTH_SHORT);
				t3.show();
			}


//		Toast t4=Toast.makeText(getActivity(),"Usuario Logueado" + mKinveyClient.user().getUsername(), Toast.LENGTH_SHORT).show();
	



		});
		
//		btnCuenta.setOnClickListener(new OnClickListener() {	 
//			@Override
//			public void onClick(View v) {
//				mKinveyClient = new Client.Builder(getActivity().getApplicationContext()).build();
//				if (!mKinveyClient.user().isUserLoggedIn()) {
//					mensajeConfirmacion();
//				}else{
//					
//					if (mKinveyClient.user().isUserLoggedIn()) {
//						mensajeConfirmacionDesloguear();
//						//desloguearUsuario();
//					}
//				}
//			}
//		});
		
		btnEditarUsuario.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!obj.captarUsuarioLogueado().user().isUserLoggedIn()){	
					mensajeConfirmacion();
				}else{
<<<<<<< 8a56b019027e0b215327bd641bb423760733d5ce
					if (obj.captarUsuarioLogueado().user().isUserLoggedIn()) {
						Intent intent = new Intent(getActivity(), EditarUsuarioActivity.class);
						startActivity(intent);
=======
					
					if (mKinveyClient.user().isUserLoggedIn()) {
						mensajeConfirmacionDesloguear(obj);
						//desloguearUsuario();
>>>>>>> 16c003e6586b220188a279caa9534e0a96fef792
					}
				}
			}
		});

		btnLoguear.setOnClickListener(new OnClickListener() {	 
			@Override
			public void onClick(View v) {
				//AsyncUser<User> usu = obj.captarUsuarioLogueado().user();
				//mKinveyClient = new Client.Builder(getActivity().getApplicationContext()).build();
				//mKinveyClient = new Client.Builder(this.getApplicationContext()).build();
				//if (!mKinveyClient.user().isUserLoggedIn()) {
				if (!obj.captarUsuarioLogueado().user().isUserLoggedIn()){	
					mensajeConfirmacion();

				}else{
					//if (mKinveyClient.user().isUserLoggedIn()) {
					if (obj.captarUsuarioLogueado().user().isUserLoggedIn()) {
						mensajeConfirmacionDesloguear(obj);
					}
				}
			}
		});
		
		btnObrasVistas.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), ObrasVistas.class);
				startActivity(intent);
			}

		});
		
	}

}