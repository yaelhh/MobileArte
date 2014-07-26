package com.example.clientarte;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Intents;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import dominio.Butaca;
import dominio.Funcion;
import dominio.Obra;
import dominio.Sala;
import dominio.Sector;
import dominio.Usuario;


public class CompraActivity extends Activity {

	TextView lblPulsado;
	GridView grdOpciones;
	Object click_item;
	GridView gridviewSectorA;
	GridView gridviewSectorB;

	Button bttnSectorA;
	Button bttnSectorB;
	Button bttnSectorC;
	Button btnComprar;
	Dialog customDialog;

	ArrayAdapter<String> adaptador;
	ImageButton butaca;
	Sector sector;
	Integer cantButaca;
	ArrayList<Butaca> listabutacas;
	ArrayList<Sector> listSectores= new ArrayList<Sector>();
	private HashMap<String, Boolean> hashComodidades = new HashMap <String, Boolean> ();	
	private Sala sala;
	private Butaca miButaca;
	private Objetos obj;
	private Obra obra;
	private Sector sectorA;
	private Sector sectorB;
	private Sector sectorC;
	private EditText cantEntradas;
	private Usuario usuario;
	private int cant;
	private Boolean yaSeleccionada=false;
	private static final int REQUEST_TEXT = 1;
	int precioTotal=0;
	ArrayList<Butaca> butacasSeleccionadas= new ArrayList<Butaca>();
	Funcion funcionSeleccionada;
	Spinner sprHorario;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compra);
		sala=new Sala();
		obj= new Objetos();
		sala= obj.getListSalas().get(0);
		obra= new Obra();
		obra= getIntent().getParcelableExtra("obra");
		TextView titulo= (TextView)findViewById(R.id.idTitulo);
		titulo.setText(obra.getNombre());
		sprHorario= (Spinner)findViewById(R.id.spinner_horario);
		ArrayList<Funcion>lista= obra.getListaFunciones();	
		//Creamos el adaptador
		ArrayAdapter<Funcion> spinner_adapter = new ArrayAdapter<Funcion>(this,android.R.layout.simple_spinner_item, lista);
		sprHorario.setAdapter(spinner_adapter);
		funcionSeleccionada= (Funcion) sprHorario.getOnItemSelectedListener();
		Log.e("funcion seleccionada",""+ funcionSeleccionada);
		TextView txtSala= (TextView)findViewById(R.id.sala);
		txtSala.setText(sala.getNombreSala());
		cantEntradas=(EditText)findViewById(R.id.idCantidadEntradas);
		//        cant=cantEntradas.getText().toString();
		usuario= new Usuario();

		//El cantButaca va a ser de acuerdo al sector que estemos creando, aca lo inicializamos asi
		bttnSectorA=(Button)findViewById(R.id.sectorA);
		bttnSectorB=(Button)findViewById(R.id.sectorB);
		bttnSectorC=(Button)findViewById(R.id.sectorC);
		btnComprar=(Button)findViewById(R.id.button_comprar);
		//		btnComprar.setClickable(false);
		sectorA= new Sector();
		sectorA=sala.getListaSectores().get(0);
		sectorB= new Sector();
		sectorB=sala.getListaSectores().get(1);
		sectorC= new Sector();
		sectorC=sala.getListaSectores().get(2);
		bttnSectorA.setText("Precio "+ sectorA.getPrecioSector());
		bttnSectorB.setText("Precio "+ sectorB.getPrecioSector());
		bttnSectorC.setText("Precio "+ sectorC.getPrecioSector());

		//		bttnSectorA.setOnClickListener((OnClickListener) entrarSector());		

		//		crearObjetos();
		//		addListenerOnButton();
		sprHorario.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> a, View v,int position, long id) {
				funcionSeleccionada=(Funcion) a.getItemAtPosition(position);
				Log.e("funcion seleccionada",""+ funcionSeleccionada);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				lblPulsado.setText("");
			}

		});



	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String contents = data.getStringExtra("SCAN_RESULT");
				String format = data.getStringExtra("SCAN_RESULT_FORMAT");
				// Handle successful scan
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
			}
		} 
		if ( requestCode == REQUEST_TEXT ){
			if ( resultCode == Activity.RESULT_OK ){

				yaSeleccionada= data.getExtras().getBoolean("yaSeleccionadas");



				Log.e("yaSeleciconada", "" + yaSeleccionada);
			}
		}

	}

	//	yaSeleccionada= data.getBooleanExtra("yaSeleccionada",false);
	//	Log.e("yaSeleciconada", "" + yaSeleccionada);

	//  	private void crearObjetos() {
	//  		//Crear sector
	//  		sector = new Sector();
	//		sector.setIdSector(1); 
	//		sector.setTotalButacas(24);
	//		
	//		//Crear las butacas del sector 
	//		listabutacas = new ArrayList<Butaca>();
	//		for (int i = 0; i < sector.getTotalButacas(); i ++) {
	//			miButaca = new Butaca();
	//			miButaca.setIdButaca(i);
	//			miButaca.setEstadoButaca(true);
	//			listabutacas.add(miButaca);
	//			//Log.d("Creación de la lista de butacas", "ID Butaca: " + Integer.toString(butaca.getIdButaca()));
	//		}
	//		//Agrego la lista de butacas al sector
	//		sector.setListaButacas(listabutacas);
	//		
	//
	//		miSala= new Sala(1, "Nombre Sala",listSectores,hashComodidades, 200);
	//		listSectores.add(miSector);
	//		miSala.setListaSectores(listSectores);
	////		Log.e("Mi sala 1",Integer.toString(miSala.getIdSala()));		
	//	}

	public void entrarSector(View v){
		cantEntradas=(EditText)findViewById(R.id.idCantidadEntradas);
		String s=cantEntradas.getText().toString();
		if(!s.equals("")){
			cant= Integer.parseInt(cantEntradas.getText().toString());
			Intent intent = new Intent(CompraActivity.this, SectorAActivity.class);
			intent.putExtra("cantEntrada", cant);
			Log.e("v.getId "+ v.getId(), " A "+R.id.sectorA +" B "+R.id.sectorB +" C "+R.id.sectorC );
			switch (v.getId()){
			case R.id.sectorA:
				intent.putExtra("sector",sectorA );
				break;
			case R.id.sectorB:
				intent.putExtra("sector",sectorB );	
				break;
			case R.id.sectorC:
				intent.putExtra("sector",sectorC );
				break;
			}	
			//			startActivity(intent);
			CompraActivity.this.startActivityForResult(intent, REQUEST_TEXT);

		}else{
			Toast.makeText(this,"Debe seleccionar la cantidad de entradas a seleccionar" , Toast.LENGTH_SHORT).show();
		}


	}


	public void realizarCompra(View v)  {

		Log.e("funcion seleccionada",""+ funcionSeleccionada);

		if(yaSeleccionada){
			if(usuario!= null){
				//			Date dt = new Date();
				//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				//			String formatteDate = df.format(dt.getDate());
				//			Compra compra= new Compra(formatteDate,obra,false,usuario,precioTotal,funcionSeleccionada,butacasSeleccionadas);

//						Intent intent = new Intent(CompraActivity.this, CompraRealizadaActivity.class);
				//			intent.putExtra("Compra", compra);
//						startActivity(intent);
				//			Intent intent = new Intent("com.google.zxing.client.android.ENCODE");
				//			intent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
				//			intent.putExtra("ENCODE_DATA", "Hola ZOMWI!!!");
				//			startActivity(intent);
//				Intent emailIntent = new Intent(Intent.ACTION_SEND);
//		        emailIntent.setData(Uri.parse("mailto:"));
//				emailIntent.putExtra(Intent.EXTRA_EMAIL, "yaelhh@gmail.com");
//		       
//		        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Su compra ha sido realizada");
//		        emailIntent.putExtra(Intent.EXTRA_TEXT, "Felicidades por su compra");
//		        emailIntent.setType("message/rfc822");
//			    startActivity(Intent.createChooser(emailIntent, "Email "+intent));
	        
				String s= "El nombre de la obra es: "+obra.getNombre() + " la funcion elegida es  "+obra.getListaFunciones().get(0)+" y la cantidad de entradas elegidas es "+cantEntradas.getText().toString();
				Funcion funcionElegida=obra.getListaFunciones().get(0);
				String fecha= funcionElegida.getFechaObra();
				String hora= funcionElegida.getHoraComienzo();
				String duracion= Double.toString(funcionElegida.getDuracion());
				Intent intent = new Intent("com.google.zxing.client.android.ENCODE");
			    intent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
			    intent.putExtra("ENCODE_DATA", s);
			    intent.putExtra("fecha", fecha);
			    intent.putExtra("hora", hora);
			    intent.putExtra("duracion", duracion);
			    intent.putExtra("tituloObra", obra.getNombre());

			    startActivity(intent);
				

				
			}else{
				AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);  
				dialogo1.setTitle("Importante");  
				dialogo1.setMessage("Para continuar la compra necesita estar logueado,¿Desea loguearse?");            
				dialogo1.setCancelable(false);  
				dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {  
					public void onClick(DialogInterface dialogo1, int id) {  
						Intent intent = new Intent(CompraActivity.this, LoginActivity.class);
						startActivity(intent);

					}  
				});  
				dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {  
					public void onClick(DialogInterface dialogo1, int id) {  
						cancelar();
					}  
				});            
				dialogo1.show();        


			}
		}else{
			Toast.makeText(this,"Debe seleccionar las butacas antes de continuar" , Toast.LENGTH_SHORT).show();

		}
	}

	public void aceptar() {
		Toast t=Toast.makeText(this,"Bienvenido a probar el programa.", Toast.LENGTH_SHORT);
		t.show();
	}

	public void cancelar() {
		finish();
	}

//	private void enviar(String[] to, String[] cc, String asunto, String mensaje) {
//	        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//	        emailIntent.setData(Uri.parse("mailto:"));
//	        //String[] to = direccionesEmail;
//	        //String[] cc = copias;
//	        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
//	        emailIntent.putExtra(Intent.EXTRA_CC, cc);
//	        emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
//	        emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
//	        emailIntent.setType("message/rfc822");
//	        startActivity(Intent.createChooser(emailIntent, "Email "));
//	    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compra, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void addListenerOnButton() {
		//		 final boolean bol = false;


		bttnSectorA.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CompraActivity.this, SectorAActivity.class);
				cantEntradas=(EditText)findViewById(R.id.idCantidadEntradas);
				//				cant= cantEntradas.getText().toString();
				intent.putExtra("cantEntrada",cant );
				intent.putExtra("sector",sectorA ); 
				CompraActivity.this.startActivityForResult(intent, REQUEST_TEXT);
				//				startActivity(intent);				    
			}

		});
		bttnSectorB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CompraActivity.this, SectorAActivity.class);
				intent.putExtra("cantEntrada", cantEntradas.getText().toString());
				intent.putExtra("sector", sectorB); 
				CompraActivity.this.startActivityForResult(intent, REQUEST_TEXT);

				//				startActivity(intent);				    
			}

		});
		bttnSectorC.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CompraActivity.this, SectorAActivity.class);
				intent.putExtra("cantEntrada", cantEntradas.getText().toString());
				intent.putExtra("sector",sectorC); 
				CompraActivity.this.startActivityForResult(intent, REQUEST_TEXT);

				//				startActivity(intent);				    
			}

		});
		btnComprar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(usuario.getLogueado()){
					Intent intent = new Intent(CompraActivity.this, SectorAActivity.class);
					intent.putExtra("usuario",usuario);			
					startActivity(intent);
				}else{
					Intent intent = new Intent(CompraActivity.this, LoginActivity.class);

					startActivity(intent);

				}		


			}

		});

	}
}
