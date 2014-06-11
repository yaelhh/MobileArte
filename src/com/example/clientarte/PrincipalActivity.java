package com.example.clientarte;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class PrincipalActivity extends Activity {

private ImageButton botonprogramacion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		botonprogramacion = (ImageButton) findViewById(R.id.imageProgramacion);
		addListenerOnButton();
	}
	
	public void addListenerOnButton() {
		 
		botonprogramacion.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PrincipalActivity.this, ProgramacionActivity.class);
				startActivity(intent);
			}

		});
		
	}
	
}
