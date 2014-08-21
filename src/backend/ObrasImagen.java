package backend;

import com.google.api.client.util.Key;
import com.kinvey.java.LinkedResources.LinkedGenericJson;

public class ObrasImagen extends LinkedGenericJson{
	
	@Key("_id") // nombre del campo definido en el backend
	private String idObraImagen;
	@Key("idObra")
	private String idObra;
	@Key("imagen")
	private String idImagen;
	
	
	public ObrasImagen() {
		//"attachment" is the JSON element used to maintain a Linked File.
        putFile("attachment");
	}
	
	public String getIdObraImagen() {
		return idObraImagen;
	}


	public void setIdObraImagen(String idObraImagen) {
		this.idObraImagen = idObraImagen;
	}

	
	public String getIdObra() {	
		return idObra;
	}

	public void setIdObra(String idObra) {
		this.idObra = idObra;
	}

	public String getidImagen() {
		return idImagen;
	}

	public void setidImagen(String imagen) {
		this.idImagen = imagen;
	}
	
	
}
