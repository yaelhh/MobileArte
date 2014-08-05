package com.example.clientarte;

import java.util.List;

import backend.DatabaseHelper;
import backend.UpdateEntity;
import backend.UsuarioBackend;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.Query;
import com.kinvey.java.User;
import com.kinvey.java.core.KinveyClientCallback;

import dominio.Usuario;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAccountActivity extends ActionBarActivity {

	private TextView dateDisplay; 
	private Button pickDate; 
	private int year; 
	private int month; 
	private int day; 
	static final int DATE_DIALOG_ID = 0;
	//public static final String TAG = LoginActivity.class.getSimpleName();
	private Client kinveyClient;
	public EditText mEditFirstName;
	public EditText mEditLastName;
	public EditText mEditEmailAddress;
	public EditText mEditPassword;
	public EditText mEditPasswordConfirm;
	//private EditText mEditNombreUsuario;
	private Button mRegisterAccount;
	public static final String TAG = "ArteBackend";
	private String appKey="kid_VT8_It3ePE";
	private String appSecret="1b0fa51481984d2da5910f78a9d26ccc";
	private String mensaje;
	private boolean resultado;
	private CalendarView cal;
	//private static final int REQUEST_TEXT = 5;

	private Bitmap image;
	public String path = null;
	private Uri mImageCaptureUri;
	public Bitmap bitmap = null;
	private List<UpdateEntity> shareList;
	//MySQLiteOpenHelper MSQL;
	DatabaseHelper dh;
	private Button btnCrearUsuario;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		//MSQL = new MySQLiteOpenHelper(getApplicationContext());
		dh = new DatabaseHelper(getApplicationContext());
		
		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		//kinveyClient = obj.getUsuKinvey();
		kinveyClient = new Client.Builder(this.getApplicationContext()).build();
		kinveyClient.ping(new KinveyPingCallback() {
		    public void onFailure(Throwable t) {
		        Log.e("Probando Kinvey Connection", "Kinvey Ping Failed", t);
		    }
		    public void onSuccess(Boolean b) {
		        Log.d("Probando Kinvey Connection", "Kinvey Ping Success");
		    }
		});
		
		btnCrearUsuario= (Button)findViewById(R.id.btnRegister);
		btnCrearUsuario.setOnClickListener(new OnClickListener() {  
			@Override
			public void onClick(View v) {
				if (validatePasswordMatch()) {
					//processSignup(view);
					registroUsuario(v, obj);
					limpiarCampos();


				} else {
					Toast.makeText(CreateAccountActivity.this, "No coinciden las contrasenas", Toast.LENGTH_SHORT).show();
				} 
			}
		});



	}


public void confirmarUsuario(){
	mRegisterAccount = (Button) findViewById(R.id.btnRegister);
	mRegisterAccount.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {

			//registrarUsuario();
			//processSignup(v);
			limpiarCampos();
		}


	});

}


private void registroUsuario(View view, final ObjetosBackend obj) {
	mEditFirstName = (EditText) findViewById(R.id.etFirstName);
	mEditLastName = (EditText) findViewById(R.id.etLastName);
	mEditEmailAddress = (EditText) findViewById(R.id.etEmailAddress);
	mEditPassword = (EditText) findViewById(R.id.etPassword);
	mEditPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);

	final UsuarioBackend entity = new UsuarioBackend ();
	//usuarioExiste(mEditNombreUsuario);
	//if (!resultado){
	String nombrePila = mEditFirstName.getText().toString();
	String apellido = mEditLastName.getText().toString();
	final String nomUsuario = mEditEmailAddress.getText().toString();
	final String pass = mEditPassword.getText().toString();

	entity.put("nombre", nombrePila);
	entity.put("apellido", apellido);
	entity.put("username",nomUsuario);
	entity.put("password", pass);
	entity.put("fechaNacimiento",pass);
	entity.put("estaLogueado","0");
	entity.put("mascaras","50");

	kinveyClient.user().create(entity.getNombreUsuario(), entity.getPassword(), new KinveyUserCallback() {
		@Override
		public void onSuccess(User result) {

			Toast.makeText(CreateAccountActivity.this,"Entity Saved\nTitle: " + result.getUsername()
					+ "\nDescription: " + result.get("Description"), Toast.LENGTH_LONG).show();

			saveUsuaro (entity);
			loginUsuario(nomUsuario,pass);
			crearUsuarioBase(nomUsuario, pass, 1);
			obj.setmKinveyClient(kinveyClient);
		}

		@Override
		public void onFailure(Throwable error) {
			Log.e(TAG, "AppData.save Failure", error);
			Toast.makeText(CreateAccountActivity.this, "Save All error: " + error.getMessage(), Toast.LENGTH_LONG).show();
		}
	});
	//});
	}

public void saveUsuaro (UsuarioBackend entity){
	kinveyClient.appData("Usuario", UsuarioBackend.class).save(entity, new KinveyClientCallback<UsuarioBackend>() {
		
		
		@Override
		public void onSuccess(UsuarioBackend result) {

			Toast.makeText(CreateAccountActivity.this,"Revoluciones: " + result.getNombreUsuario()
					+ "\nDescription: " , Toast.LENGTH_LONG).show();

			
		}

		@Override
		public void onFailure(Throwable error) {
			Log.e(TAG, "AppData.save Failure", error);
			Toast.makeText(CreateAccountActivity.this, "Save All error: " + error.getMessage(), Toast.LENGTH_LONG).show();
		}
	});
	//});
	
	
}

public void loginBaseUsuario (String nombre, String pass){
	kinveyClient = new Client.Builder(nombre, pass, this).build();
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



public void crearUsuarioBase(String nomUsuario, String pass, int i){
	Usuario todo = new Usuario (nomUsuario,pass, 1 );
	dh.createUsuario(todo);   
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


//public void registerAccount(View view) {
//	//if (validateFields()) {
//	if (validatePasswordMatch()) {
//		//processSignup(view);
//		registroUsuario(view);
//		limpiarCampos();
//
//
//	} else {
//		Toast.makeText(this, "No coinciden las contrasenas", Toast.LENGTH_SHORT).show();
//	} 
	//		} else {
	//			Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
	//		}



	private void loginUsuario(String nombreUsuario, String pass) {
	final String nombreUsuarioUno = nombreUsuario;
	final String passUno = pass;	
	if (!kinveyClient.user().isUserLoggedIn()) {
		//Si no está "logeado" se realiza el login
		kinveyClient.user().login(nombreUsuarioUno, passUno, new KinveyUserCallback() {
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
					Usuario usuLogueado = new Usuario (nombreUsuarioUno, passUno, 1);
					Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
					intent.putExtra("username",usuLogueado);
					startActivity(new Intent(CreateAccountActivity.this, MainActivity.class));
				}
			});
		} else {
			mensaje = "Utilizando usuario cacheado: " + kinveyClient.user().getUsername() + ".";
			Log.d("Realizando Kinvey Login", mensaje);
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
	mEditFirstName = (EditText) findViewById(R.id.etFirstName);
	mEditLastName = (EditText) findViewById(R.id.etLastName);
	mEditEmailAddress = (EditText) findViewById(R.id.etEmailAddress);
	mEditPassword = (EditText) findViewById(R.id.etPassword);
	mEditPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
	String pass = mEditPassword.getText().toString();
	String confirPass = mEditPasswordConfirm.getText().toString();
	if (pass.equals(confirPass)) {
		return true;
	} else {
		return false;
	}
}

//		public void processSignup(View view) {
//			//registrarUsuario();
//			Toast.makeText(this, "Registrando usuario...", Toast.LENGTH_SHORT).show();
//		    kinveyClient.user().create(mEditEmailAddress.getText().toString(), mEditPassword.getText().toString(), new KinveyUserCallback() {
//	            public void onFailure(Throwable t) {
//	                CharSequence text = "Could not sign up -> " + t.getMessage();
//	                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
//	                Log.e(TAG, "Sign-up error", t);
//	            }
//
//	            public void onSuccess(User u) {
//	            	//registrarUsuario();
//	                CharSequence text = "Welcome," + u.get("username") + ".  Your account has been registered.  Please login to confirm your credentials.";
////	                u.put("email", u.get("username"));
////	                u.put("firstname", mEditFirstName.getText().toString());
////	                u.put("lastname", mEditLastName.getText().toString());
//	                
//	                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
//	                CreateAccountActivity.this.startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class));
//	                CreateAccountActivity.this.finish();
//
//	            }
//	        });
//		}

public void  limpiarCampos(){
	mEditFirstName.setText("");
	mEditLastName.setText("");
	mEditEmailAddress.setText("");
	mEditPassword.setText("");
	mEditPasswordConfirm.setText("");
	//mEditNombreUsuario.setText("");
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

public static int calculateInSampleSize(
		BitmapFactory.Options options, int reqWidth, int reqHeight) {
	// Raw height and width of image
	final int height = options.outHeight;
	final int width = options.outWidth;

	int stretch_width = Math.round((float) width / (float) reqWidth);
	int stretch_height = Math.round((float) height / (float) reqHeight);

	if (stretch_width <= stretch_height) return stretch_height;
	else return stretch_width;
}

public List<UpdateEntity> getShareList() {
	return shareList;
}

public void setShareList(List<UpdateEntity> shareList) {
	this.shareList = shareList;
}



}