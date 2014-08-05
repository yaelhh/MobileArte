package com.example.clientarte;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyPingCallback;

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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/*  Fragment para seccion perfil */ 
public class PerfilFragment extends Fragment {
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
    public PerfilFragment(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.activity_perfil, container, false);
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
      		btnCuenta= (Button)rootView.findViewById(R.id.btnCuenta);
    		btnRegistrar= (Button)rootView.findViewById(R.id.registrarUsuarioPerfil);
    		btnLoguear= (Button)rootView.findViewById(R.id.LoguearDesloguear);
    		btnProxObras = (Button)rootView.findViewById(R.id.btnPrxObras);
    		addListenerOnButton();
        return rootView;
    }
	
	public void desloguearUsuario () {
		super.onDestroy();
		mKinveyClient.user().logout().execute();
		onCreateDialog();
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
	
	public void mensajeConfirmacionDesloguear(){
		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getActivity()); 
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
		Toast t=Toast.makeText(getActivity(),"Bienvenido a probar el programa.", Toast.LENGTH_SHORT);
		t.show();
	}

	public void cancelar() {
		//finish();
		Toast t=Toast.makeText(getActivity(),"Presiono cancelar", Toast.LENGTH_SHORT);
		t.show();
	}

    
    

//    public void addListenerOnButton() {
//		 
//    	btnCuenta.setOnClickListener(new OnClickListener() {
//    		
// 
//			@Override
//			public void onClick(View v) {
//							
//				}
//
//		});
//    	
//    	btnProxObras.setOnClickListener(new OnClickListener() {
//    		 
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(getActivity(), ProgramacionActivity.class);
//				startActivity(intent);
//			}
//
//		});
//    	
//		
//    } 
	
    
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
    
    
    public void addListenerOnButton() {

		btnProxObras.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), ProgramacionActivity.class);
				startActivity(intent);
			}

		});
		btnRegistrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast t3=Toast.makeText(getActivity(),"Usuario Logueado< de prueba" + mKinveyClient.user().getUsername(), Toast.LENGTH_SHORT);
				t3.show();
			}

		});
		
		btnCuenta.setOnClickListener(new OnClickListener() {	 
			@Override
			public void onClick(View v) {
				mKinveyClient = new Client.Builder(getActivity().getApplicationContext()).build();
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
				mKinveyClient = new Client.Builder(getActivity().getApplicationContext()).build();
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

}