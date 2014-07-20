package com.example.clientarte;

import java.util.ArrayList;
import java.util.Calendar;

import backend.ComunidadBackend;
import backend.UserLogin;
import backend.UsuarioBackend;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.Query;
import com.kinvey.java.User;
import com.kinvey.java.core.KinveyClientCallback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CalendarView.OnDateChangeListener;

public class CreateAccountActivity extends ActionBarActivity {

	private TextView dateDisplay; 
	private Button pickDate; 
	private int year; 
	private int month; 
	private int day; 
	static final int DATE_DIALOG_ID = 0;
	
	//public static final String TAG = LoginActivity.class.getSimpleName();

	private Client kinveyClient;
	private EditText mEditFirstName;
	private EditText mEditLastName;
	private EditText mEditEmailAddress;
	private EditText mEditPassword;
	private EditText mEditPasswordConfirm;
	private EditText mEditNombreUsuario;
	private Button mRegisterAccount;
	public static final String TAG = "ArteBackend";
	private String appKey="kid_VT8_It3ePE";
	private String appSecret="1b0fa51481984d2da5910f78a9d26ccc";
	private String mensaje;
	private boolean resultado;
	private CalendarView cal;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.activity_create_account, new PlaceholderFragment()).commit();
		
		super.onCreate(savedInstanceState);
		pickDate.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { showDialog(DATE_DIALOG_ID); } });
		final Calendar c = Calendar.getInstance(); 
//		year = c.get(Calendar.YEAR); 
//		month = c.get(Calendar.MONTH); 
//		day = c.get(Calendar.DAY_OF_MONTH);
		setContentView(R.layout.activity_create_account);

		dateDisplay = (TextView) findViewById(R.id.dateDisplay); 
		pickDate = (Button) findViewById(R.id.pickDate); 
		
//		pickDate.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { showDialog(DATE_DIALOG_ID); } });
//		updateDate() ;
		
		conectarBackend();
		confirmarUsuario();
		
		
		/*pickDate.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { showDialog(DATE_DIALOG_ID); } });
		final Calendar c = Calendar.getInstance(); 
		year = c.get(Calendar.YEAR); 
		month = c.get(Calendar.MONTH); 
		day = c.get(Calendar.DAY_OF_MONTH);
		setContentView(R.layout.activity_create_account);

		dateDisplay = (TextView) findViewById(R.id.dateDisplay); 
		pickDate = (Button) findViewById(R.id.pickDate); 
		
		pickDate.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { showDialog(DATE_DIALOG_ID); } });
		 updateDate() ;*/
		
	}
	
	public void confirmarUsuario(){
		mRegisterAccount = (Button) findViewById(R.id.btnRegister);
		mRegisterAccount.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				registrarUsuario();
				processSignup(v);
				limpiarCampos();
			}
			

		});
		
	}

//	private DatePickerDialog mDateSetListener = new DatePickerDialog(mDateSetListener, year, month, day) { 
//
//		public void onDateSet(DatePicker view, int yearOf, int monthOfYear, int dayOfMonth) {
//		year = yearOf; 
//		month = monthOfYear; 
//		day = dayOfMonth; 
//		updateDate();
//		//Show the date on the TextView 
//		}
//	};
//	private void updateDate() { 
//		dateDisplay.setText( 
//				new StringBuilder() 
//				.append(month + 1).append("-")
//				.append(day).append("-") 
//				.append(year).append(" ")); 
//				} 
	
//	private DatePickerDialog onCreateDialog(int id) { 
//		switch (id) { 
//		case DATE_DIALOG_ID: 
//			return new DatePickerDialog(this, mDateSetListener, year, month, day); 
//			} 
//		return null;
//	} 
	
	/*

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_account, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/*
	 * ALTA
	 * BAJA
	 * DE USUARIOS BACKEND
	 * */
	
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
		//mKinveyClient.user().login("nlema", "nlema", new KinveyUserCallback() {
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

	
	private void registrarUsuario() {
		mEditFirstName = (EditText) findViewById(R.id.etFirstName);
		mEditLastName = (EditText) findViewById(R.id.etLastName);
		mEditEmailAddress = (EditText) findViewById(R.id.etEmailAddress);
		mEditPassword = (EditText) findViewById(R.id.etPassword);
		mEditPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
		mEditNombreUsuario = (EditText) findViewById(R.id.nomUsuario);
		cal = (CalendarView) findViewById(R.id.calendarViewCreateAccount);
		
		//kinveyClient = ((UserLogin) getApplication()).getKinveyService();
		//UsuarioBackend entity = new UsuarioBackend (mEditFirstName.getText().toString());
		UsuarioBackend entity = new UsuarioBackend ("Usuario");
		usuarioExiste(mEditNombreUsuario);
		if (!resultado){


			entity.put("nombre", mEditFirstName.getText().toString());
			entity.put("apellido", mEditLastName.getText().toString());
			entity.put("estaLogueado","0");
			entity.put("fechaNacimiento",mEditFirstName.getText().toString());
			entity.put("mascaras","50");
			entity.put("nombreUsuario",mEditNombreUsuario.getText().toString());
			entity.put("fechaNacimiento",cal.getDate());

			kinveyClient.appData("Usuario", UsuarioBackend.class).save(entity, new KinveyClientCallback<UsuarioBackend>() {
				@Override
				public void onSuccess(UsuarioBackend result) {

					Toast.makeText(CreateAccountActivity.this,"Entity Saved\nTitle: " + result.getNombreUsuario()
							+ "\nDescription: " + result.get("Description"), Toast.LENGTH_LONG).show();
				}
			
				@Override
				public void onFailure(Throwable error) {
					Log.e(TAG, "AppData.save Failure", error);
					Toast.makeText(CreateAccountActivity.this, "Save All error: " + error.getMessage(), Toast.LENGTH_LONG).show();
				}
			});
		}else{
			Toast.makeText(CreateAccountActivity.this, "Revise los campos: " + Toast.LENGTH_LONG, 0).show();
		}

	}
	
	private void usuarioExiste(final EditText mEditNombreUsuario) {
		Query myQuery = kinveyClient.query();
		kinveyClient.appData("Usuario", UsuarioBackend.class).get(myQuery, new KinveyListCallback<UsuarioBackend>() {
			
			@Override
			public void onSuccess(UsuarioBackend[] resultadoconsulta) {
				String nu = "";
				for (int i = 0; i < resultadoconsulta.length; i++) {
					Log.d(TAG + "- recuperarUsuario", mensaje);
					nu = resultadoconsulta[i].getNombreUsuario();
				}
				if (mEditNombreUsuario.getText().toString().equals(nu)){
					resultado = false;
				}else{
					resultado = true;
				}

			}
			@Override
			public void onFailure(Throwable error) {
				Log.e(TAG, "AppData.get by Query Failure", error);
			}
		});
		
		
	}
	

	public void registerAccount(View view) {
		if (validateFields()) {
			if (validatePasswordMatch()) {
				processSignup(view);
			} else {
				Toast.makeText(this, "No coinciden las contrasenas", Toast.LENGTH_SHORT).show();
			} 
		} else {
			Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	public void cancelarAccount(View view) {
		//cancelarAccount(view);
		Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
	}
	
	// TODO:  Implement Text Listeners to handle this
		private boolean validateFields() {
			if (mEditFirstName.getText().length()>0 && mEditLastName.getText().length()>0 
					&& mEditEmailAddress.length()>0 && mEditPassword.getText().length()>0 
					&& mEditPasswordConfirm.getText().length()>0) {
				return true;
			} else {
				return false;
			}
		}
		
		private boolean validatePasswordMatch() {
			if (mEditPassword.getText().toString().equals(mEditPasswordConfirm.getText().toString())) {
				return true;
			} else {
				return false;
			}
		}
		
		public void processSignup(View view) {
			Toast.makeText(this, "Registrando usuario...", Toast.LENGTH_SHORT).show();
		    kinveyClient.user().create(mEditEmailAddress.getText().toString(), mEditPassword.getText().toString(), new KinveyUserCallback() {
	            public void onFailure(Throwable t) {
	                CharSequence text = "Could not sign up -> " + t.getMessage();
	                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
	                Log.e(TAG, "Sign-up error", t);
	            }

	            public void onSuccess(User u) {
	            	//registrarUsuario();
	                CharSequence text = "Welcome," + u.get("username") + ".  Your account has been registered.  Please login to confirm your credentials.";
//	                u.put("email", u.get("username"));
//	                u.put("firstname", mEditFirstName.getText().toString());
//	                u.put("lastname", mEditLastName.getText().toString());
	                
	                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
	                CreateAccountActivity.this.startActivity(new Intent(CreateAccountActivity.this, LoginActivity_OLD.class));
	                CreateAccountActivity.this.finish();

	            }
	        });
		}
		
		public void  limpiarCampos(){
			mEditFirstName.setText("");
			mEditLastName.setText("");
			mEditEmailAddress.setText("");
			mEditPassword.setText("");
			mEditPasswordConfirm.setText("");
			mEditNombreUsuario.setText("");
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
