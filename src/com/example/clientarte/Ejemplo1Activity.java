package com.example.clientarte;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class Ejemplo1Activity extends Activity {

	private TextView lblMensaje;
	private GridView grdOpciones;

	private String[] datos = new String[25];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo1);
        
        for(int i=1; i<=25; i++)
        	datos[i-1] = "Dato " + i;
          
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.activity_list_item, datos);
        
       // lblMensaje = (TextView)findViewById(R.id.LblMensaje);
        grdOpciones = (GridView)findViewById(R.id.GridOpciones);
        
        grdOpciones.setOnItemClickListener(
            	new AdapterView.OnItemClickListener() {
        		public void onItemClick(AdapterView<?> parent,
        			android.view.View v, int position, long id) {
           				lblMensaje.setText("Opción seleccionada: " + datos[position]);
        		}
        });
        
        grdOpciones.setAdapter(adaptador);
    }

    /*
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.activity_ejemplo1, menu);
        return true;
    }*/
}
