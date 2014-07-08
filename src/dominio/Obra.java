
package dominio;

import java.util.ArrayList;

public class Obra {
	//Variables
	private int idObra;
	private String nombre;
	private String descripcion;
	private ArrayList listaImagenes;
	private ArrayList listaFunciones;
//	get and set
	public int getIdObra() {
		return idObra;
	}
	public void setIdObra(int idObra) {
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
	public Obra(int idObra, String nombre) {
		super();
		this.idObra = idObra;
		this.nombre = nombre;
	}
	public Obra() {
		// TODO Auto-generated constructor stub
	}
	
	public Obra(int idObra, String nombre, String descripcion) {
		super();
		this.idObra = idObra;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Obra(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Obra(String nombre) {
		super();
		this.nombre = nombre;
	}
	
}
