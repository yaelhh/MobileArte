package com.example.clientarte;



import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import backend.ButacaFuncionSectorBackend;
import backend.CompraBackend;
import backend.CompraButacasBackend;
import backend.UsuarioBackend;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.java.Query;
import com.kinvey.java.core.DownloaderProgressListener;
import com.kinvey.java.core.KinveyClientCallback;
import com.kinvey.java.core.MediaHttpDownloader;

import dominio.Butaca;
import dominio.Compra;
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
	private TextView textPrecioTotal;
	private int cantSeleccionadas;
	private ProgressBar bar;
	private TextView txtCargando;
	private Client mKinveyClient;
	private int mascaras=0;
	private int Descuento=0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compra);
		
		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		obra= new Obra();
		obra= getIntent().getParcelableExtra("obra");
		mascaras=getIntent().getExtras().getInt("mascaras");
		Descuento=mascaras*3;
		setTitle(obra.getNombre());
		Log.e("Precio total con mascaras ", precioTotal+" MAscaras "+ mascaras);
		TextView titulo= (TextView)findViewById(R.id.idTitulo);
		titulo.setText(obra.getNombre());
		sprHorario= (Spinner)findViewById(R.id.spinner_horario);
		bar=(ProgressBar)findViewById(R.id.progressBarCompra);
		bar.setIndeterminate(true);
		bar.setVisibility(View.GONE);
		ArrayList<Funcion>lista= obra.getListaFunciones();	
		//Creamos el adaptador
		ArrayAdapter<Funcion> spinner_adapter = new ArrayAdapter<Funcion>(this,android.R.layout.simple_spinner_item, lista);
		sprHorario.setAdapter(spinner_adapter);
		funcionSeleccionada= (Funcion) sprHorario.getOnItemSelectedListener();
		textPrecioTotal=(TextView)findViewById(R.id.idTotal);
		textPrecioTotal.setText(String.valueOf(precioTotal));
		TextView txtSala= (TextView)findViewById(R.id.sala);
		sala= obtenerSala(obj);
		txtSala.setText(sala.getNombreSala());
		cantEntradas=(EditText)findViewById(R.id.idCantidadEntradas);
		usuario= new Usuario();

		//El cantButaca va a ser de acuerdo al sector que estemos creando, aca lo inicializamos asi
		bttnSectorA=(Button)findViewById(R.id.sectorA);
		bttnSectorB=(Button)findViewById(R.id.sectorB);
		bttnSectorC=(Button)findViewById(R.id.sectorC);
		btnComprar=(Button)findViewById(R.id.button_comprar);
		//		btnComprar.setClickable(false);
		
			realizarCompra(obj,bar);
		
		


		sprHorario.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> a, View v,int position, long id) {
				funcionSeleccionada=(Funcion) a.getItemAtPosition(position);
				precioTotal=0;
				crearSectores(funcionSeleccionada);


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
				Intent intent = new Intent(CompraActivity.this, MainActivity.class);
				Toast.makeText(this,"Su compra ha sido realizada, para saber los detalles dirijase a ver compras realizadas" , Toast.LENGTH_SHORT).show();

				startActivity(intent);
				// Handle successful scan
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
			}
		} 
		if ( requestCode == REQUEST_TEXT ){
			if ( resultCode == Activity.RESULT_OK ){
				Sector s = new Sector();
				s=data.getExtras().getParcelable("sectorElegido");
				for(int x=0;x<funcionSeleccionada.getListaSetores().size();x++){
					if(s.getIdSector()==funcionSeleccionada.getListaSetores().get(x).getIdSector()){
						switch (x){
						case 0:
							sectorA=s;
							break;
						case 1:
							sectorB=s;
							break;
						case 2:
							sectorC=s;
							break;				
						}
					}
				}
				cantSeleccionadas-= data.getExtras().getInt("cantSeleccionadas");
				//				cant-=cantSeleccionadas;
				yaSeleccionada= data.getExtras().getBoolean("yaSeleccionadas");
				precioTotal+= data.getExtras().getInt("precioTotal");
				//				butacasSeleccionadas=data.getExtras().getParcelableArrayList("ButacasSeleccionadas");
				textPrecioTotal.setText(String.valueOf(precioTotal));

				//				Log.e("butacasSeleccionadas", "" + butacasSeleccionadas.get(0));
			}
		}

	}



	public Sala obtenerSala(ObjetosBackend obj){
		Sala sala= new Sala();
		ArrayList<Sala> listSalas= new ArrayList<Sala>();
		listSalas= obj.getListSalas();
		for(int x=0;x<listSalas.size();x++){
			Sala salaActual= listSalas.get(x);
			for(int y=0;y<salaActual.getListaObras().size();y++){
				Obra obraActual= salaActual.getListaObras().get(y);
				if(obra.getIdObra()==obraActual.getIdObra()){
					return listSalas.get(x);
				}
			}
		}
		return sala;
	}

	public void crearSectores(Funcion funcion){
		//		LinearLayout contendedor=(LinearLayout)findViewById(R.id.contenedorSectores);
		for(int x=0;x<funcion.getListaSetores().size();x++){
			sectorA= new Sector();
			sectorA=funcion.getListaSetores().get(0);
			sectorB= new Sector();
			sectorB=funcion.getListaSetores().get(1);
			sectorC= new Sector();
			sectorC=funcion.getListaSetores().get(2);
			bttnSectorA.setText("Precio "+ sectorA.getPrecioSector());
			bttnSectorB.setText("Precio "+ sectorB.getPrecioSector());
			bttnSectorC.setText("Precio "+ sectorC.getPrecioSector());
			//			Button bttnSector=new Button(this);
			//			bttnSector.setId(x);
			//			bttnSector.setWidth(sector.getLinea()*100);
			//			bttnSector.setHeight(sector.getLinea()*50);
			//			bttnSector.setBackgroundColor(new Random().nextInt());
			//			bttnSector.setText("Precio "+ sector.getPrecioSector());
			//			contendedor.addView(bttnSector);
			Toast.makeText(this,funcion.getIdFuncion()+" " , Toast.LENGTH_SHORT).show();

		}




	}

	public void entrarSector(View v){
		cantEntradas=(EditText)findViewById(R.id.idCantidadEntradas);
		String s=cantEntradas.getText().toString();
		if(!s.equals("")){
			cant= Integer.parseInt(cantEntradas.getText().toString());
			if(!yaSeleccionada){
				cantSeleccionadas=cant;
			}
			if(cant>0 ){
				Intent intent = new Intent(CompraActivity.this, SectorAActivity.class);
				intent.putExtra("cantEntrada", cantSeleccionadas);
				intent.putExtra("funcion", funcionSeleccionada);
				//			Log.e("v.getId "+ v.getId(), " A "+R.id.sectorA +" B "+R.id.sectorB +" C "+R.id.sectorC );
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
				Toast.makeText(this,"Debe ingresar un número mayor a 0" , Toast.LENGTH_SHORT).show();

			}
		}else{
			Toast.makeText(this,"Debe seleccionar la cantidad de entradas a seleccionar" , Toast.LENGTH_SHORT).show();
		}


	}


	public void realizarCompra(final ObjetosBackend obj,ProgressBar b)  {
		final ObjetosBackend o=obj;
		final ProgressBar bar=b;
		mKinveyClient=o.getmKinveyClient();
		//		Log.e("funcion seleccionada",""+ funcionSeleccionada);
		btnComprar.setOnClickListener(new OnClickListener() { 
			@Override
			public void onClick(View v) {
				//Se verifica que se hayan seleccionado las butacas
				if(cant>0 && cantSeleccionadas==0){
					//Se verifica que el usuario este logueado
					usuario=new Usuario();
					usuario.setMiNombreUsuario(o.captarUsuarioLogueado().user().getUsername());
					obtenerButacasSeleccionadas();
					Calendar c = Calendar.getInstance();
					SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
					String fechaActual = df1.format(c.getTime());
					//Obtenemos fecha de vigencia
					String fechaVigencia = df1.format(sumarDiasFecha());
					Compra compra=new Compra(fechaActual,fechaVigencia,obra,false,usuario,precioTotal,funcionSeleccionada,butacasSeleccionadas );
					if(Descuento>0){
						calcularDescuento(compra,o,bar,mKinveyClient);
					}else{
						Log.e("datos","precio "+ precioTotal+" fecha "+ fechaActual+ " butacas "+ butacasSeleccionadas.size());
						guardarCompra(compra,o,bar,mKinveyClient);

					}

				}else{
					Toast.makeText(CompraActivity.this,"Debe seleccionar todas las butacas antes de continuar" , Toast.LENGTH_SHORT).show();

				}
			}
		});
	}
	//Calcula el descuento de las mascaras
	public void calcularDescuento(final Compra compra,final ObjetosBackend obj,final ProgressBar b,final Client kinveyClient){
		if(Descuento<=precioTotal){
			compra.setPrecioTotal(precioTotal-Descuento);
			Toast.makeText(CompraActivity.this,"A usted se le han descontado "+ mascaras+" máscaras " +"Un total de $" +Descuento , Toast.LENGTH_SHORT).show();
			mascaras=0;
		}else{
			mascaras= (Descuento-precioTotal)/3;
			Toast.makeText(CompraActivity.this,"A usted se le han descontado "+ mascaras+" máscaras " +"Un total de $" +Descuento , Toast.LENGTH_SHORT).show();
			compra.setPrecioTotal(0);
		}
		final UsuarioBackend usuarioLogueado = new UsuarioBackend();
		String idU = kinveyClient.user().getUsername().toString();
		Query query = kinveyClient.query ();
		query.equals("username", idU);
		AsyncAppData<UsuarioBackend> searchedEvents = kinveyClient.appData("Usuario", UsuarioBackend.class);
		searchedEvents.get(query, new KinveyListCallback<UsuarioBackend>() {
			@Override
			public void onSuccess(UsuarioBackend[] resultadoconsulta) { 
				mKinveyClient.appData("Usuario",UsuarioBackend.class).getEntity(resultadoconsulta[0].getIdUsuario(), new KinveyClientCallback<UsuarioBackend>() {

					@Override
					public void onSuccess(UsuarioBackend arg0) {
						arg0.put("mascaras", String.valueOf(mascaras));
						Log.e("Guardar mascaras","seteo la nueva cant de mascaras "+ mascaras );
						mKinveyClient.appData("Usuario", UsuarioBackend.class).save(arg0, new KinveyClientCallback<UsuarioBackend>() {
							@Override
							public void onSuccess(UsuarioBackend result) {
								Log.e("seteo ","Seteo mascarass" +result.getMascaras());
								guardarCompra(compra,obj,bar,mKinveyClient);
							}

							@Override
							public void onFailure(Throwable error) {
								Log.e("Error ","Error"+ error);	
							}
						});
					}

					@Override
					public void onFailure(Throwable arg0) {
						// TODO Auto-generated method stub
						bar.setVisibility(View.GONE);

					}


				});
			}



			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}
		});


	}
	public void guardarCompra(Compra compra,ObjetosBackend obj,ProgressBar b,Client mKinveyClient){
		final String fechaActual=compra.getFechaRealizada();
		final String fechaVigencia=compra.getFechaVigencia();
		final Obra obra=compra.getMiObra();
		final Usuario usuario=compra.getMiUsuario();
		final int precioTotal=compra.getPrecioTotal();
		final Funcion funcionSeleccionada=compra.getFuncionSeleccionada();
		final ArrayList<Butaca> butacasSeleccionadas=compra.getButacasSeleccionadas();
		final ObjetosBackend o=obj;
		final ProgressBar bar=b;
		bar.setVisibility(View.VISIBLE);

		//        txtCargando.setVisibility(View.VISIBLE);
		//Se setean en la tabla BUTACAFUNCIONSECTOR las butacas como ocupadas
		for(int x=0;x<butacasSeleccionadas.size();x++){
			//Funcion para buscar las butacas en la tabla BUTACAFUNCIONSECTOR
			buscarButaca(butacasSeleccionadas.get(x),compra,x,mKinveyClient);

		}

	}

	//Funcion para buscar las butacas en la tabla BUTACAFUNCIONSECTOR
	public void buscarButaca(Butaca butaca,final Compra compra,final int cant,final Client mKinveyClient){
		Query query1 = mKinveyClient.query ();
		Query query2 = mKinveyClient.query ();
		query1.equals("idButaca", String.valueOf(butaca.getIdButaca()));
		query2.equals("idFuncion", String.valueOf(compra.getFuncionSeleccionada().getIdFuncion()));
		AsyncAppData<ButacaFuncionSectorBackend> searchedEvents = mKinveyClient.appData("ButacaFuncionSector", ButacaFuncionSectorBackend.class);
		searchedEvents.get(query1.and(query2), new KinveyListCallback<ButacaFuncionSectorBackend>(){			
			@Override
			public void onSuccess(ButacaFuncionSectorBackend[] result) {
				//				ButacaFuncionSectorBackend entity= new ButacaFuncionSectorBackend();
				//				entity.setEstadoButaca(1);
				for(int x=0; x<result.length;x++){
					Log.e("Encontre ButacaFuncionSectorBackend ",result[x].getIdButaca()+"--"+result[x].getEstadoButaca());
					//Seteo como ocupadas las butacas
					guardarButacasFuncionSector(result[x],compra,cant,mKinveyClient);
				}				

			}

			@Override
			public void onFailure(Throwable error) {
				Log.e("Error ","no entra a ButacaFuncionSectorBackend");	
				bar.setVisibility(View.GONE);

			}


		});
	}

	//Funcion para setear como ocupadas las butacas
	public void guardarButacasFuncionSector(ButacaFuncionSectorBackend bfsb,final Compra compra, final int cant,final Client mKinveyClient){
		mKinveyClient.appData("ButacaFuncionSector",ButacaFuncionSectorBackend.class).getEntity(bfsb.getId(), new KinveyClientCallback<ButacaFuncionSectorBackend>() {

			@Override
			public void onSuccess(ButacaFuncionSectorBackend arg0) {
				arg0.put("estadoButaca", "1");
				Log.e("Guardar ButacasCompra","Butacas guardadas en compraButaca "+ arg0.getIdButaca()+"--" +arg0.getIdFuncion()+"--" +arg0.getEstadoButaca() );
				mKinveyClient.appData("ButacaFuncionSector", ButacaFuncionSectorBackend.class).save(arg0, new KinveyClientCallback<ButacaFuncionSectorBackend>() {
					@Override
					public void onSuccess(ButacaFuncionSectorBackend result) {
						Log.e("seteo ","Seteo las butacas como ocupadas");
						if(cant==compra.getButacasSeleccionadas().size()-1){
							guardarCompra(compra,mKinveyClient);
						}
					}

					@Override
					public void onFailure(Throwable error) {
						Log.e("Error ","Error"+ error);	
						bar.setVisibility(View.GONE);
					}
				});
			}

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				bar.setVisibility(View.GONE);

			}


		});


	}

	//Funcion para guardar la compra en la base de datos
	public void guardarCompra(final Compra compra,Client mKinveyClient){
		CompraBackend compraBknd = new CompraBackend();
		compraBknd.setFechaRealizada(compra.getFechaRealizada());
		compraBknd.setFechaVigencia(compra.getFechaVigencia());
		compraBknd.setIdFuncion(compra.getFuncionSeleccionada().getIdFuncion());
		compraBknd.setIdUsuario(compra.getMiUsuario().getMiNombreUsuario());
		compraBknd.setIdObra(compra.getMiObra().getIdObra());
		compraBknd.setPago(false);
		compraBknd.setPrecioTotal(compra.getPrecioTotal());
		AsyncAppData<CompraBackend> myevents = mKinveyClient.appData("Compra",CompraBackend.class);
		myevents.save(compraBknd, new KinveyClientCallback<CompraBackend>() {
			@Override
			public void onSuccess(CompraBackend arg0) {

				Log.e("idCompra",arg0.getId());
				compra.setIdCompra(arg0.getId());;
				String s= " Obra: "+ obra.getNombre()+ " Funcion "+ funcionSeleccionada.toString()+ " Precio total "+ precioTotal;
				Funcion funcionElegida=compra.getFuncionSeleccionada();
				String fecha= funcionElegida.getFechaObra();
				String hora= funcionElegida.getHoraComienzo();
				String duracion= Double.toString(funcionElegida.getDuracion());
				Intent intent = new Intent("com.google.zxing.client.android.ENCODE");
				intent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
				intent.putExtra("ENCODE_DATA", compra.toString());
				intent.putExtra("fecha", fecha);
				intent.putExtra("hora", hora);
				intent.putExtra("duracion", duracion);
				intent.putExtra("tituloObra", obra.getNombre());
				intent.putExtra("nuevo", "si");
				//				intent.putExtra("compra", compra.toString());
				CompraActivity.this.startActivityForResult(intent, 0);
				bar.setVisibility(View.GONE);
				guardarButacasCompra(compra);
				guardarMascara(compra.getMiUsuario(),compra.getPrecioTotal());

			}
			@Override
			public void onFailure(Throwable e) {
				Toast.makeText(getApplicationContext(), "Ups.. no fue posible guardar su compra, asegurece tener conexión a internet", Toast.LENGTH_LONG).show();
				cancelarCompra(compra);
				bar.setVisibility(View.GONE);
			}
		});

	}

	//Funcion para cargarle mascaras al usuario
	public void guardarMascara(Usuario usuarioCompra, final int precio){
		Query query2 = mKinveyClient.query ();
		query2.equals("username",usuarioCompra.getMiNombreUsuario() );
		AsyncAppData<UsuarioBackend> searchedEvents = mKinveyClient.appData("Usuario", UsuarioBackend.class);
		searchedEvents.get(query2, new KinveyListCallback<UsuarioBackend>(){			
			@Override
			public void onSuccess(UsuarioBackend[] result) {
				Log.e("Encontre usuario","la suma mascaras al usuario");

				for(int x=0; x<result.length;x++){
					Log.e("Encontre usuario","la suma mascaras al usuario "+result[x].getIdUsuario());
					mKinveyClient.appData("Usuario",UsuarioBackend.class).getEntity(result[x].getIdUsuario(), new KinveyClientCallback<UsuarioBackend>() {

						@Override
						public void onSuccess(UsuarioBackend arg0) {
							int mascaras= (int) (precio*0.05);
							mascaras +=Integer.parseInt(arg0.getMascaras());
							arg0.put("mascaras", String.valueOf(mascaras));
							mKinveyClient.appData("Usuario", UsuarioBackend.class).save(arg0, new KinveyClientCallback<UsuarioBackend>() {
								@Override
								public void onSuccess(UsuarioBackend result) {
									Log.e("sumo mascaras","se le suman 50 mascaras");

								}

								@Override
								public void onFailure(Throwable error) {
									Log.e("Error ","Error"+ error);	
									bar.setVisibility(View.GONE);
								}
							});
						}

						@Override
						public void onFailure(Throwable arg0) {
							// TODO Auto-generated method stub
							bar.setVisibility(View.GONE);

						}


					});

				}				

			}

			@Override
			public void onFailure(Throwable error) {
				Log.e("Error ","no entra a ButacaFuncionSectorBackend");	
				bar.setVisibility(View.GONE);

			}


		});
	}
	//Funcion para setear como ocupadas las butacas
	public void librarButacasFuncionSector(ButacaFuncionSectorBackend bfsb,final Compra compra,final int cant,final Client mKinveyClient){
		mKinveyClient.appData("ButacaFuncionSector",ButacaFuncionSectorBackend.class).getEntity(bfsb.getId(), new KinveyClientCallback<ButacaFuncionSectorBackend>() {

			@Override
			public void onSuccess(ButacaFuncionSectorBackend arg0) {
				arg0.put("estadoButaca", "0");
				Log.e("Guardar ButacasCompra","Butacas guardadas en compraButaca "+ arg0.getIdButaca()+"--" +arg0.getIdFuncion()+"--" +arg0.getEstadoButaca() );
				mKinveyClient.appData("ButacaFuncionSector", ButacaFuncionSectorBackend.class).save(arg0, new KinveyClientCallback<ButacaFuncionSectorBackend>() {
					@Override
					public void onSuccess(ButacaFuncionSectorBackend result) {
						Log.e("seteo ","Seteo las butacas como libres");
					}
					@Override
					public void onFailure(Throwable error) {
						Log.e("Error ","Error"+ error);	
						bar.setVisibility(View.GONE);
					}
				});
			}
			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				bar.setVisibility(View.GONE);

			}
		});
	}


	//Funcion para guardar en la tabla BUTACASCOMPRA
	public void guardarButacasCompra(Compra compra){
		for(int x=0;x<compra.getButacasSeleccionadas().size();x++){
			Butaca b=compra.getButacasSeleccionadas().get(x);
			CompraButacasBackend comButBknd = new CompraButacasBackend();
			comButBknd.setIdCompra(compra.getIdCompra());
			comButBknd.setIdButaca(b.getIdButaca());

			AsyncAppData<CompraButacasBackend> myevents = mKinveyClient.appData("ButacasCompra",CompraButacasBackend.class);
			myevents.save(comButBknd, new KinveyClientCallback<CompraButacasBackend>() {
				@Override
				public void onSuccess(CompraButacasBackend arg0) {
					Log.e("Guardar ButacasCompra","Butacas guardadas en compraButaca");				
				}
				@Override
				public void onFailure(Throwable arg0) {
					// TODO Auto-generated method stub
					bar.setVisibility(View.GONE);

				}

			});
		}

	}

	//Funcion para volver a setear las butacas como libres ya que no fue posible realizar la compra
	public void cancelarCompra(final Compra compra){
		for(int x=0;x<compra.getButacasSeleccionadas().size();x++){
			Butaca b=compra.getButacasSeleccionadas().get(x);   
			Query query1 = mKinveyClient.query ();
			Query query2 = mKinveyClient.query ();
			query1.equals("idButaca", String.valueOf(b.getIdButaca()));
			query2.equals("idFuncion", String.valueOf(compra.getFuncionSeleccionada().getIdFuncion()));
			AsyncAppData<ButacaFuncionSectorBackend> searchedEvents = mKinveyClient.appData("ButacaFuncionSector", ButacaFuncionSectorBackend.class);
			searchedEvents.get(query1.and(query2), new KinveyListCallback<ButacaFuncionSectorBackend>(){			
				@Override
				public void onSuccess(ButacaFuncionSectorBackend[] result) {
					//				ButacaFuncionSectorBackend entity= new ButacaFuncionSectorBackend();
					//				entity.setEstadoButaca(1);
					for(int x=0; x<result.length;x++){
						Log.e("Encontre ButacaFuncionSectorBackend para cancelarla ",result[x].getIdButaca()+"--"+result[x].getEstadoButaca());
						//Seteo como libres las butacas
						librarButacasFuncionSector(result[x],compra,cant,mKinveyClient);
						bar.setVisibility(View.GONE);
					}				

				}

				@Override
				public void onFailure(Throwable error) {
					Log.e("Error ","no entra a ButacaFuncionSectorBackend");	
					bar.setVisibility(View.GONE);
				}


			});
		}
	}

	//Funcion para obtener la fecha de vigencia
	public Date sumarDiasFecha(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 2);  // numero de días a añadir, o restar en caso de días<0
		return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
	}
	//Funcion para obtener las butacas que se seleccionaron
	public void obtenerButacasSeleccionadas(){
		for(int x=0;x<sectorA.getListaButacas().size();x++){
			if(sectorA.getListaButacas().get(x).getEstadoButaca()==2){
				butacasSeleccionadas.add(sectorA.getListaButacas().get(x));
				sectorA.getListaButacas().get(x).setEstadoButaca(1);
			}	
		}

		for(int x=0;x<sectorB.getListaButacas().size();x++){
			if(sectorB.getListaButacas().get(x).getEstadoButaca()==2){
				butacasSeleccionadas.add(sectorB.getListaButacas().get(x));
				sectorB.getListaButacas().get(x).setEstadoButaca(1);
			}
		}
		for(int x=0;x<sectorC.getListaButacas().size();x++){
			if(sectorC.getListaButacas().get(x).getEstadoButaca()==2){
				butacasSeleccionadas.add(sectorC.getListaButacas().get(x));
				sectorC.getListaButacas().get(x).setEstadoButaca(1);
			}
		}
	}

	//	public Compra crearObjetoCompra(){
	//		
	//		//Obtenemos fecha actual
	//		Calendar c = Calendar.getInstance();
	//		SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
	//		String fechaActual = df1.format(c.getTime());
	//		//Obtenemos fecha de vigencia
	//
	//		// Configuramos la fecha que se recibe
	//		String fechaVigencia = df1.format(sumarDiasFecha());
	//		Log.e("datos","precio "+ precioTotal+" fecha "+ fechaActual+ " butacas "+ butacasSeleccionadas.size());
	//		//						 + " funcion "+ CompraActivity.this.funcionSeleccionada
	//		Compra compra= new Compra(fechaActual,fechaVigencia,obra,false,usuario,precioTotal,CompraActivity.this.funcionSeleccionada,butacasSeleccionadas );
	//		return compra;
	//	}

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





}
