package com.example.clientarte;



//import java.util.ArrayList;

//import android.app.ActionBar;
//import android.app.Activity;
//import android.content.Context;
import backend.ObraBackend;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.java.Query;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
//import android.support.v4.app.ActionBarDrawerToggle;
//import android.support.v4.widget.DrawerLayout;
//import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
//import android.widget.ListView;
import android.widget.Toast;


//import com.example.clientarte.MainActivity;


public class PrincipalActivity extends Activity implements OnQueryTextListener{

	private Client mKinveyClient;
private static final int REQUEST_TEXT = 5;
private ImageButton botonprogramacion, botonComunidad, botonNosotros, botonNovedades;
private SearchView mSearchView;
Fragment fragment = null;


//	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH) @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_pricipal);
		
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
		
		
		
  
		botonprogramacion = (ImageButton) findViewById(R.id.imageProgramacion);
		botonComunidad= (ImageButton) findViewById(R.id.ImageComunidad);

		botonNosotros=(ImageButton) findViewById(R.id.ImageNosotros);
		botonNovedades =(ImageButton) findViewById(R.id.ImageNovedades);
		
		addListenerOnButton();
		
	}
	
	public void addListenerOnButton() {
		 
		botonprogramacion.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(PrincipalActivity.this, ProgramacionFragment.class);
//				startActivity(intent);
				fragment = new ProgramacionFragment();
			}

		});
		botonComunidad.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PrincipalActivity.this, PerfilActivity.class);
				startActivity(intent);
			}	
		
		});
		
		botonNosotros.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PrincipalActivity.this, ComunidadFragment.class);
				startActivity(intent);
			}
			
		});
		botonNovedades.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PrincipalActivity.this, NovedadesActivity.class);
				startActivity(intent);
			}
			
		});
		
			
}
	@Override
	public boolean onQueryTextChange(String newText) {

	    Toast.makeText(this, newText, Toast.LENGTH_SHORT).show();

	    return false;
	}
	@Override
	public boolean onQueryTextSubmit(String text) {

	    Toast.makeText(this, "Searching for " + text, Toast.LENGTH_LONG).show();

	    return false;
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        
        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchItem.getActionView();
        mSearchView.setQueryHint("Search…");
        mSearchView.setOnQueryTextListener(this);
        EditText searchBar = (EditText) findViewById(R.id.search_bar);
        Query query = new Query();
        query.regEx("name", searchBar.getText().toString());
        AsyncAppData<ObraBackend> searchedEvents = mKinveyClient.appData("events", ObraBackend.class);
        searchedEvents.get(query, new KinveyListCallback<ObraBackend>() {
          @Override
          public void onSuccess(ObraBackend[] event) { 
          }

		@Override
		public void onFailure(Throwable arg0) {
			// TODO Auto-generated method stub
			
		}
        });
        
        return true;
    }
	
	 @Override 
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
	
}
