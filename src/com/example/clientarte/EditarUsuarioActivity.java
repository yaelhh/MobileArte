package com.example.clientarte;

import java.util.ArrayList;

import backend.ButacaFuncionSectorBackend;
import backend.ComunidadBackend;
import backend.DatabaseHelper;
import backend.UsuarioBackend;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.java.Query;
import com.kinvey.java.core.KinveyClientCallback;

import dominio.Compra;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EditarUsuarioActivity extends ActionBarActivity {  
	
	private Client kinveyClient;
	public EditText mEditFirstName;
	public EditText mEditLastName;
	public EditText mEditEmailAddress;
	public EditText mEditPassword;
	public EditText mEditPasswordConfirm;
	private Button mRegisterAccount;
	public static final String TAG = "ArteBackend";
	static final int DATE_DIALOG_ID = 0;
	DatabaseHelper dh;
	private Button btnEditarUsuario;
	private Button btnCancelar;

	private TextView mDateDisplay;    
	//private Button mPickDate;    
	private int mYear;    
	private int mMonth;    
	private int mDay;    
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		dh = new DatabaseHelper(getApplicationContext());

		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		kinveyClient = obj.captarUsuarioLogueado();
		//Conexión de la APP a Kinvey
		//kinveyClient = new Client.Builder(EditarUsuarioActivity.this.getApplicationContext()).build();
		kinveyClient.ping(new KinveyPingCallback() {
      			public void onFailure(Throwable t) {
      				Log.e("Probando Kinvey Connection", "Kinvey Ping Failed", t);
      			}
      			public void onSuccess(Boolean b) {
      				Log.d("Probando Kinvey Connection", "Kinvey Ping Success");
      			}
      		});
		
		try{
			obtenerDatosUsuarioEditar(obj, kinveyClient);		
		}catch (Exception io){
			Toast.makeText(getApplicationContext(), "Ups.. no nos pudimos conectar con la base de datos, asegurece tener conexión a internet", Toast.LENGTH_LONG).show();
		}
		
		
		//entity = obj.obtenerUsuarioBackendLogueado();
		
		btnEditarUsuario= (Button)findViewById(R.id.btnRegister);
		btnEditarUsuario.setOnClickListener(new OnClickListener() {  
			@Override
			public void onClick(View v) {
				final UsuarioBackend entity = obj.obtenerUsuarioBackendLogueado();
				editarDatosUsuario(entity);
			}
		});
		
		btnCancelar= (Button)findViewById(R.id.btnCancelarRegistro);
		btnCancelar.setOnClickListener(new OnClickListener() {  
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}	
	
	
	public void obtenerDatosUsuarioEditar(final ObjetosBackend obj, Client kinvey){
		final UsuarioBackend usuarioLogueado = new UsuarioBackend();
		String idU = kinveyClient.user().getUsername().toString();
		Query query = kinveyClient.query ();
		query.equals("username", idU);
		AsyncAppData<UsuarioBackend> searchedEvents = kinveyClient.appData("Usuario", UsuarioBackend.class);
		searchedEvents.get(query, new KinveyListCallback<UsuarioBackend>() {
			@Override
			public void onSuccess(UsuarioBackend[] resultadoconsulta) { 
				for (int i = 0; i < resultadoconsulta.length; i++) {
					Log.e("Obtener datos usuario", resultadoconsulta[i].getNombreUsuario().toString());
					usuarioLogueado.setIdUsuario(resultadoconsulta[i].getIdUsuario().toString());
					usuarioLogueado.setNombreUsuario(resultadoconsulta[i].getNombreUsuario().toString());
					usuarioLogueado.setNombre(resultadoconsulta[i].getNombre().toString());
					usuarioLogueado.setApellido(resultadoconsulta[i].getApellido().toString());
					usuarioLogueado.setPassword(resultadoconsulta[i].getPassword().toString());
					usuarioLogueado.setMascaras(resultadoconsulta[i].getMascaras());
					usuarioLogueado.setFechaNacimiento(resultadoconsulta[i].getFechaNacimiento().toString());
					obj.guardarUsuarioBackendLogueado(usuarioLogueado);
					dibujarCampos(usuarioLogueado);
					
					
					

				}
			}



			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}
		});
		//return usuarioLogueado;
		//return usuarioLogueado;
	}
	
	public void dibujarCampos (UsuarioBackend ub){
		if (ub != null){
			mEditFirstName = (EditText) findViewById(R.id.etFirstName);
			mEditLastName = (EditText) findViewById(R.id.etLastName);
			mEditEmailAddress = (EditText) findViewById(R.id.etEmailAddress);
			mEditPassword = (EditText) findViewById(R.id.etPassword);
			mEditPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
			mDateDisplay = (TextView) findViewById(R.id.dateDisplay);

			mEditFirstName.setText(ub.getNombre().toString());
			mEditLastName.setText(ub.getApellido().toString());
			mEditEmailAddress.setText(ub.getNombreUsuario().toString());
			mEditEmailAddress.setEnabled(false);
			mDateDisplay.setEnabled(false);
			mEditPassword.setEnabled(false);
			mEditPasswordConfirm.setEnabled(false);
			mEditPassword.setText(ub.getPassword().toString());
			mEditPasswordConfirm.setText(ub.getPassword().toString());
			mDateDisplay.setText(ub.getFechaNacimiento().toString());
		}else{
			Toast tIO = Toast.makeText(EditarUsuarioActivity.this,"Ha ocurrido un error cargar usuario, verifique su conexión a la red . ", Toast.LENGTH_SHORT);
			tIO.show();
		}
	}
	
	//public void editarDatosUsuario(UsuarioBackend entity){
	public void editarDatosUsuario(UsuarioBackend entity){
		mEditFirstName = (EditText) findViewById(R.id.etFirstName);
		mEditLastName = (EditText) findViewById(R.id.etLastName);
		mEditEmailAddress = (EditText) findViewById(R.id.etEmailAddress);
		mEditPassword = (EditText) findViewById(R.id.etPassword);
		mEditPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
		//final UsuarioBackend entity = new UsuarioBackend ();
		//usuarioExiste(mEditNombreUsuario);
		//if (!resultado){
		String nombrePila = mEditFirstName.getText().toString();
		String apellido = mEditLastName.getText().toString();
		final String nomUsuario = mEditEmailAddress.getText().toString();
		final String pass = mEditPassword.getText().toString();
		final String fechaNac = mDay+"/"+0+mMonth+"/"+mYear;
//		this.entity.put("nombre", nombrePila);
//		this.entity.put("apellido", apellido);
//		this.entity.put("username",nomUsuario);
//		this.entity.put("password", pass);
//		this.entity.put("fechaNacimiento",fechaNac);
//		this.entity.put("estaLogueado","0");
//		this.entity.put("mascaras","50");
		entity.put("nombre", nombrePila);
		entity.put("apellido", apellido);
		entity.put("username",nomUsuario);
		entity.put("password", pass);
		//entity.put("fechaNacimiento",fechaNac);
		entity.put("estaLogueado","0");
		//entity.put("mascaras","50");
		//obtenerId(entity);
		guardarDatosEdicionUsuario(entity);
	}
	
	
	
	public void guardarDatosEdicionUsuario(final UsuarioBackend entity){
		
		kinveyClient.appData("Usuario",UsuarioBackend.class).getEntity(entity.getIdUsuario(), new KinveyClientCallback<UsuarioBackend>() {

			@Override
			public void onSuccess(UsuarioBackend arg0) {
				//arg0.put("estadoButaca", "1");
				arg0.put("apellido", entity.getApellido());
				arg0.put("estaLogueado", entity.getEstaLogueado());
				arg0.put("fechaNacimiento", entity.getFechaNacimiento());
				arg0.put("mascaras", entity.getMascaras());
				arg0.put("nombre", entity.getNombre());
				arg0.put("password", entity.getPassword());
				arg0.put("username", entity.getNombreUsuario());
				//Log.e("Guardar ButacasCompra","Butacas guardadas en compraButaca "+ arg0.getIdButaca()+"--" +arg0.getIdFuncion()+"--" +arg0.getEstadoButaca() );
				Log.e("Editar usuarios", "Prueba de edicion" + arg0.getNombreUsuario()+"--" +arg0.getNombre()+"--" +arg0.getApellido());
				kinveyClient.appData("Usuario", UsuarioBackend.class).save(arg0, new KinveyClientCallback<UsuarioBackend>() {
					@Override
					public void onSuccess(UsuarioBackend result) {
						Log.e("seteo ","Seteo los datos del usuario");
						dh.actualizarPassUsuarioLogueado(result.getNombreUsuario().toString(), result.getPassword().toString());
						//kinveyClient.user().logout();
						limpiarCampos();
						//llamarLogin();
						
						
//						if(cant==compra.getButacasSeleccionadas().size()-1){
//							guardarCompra(compra,mKinveyClient);
//						}
					}

					@Override
					public void onFailure(Throwable error) {
						Log.e("Error ","Error"+ error);	
					}
				});
			}

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}


		});

		
	}
	
	public void llamarLogin(){
		Intent intent = new Intent(EditarUsuarioActivity.this, LoginActivity.class);
		startActivity(intent);
	}
	
	public void  limpiarCampos(){
		mEditFirstName.setText("");
		mEditLastName.setText("");
		mEditEmailAddress.setText("");
		mEditPassword.setText("");
		mEditPasswordConfirm.setText("");
		//dateDisplay.setText("");
	}


	
	

	
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {                
		public void onDateSet(DatePicker view, int year,                                       
			int monthOfYear, int dayOfMonth) {                    
			mYear = year;                    
			mMonth = monthOfYear + 1;                    
			mDay = dayOfMonth;                    
			updateDisplay();                
		}            
	};
	
	// updates the date in the TextView    
		private void updateDisplay() {        
			mDateDisplay.setText(            
					new StringBuilder()                    
					// Month is 0 based so add 1                    
					.append(mDay).append("/")                    
					.append(mMonth).append("/")
					.append(mYear).append(" "));    
		}
		
		@Override
		protected Dialog onCreateDialog(int id) {    
			switch (id) {   
			case DATE_DIALOG_ID:        
				return new DatePickerDialog(this,                    
						mDateSetListener,                    
						mYear, mMonth, mDay);    
			}    
			return null;
		}
		
		
		/**
		 * A placeholder fragment containing a simple view.
		 */
		public static class PlaceholderFragment extends Fragment {

			public PlaceholderFragment() {
			}


			public View onCreateView(LayoutInflater inflater, ViewGroup container,
					Bundle savedInstanceState) {
				View rootView = inflater.inflate(R.layout.fragment_create_account,
						container, false);
				return rootView;
			}
		}

}
