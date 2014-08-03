package backend;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class ObrasSalasBackend extends GenericJson {

	@Key("_id") // nombre del campo definido en el backend
	private int idObrasSala;
	@Key
	private int idObras;
	@Key
	private String idSalas;
	
	public ObrasSalasBackend(){}
	
	public int getIdObrasSala() {
		return idObrasSala;
	}
	public void setIdObrasSala(int idObrasSala) {
		this.idObrasSala = idObrasSala;
	}
	public int getIdObras() {
		return idObras;
	}
	public void setIdObras(int idObras) {
		this.idObras = idObras;
	}
	public String getIdSalas() {
		return idSalas;
	}
	public void setIdSalas(String idSalas) {
		this.idSalas = idSalas;
	}
	
	
	
	

}
