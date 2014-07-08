package com.example.clientarte;

import android.app.Activity;
import android.os.Bundle;

public class PrefActivity extends Activity {
	
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	 
	        // Cambiamos el contenido de la Activity por nuestro propio fragment.
	        getFragmentManager().beginTransaction()
	                .replace(android.R.id.content, new PreferencesFromXml())
	                .commit();
	    }
	}


