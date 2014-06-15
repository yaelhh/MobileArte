package dominio;

import android.R.integer;

public class Butaca {
	private integer idButaca;
	private Sector miSector;
	private Boolean miEstado;
	public integer getIdButaca() {
		return idButaca;
	}
	public void setIdButaca(integer idButaca) {
		this.idButaca = idButaca;
	}
	public Sector getMiSector() {
		return miSector;
	}
	public void setMiSector(Sector miSector) {
		this.miSector = miSector;
	}
	public Boolean getMiEstado() {
		return miEstado;
	}
	public void setMiEstado(Boolean miEstado) {
		this.miEstado = miEstado;
	}
	public Butaca(integer idButaca, Sector miSector, Boolean miEstado) {
		super();
		this.idButaca = idButaca;
		this.miSector = miSector;
		this.miEstado = miEstado;
	}
	

}
