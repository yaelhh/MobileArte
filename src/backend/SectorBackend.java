package backend;


import java.util.ArrayList;
import java.util.List;

import com.google.api.client.util.Key;
import com.kinvey.java.LinkedResources.LinkedGenericJson;

import android.os.Parcel;
import android.os.Parcelable;


public class SectorBackend extends LinkedGenericJson {
	@Key("_id")
	private int idSector;
	@Key
	private int totalButacas;
	@Key
	private int linea;
	@Key
	private int precioSector;

	
	public int getPrecioSector() {
		return precioSector;
	}

	public void setPrecioSector(int precioSector) {
		this.precioSector = precioSector;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	
	// Constructor por defecto
	public SectorBackend() {
		// TODO Auto-generated constructor stub
		
	}

	
	// Set & Get	
	public void setIdSector (int id) {
		this.idSector = id;
	}
	
	public void setTotalButacas (int cant) {
		this.totalButacas = cant;
	}
	
	
	public int getIdSector() {
		return this.idSector;
	}
	
	public int getTotalButacas() {
		return this.totalButacas;
	}
	
	
	

}
