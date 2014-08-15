package backend;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.kinvey.java.LinkedResources.LinkedGenericJson;


public class ObraBackend extends LinkedGenericJson {

	@Key("_id") // nombre del campo definido en el backend
	private int idObras;
	@Key
	private String descripcionObras;
	@Key
	private String nombreObras;
//	@Key("imagenAdjunta")
//    private String text;
	 
	
	public ObraBackend() {
		//"attachment" is the JSON element used to maintain a Linked File.
		//putFile("attachment");
	}
	
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