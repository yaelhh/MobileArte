package com.example.clientarte;

import com.example.clientarte.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

public class PreferencesFromXml extends PreferenceFragment {
	 
	 
	/*public void onCreate(Bundle savedInstanceState) {
	    /*super.onCreate(savedInstanceState);
	    // Load the preferences from an XML resource
	    addPreferencesFromResource(R.xml.preferences);*/
		  //super.onCreate(savedInstanceState);
		 /* 
	        // Cambiamos el contenido de la Activity por nuestro propio fragment.
	        getFragmentManager().beginTransaction()
	                .replace(android.R.id.content, new PreferenceFragment())
	                .commit();
	  }*/
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
	 
	}