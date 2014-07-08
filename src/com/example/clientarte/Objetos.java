package com.example.clientarte;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.clientarte.R.drawable;

import dominio.*;

public class Objetos {
	
	
	//Creo listas 
	
	private ArrayList<Funcion> listaFunciones;
	private ArrayList<Sala> listaSalas;
	private ArrayList<Sector> listaSectores;
	//	private ArrayList<Comodidad> listaComodidades;
	private ArrayList<Obra> listaObra;
	private ArrayList<Butaca> listabutacas;
	
	private Funcion funcionA;
	private Funcion funcionB;
	private Obra obra1;
	private Obra obra2;
	private Sala sala1;
	private Sala sala2;
	private Sector sector1;
	private Sector sector2;
	private Sector sector3;
	
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
	
	}
	
	//Funcion para obtener obras
	public ArrayList<Obra> getListObra(){
		// Creo Objetos funciones
		listaFunciones= new ArrayList<Funcion>();
		Date fechaFuncion= new Date(19,29,00);
		funcionA= new Funcion(11, 3.0,fechaFuncion);
		funcionB= new Funcion(12, 3.5,fechaFuncion);
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
		
		obra2= new Obra(2,"Les Luthiers. ‘Viejos hazmerreíres’");
		obra2.setListaFunciones(listaFunciones);
		int[] listaImagenes2= new int[3];
		listaImagenes2[0]=R.drawable.les_1;
		listaImagenes2[1]=R.drawable.les_2;
		listaImagenes2[2]=R.drawable.les_3;
		obra2.setListaImagenes(listaImagenes2);
		listaObra= new ArrayList<Obra>();
		listaObra.add(obra1);
		listaObra.add(obra2);
		return listaObra;
	}

	//Funcion para obtener salas
	public ArrayList<Sala> getListSalas(){
		hashComodidades.put("wifi", true);
		hashComodidades.put("accesibilidad", false);
		hashComodidades.put("Contra incendio", true);
		listaSectores=getListSector();
		sala1 = new Sala(1, "Nombre Sala",listaSectores,hashComodidades, 200);
		sala1.setListaSectores(listaSectores);
		sala2 = new Sala(2, "Nombre Sala2",listaSectores,hashComodidades, 200);
		listaSalas.add(sala1);
		listaSalas.add(sala2);
		return listaSalas;
	}

	public ArrayList<Sector> getListSector(){
		sector1 = new Sector();
		sector1.setIdSector(1); 
		sector1.setTotalButacas(24);

		sector2 = new Sector();
		sector2.setIdSector(2); 
		sector2.setTotalButacas(15);

		sector3 = new Sector();
		sector3.setIdSector(3); 
		sector3.setTotalButacas(20);


		//Crear las butacas del sector 
		listabutacas = new ArrayList<Butaca>();
		ArrayList<Butaca>listabutacas2 = new ArrayList<Butaca>();
		ArrayList<Butaca> listabutacas3 = new ArrayList<Butaca>();

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
		//Agrego la lista de butacas al sector
		sector1.setListaButacas(listabutacas);
		sector2.setListaButacas(listabutacas2);
		sector3.setListaButacas(listabutacas3);


		//	Agrego el sector a la lista de sectores
		listaSectores.add(sector1);
		listaSectores.add(sector2);
		listaSectores.add(sector3);

		return listaSectores;
	}



}
