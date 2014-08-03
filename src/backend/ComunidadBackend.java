package backend;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.kinvey.java.LinkedResources.LinkedGenericJson;


public class ComunidadBackend extends LinkedGenericJson{

	@Key("_id") // nombre del campo definido en el backend
	private String idComunidad;
	@Key
	private String descripcion;
	@Key
	private String usuario;
	@Key("imagenComentario")
	private String idImagen;
	@Key
	private String idObra;
	 
	
	public ComunidadBackend() {
		//"attachment" is the JSON element used to maintain a Linked File.
        putFile("attachment");
	}
	
	public ComunidadBackend (String desc) {
		super();
		this.descripcion = desc;
	}
	
	public String getIdComunidad() {
		return this.idComunidad;
	}
		
	public String getDescripcionComunidad() {
		return this.descripcion;
	}

	public void setDescripcionComunidad(String desc) {
		this.descripcion = desc;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(String idImagen) {
		this.idImagen = idImagen;
	}

	public String getIdObra() {
		return idObra;
	}

	public void setIdObra(String idObra) {
		this.idObra = idObra;
	}
	
	
}