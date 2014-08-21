package backend;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Application;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.java.Query;

import dominio.Butaca;
import dominio.Funcion;
import dominio.Obra;
import dominio.Sala;
import dominio.Sector;
import dominio.Usuario;

public class ObjetosBackend extends Application{


	//Creo listas 

	private ArrayList<Funcion> listaFunciones= new ArrayList<Funcion>();
	private ArrayList<Sala> listaSalas= new ArrayList<Sala>();
	private ArrayList<Sector> listaSectores=new ArrayList<Sector>();
	//	private ArrayList<Comodidad> listaComodidades;
	private ArrayList<Obra> listaObra= new ArrayList<Obra>();
	private ArrayList<Butaca> listabutacas= new ArrayList<Butaca>();
	ArrayList<Funcion> listaFuncionesObra= new ArrayList<Funcion>();
	ArrayList<Butaca>listabutacas2;
	ArrayList<Butaca> listabutacas3;
	ArrayList<Butaca> listabutacas4;

	private Funcion funcionA;
	private Funcion funcionB;
	private Obra obra1;
	private Obra obra2;
	private Sala sala1;
	private Sala sala2;
	private Usuario miUsuario;
//	private SectorBackend sector1;
//	private SectorBackend sector2;
//	private SectorBackend sector3;
//	private SectorBackend sector4;

	private Butaca miButaca;
	private HashMap<String, Boolean> hashComodidades = new HashMap <String, Boolean> ();
	public static final String TAG = "ArteBackend";
	private String appKey="kid_VT8_It3ePE";
	private String appSecret="1b0fa51481984d2da5910f78a9d26ccc";
	private String mensaje;
	private Client mKinveyClient;
	Query myQuery;



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
		//ArrayList<SectorBackend> listaSectores= new ArrayList<SectorBackend>();
		//	private ArrayList<Comodidad> listaComodidades;
		ArrayList<Obra> listaObra= new ArrayList<Obra>();
		ArrayList<Butaca> listabutacas= new ArrayList<Butaca>();
		ArrayList<Butaca>listabutacas2 = new ArrayList<Butaca>();
		ArrayList<Butaca> listabutacas3 = new ArrayList<Butaca>();


		//mKinveyClient = new Client.Builder(this.getApplicationContext()).build();
		//		conectarBackend();			

	}

	public ObjetosBackend(Client KinveyClient){
		mKinveyClient=KinveyClient;
		myQuery = mKinveyClient.query();
		ArrayList listaImagenes= new ArrayList();
		ArrayList<Funcion> listaFunciones= new ArrayList<Funcion>();
		ArrayList<Sala> listaSalas= new ArrayList<Sala>();
		//ArrayList<SectorBackend> listaSectores= new ArrayList<SectorBackend>();
		//	private ArrayList<Comodidad> listaComodidades;
		ArrayList<Obra> listaObra= new ArrayList<Obra>();
		ArrayList<Butaca> listabutacas= new ArrayList<Butaca>();
		ArrayList<Butaca>listabutacas2 = new ArrayList<Butaca>();
		ArrayList<Butaca> listabutacas3 = new ArrayList<Butaca>();
		
		//		crearListObra();

	}

//	public void inicialize(Client KinveyClient){
//		mKinveyClient=KinveyClient;
//		myQuery = mKinveyClient.query();
//		ArrayList listaImagenes= new ArrayList();
//		ArrayList<Funcion> listaFunciones= new ArrayList<Funcion>();
//
//
//		ArrayList<Sala> listaSalas= new ArrayList<Sala>();
//		//ArrayList<SectorBackend> listaSectores= new ArrayList<SectorBackend>();
//		//	private ArrayList<Comodidad> listaComodidades;
//		ArrayList<Obra> listaObra= new ArrayList<Obra>();
//		ArrayList<Butaca> listabutacas= new ArrayList<Butaca>();
//		ArrayList<Butaca>listabutacas2 = new ArrayList<Butaca>();
//		ArrayList<Butaca> listabutacas3 = new ArrayList<Butaca>();
//		//		crearListObra();
//		//		crearListFunciones();
//
//	}
//
//
//	public void traerDatos(){
//		crearListObra();
//		crearUsuario ();
////		crearListFunciones();
////		crearListSalas();
////		crearSectores();
////		AgregarButacaSector();
////		AgregarSectorAFuncion();	
////		AgregarObrasaSalas();
//	}

	public ArrayList<Obra> getListObras(){
		return this.listaObra;
	}

	public ArrayList<Sala> getListSalas(){
		return  this.listaSalas;
	}


	//Funcion para obtener obras
	public void crearListObra(){
		//		Query myQuery = mKinveyClient.query();
		/**
		 Cargo las obras de la tabla
		 */
		mKinveyClient.appData("Obras", ObraBackend.class).get(myQuery, new KinveyListCallback<ObraBackend>() {
			@Override
			public void onSuccess(ObraBackend[] resultadoconsulta) {
				for (int i = 0; i < resultadoconsulta.length; i++) {
					//Obra obra= new Obra(resultadoconsulta[i].getIdObra(),resultadoconsulta[i].getNombreObras(),resultadoconsulta[i].getDescripcipnObras());
					//listaObra.add(obra);
				}
			}

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	
	public void crearUsuario(){
		mKinveyClient.appData("Usuario", UsuarioBackend.class).get(myQuery, new KinveyListCallback<UsuarioBackend>() {
			@Override
			public void onSuccess(UsuarioBackend[] resultadoconsulta) {
				for (int i = 0; i < resultadoconsulta.length; i++) {
					//Obra obra= new Obra(resultadoconsulta[i].getIdObra(),resultadoconsulta[i].getNombreObras(),resultadoconsulta[i].getDescripcipnObras());
					//listaObra.add(obra);
				}
			}

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	/** 
		 Cargo las funciones de la tabla
	 */
//	public void crearListFunciones(){
//		//			Query myQuery = mKinveyClient.query();
//		mKinveyClient.appData("Funciones", FuncionBackend.class).get(myQuery, new KinveyListCallback<FuncionBackend>() {
//			@Override
//			public void onSuccess(FuncionBackend[] resultadoconsulta) {
//				for (int i = 0; i < resultadoconsulta.length; i++) {
//					//					ArrayList<Funcion> listaFuncionesObra= new ArrayList<Funcion>();
//					Funcion funcion= new Funcion(resultadoconsulta[i].getIdFuncion(),Double.parseDouble(resultadoconsulta[i].getDuracion()),resultadoconsulta[i].getFechaObra(),resultadoconsulta[i].getHoraComienzo());
//					for(int x=0;x<listaObra.size()-1;x++){
//						if(resultadoconsulta[i].getIdObra()==listaObra.get(x).getIdObra()){
//							//						if(resultadoconsulta[i].getIdObra().equalsIgnoreCase(listaObra.get(x).getIdObra())){
//							//							Log.e("resultado "+ i+" " +resultadoconsulta[i].getIdObra(), "lista  "+x+" "+listaObra.get(x).getIdObra());
//							listaObra.get(x).getListaFunciones().add(funcion);
//							break;
//						}
//
//					}
//
//				}
//
//			}
//
//			@Override
//			public void onFailure(Throwable arg0) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//
//
//
//	}

	//Funcion para obtener salas
	//public void crearListSalas(){
		//			Query myQuery = mKinveyClient.query();
		/**
			 Cargo las sala de la tabla
		 */
//		mKinveyClient.appData("Sala", SalaBackend.class).get(myQuery, new KinveyListCallback<SalaBackend>() {
//			@Override
//			public void onSuccess(SalaBackend[] resultadoconsulta) {
//				for (int i = 0; i < resultadoconsulta.length; i++) {
//					Sala sala= new Sala(resultadoconsulta[i].getIdSala(),resultadoconsulta[i].getNombreSala(),resultadoconsulta[i].getCapacidad());
//					listaSalas.add(sala);
//				}
//			}

//			@Override
//			public void onFailure(Throwable arg0) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//	}
//
//	public void AgregarObrasaSalas(){
//		/**
//			 Cargo la tabla salaObras
//		 */
//		mKinveyClient.appData("ObrasSalas", ObrasSalasBackend.class).get(myQuery, new KinveyListCallback<ObrasSalasBackend>() {
//			@Override
//			public void onSuccess(ObrasSalasBackend[] resultadoconsulta) {
//				for (int i = 0; i < resultadoconsulta.length; i++) {
//					for (int x = 0; x < listaObra.size(); x++) {
//						Obra obraActual= listaObra.get(x);
//						if(resultadoconsulta[i].getIdObras()==obraActual.getIdObra()){
//							for (int y = 0; y < listaSalas.size(); y++) {
//								Sala salaActual=listaSalas.get(y);
//								if(resultadoconsulta[i].getIdSalas().equalsIgnoreCase(salaActual.getIdSala())){
//									listaSalas.get(y).getListaObras().add(obraActual);
//									Log.e(salaActual.getIdSala(),obraActual.getIdObra()+"");
//								}	
//							}
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
//
//
//
//	public void crearSectores(){	
//		/**
//			 Creo Sectores
//		 */
//		mKinveyClient.appData("Sector", SectorBackend.class).get(myQuery, new KinveyListCallback<SectorBackend>() {
//			@Override
//			public void onSuccess(SectorBackend[] resultadoconsulta) {
//				for (int i = 0; i < resultadoconsulta.length; i++) {
//					Sector sector= new Sector(resultadoconsulta[i].getIdSector(),resultadoconsulta[i].getTotalButacas(),resultadoconsulta[i].getLinea(),resultadoconsulta[i].getPrecioSector());
//					listaSectores.add(sector);
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
//
//	/**
//		 Traigo tabla FuncionSector
//	 */
//	public void AgregarSectorAFuncion(){	
//		/**
//			 Traigo datos funcionSector y cargo la lista de sectores en la funcion
//		 */
//		mKinveyClient.appData("FuncionSector", FuncionSectorBackend.class).get(myQuery, new KinveyListCallback<FuncionSectorBackend>() {
//			@Override
//			public void onSuccess(FuncionSectorBackend[] resultadoconsulta) {
//				int rIdF,fidF,rIdS,sIds;
//
//				Log.e("Entre","entre");
//				for (int i = 0; i < resultadoconsulta.length; i++) {
//					for (int x = 0; x < listaObra.size(); x++) {
//						listaFunciones= listaObra.get(x).getListaFunciones();
//						for(int j=0;j<listaFunciones.size();j++){
//							Funcion funcion= listaFunciones.get(j);	
//							if(resultadoconsulta[i].getIdFuncion()==(funcion.getIdFuncion()) ){
//								for (int y = 0; y < listaSectores.size(); y++) {
//									rIdF=resultadoconsulta[i].getIdFuncion();
//									fidF=funcion.getIdFuncion();
//									rIdS=resultadoconsulta[i].getIdSector();
//									sIds=listaSectores.get(y).getIdSector();
//									if(resultadoconsulta[i].getIdSector()== listaSectores.get(y).getIdSector()){
//										Log.e("idFuncion",resultadoconsulta[i].getIdFuncion()+"-"+funcion.getIdFuncion()+" "+resultadoconsulta[i].getIdSector()+" - "+listaSectores.get(y).getIdSector());
//										listaObra.get(x).getListaFunciones().get(j).getListaSetores().add(listaSectores.get(y));
//									}
//								}
//							}
//						}	
//					}
//				}
//
//			}
//
//			@Override
//			public void onFailure(Throwable arg0) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//
//	}
//
//	/**
//	 Traigo tabla ButacaSector
//	 */
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
//
//	}




}
