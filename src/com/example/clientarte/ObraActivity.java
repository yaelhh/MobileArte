package com.example.clientarte;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class ObraActivity extends ActionBarActivity {
	
	SparseArray<GrupoDeItems> grupos = new SparseArray<GrupoDeItems>();
	ListView listView ;
	ImageView selectedImage;  
    private Integer[] mImageIds = {
               R.drawable.logo_app,
               R.drawable.novedades,
               R.drawable.nosotros,
             
       };
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_obra);
        
        Gallery gallery = (Gallery) findViewById(R.id.gallery);
        selectedImage=(ImageView)findViewById(R.id.iconCompra);
        gallery.setSpacing(1);
        gallery.setAdapter(new GalleryImageAdapter(this));

         // clicklistener for Gallery
        gallery.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(ObraActivity.this, "Your selected position = " + position, Toast.LENGTH_SHORT).show();
                // show the selected Image
                selectedImage.setImageResource(mImageIds[position]);
                addListenerOnButton();
            }
        });
       
        crearDatos();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listViewexp);
        Adaptador adapter = new Adaptador(this, grupos);
        listView.setAdapter(adapter);
        
       
    }
        
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
     /*
        getMenuInflater().inflate(R.menu.main, menu);
        return true;*/
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
      case R.id.action_settings:
              Intent intent = new Intent(this, Settings.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
              startActivity(intent);
              break;
      /*case R.id.action_settings:
        Toast.makeText(this, "Reloading parkings...", Toast.LENGTH_LONG)
            .show();
        refresh();
        break;*/
     
      /*default:
        break;*/
      }
	return false;
    }
    
    public void crearDatos() {
        GrupoDeItems grupo0 = new GrupoDeItems("Lunes 16/06");
        grupo0.children.add("20:00 - Sala 1");
        grupo0.children.add("22:00 - Sala 1");
        grupos.append(0, grupo0);
        GrupoDeItems grupo1 = new GrupoDeItems("Lunes 23/06");
        grupo1.children.add("22:00 - Sala 2");
        grupo1.children.add("00:00 - Sala 2");
        grupo1.children.add("01:30 - Sala 3");
        grupos.append(1, grupo1);
        GrupoDeItems grupo2 = new GrupoDeItems("Lunes 30/06");
        grupo2.children.add("19:00 - Sala 5");
        grupo2.children.add("23:00 - Sala 5");
        //grupo2.children.add("20:00 - Sala 1");
        grupos.append(2, grupo2);
     }
    
    public void addListenerOnButton() {
		 
    	selectedImage.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ObraActivity.this, CompraActivity.class);
				startActivity(intent);
			}

		});
}
  
}