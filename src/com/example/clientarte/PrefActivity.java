package com.example.clientarte;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class PrefActivity extends Activity {
	
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	 
	        // Cambiamos el contenido de la Activity por nuestro propio fragment.
	        getFragmentManager().beginTransaction()
	                .replace(android.R.id.content, new PreferencesFromXml())
	                .commit();
	    }
	}


