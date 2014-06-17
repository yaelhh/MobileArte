package com.example.clientarte;



//import java.util.ArrayList;

//import android.app.ActionBar;
//import android.app.Activity;
//import android.content.Context;
import com.example.clientarte.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.ActionBarDrawerToggle;
//import android.support.v4.widget.DrawerLayout;
//import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
//import android.widget.ListView;
import android.widget.Toast;


//import com.example.clientarte.MainActivity;


public class PrincipalActivity extends Activity implements OnQueryTextListener{

private ImageButton botonprogramacion, botonComunidad, botonNosotros, botonNovedades;
private SearchView mSearchView;


//	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH) @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
  
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
				Intent intent = new Intent(PrincipalActivity.this, ProgramacionActivity.class);
				startActivity(intent);
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
        
        return true;
    }
	
}
