package com.example.clientarte;


/*import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;*/


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class PerfilActivity extends Activity {
	
	private Button btnCuenta;
	private Button btnProxObras;
	private Button btnObrasVistas;
	private Button btnMascaras;
	private Button btnCanjeMasc;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil);
		
		btnCuenta= (Button)findViewById(R.id.btnCuenta);
		btnProxObras= (Button)findViewById(R.id.btnPrxObras);
		btnObrasVistas= (Button)findViewById(R.id.BtnObrasVistas);
		btnMascaras= (Button)findViewById(R.id.btnMascaras);
		btnCanjeMasc= (Button)findViewById(R.id.btnCanjear);
		
		addListenerOnButton();
    }
    
    
    public void addListenerOnButton() {
		 
    	btnCuenta.setOnClickListener(new OnClickListener() {
    		
 
			@Override
			public void onClick(View v) {
							
				}

		});
    	
    	btnProxObras.setOnClickListener(new OnClickListener() {
    		 
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PerfilActivity.this, ProgramacionActivity.class);
				startActivity(intent);
			}

		});
    	
		
    }
   
}
