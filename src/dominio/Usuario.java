package dominio;

import java.util.ArrayList;
import java.util.Date;

import android.R.integer;

public class Usuario {
//	Variables
	private integer idUsuario;
	private String miNombre;
	private String miApellido;
	private String miNombreUsuario;
	private Date fechaNacimiento;
	private integer mascaras;
	private ArrayList<Obra> misObrasVistas;
	private ArrayList<Obra> misProximasObras;
	private boolean estaLogueado;
	
//Constructor
	public Usuario(integer idUsuario, String miNombre, String miApellido,
			String miNombreUsuario, Date fechaNacimiento, integer mascaras,
			ArrayList<Obra> misObrasVistas, ArrayList<Obra> misProximasObras, boolean estaLogueado) {
		super();
		this.idUsuario = idUsuario;
		this.miNombre = miNombre;
		this.miApellido = miApellido;
		this.miNombreUsuario = miNombreUsuario;
		this.fechaNacimiento = fechaNacimiento;
		this.mascaras = mascaras;
		this.misObrasVistas = misObrasVistas;
		this.misProximasObras = misProximasObras;
		this.estaLogueado = estaLogueado;
	}
//get and set
	public integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getMiNombre() {
		return miNombre;
	}
	public void setMiNombre(String miNombre) {
		this.miNombre = miNombre;
	}
	public String getMiApellido() {
		return miApellido;
	}
	public void setMiApellido(String miApellido) {
		this.miApellido = miApellido;
	}
	public String getMiNombreUsuario() {
		return miNombreUsuario;
	}
	public void setMiNombreUsuario(String miNombreUsuario) {
		this.miNombreUsuario = miNombreUsuario;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public integer getMascaras() {
		return mascaras;
	}
	public void setMascaras(integer mascaras) {
		this.mascaras = mascaras;
	}
	public ArrayList<Obra> getMisObrasVistas() {
		return misObrasVistas;
	}
	public void setMisObrasVistas(ArrayList<Obra> misObrasVistas) {
		this.misObrasVistas = misObrasVistas;
	}
	public ArrayList<Obra> getMisProximasObras() {
		return misProximasObras;
	}
	public void setMisProximasObras(ArrayList<Obra> misProximasObras) {
		this.misProximasObras = misProximasObras;
	}
	public boolean isEstaLogueado() {
		return estaLogueado;
	}
	public void setEstaLogueado(boolean estaLogueado) {
		this.estaLogueado = estaLogueado;
	}
	
	

}
