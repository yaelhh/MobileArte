package com.example.clientarte;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class NovedadesActivity extends ActionBarActivity {
	
	ImageView selectedImage;  
    private Integer[] mImageIds = {
               R.drawable.logo_app,
               R.drawable.novedades,
               R.drawable.nosotros,
             
       };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/*super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_novedades);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.gallery, new PlaceholderFragment()).commit();
		}*/
		
		
	        super.onCreate(savedInstanceState);
	        //setContentView(R.layout.obra_main);
	        //setContentView(R.layout.novedades_main);
	        setContentView(R.layout.activity_obra);
	       
	             Gallery gallery = (Gallery) findViewById(R.id.gallery);
	        selectedImage=(ImageView)findViewById(R.id.iconCompra);
	        gallery.setSpacing(1);
	       // gallery.setAdapter(new GalleryImageAdapter(this));

	         // clicklistener for Gallery
	        gallery.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
	            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	                Toast.makeText(NovedadesActivity.this, "Your selected position = " + position, Toast.LENGTH_SHORT).show();
	                // show the selected Image
	                selectedImage.setImageResource(mImageIds[position]);
	            }
	        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.novedades_main, menu);
		return true;
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
