
package dominio;

import java.util.ArrayList;

import android.R.integer;

public class Obra {
	//Variables
	private integer idObra;
	private String nombre;
	private ArrayList listaImagenes;
	private String descripcion;
	private ArrayList listaFunciones;
//	get and set
	public integer getIdObra() {
		return idObra;
	}
	public void setIdObra(integer idObra) {
		this.idObra = idObra;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList getListaImagenes() {
		return listaImagenes;
	}
	public void setListaImagenes(ArrayList listaImagenes) {
		this.listaImagenes = listaImagenes;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public ArrayList getListaFunciones() {
		return listaFunciones;
	}
	public void setListaFunciones(ArrayList listaFunciones) {
		this.listaFunciones = listaFunciones;
	}
//	Constructor
	public Obra(integer idObra, String nombre) {
		super();
		this.idObra = idObra;
		this.nombre = nombre;
	}
	
	
}
