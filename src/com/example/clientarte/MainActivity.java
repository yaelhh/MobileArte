package com.example.clientarte;



import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	ImageView selectedImage;  
    private Integer[] mImageIds = {
               R.drawable.logo_app,
               R.drawable.novedades,
               R.drawable.nosotros,
             
       };
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.obra_main);
        //setContentView(R.layout.novedades_main);
        setContentView(R.layout.obra_main2);
       
             Gallery gallery = (Gallery) findViewById(R.id.gallery1);
        selectedImage=(ImageView)findViewById(R.id.Gallery02);
        gallery.setSpacing(1);
        gallery.setAdapter(new GalleryImageAdapter(this));

         // clicklistener for Gallery
        gallery.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "Your selected position = " + position, Toast.LENGTH_SHORT).show();
                // show the selected Image
                selectedImage.setImageResource(mImageIds[position]);
            }
        });
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
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
  
}
 


