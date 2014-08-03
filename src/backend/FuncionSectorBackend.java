package backend;


import com.google.api.client.util.Key;
import com.kinvey.java.LinkedResources.LinkedGenericJson;


public class FuncionSectorBackend extends LinkedGenericJson {
	
	@Key("_id")
	private String id;
	@Key
	private int idFuncion;
	@Key
	private int idSector;
	
	public FuncionSectorBackend(){}
	
	public int getIdFuncion() {
		return idFuncion;
	}
	public void setIdFuncion(int idFuncion) {
		this.idFuncion = idFuncion;
	}
	public int getIdSector() {
		return idSector;
	}
	public void setIdSector(int idSector) {
		this.idSector = idSector;
	}
	
	
	
}