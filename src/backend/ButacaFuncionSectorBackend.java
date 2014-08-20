package backend;


import com.google.api.client.util.Key;
import com.kinvey.java.LinkedResources.LinkedGenericJson;


public class ButacaFuncionSectorBackend extends LinkedGenericJson {
	
	@Key("_id")
	 private String id;
	 @Key("estadoButaca")
	private String estadoButaca;
	 @Key("idButaca")
	 private String idButaca;
	 @Key ("idFuncion")
	 private String idFuncion;
	 @Key
	 private String idSector;
	
	
	
	public ButacaFuncionSectorBackend(){}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getEstadoButaca() {
		return estadoButaca;
	}



	public void setEstadoButaca(String estadoButaca) {
		this.estadoButaca = estadoButaca;
	}



	public String getIdButaca() {
		return idButaca;
	}



	public void setIdButaca(String idButaca) {
		this.idButaca = idButaca;
	}



	public String getIdFuncion() {
		return idFuncion;
	}



	public void setIdFuncion(String idFuncion) {
		this.idFuncion = idFuncion;
	}



	public String getIdSector() {
		return idSector;
	}



	public void setIdSector(String idSector) {
		this.idSector = idSector;
	}

		
}