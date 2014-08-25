package com.example.clientarte;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;





import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
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
import com.kinvey.java.core.MediaHttpUploader;
import com.kinvey.java.core.MetaUploadProgressListener;
import com.kinvey.java.model.FileMetaData;
import com.kinvey.java.model.KinveyMetaData;

import dominio.Usuario;

public class CreateAccountActivity extends ActionBarActivity {

	private TextView dateDisplay; 
	private Button pickDate; 
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
//	private String appKey="kid_VT8_It3ePE";
//	private String appSecret="1b0fa51481984d2da5910f78a9d26ccc";
	private String mensaje;
	private boolean resultado;
	//private static final int REQUEST_TEXT = 5;

	private Bitmap image;
	public String path = null;
	private Uri mImageCaptureUri;
	public Bitmap bitmap = null;
	private List<UpdateEntity> shareList;
	DatabaseHelper dh;
	private Button btnCrearUsuario;
	private TextView fe; 
	private Button btnCancelar;
	private static final int PICK_FROM_FILE = 2;
	
	/**
	 *CAMPOS PARA FECHA  
	 **/
	

	static final int DATE_DIALOG_ID = 0;

	private TextView mDateDisplay;    
	//private Button mPickDate;    
	private int mYear;    
	private int mMonth;    
	private int mDay;  
	private ProgressBar bar;


	// the callback received when the user "sets" the date in the dialog    
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {                
		public void onDateSet(DatePicker view, int year,                                       
			int monthOfYear, int dayOfMonth) {                    
			mYear = year;                    
			mMonth = monthOfYear + 1;                    
			mDay = dayOfMonth;                    
			updateDisplay();                
		}            
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		dh = new DatabaseHelper(getApplicationContext());
		setTitle("Registrarse");
		bar=(ProgressBar)findViewById(R.id.progressBarRegistrar);
		bar.setIndeterminate(true);
		bar.setVisibility(View.GONE);
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
					//if (validarCamposObligatorios()){
					//processSignup(view);
					registroUsuario(v, obj);
					//limpiarCampos();


				} else {
					Toast.makeText(CreateAccountActivity.this, "No coinciden las contrasenas", Toast.LENGTH_SHORT).show();
				} 
//				}else{
//					Toast.makeText(CreateAccountActivity.this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
//				}
			}
		});
		
//		btnCancelar= (Button)findViewById(R.id.btnCancelarRegistro);
//		btnCancelar.setOnClickListener(new OnClickListener() {  
//			@Override
//			public void onClick(View v) {
//				finish();
//			}
//		});
//		
		//mDateDisplay = (TextView) findViewById(R.id.dateDisplay);   
//		fe = (TextView) findViewById(R.id.textViewCumpleanos);
//		fe.setOnClickListener(new View.OnClickListener()
//		{            
//			public void onClick(View v) {                
//				showDialog(DATE_DIALOG_ID);            
//			}        
//		});  
		fe = (TextView) findViewById(R.id.textViewCumpleanos);
		mDateDisplay = (TextView) findViewById(R.id.dateDisplay);
		mDateDisplay.setOnClickListener(new View.OnClickListener()
		{            
			public void onClick(View v) {                
				showDialog(DATE_DIALOG_ID);            
			}        
		});  
		// get the current date        
		final Calendar c = Calendar.getInstance();        
		mYear = c.get(Calendar.YEAR);        
		mMonth = c.get(Calendar.MONTH);        
		mDay = c.get(Calendar.DAY_OF_MONTH);        
		// display the current date (this method is below)        
		updateDisplay(); 

	}
	
	public boolean validarEdad (){
		int edadIngresada = Integer.parseInt(mDateDisplay.getText().toString());
		boolean retorno = false;
		if (edadIngresada <= 15){
			retorno = false;
		}else{
			retorno = true;
		}
		return retorno;
	}


	public void confirmarUsuario(){
		mRegisterAccount = (Button) findViewById(R.id.btnRegister);
		mRegisterAccount.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				//registrarUsuario();
				//processSignup(v);
				//limpiarCampos();
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
		final String fechaNac = mDay+"/"+0+mMonth+"/"+mYear;
		entity.put("nombre", nombrePila);
		entity.put("apellido", apellido);
		entity.put("username",nomUsuario);
		entity.put("password", pass);
		entity.put("fechaNacimiento",fechaNac);
		entity.put("estaLogueado","0");
		entity.put("mascaras","50");
		//if (validarEdad()){
		if (validarCampos()&& validarFecha()){
			kinveyClient.user().create(entity.getNombreUsuario(), entity.getPassword(), new KinveyUserCallback() {
				@Override
				public void onSuccess(User result) {

					//				Toast.makeText(CreateAccountActivity.this,"Entity Saved\nTitle: " + result.getUsername()
					//						+ "\nDescription: " + result.get("Description"), Toast.LENGTH_LONG).show();


					saveUsuaro (entity);
					loginUsuario(nomUsuario,pass);
					crearUsuarioBase(nomUsuario, pass, 1, fechaNac);
					obj.setmKinveyClient(kinveyClient);
					limpiarCampos();
					finish();

				}

				@Override
				public void onFailure(Throwable error) {
					Log.e(TAG, "AppData.save Failure", error);
					Toast.makeText(CreateAccountActivity.this, "El usuario ya existe, debe ingresar otro nombre de usuario", Toast.LENGTH_LONG).show();
				}
			});
			//}
			//});
		}
	}
	public void saveUsuaro (UsuarioBackend entity){
		kinveyClient.appData("Usuario", UsuarioBackend.class).save(entity, new KinveyClientCallback<UsuarioBackend>() {


			@Override
			public void onSuccess(UsuarioBackend result) {

				Toast.makeText(CreateAccountActivity.this,"Su usuario ha sido creado corretamente. Bienvenid@:" + " " + result.getNombreUsuario()
						, Toast.LENGTH_LONG).show();
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
	
	public boolean validarFecha (){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy");
		String fechaActual = df1.format(c.getTime());
		 
		if  (Integer.parseInt(fechaActual)-mYear>=18){
			return true;
		}else{
			Toast.makeText(CreateAccountActivity.this,"Usted debe ser mayor a 18 años.", Toast.LENGTH_LONG).show();
			return false;
		}
	}
	



	public void crearUsuarioBase(String nomUsuario, String pass, int i, String fechaN){
		Usuario todo = new Usuario (nomUsuario,pass, 1, fechaN);
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
					ObjetosBackend obj = new ObjetosBackend();
					obj.setmKinveyClient(kinveyClient);
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

//	public void cancelarAccount(View view) {
//		//cancelarAccount(view);
//		Intent intent = new Intent(this, PrincipalActivity.class);
//		startActivity(intent);
//	}

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

	private boolean validarCampos() {
		mEditFirstName = (EditText) findViewById(R.id.etFirstName);
		mEditLastName = (EditText) findViewById(R.id.etLastName);
		mEditEmailAddress = (EditText) findViewById(R.id.etEmailAddress);
		mEditPassword = (EditText) findViewById(R.id.etPassword);
		mEditPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
		
		String nomUsuario = mEditEmailAddress.getText().toString();
		String nombrePila = mEditEmailAddress.getText().toString();
		String apellido = mEditEmailAddress.getText().toString();
		String pass = mEditPassword.getText().toString();
		String confirPass = mEditPasswordConfirm.getText().toString();
		if (nomUsuario.isEmpty()|| nombrePila.isEmpty()||apellido.isEmpty()||pass.isEmpty()){
			Toast.makeText(getApplicationContext(), "Debe ingresar todos los datos requeridos.", Toast.LENGTH_SHORT).show();
			return false;
		}else{
			return true;
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
	
//	public boolean validarCamposObligatorios(){
//		mEditFirstName = (EditText) findViewById(R.id.etFirstName);
//		mEditLastName = (EditText) findViewById(R.id.etLastName);
//		mEditEmailAddress = (EditText) findViewById(R.id.etEmailAddress);
//		mEditPassword = (EditText) findViewById(R.id.etPassword);
//		mEditPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
//		mDateDisplay = (TextView)findViewById(R.id.dateDisplay);
//
//		String nombre = mEditFirstName.getText().toString();
//		String apellido = mEditLastName.getText().toString();
//		String nombreUsuario = mEditEmailAddress.getText().toString();
//		String pass = mEditPassword.getText().toString();
//		String confirPass = mEditPasswordConfirm.getText().toString();
//		String fecha = mDateDisplay.getText().toString();
//		//boolean retorno = false;
//
//		if (nombre == "") {return false;}
//		if (apellido == "") {return  false;}
//		if (nombre == "") {return  false;}
//		if (nombreUsuario == "") {return false;}
//		if (pass == "") {return false;}
//		if (confirPass == "") {return false;}
//		if (fecha == "") {return false;}
//
//
//	}

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
		//dateDisplay.setText("");
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

	public List<UpdateEntity> getShareList() {
		return shareList;
	}

	public void setShareList(List<UpdateEntity> shareList) {
		this.shareList = shareList;
	}


	// updates the date in the TextView    
	private void updateDisplay() {        
		mDateDisplay.setText(            
				new StringBuilder()                    
				// Month is 0 based so add 1                    
				.append(mDay).append("/")                    
				.append(mMonth + 1).append("/")
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
     * This method gets a file location from a URI
     *
     * @param contentUri the URI of the image to upload
     * @return the file path of the image
     */
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);

        if (cursor == null) {
            return null;
        }

        String ret = "";
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        if (columnIndex == -1) {
            //picassa bug
            //see: http://jimmi1977.blogspot.com/2012/01/android-api-quirks-getting-image-from.html

            columnIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME);

        }

        cursor.moveToFirst();
        return cursor.getString(columnIndex);
    }
    
    /**
     * This method wraps the code to kick off the "chooser" intent, which allows user to select where to select image from
     */
    public void startFilePicker() {
        Intent intent = new Intent();

        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_FILE);

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

    private void uploadPicture(java.io.File file) {
        final FileMetaData metadata = new FileMetaData();
        KinveyMetaData.AccessControlList acl = new KinveyMetaData.AccessControlList();
        acl.setGloballyReadable(true);
        metadata.setAcl(acl);
        metadata.setFileName(file.getClass().getName());

        kinveyClient.file().upload(metadata, file, new MetaUploadProgressListener() {

            public void metaDataRetrieved(com.kinvey.java.model.FileMetaData meta) {
                Log.i(TAG, "metaDataRetrieved " + meta.getId());
            }

            public void onSuccess(Void result) {
                Log.i(TAG, "successfully upload file " + metadata.getId());
            }

            public void onFailure(Throwable error) {
                Log.e(TAG, "failed to upload file.", error);
            }

            public void progressChanged(MediaHttpUploader uploader) throws IOException {
                Log.i(TAG, "upload progress: " + uploader.getUploadState());
                switch (uploader.getUploadState()) {
                    case INITIATION_STARTED:
                        Log.i(TAG, "Initiation Started");
                        break;
                    case INITIATION_COMPLETE:
                        Log.i(TAG, "Initiation Completed");
                        break;
                    case UPLOAD_IN_PROGRESS:
                        Log.i(TAG, "Upload percentage: " + uploader.getProgress());
                        break;
                    case UPLOAD_COMPLETE:
                        Log.i(TAG, "Upload Completed!");
                        break;
                }
            }
        });
    }
    
}

