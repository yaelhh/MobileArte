package backend;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;


public class SalaBackend extends GenericJson {

	@Key("_id") // nombre del campo definido en el backend
	private String idSala;
	@Key
	private String SalaNombre;
	@Key
	private int capacidad;
	 
	
	public SalaBackend() {}
	
	public SalaBackend (String nombreSala) {
		super();
		this.SalaNombre = nombreSala;
	}
	
	public String getIdSala() {
		return this.idSala;
	}
		
	public String getNombreSala() {
		return this.SalaNombre;
	}

	public void setNombreSala(String nombreSala) {
		this.SalaNombre = nombreSala;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	
}