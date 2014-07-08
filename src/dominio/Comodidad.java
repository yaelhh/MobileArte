package dominio;

import android.R.integer;
//No estoy del todo segura si debería haber una clase comodidades o capaz con un simple array lo solucionamos
//REVISAR
public class Comodidad {
	private integer idComodidad;
	private boolean entradaDiscapacitados;
	private boolean detectorHumo;
	private boolean salidaEmergencia;
	private boolean wifi;
	public integer getIdComodidad() {
		return idComodidad;
	}
	public void setIdComodidad(integer idComodidad) {
		this.idComodidad = idComodidad;
	}
	public boolean isEntradaDiscapacitados() {
		return entradaDiscapacitados;
	}
	public void setEntradaDiscapacitados(boolean entradaDiscapacitados) {
		this.entradaDiscapacitados = entradaDiscapacitados;
	}
	public boolean isDetectorHumo() {
		return detectorHumo;
	}
	public void setDetectorHumo(boolean detectorHumo) {
		this.detectorHumo = detectorHumo;
	}
	public boolean isSalidaEmergencia() {
		return salidaEmergencia;
	}
	public void setSalidaEmergencia(boolean salidaEmergencia) {
		this.salidaEmergencia = salidaEmergencia;
	}
	public boolean isWifi() {
		return wifi;
	}
	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}
	
	

}
