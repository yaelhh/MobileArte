package com.example.clientarte;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
				//Intent intent = new Intent(PrincipalActivity.this, ProgramacionActivity.class);
				//startActivity(intent);
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