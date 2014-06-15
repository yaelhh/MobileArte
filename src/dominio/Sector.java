package dominio;

import android.R.integer;

public class Sector {
//Variables
	private integer idSector;
	private Precio miPrecio;
	private integer totalButacas;
//constructor
	public Sector(integer idSector, integer totalButacas) {
		super();
		this.idSector = idSector;
		this.totalButacas = totalButacas;
	}
//get and set
	public integer getIdSector() {
		return idSector;
	}
	public void setIdSector(integer idSector) {
		this.idSector = idSector;
	}
	public Precio getMiPrecio() {
		return miPrecio;
	}
	public void setMiPrecio(Precio miPrecio) {
		this.miPrecio = miPrecio;
	}
	public integer getTotalButacas() {
		return totalButacas;
	}
	public void setTotalButacas(integer totalButacas) {
		this.totalButacas = totalButacas;
	}
	
}
