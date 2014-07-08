package dominio;

import java.util.Date;

import android.R.integer;

public class Funcion {
	//Variables
	private integer idFuncion;
	private Double duracion;
	private Date horaComienzo;
	private Date fechaObra;
	
	public Funcion(integer idFuncion, Double duracion, Date horaComienzo,Date fechaObra) {
		super();
		this.idFuncion = idFuncion;
		this.duracion = duracion;
		this.horaComienzo = horaComienzo;
		this.fechaObra = fechaObra;
	}

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

	public Date getHoraComienzo() {
		return horaComienzo;
	}

	public void setHoraComienzo(Date horaComienzo) {
		this.horaComienzo = horaComienzo;
	}

	public Date getFechaObra() {
		return fechaObra;
	}

	public void setFechaObra(Date fechaObra) {
		this.fechaObra = fechaObra;
	}
	
	
	
	
	
	

}
