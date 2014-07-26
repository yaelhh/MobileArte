package com.example.clientarte;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import android.widget.TextView;

import com.example.clientarte.R.drawable;
import com.kinvey.android.Client;

import dominio.*;

public class Objetos {
	
	
	//Creo listas 
	
	private ArrayList<Funcion> listaFunciones;
	private ArrayList<Sala> listaSalas;
	private ArrayList<Sector> listaSectores;
	//	private ArrayList<Comodidad> listaComodidades;
	private ArrayList<Obra> listaObra;
	private ArrayList<Butaca> listabutacas;
	ArrayList<Butaca>listabutacas2;
	ArrayList<Butaca> listabutacas3;
	ArrayList<Butaca> listabutacas4;
	
	private Funcion funcionA;
	private Funcion funcionB;
	private Obra obra1;
	private Obra obra2;
	private Sala sala1;
	private Sala sala2;
	private Sector sector1;
	private Sector sector2;
	private Sector sector3;
	private Sector sector4;

	private Butaca miButaca;
	private HashMap<String, Boolean> hashComodidades = new HashMap <String, Boolean> ();
	
	
	

	public void Objetos(){
		ArrayList listaImagenes= new ArrayList();
		ArrayList<Funcion> listaFunciones= new ArrayList<Funcion>();
		ArrayList<Sala> listaSalas= new ArrayList<Sala>();
		ArrayList<Sector> listaSectores= new ArrayList<Sector>();
		//	private ArrayList<Comodidad> listaComodidades;
		ArrayList<Obra> listaObra= new ArrayList<Obra>();
		ArrayList<Butaca> listabutacas= new ArrayList<Butaca>();
		ArrayList<Butaca>listabutacas2 = new ArrayList<Butaca>();
		ArrayList<Butaca> listabutacas3 = new ArrayList<Butaca>();
		
	}
	
	//Funcion para obtener obras
	public ArrayList<Obra> getListObra(){
		// Creo Objetos funciones
		listaFunciones= new ArrayList<Funcion>();
		Date fechaFuncion= new Date(19,29,00);
		funcionA= new Funcion(11, 3.0,"20-11-2014","19:30:00");
		funcionB= new Funcion(12, 3.5,"20-11-2014","20:30:00");
		listaFunciones.add(funcionA);
		listaFunciones.add(funcionB);
		//Creo obras
		obra1= new Obra(1,"Carmen");
		obra1.setListaFunciones(listaFunciones);
		int [] listaImagenes= new int[3];
		listaImagenes[0]=R.drawable.carmen_1;
		listaImagenes[1]=R.drawable.carmen_2;
		listaImagenes[2]=R.drawable.carmen_3;
		obra1.setListaImagenes(listaImagenes);
		obra1.setDescripcion("Carmen es una opéra comique francesa en cuatro actos con música de Georges Bizet y libreto en francés de Ludovic Halévy y Henri Meilhac, basado en la novela Carmen de Prosper Mérimée, publicada por vez primera en 1845,2 la cual a su vez posiblemente estuviera influida por el poema narrativo Los gitanos (1824) de Aleksander Pushkin.3 Mérimée había leído el poema en ruso en 1840 y lo tradujo al francés en 1852");
		
		obra2= new Obra(2,"Les Luthiers. ‘Viejos hazmerreíres’");
		obra2.setListaFunciones(listaFunciones);
		int[] listaImagenes2= new int[3];
		listaImagenes2[0]=R.drawable.les_1;
		listaImagenes2[1]=R.drawable.les_2;
		listaImagenes2[2]=R.drawable.les_3;
		obra2.setListaImagenes(listaImagenes2);
		obra2.setDescripcion("“Viejos hazmerreíres” es una antología de grandes éxitos de Les Luthiers, pero con un total reordenamiento de las escenas.Los momentos más brillantes y reideros en un nuevo contexto.");
		listaObra= new ArrayList<Obra>();
		listaObra.add(obra1);
		listaObra.add(obra2);
		return listaObra;
	}

	//Funcion para obtener salas
	public ArrayList<Sala> getListSalas(){
		listaSalas= new ArrayList<Sala>();
		hashComodidades.put("wifi", true);
		hashComodidades.put("accesibilidad", false);
		hashComodidades.put("Contra incendio", true);
		listaSectores=getListSector();
		sala1 = new Sala(1, "Eduardo Fabini",listaSectores,hashComodidades, 2000);
		sala1.setListaSectores(listaSectores);
		sala2 = new Sala(2, "Hugo Balzo",listaSectores,hashComodidades, 280);
		listaSalas.add(sala1);
		listaSalas.add(sala2);
		return listaSalas;
	}

	public ArrayList<Sector> getListSector(){
		sector1 = new Sector();
		sector1.setIdSector(1); 
		sector1.setTotalButacas(20);
		sector1.setLinea(20);
		sector1.setPrecioSector(1000);

		sector2 = new Sector();
		sector2.setIdSector(2); 
		sector2.setTotalButacas(133);
		sector2.setLinea(19);
		sector2.setPrecioSector(2300);

		sector3 = new Sector();
		sector3.setIdSector(3); 
		sector3.setTotalButacas(75);
		sector3.setLinea(25);
		sector3.setPrecioSector(1500);

		sector4 = new Sector();
		sector4.setIdSector(3); 
		sector4.setTotalButacas(75);
		sector4.setLinea(25);
		sector4.setPrecioSector(1200);
		
		//Crear las butacas del sector 
		listabutacas = new ArrayList<Butaca>();
		listabutacas2 = new ArrayList<Butaca>();
		listabutacas3 = new ArrayList<Butaca>();
		listabutacas4 = new ArrayList<Butaca>();

		for (int i = 0; i < sector1.getTotalButacas(); i ++) {
			miButaca = new Butaca();
			miButaca.setIdButaca(i);
			miButaca.setEstadoButaca(true);
			listabutacas.add(miButaca);
			//Log.d("Creación de la lista de butacas", "ID Butaca: " + int.toString(butaca.getIdButaca()));
		}
		for (int i = 0; i < sector2.getTotalButacas(); i ++) {
			miButaca = new Butaca();
			miButaca.setIdButaca(i);
			miButaca.setEstadoButaca(true);
			listabutacas2.add(miButaca);
			//Log.d("Creación de la lista de butacas", "ID Butaca: " + int.toString(butaca.getIdButaca()));
		}
		for (int i = 0; i < sector3.getTotalButacas(); i ++) {
			miButaca = new Butaca();
			miButaca.setIdButaca(i);
			miButaca.setEstadoButaca(true);
			listabutacas3.add(miButaca);
			//Log.d("Creación de la lista de butacas", "ID Butaca: " + int.toString(butaca.getIdButaca()));
		}
		for (int i = 0; i < sector4.getTotalButacas(); i ++) {
			miButaca = new Butaca();
			miButaca.setIdButaca(i);
			miButaca.setEstadoButaca(true);
			listabutacas4.add(miButaca);
			//Log.d("Creación de la lista de butacas", "ID Butaca: " + int.toString(butaca.getIdButaca()));
		}
		//Agrego la lista de butacas al sector
		sector1.setListaButacas(listabutacas);
		sector2.setListaButacas(listabutacas2);
		sector3.setListaButacas(listabutacas3);
		sector4.setListaButacas(listabutacas4);
		
		listaSectores= new ArrayList<Sector>();
		//	Agrego el sector a la lista de sectores
		listaSectores.add(sector1);
		listaSectores.add(sector2);
		listaSectores.add(sector3);

		return listaSectores;
	}



}
