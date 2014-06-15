package dominio;

import java.util.Date;

import android.R.integer;

public class Funcion {
	//Variables
	private integer idFuncion;
	private Double duracion;
	private Date horaComiento;
	//Constructor
	public Funcion(integer idFuncion, Double duracion, Date horaComiento) {
		super();
		this.idFuncion = idFuncion;
		this.duracion = duracion;
		this.horaComiento = horaComiento;
	}
	//get and set
	public integer getIdFuncion() {
		return idFuncion;
	}
	public void setIdFuncion(integer idFuncion) {
		this.idFuncion = idFuncion;
	}
	public Double getDuracion() {
		return duracion;
	}
	public void setDuracion(Double duracion) {
		this.duracion = duracion;
	}
	public Date getHoraComiento() {
		return horaComiento;
	}
	public void setHoraComiento(Date horaComiento) {
		this.horaComiento = horaComiento;
	}
	
	
	

}
