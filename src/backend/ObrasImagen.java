package backend;

import com.google.api.client.util.Key;
import com.kinvey.java.LinkedResources.LinkedGenericJson;

public class ObrasImagen extends LinkedGenericJson{
	
	@Key("_id") // nombre del campo definido en el backend
	private int idObra;
	@Key("imagen")
	private String imagen;
	
	public ObrasImagen() {
		//"attachment" is the JSON element used to maintain a Linked File.
		putFile("attachment");
	}
	

	public ObrasImagen(int idObra, String imagen) {
		super();
		this.idObra = idObra;
		this.imagen = imagen;
	}

	public int getIdObra() {
		return idObra;
	}

	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
}
