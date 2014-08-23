package backend;

import com.google.api.client.util.Key;
import com.kinvey.java.LinkedResources.LinkedGenericJson;

public class ObrasFavoritosBackend extends LinkedGenericJson {
	
	@Key("_id") // nombre del campo definido en el backend
	private String idObraFavorito;
	@Key("nombreObra")
	private String nombreObra;
	@Key("nombreUsuario")
	private String nomUsuario;
	
	
	public ObrasFavoritosBackend() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ObrasFavoritosBackend(String nombreObra, String nomUsuario) {
		super();
		this.nombreObra = nombreObra;
		this.nomUsuario = nomUsuario;
	}


	public String getNombreObra() {
		return nombreObra;
	}


	public void setNombreObra(String nombreObra) {
		this.nombreObra = nombreObra;
	}


	public String getNomUsuario() {
		return nomUsuario;
	}


	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}


	public String getIdObraFavorito() {
		return idObraFavorito;
	}
}