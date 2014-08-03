package backend;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;


public class ObraBackend extends GenericJson {

	@Key("_id") // nombre del campo definido en el backend
	private int idObras;
	@Key
	private String descripcionObras;
	@Key
	private String nombreObras;
	 
	
	public ObraBackend() {}
	
	public ObraBackend (String nombreObra) {
		super();
		this.nombreObras = nombreObra;
	}
	public ObraBackend (String nombreObra,String descripcion) {
		super();
		this.nombreObras = nombreObra;
		this.descripcionObras= descripcion;
	}
	
	
	public int getIdObra() {
		return this.idObras;
	}
		
	public String getNombreObras() {
		return this.nombreObras;
	}

	public void setNombreSala(String nombreObra) {
		this.nombreObras = nombreObra;
	}
	
	public String getDescripcipnObras() {
		return this.descripcionObras;
	}

	public void setDescripcipnObras(String descripcionObra) {
		this.descripcionObras = descripcionObra;
	}
}