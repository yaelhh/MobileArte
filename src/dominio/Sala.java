package dominio;

import java.util.ArrayList;

import android.R.integer;

public class Sala {
//Variables
	private Integer idSala;
	private String nombreSala;
	private ArrayList<Obra> listaObras;
	private ArrayList<Sector> listaSectores;
	private ArrayList<Comodidad> listaComodidades;
	private integer capacidad;
	
	//Constructor	
	public Sala(Integer idSala, String nombreSala,
			ArrayList<Sector> listaSectores,
			ArrayList<Comodidad> listaComodidades, integer capacidad) {
		super();
		this.idSala = idSala;
		this.nombreSala = nombreSala;
		this.listaSectores = listaSectores;
		this.listaComodidades = listaComodidades;
		this.capacidad = capacidad;
	}
	//Get and Set 
	public Integer getIdSala() {
		return idSala;
	}

	public void setIdSala(Integer idSala) {
		this.idSala = idSala;
	}

	public String getNombreSala() {
		return nombreSala;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}

	public ArrayList<Obra> getListaObras() {
		return listaObras;
	}

	public void setListaObras(ArrayList<Obra> listaObras) {
		this.listaObras = listaObras;
	}

	public ArrayList<Sector> getListaSectores() {
		return listaSectores;
	}

	public void setListaSectores(ArrayList<Sector> listaSectores) {
		this.listaSectores = listaSectores;
	}

	public ArrayList<Comodidad> getListaComodidades() {
		return listaComodidades;
	}

	public void setListaComodidades(ArrayList<Comodidad> listaComodidades) {
		this.listaComodidades = listaComodidades;
	}

	public integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(integer capacidad) {
		this.capacidad = capacidad;
	}	
	

	
}
