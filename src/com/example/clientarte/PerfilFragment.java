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
import android.widget.TextView;
import android.widget.Toast;
import backend.DatabaseHelper;
import backend.UsuarioBackend;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.java.Query;

/*  Fragment para seccion perfil */ 
public class PerfilFragment extends android.support.v4.app.Fragment {
	//private Button btnCuenta;
	private Button btnProxObras;
	private Button btnObrasVistas;
	private Button btnFavoritos;
	private Button btnCanjeMasc;
	private Button btnRegistrar;
	private Button btnLoguear;
	private Button btnEditarUsuario;
	private TextView txtNombreUsuario;


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
		txtNombreUsuario=(TextView)rootView.findViewById(R.id.nombreUsuarioCuenta);
		//Conexión de la APP a Kinvey
		mKinveyClient = new Client.Builder(getActivity().getApplicationContext()).build();
		mKinveyClient.ping(new KinveyPingCallback() {
			public void onFailure(Throwable t) {
				Log.e("Probando Kinvey Connection", "Kinvey Ping Failed", t);
			}
			public void onSuccess(Boolean b) {
				Log.d("Probando Kinvey Connection", "Kinvey Ping Success");
				txtNombreUsuario.setText(mKinveyClient.user().getUsername());

			}
		});
		//btnCuenta= (Button)rootView.findViewById(R.id.btnCuenta);
		btnRegistrar= (Button)rootView.findViewById(R.id.registrarUsuarioPerfil);
		btnLoguear= (Button)rootView.findViewById(R.id.LoguearDesloguear);
		btnProxObras = (Button)rootView.findViewById(R.id.btnPrxObras);
		btnObrasVistas= (Button)rootView.findViewById(R.id.BtnObrasVistas);
		mKinveyClient = obj.captarUsuarioLogueado();
		btnEditarUsuario= (Button)rootView.findViewById(R.id.btnCuenta);
		btnCanjeMasc=(Button)rootView.findViewById(R.id.btnCanjear);
		btnFavoritos=(Button)rootView.findViewById(R.id.btnFavoritos);

		//mKinveyClient = obj.captarUsuarioLogueado();

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





	public void desloguearUsuario (ObjetosBackend obj) {
		//super.onDestroy();
		try{
			mKinveyClient = obj.captarUsuarioLogueado();

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
				txtNombreUsuario.setText(mKinveyClient.user().getUsername());

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

					if (obj.captarUsuarioLogueado().user().isUserLoggedIn()) {
						Intent intent = new Intent(getActivity(), EditarUsuarioActivity.class);
						startActivity(intent);

						if (mKinveyClient.user().isUserLoggedIn()) {
							mensajeConfirmacionDesloguear(obj);
							//desloguearUsuario();

						}
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
		
		btnCanjeMasc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getActivity());  
				dialogo1.setTitle("Canjear mascaras");  
				dialogo1.setMessage("¿Desea canjear sus mascaras?");            
				dialogo1.setCancelable(false);  
				dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {  
					public void onClick(DialogInterface dialogo1, int id) {  
						final UsuarioBackend usuarioLogueado = new UsuarioBackend();
						String idU = mKinveyClient.user().getUsername().toString();
						Query query = mKinveyClient.query ();
						query.equals("username", idU);
						AsyncAppData<UsuarioBackend> searchedEvents = mKinveyClient.appData("Usuario", UsuarioBackend.class);
						searchedEvents.get(query, new KinveyListCallback<UsuarioBackend>() {
							@Override
							public void onSuccess(UsuarioBackend[] resultadoconsulta) { 
								Intent intent = new Intent(getActivity(), NovedadesActivity.class);
								intent.putExtra("mascaras", resultadoconsulta[0].getMascaras());
								Log.e("PerfilFragment mascaras", resultadoconsulta[0].getMascaras());
								startActivity(intent);	
						}
						@Override
						public void onFailure(Throwable arg0) {
							// TODO Auto-generated method stub

						}
					});
					}  
				});  
				dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {  
					public void onClick(DialogInterface dialogo1, int id) {  
						cancelar();
					}  
				});            
				dialogo1.show();        


			}
			

		});
		btnFavoritos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), ObrasFavoritasActivity.class);
				startActivity(intent);
			}

		});
		}
	
	
	}