package com.example.clientarte;



import android.support.v7.app.ActionBarActivity;


import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.Gallery;

import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	ImageView selectedImage;  
    private Integer[] mImageIds = {
               R.drawable.comunidad,
               R.drawable.logo_app,
               R.drawable.nosotros,
             
       };
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.obra_main);
       
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
  
}
 


