package backend;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;


public class ObraBackend extends GenericJson {

	@Key("_id") // nombre del campo definido en el backend
	private String idObras;
	@Key
	private String nombreObras;
	@Key
	private String descripcipnObras;
	 
	
	public ObraBackend() {}
	
	public ObraBackend (String nombreObra) {
		super();
		this.nombreObras = nombreObra;
	}
	
	public String getIdObra() {
		return this.idObras;
	}
		
	public String getNombreObras() {
		return this.nombreObras;
	}

	public void setNombreSala(String nombreObra) {
		this.nombreObras = nombreObra;
	}
	
	public String getDescripcipnObras() {
		return this.descripcipnObras;
	}

	public void setDescripcipnObras(String descripcionObra) {
		this.descripcipnObras = descripcionObra;
	}
}