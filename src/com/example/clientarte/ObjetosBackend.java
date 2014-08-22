package com.example.clientarte;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Application;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.Toast;
import backend.ButacaFuncionSectorBackend;
import backend.CompraButacasBackend;
import backend.FuncionBackend;
import backend.NotificacionesBackend;
import backend.ObraBackend;
import backend.ObrasImagen;
import backend.ObrasSalasBackend;
import backend.SalaBackend;
import backend.SectorBackend;
import backend.UsuarioBackend;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.java.Query;
import com.kinvey.java.core.KinveyClientCallback;
import com.kinvey.java.model.FileMetaData;
import com.kinvey.java.query.AbstractQuery.SortOrder;

import dominio.Butaca;
import dominio.Compra;
import dominio.Funcion;
import dominio.Obra;
import dominio.Sala;
import dominio.Sector;

public class ObjetosBackend extends Application{


	//Creo listas 

	private ArrayList<Funcion> listaFunciones= new ArrayList<Funcion>();
	private ArrayList<Sala> listaSalas= new ArrayList<Sala>();
	private ArrayList<Sector> listaSectores=new ArrayList<Sector>();
	//	private ArrayList<Comodidad> listaComodidades;
	private ArrayList<Obra> listaObra= new ArrayList<Obra>();
	private ArrayList<Butaca> listabutacas= new ArrayList<Butaca>();
	ArrayList<Funcion> listaFuncionesObra= new ArrayList<Funcion>();
	private ButacaFuncionSectorBackend[] listabutacasSeleccionadas;
	ArrayList<Butaca> listabutacas3;
	ArrayList<Butaca> listabutacas4;
	HashMap<Obra, Funcion> obraSegunFuncion= new HashMap<Obra, Funcion>();

	private UsuarioBackend usuBackend;
	private Funcion funcionA;
	private Funcion funcionB;
	private Obra obra1;
	private Obra obra2;
	private Sala sala1;
	private Sala sala2;
	private SectorBackend sector1;
	private SectorBackend sector2;
	private SectorBackend sector3;
	private SectorBackend sector4;

	private Butaca miButaca;
	private HashMap<String, Boolean> hashComodidades = new HashMap <String, Boolean> ();
	public static final String TAG = "ArteBackend";
	private String appKey="kid_VT8_It3ePE";
	private String appSecret="1b0fa51481984d2da5910f78a9d26ccc";
	private String mensaje;
	private Client mKinveyClient;
	private UsuarioBackend usuarioLogueado;
	Query myQuery;

	private ArrayList<NotificacionesBackend> listaNotificaciones= new ArrayList<NotificacionesBackend>();

	private Compra compra = null;
	private ProgressDialog progressDialog;

	//	@Override
	//	public void onCreate() {
	//		super.onCreate();
	//		initialize();
	//	}
	//
	//	private void initialize() { 
	//		// Enter your app credentials here 
	//		//service = new Client.Builder(this).build();
	//		mKinveyClient = new Client.Builder(appKey,appSecret,this).build();
	//	}

	public ObjetosBackend(){
		ArrayList listaImagenes= new ArrayList();
		ArrayList<Funcion> listaFunciones= new ArrayList<Funcion>();
		ArrayList<Sala> listaSalas= new ArrayList<Sala>();
		ArrayList<SectorBackend> listaSectores= new ArrayList<SectorBackend>();
		//	private ArrayList<Comodidad> listaComodidades;
		ArrayList<Obra> listaObra= new ArrayList<Obra>();
		ArrayList<Butaca> listabutacas= new ArrayList<Butaca>();
		ArrayList<Butaca>listabutacas2 = new ArrayList<Butaca>();
		ArrayList<Butaca> listabutacas3 = new ArrayList<Butaca>();
		ArrayList<NotificacionesBackend> listaNotificaciones= new ArrayList<NotificacionesBackend>();

		//mKinveyClient = new Client.Builder(this.getApplicationContext()).build();
		//		conectarBackend();			

	}

	public ObjetosBackend(Client KinveyClient){
		//mKinveyClient=KinveyClient;
		myQuery = mKinveyClient.query();
		ArrayList listaImagenes= new ArrayList();
		ArrayList<Funcion> listaFunciones= new ArrayList<Funcion>();
		ArrayList<Sala> listaSalas= new ArrayList<Sala>();
		ArrayList<SectorBackend> listaSectores= new ArrayList<SectorBackend>();
		//	private ArrayList<Comodidad> listaComodidades;
		ArrayList<Obra> listaObra= new ArrayList<Obra>();
		ArrayList<Butaca> listabutacas= new ArrayList<Butaca>();
		ArrayList<Butaca>listabutacas2 = new ArrayList<Butaca>();
		ArrayList<Butaca> listabutacas3 = new ArrayList<Butaca>();
		//		crearListObra();
		ArrayList<NotificacionesBackend> listaNotificaciones= new ArrayList<NotificacionesBackend>();

	}

	public void inicialize(Client KinveyClient){
		mKinveyClient=KinveyClient;
		myQuery = mKinveyClient.query();
		ArrayList listaImagenes= new ArrayList();
		ArrayList<Funcion> listaFunciones= new ArrayList<Funcion>();


		ArrayList<Sala> listaSalas= new ArrayList<Sala>();
		ArrayList<SectorBackend> listaSectores= new ArrayList<SectorBackend>();
		//	private ArrayList<Comodidad> listaComodidades;
		ArrayList<Obra> listaObra= new ArrayList<Obra>();
		ArrayList<Butaca> listabutacas= new ArrayList<Butaca>();
		ArrayList<Butaca>listabutacas2 = new ArrayList<Butaca>();
		ArrayList<Butaca> listabutacas3 = new ArrayList<Butaca>();
		//		crearListObra();
		//		crearListFunciones();
		ArrayList<NotificacionesBackend> listaNotificaciones= new ArrayList<NotificacionesBackend>();
	}


	public void traerDatos(){
		crearListObra();
		crearListFunciones();
		crearListSectores();
		//		AgregarSectorAFuncion();
		crearListSalas();
		AgregarObrasaSalas();
		//traerNotificaciones(idUsuario);
	}

	public ArrayList<Obra> getListObras(){
		return this.listaObra;
	}

	public ArrayList<Sala> getListSalas(){
		return  this.listaSalas;
	}

	public ArrayList<NotificacionesBackend> getListaNotificaciones() {
		return listaNotificaciones;
	}
	public ArrayList<Funcion> getListaFuncion(){
		return this.listaFunciones;
	}


	public void setListaNotificaciones(
			ArrayList<NotificacionesBackend> listaNotificaciones) {
		this.listaNotificaciones = listaNotificaciones;
	}

	//	public HashMap<Obra, Funcion> getobraSegunFuncion(){
	//		return this.obraSegunFuncion;
	//	}


	//Funcion para obtener obras

	public void crearListObra(){
		Query query = mKinveyClient.query();
		
		query.addSort("_id", SortOrder.DESC);

		mKinveyClient.appData("Obras", ObraBackend.class).get(query, new KinveyListCallback<ObraBackend>() {
			@Override
			public void onSuccess(ObraBackend[] resultadoconsulta) {
				for (int i = 0; i < resultadoconsulta.length; i++) {
					Obra obra= new Obra(resultadoconsulta[i].getIdObra(),resultadoconsulta[i].getNombreObras(),resultadoconsulta[i].getDescripcipnObras());
//					cargarImagen(obra);
					imagen(obra);
					listaObra.add(obra);
				}
				Log.e("Obras","Listas de obras cargadas");
			}
			@Override
			public void onFailure(Throwable arg0) {
				Toast.makeText(getApplicationContext(), "Ups.. no nos pudimos conectar con la base de datos, asegurece de estar logueado y tener conexi�n a internet", Toast.LENGTH_LONG).show();


			}
		});
	}
	
	public void imagen(Obra obra){
	switch (obra.getIdObra()){
		case 1:
			obra.getListaImagenes()[0]=R.drawable.carmen_1;
			obra.getListaImagenes()[0]=R.drawable.carmen_2;
			obra.getListaImagenes()[0]=R.drawable.carmen_3;
			break;
		case 6:
			obra.getListaImagenes()[1]=R.drawable.les_2;
			obra.getListaImagenes()[1]=R.drawable.les_3;
			break;	
		}	
	}
	/** 
	 Cargo las imagenes a la obra
	 */
	public void cargarImagen(final Obra obra){
		Query nquery = mKinveyClient.query ();
		String id=String.valueOf(obra.getIdObra());
		nquery.equals("idObra", id);
		mKinveyClient.appData("ObrasImagen", ObrasImagen.class).get(nquery, new KinveyListCallback<ObrasImagen>() {

			@Override
			public void onFailure(Throwable arg0) {
				Log.e("cargarImagen","cargo mal");

			}

			@Override
			public void onSuccess(ObrasImagen[] arg0) {

				if(arg0!=null){
					Log.e("cargarImagen","trajo los datos de " + obra.getNombre()+"-"+ obra.getIdObra());

					for(int x=0;x<arg0.length;x++){
						try {
							bajarImagen(arg0[x],obra);	
						}catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}Log.e("cargarImagen","agr0 es null, trajo los datos de " + obra.getNombre()+"-"+ obra.getIdObra());

			} 




		});

	}

	public void bajarImagen(final ObrasImagen obraImagen,final Obra obra) throws IOException{
		
		mKinveyClient.file(). downloadMetaData (obraImagen.getidImagen(), new KinveyClientCallback<FileMetaData>() {

			@Override	
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(FileMetaData arg0) {
//			obra.getListaImagenes().add(obraImagen.getFile(obraImagen.getidImagen().));
				// TODO Auto-generated method stub
			}
		});
		
	}
		

	/** 
		 Cargo las funciones de la tabla
	 */
	public void crearListFunciones(){
		Query myQuery = mKinveyClient.query();
		myQuery.addSort("FechaObra", SortOrder.ASC);
		mKinveyClient.appData("Funciones", FuncionBackend.class).get(myQuery, new KinveyListCallback<FuncionBackend>() {
			@Override
			public void onSuccess(FuncionBackend[] resultadoconsulta) {
				for (int i = 0; i < resultadoconsulta.length; i++) {
					//					ArrayList<Funcion> listaFuncionesObra= new ArrayList<Funcion>();
					Funcion funcion= new Funcion(resultadoconsulta[i].getIdFuncion(),Double.parseDouble(resultadoconsulta[i].getDuracion()),resultadoconsulta[i].getFechaObra(),resultadoconsulta[i].getHoraComienzo());
					for(int x=0;x<listaObra.size()-1;x++){
						if(resultadoconsulta[i].getIdObra()==listaObra.get(x).getIdObra()){
							//													if(resultadoconsulta[i].getIdObra().equalsIgnoreCase(listaObra.get(x).getIdObra())){
							Log.e("resultado "+ i+" " +resultadoconsulta[i].getIdObra(), "lista  "+x+" "+listaObra.get(x).getIdObra());
							listaObra.get(x).getListaFunciones().add(funcion);
							//							Collections.sort(listaObra.get(x).getListaFunciones()); 
							//								obraSegunFuncion.put(listaObra.get(x), funcion);

						}
					}
				}
				Log.e("Funciones","Listas de funciones cargadas");			}

			@Override
			public void onFailure(Throwable arg0) {
				//				Toast.makeText(getApplicationContext(), "Ups.. no nos pudimos conectar con la base de datos, asegurece tener conexi�n a internet", Toast.LENGTH_LONG).show();

			}
		});



	}





	public void crearListSectores(){	
		/**
			 Creo Sectores
		 */
		mKinveyClient.appData("Sector", SectorBackend.class).get(myQuery, new KinveyListCallback<SectorBackend>() {
			@Override
			public void onSuccess(SectorBackend[] resultadoconsulta) {
				for (int i = 0; i < resultadoconsulta.length; i++) {
					Sector sector= new Sector(resultadoconsulta[i].getIdSector(),resultadoconsulta[i].getTotalButacas(),resultadoconsulta[i].getLinea(),resultadoconsulta[i].getPrecioSector());
					listaSectores.add(sector);
				}
				Log.e("Sectores","Listas de sectores cargados");
				AgregarSectorAFuncion();
			}

			@Override
			public void onFailure(Throwable arg0) {
				//				Toast.makeText(getApplicationContext(), "Ups.. no nos pudimos conectar con la base de datos, asegurece tener conexi�n a internet", Toast.LENGTH_LONG).show();

			}
		});
	}


	/**
		 Traigo tabla FuncionSector
	 */
	public void AgregarSectorAFuncion(){	
		/**
			 Traigo datos funcionSector y cargo la lista de sectores en la funcion
		 */

		funcionA= new Funcion();
		ArrayList<Funcion> lfuncion=new ArrayList<Funcion>();
		for (int x = 0; x < listaObra.size(); x++) {
			ArrayList<Funcion> lista=new ArrayList<Funcion>();
			//			final Obra obraActual= new Obra();
			lfuncion= listaObra.get(x).getListaFunciones();
			for(int j=0;j<lfuncion.size();j++){
				Funcion fu=new Funcion();
				funcionA= lfuncion.get(j);	
				//			lSector= new ArrayList<Sector>();
				//String idF = String.valueOf(funcionA.getIdFuncion());
				fu=cargarButacaSector(funcionA);	
				lista.add(fu);

			}
			listaObra.get(x).setListaFunciones(lista);
		}
	}


	public Funcion cargarButacaSector(Funcion f){
		final Funcion funcionA=f;
		String idF=String.valueOf(funcionA.getIdFuncion());
		Query nquery = mKinveyClient.query ();
		nquery.equals("idFuncion", idF);
		nquery.addSort("idSector", SortOrder.DESC);
		AsyncAppData<ButacaFuncionSectorBackend> searchedEvents = mKinveyClient.appData("ButacaFuncionSector", ButacaFuncionSectorBackend.class);
		searchedEvents.get(nquery, new KinveyListCallback<ButacaFuncionSectorBackend>() {
			@Override
			public void onSuccess(ButacaFuncionSectorBackend[] resultadoconsulta) { 
				ArrayList<Sector>lSector= new ArrayList<Sector>();
				int rIdF,fidF,rIdS,sIds;
				for (int i = 0; i < resultadoconsulta.length; i++) {
					Sector sector= new Sector();
					ArrayList<Butaca> lButaca=new ArrayList<Butaca>();
					String idSector=resultadoconsulta[i].getIdSector();

					while(i < resultadoconsulta.length && idSector.equalsIgnoreCase(resultadoconsulta[i].getIdSector())){
						Butaca b= new Butaca(Integer.parseInt(resultadoconsulta[i].getIdButaca()),Integer.parseInt(resultadoconsulta[i].getEstadoButaca()));
						lButaca.add(b);
						i++;
					}
					sector=cargarButaca(idSector,lButaca);

					Log.e("butaca.size"," "+sector.getListaButacas(). size());

					lSector.add(sector);

				}
				Log.e("lsectores.size"," "+lSector. size());
				funcionA.setListaSectores(lSector);
				Log.e("funciones",funcionA.getIdFuncion()+" "+lSector. size());
				//				lSector.clear();
				Log.e("Butacas","Listas de Butacas cargadas");


			}

			@Override
			public void onFailure(Throwable arg0) {
				Toast.makeText(getApplicationContext(), "Ups.. no nos pudimos conectar con la base de datos, asegurece de estar logueado y tener conexi�n a internet", Toast.LENGTH_LONG).show();

			}
		});
		return funcionA;

	}
	public Sector cargarButaca(String idSector, ArrayList<Butaca> lButaca){
		Sector sector= new Sector();
		for (int y = 0; y < listaSectores.size(); y++) {
			if(Integer.parseInt(idSector)== listaSectores.get(y).getIdSector()){
				sector=listaSectores.get(y);
				sector.setListaButacas(lButaca);;

			}
		}
		return sector;

	}

	//Funcion para obtener salas
	public void crearListSalas(){
		//			Query myQuery = mKinveyClient.query();
		/**
				 Cargo las sala de la tabla
		 */
		mKinveyClient.appData("Sala", SalaBackend.class).get(myQuery, new KinveyListCallback<SalaBackend>() {
			@Override
			public void onSuccess(SalaBackend[] resultadoconsulta) {
				for (int i = 0; i < resultadoconsulta.length; i++) {
					Sala sala= new Sala(resultadoconsulta[i].getIdSala(),resultadoconsulta[i].getNombreSala(),resultadoconsulta[i].getCapacidad());
					listaSalas.add(sala);
				}
			}

			@Override
			public void onFailure(Throwable arg0) {
				//				Toast.makeText(getApplicationContext(), "Ups.. no nos pudimos conectar con la base de datos, asegurece tener conexi�n a internet", Toast.LENGTH_LONG).show();

			}
		});
	}

	public void AgregarObrasaSalas(){
		/**
				 Cargo la tabla salaObras
		 */
		mKinveyClient.appData("ObrasSalas", ObrasSalasBackend.class).get(myQuery, new KinveyListCallback<ObrasSalasBackend>() {
			@Override
			public void onSuccess(ObrasSalasBackend[] resultadoconsulta) {
				for (int i = 0; i < resultadoconsulta.length; i++) {
					for (int x = 0; x < listaObra.size(); x++) {
						Obra obraActual= listaObra.get(x);
						if(resultadoconsulta[i].getIdObras()==obraActual.getIdObra()){
							for (int y = 0; y < listaSalas.size(); y++) {
								Sala salaActual=listaSalas.get(y);
								if(resultadoconsulta[i].getIdSalas().equalsIgnoreCase(salaActual.getIdSala())){
									listaSalas.get(y).getListaObras().add(obraActual);
									Log.e(salaActual.getIdSala(),obraActual.getIdObra()+"");
								}	
							}
						}
					}
				}
			}

			@Override
			public void onFailure(Throwable arg0) {
				//				Toast.makeText(getApplicationContext(), "Ups.. no nos pudimos conectar con la base de datos, asegurece tener conexi�n a internet", Toast.LENGTH_LONG).show();

			}
		});
	}

	//	public void cargarSector(int idFuncion,ArrayList<Sector> lSector){
	//		Funcion f= new Funcion();
	//		ArrayList<Funcion> lfuncion=new ArrayList<Funcion>();
	//		for (int x = 0; x < listaObra.size(); x++) {
	//			lfuncion= listaObra.get(x).getListaFunciones();
	//			for(int j=0;j<lfuncion.size();j++){
	//				f= lfuncion.get(j);						
	//				if(idFuncion==(f.getIdFuncion()) ){
	//					f.setListaSectores(lSector);
	//				}
	//			}
	//		}
	//
	//	}

	/**
	 Traigo tabla ButacaSector
	 */
	//	public void AgregarButacaSector(){	
	//		/**
	//		 Traigo datos ButacaSector y cargo la lista de butacas al sector
	//		 */
	//		mKinveyClient.appData("ButacaSector", ButacaSectorBackend.class).get(myQuery, new KinveyListCallback<ButacaSectorBackend>() {
	//			@Override
	//			public void onSuccess(ButacaSectorBackend[] resultadoconsulta) {
	//				for (int i = 0; i < resultadoconsulta.length; i++) {
	//					Butaca butaca= new Butaca(resultadoconsulta[i].getIdButaca(),resultadoconsulta[i].getEstadoButaca());
	//					for(int x=0;x<listaSectores.size();x++){
	//						if(resultadoconsulta[i].getIdSector()==listaSectores.get(x).getIdSector()){
	//							listaSectores.get(x).getListaButacas().add(butaca);
	//						}
	//					}
	//				}
	//			}
	//
	//			@Override
	//			public void onFailure(Throwable arg0) {
	//				// TODO Auto-generated method stub
	//
	//			}
	//		});
	//	}

	//	public Compra guardarCompra(String fActual, String fVigencia,Funcion fSeleccionada, Usuario u, Obra obraSeleccionada, int precio, ArrayList<Butaca> butSelec){
	//		final String fechaActual=fActual;
	//		final String fechaVigencia=fVigencia;
	//		final Obra obra=obraSeleccionada;
	//		final Usuario usuario=u;
	//		final int precioTotal=precio;
	//		final Funcion funcionSeleccionada=fSeleccionada;
	//		final ArrayList<Butaca> butacasSeleccionadas=butSelec;
	//
	//		CompraBackend compraBknd = new CompraBackend();
	//		compraBknd.setFechaRealizada(fechaActual);
	//		compraBknd.setFechaVigencia(fechaVigencia);
	//		compraBknd.setIdFuncion(fSeleccionada.getIdFuncion());
	//		compraBknd.setIdUsuario(usuario.getIdUsuario());
	//		compraBknd.setIdObra(obraSeleccionada.getIdObra());
	//		compraBknd.setPago(false);
	//		compraBknd.setPrecioTotal(precioTotal);
	//
	//		AsyncAppData<CompraBackend> myevents = mKinveyClient.appData("Compra",CompraBackend.class);
	//		myevents.save(compraBknd, new KinveyClientCallback<CompraBackend>() {
	//
	//
	//			@Override
	//			public void onSuccess(CompraBackend arg0) {
	//				Log.e("idCompra",arg0.getId());
	//				compra=new Compra(arg0.getId(),fechaActual,fechaVigencia,obra,false,usuario,precioTotal,funcionSeleccionada,butacasSeleccionadas );
	//				for(int x=0;x<butacasSeleccionadas.size();x++){
	//
	//					guardarButacasCompra(compra,butacasSeleccionadas.get(x));
	//					buscarButacasFuncionSector(compra,butacasSeleccionadas.get(x));
	//					Log.e("Guardar Compra","Compra guardada");
	//					
	//				}
	//				String s= " Obra: "+ obra.getNombre()+ " Funcion "+ funcionSeleccionada.toString()+ " Precio total "+ precioTotal;
	//				Funcion funcionElegida=funcionSeleccionada;
	//				String fecha= funcionElegida.getFechaObra();
	//				String hora= funcionElegida.getHoraComienzo();
	//				String duracion= Double.toString(funcionElegida.getDuracion());
	//				Intent intent = new Intent("com.google.zxing.client.android.ENCODE");
	//				intent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
	//				intent.putExtra("ENCODE_DATA", s);
	//				intent.putExtra("fecha", fecha);
	//				intent.putExtra("hora", hora);
	//				intent.putExtra("duracion", duracion);
	//				intent.putExtra("tituloObra", obra.getNombre());
	////				intent.putExtra("compra", compra.toString());
	//				CompraActivity.startActivityForResult(intent, 0);
	//
	//			}
	//			@Override
	//			public void onFailure(Throwable e) {
	//				Toast.makeText(getApplicationContext(), "Ups.. no fue posible guardar su compra, asegurece tener conexi�n a internet", Toast.LENGTH_LONG).show();
	//				Log.e(TAG, "failed to save event data", e);
	//			}
	//		});
	//
	//		return compra;
	//
	//	}
	//

	public void guardarButacasCompra(Compra compra,Butaca butaca){
		CompraButacasBackend comButBknd = new CompraButacasBackend();
		comButBknd.setIdCompra(compra.getIdCompra());
		comButBknd.setIdButaca(butaca.getIdButaca());

		AsyncAppData<CompraButacasBackend> myevents = mKinveyClient.appData("ButacasCompra",CompraButacasBackend.class);
		myevents.save(comButBknd, new KinveyClientCallback<CompraButacasBackend>() {

			@Override
			public void onSuccess(CompraButacasBackend arg0) {
				Log.e("Guardar ButacasCompra","Butacas guardadas en compraButaca");				
			}
			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}

		});

	}

	//	public void buscarButacasFuncionSector(Compra compra,Butaca butaca){
	//
	//		Query query1 = mKinveyClient.query ();
	//		Query query2 = mKinveyClient.query ();
	//		query1.equals("idButaca", String.valueOf(butaca.getIdButaca()));
	//		query2.equals("idFuncion", String.valueOf(compra.getFuncionSeleccionada().getIdFuncion()));
	//		AsyncAppData<ButacaFuncionSectorBackend> searchedEvents = mKinveyClient.appData("ButacaFuncionSector", ButacaFuncionSectorBackend.class);
	//		searchedEvents.get(query1.and(query2), new KinveyListCallback<ButacaFuncionSectorBackend>(){			
	//			@Override
	//			public void onSuccess(ButacaFuncionSectorBackend[] result) {
	//				//				ButacaFuncionSectorBackend entity= new ButacaFuncionSectorBackend();
	//				//				entity.setEstadoButaca(1);
	//				for(int x=0; x<result.length;x++){
	//					Log.e("Encontre ButacaFuncionSectorBackend ",result[x].getIdButaca()+"--"+result[x].getEstadoButaca());
	//					guardarButacasFuncionSector(result[x]);
	//				}				
	//
	//			}
	//
	//			@Override
	//			public void onFailure(Throwable error) {
	//				Log.e("Error ","no entra a ButacaFuncionSectorBackend");	
	//
	//			}
	//
	//
	//		});
	//
	//	}
	//
	//		public void guardarButacasFuncionSector(ButacaFuncionSectorBackend butaca){
	//		
	//		String i=butaca.getId();
	//		mKinveyClient.appData("ButacaFuncionSector",ButacaFuncionSectorBackend.class).getEntity(butaca.getId(), new KinveyClientCallback<ButacaFuncionSectorBackend>() {
	//
	//			@Override
	//			public void onSuccess(ButacaFuncionSectorBackend arg0) {
	//				arg0.put("estadoButaca", "1");
	//				Log.e("Guardar ButacasCompra","Butacas guardadas en compraButaca "+ arg0.getIdButaca()+"--" +arg0.getIdFuncion()+"--" +arg0.getEstadoButaca() );
	//				mKinveyClient.appData("ButacaFuncionSector", ButacaFuncionSectorBackend.class).save(arg0, new KinveyClientCallback<ButacaFuncionSectorBackend>() {
	//					@Override
	//					public void onSuccess(ButacaFuncionSectorBackend result) {
	//						Log.e("seteo ","Seteo las butacas como ocupadas");
	//						guardarCompra();
	//					}
	//
	//					@Override
	//					public void onFailure(Throwable error) {
	//						Log.e("Error ","Error"+ error);	
	//					}
	//				});
	//			}
	//
	//			@Override
	//			public void onFailure(Throwable arg0) {
	//				// TODO Auto-generated method stub
	//
	//			}
	//
	//
	//		});
	//	}
	//	public ButacaFuncionSectorBackend[] verificarButacasDisponibles(Funcion funcion,ArrayList<Butaca>butacasSeleccionadas){
	//		Query query1 = mKinveyClient.query ();
	//		Query query2 = mKinveyClient.query ();
	//		for(int i=0;i<butacasSeleccionadas.size();i++){
	//			query1.equals("idButaca", String.valueOf(butacasSeleccionadas.get(i).getIdButaca()));
	//			query2.equals("idFuncion", String.valueOf(funcion.getIdFuncion()));
	//			AsyncAppData<ButacaFuncionSectorBackend> searchedEvents = mKinveyClient.appData("ButacaFuncionSector", ButacaFuncionSectorBackend.class);
	//			searchedEvents.get(query1.and(query2), new KinveyListCallback<ButacaFuncionSectorBackend>(){			
	//				@Override
	//				public void onSuccess(ButacaFuncionSectorBackend[] result) {
	//
	//					for(int x=0; x<result.length;x++){
	//						Log.e("Encontre ButacaFuncionSectorBackend ",result[x].getIdButaca()+"--"+result[x].getEstadoButaca());
	//						//						if(result[x].getEstadoButaca()==0){
	//						//							listabutacasSeleccionadas[x]=result[x];
	//						//						}
	//					}				
	//
	//				}
	//
	//				@Override
	//				public void onFailure(Throwable error) {
	//					Log.e("Error ","no entra a ButacaFuncionSectorBackend");	
	//
	//				}
	//
	//
	//			});
	//		}
	//		return listabutacasSeleccionadas;
	//	}

	//	public void guardarCompra(){
	//		CompraBackend compraBknd = new CompraBackend();
	//		compraBknd.setFechaRealizada(fechaActual);
	//		compraBknd.setFechaVigencia(fechaVigencia);
	//		compraBknd.setIdFuncion(fSeleccionada.getIdFuncion());
	//		compraBknd.setIdUsuario(usuario.getIdUsuario());
	//		compraBknd.setIdObra(obraSeleccionada.getIdObra());
	//		compraBknd.setPago(false);
	//		compraBknd.setPrecioTotal(precioTotal);
	//		AsyncAppData<CompraBackend> myevents = mKinveyClient.appData("Compra",CompraBackend.class);
	//		myevents.save(compraBknd, new KinveyClientCallback<CompraBackend>() {
	//			@Override
	//			public void onSuccess(CompraBackend arg0) {
	//				
	//				Log.e("idCompra",arg0.getId());
	//				Compra compra=new Compra(arg0.getId(),fechaActual,fechaVigencia,obra,false,usuario,precioTotal,funcionSeleccionada,butacasSeleccionadas );
	//				for(int x=0;x<butacasSeleccionadas.size();x++){
	//
	//					o.guardarButacasCompra(compra,butacasSeleccionadas.get(x));
	//					o.buscarButacasFuncionSector(compra,butacasSeleccionadas.get(x));
	//					Log.e("Guardar Compra","Compra guardada");
	//					
	//				}
	//				String s= " Obra: "+ obra.getNombre()+ " Funcion "+ funcionSeleccionada.toString()+ " Precio total "+ precioTotal;
	//				Funcion funcionElegida=funcionSeleccionada;
	//				String fecha= funcionElegida.getFechaObra();
	//				String hora= funcionElegida.getHoraComienzo();
	//				String duracion= Double.toString(funcionElegida.getDuracion());
	//				Intent intent = new Intent("com.google.zxing.client.android.ENCODE");
	//				intent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
	//				intent.putExtra("ENCODE_DATA", s);
	//				intent.putExtra("fecha", fecha);
	//				intent.putExtra("hora", hora);
	//				intent.putExtra("duracion", duracion);
	//				intent.putExtra("tituloObra", obra.getNombre());
	////				intent.putExtra("compra", compra.toString());
	//				CompraActivity.this.startActivityForResult(intent, 0);
	//
	//			}
	//			@Override
	//			public void onFailure(Throwable e) {
	//				Toast.makeText(getApplicationContext(), "Ups.. no fue posible guardar su compra, asegurece tener conexi�n a internet", Toast.LENGTH_LONG).show();
	//			}
	//		});
	//
	//	}

	public void crearUsuarioLogueado (UsuarioBackend entity){
		mKinveyClient.appData("Usuario", UsuarioBackend.class).save(entity, new KinveyClientCallback<UsuarioBackend>() {
			@Override
			public void onSuccess(UsuarioBackend result) {

				//				Toast.makeText(CreateAccountActivity.this,"Entity Saved\nTitle: " + result.getNombreUsuario()
				//						+ "\nDescription: " + result.get("Description"), Toast.LENGTH_LONG).show();

			}
			@Override
			public void onFailure(Throwable error) {
				Log.e(TAG, "AppData.save Failure", error);
				//				Toast.makeText(CreateAccountActivity.this, "Save All error: " + error.getMessage(), Toast.LENGTH_LONG).show();
			}
		});

	}

	//	public Client getUsuKinvey (){
	//		this.mKinveyClient = new Client.Builder(this.getApplicationContext()).build();
	//		this.mKinveyClient.ping(new KinveyPingCallback() {
	//		    public void onFailure(Throwable t) {
	//		        Log.e("Probando Kinvey Connection", "Kinvey Ping Failed", t);
	//		    }
	//		    public void onSuccess(Boolean b) {
	//		        Log.d("Probando Kinvey Connection", "Kinvey Ping Success");
	//		    }
	//		});
	//		return this.mKinveyClient;
	//	}

	public Client getmKinveyClient() {
		return mKinveyClient;
	}

	public void setmKinveyClient(Client mKinveyClient) {
		this.mKinveyClient = mKinveyClient;
	}


	public void cargarUsuarioLogueado (String nombre){
		this.mKinveyClient.user().setUsername(nombre);
	}

	public Client captarUsuarioLogueado (){
		return this.mKinveyClient;
	}



	public UsuarioBackend getUsuBackend() {
		return usuBackend;
	}

	public void setUsuBackend(UsuarioBackend usuBackend) {
		this.usuBackend = usuBackend;
	}

	//Funcion para obtener NOTIFICACIONES
	public void traerNotificaciones(int idUsuario){
		//		mKinveyClient.appData("NotificacionesUsuario", NotificacionesBackend.class).get(myQuery, new KinveyListCallback<NotificacionesBackend>() {
		//			@Override
		//			public void onSuccess(NotificacionesBackend[] resultadoconsulta) {
		//				for (int i = 0; i < resultadoconsulta.length; i++) {
		//					NotificacionesBackend nb= new NotificacionesBackend(resultadoconsulta[i].getIdUsuario(),resultadoconsulta[i].getTipo(),resultadoconsulta[i].getTitulo(),resultadoconsulta[i].getTexto());
		//					listaNotificaciones.add(nb);
		//				}
		//			}
		//			@Override
		//			public void onFailure(Throwable arg0) {
		//				// TODO Auto-generated method stub
		//
		//			}
		//		});
		String idO = idUsuario + "";
		Query query = mKinveyClient.query ();
		query.equals("idNotificacion", idO);
		AsyncAppData<NotificacionesBackend> searchedEvents = mKinveyClient.appData("NotificacionesUsuario", NotificacionesBackend.class);
		searchedEvents.get(query, new KinveyListCallback<NotificacionesBackend>() {
			@Override
			public void onSuccess(NotificacionesBackend[] resultadoconsulta) { 
				for (int i = 0; i < resultadoconsulta.length; i++) {
					NotificacionesBackend nb= new NotificacionesBackend(resultadoconsulta[i].getIdUsuario(),resultadoconsulta[i].getTipo(),resultadoconsulta[i].getTitulo(),resultadoconsulta[i].getTexto());
					listaNotificaciones.add(nb);
				}
			}



			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	public UsuarioBackend obtenerNacimientoUsuario (String nombreUsuario){

		Query query = mKinveyClient.query ();
		query.equals("username", nombreUsuario);
		AsyncAppData<UsuarioBackend> searchedEvents = mKinveyClient.appData("Usuario", UsuarioBackend.class);
		searchedEvents.get(query, new KinveyListCallback<UsuarioBackend>() {
			@Override
			public void onSuccess(UsuarioBackend[] resultadoconsulta) { 
				for (int i = 0; i < resultadoconsulta.length; i++) {
					usuBackend = new UsuarioBackend(resultadoconsulta[i].getNombreUsuario(),resultadoconsulta[i].getFechaNacimiento(),resultadoconsulta[i].getMascaras());
					Log.d("Entre usuario obtenido: " + usuBackend.getNombreUsuario().toString(), "a ver");
				}
			}


			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}
		});
		return usuBackend;
	}

	
	public void guardarUsuarioBackendLogueado(UsuarioBackend u){
		this.usuarioLogueado = u;
	}
	
	public UsuarioBackend obtenerUsuarioBackendLogueado(){
		//this.usuarioLogueado = u;
		return this.usuarioLogueado;
	}
	
	
}











