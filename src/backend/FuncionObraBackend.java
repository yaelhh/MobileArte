package backend;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;


public class FuncionObraBackend extends GenericJson {

	@Key("_id") // nombre del campo definido en el backend
	private String idFuncionObra;
	@Key
	private String idObra;
	@Key
	private String idFuncion;
	 
	
	public FuncionObraBackend() {}

	

	public FuncionObraBackend(String idFuncionObra, String idObra,
			String idFuncion) {
		super();
		this.idFuncionObra = idFuncionObra;
		this.idObra = idObra;
		this.idFuncion = idFuncion;
	}



	public String getIdFuncionObra() {
		return idFuncionObra;
	}

	public String getIdObra() {
		return idObra;
	}


	public void setIdObra(String idObra) {
		this.idObra = idObra;
	}


	public String getIdFuncion() {
		return idFuncion;
	}


	public void setIdFuncion(String idFuncion) {
		this.idFuncion = idFuncion;
	}
	
	
	
}