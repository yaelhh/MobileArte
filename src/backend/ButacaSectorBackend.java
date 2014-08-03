package backend;


import com.google.api.client.util.Key;
import com.kinvey.java.LinkedResources.LinkedGenericJson;


public class ButacaSectorBackend extends LinkedGenericJson {
	
	@Key("_id")
	private String id;
	@Key
	private int idButaca;
	@Key
	private int idSector;
	@Key
	private int estadoButaca;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getIdButaca() {
		return idButaca;
	}
	public void setIdButaca(int idButaca) {
		this.idButaca = idButaca;
	}
	public int getIdSector() {
		return idSector;
	}
	public void setIdSector(int idSector) {
		this.idSector = idSector;
	}
	public int getEstadoButaca() {
		return estadoButaca;
	}
	public void setEstadoButaca(int estadoButaca) {
		this.estadoButaca = estadoButaca;
	}
	
	public ButacaSectorBackend(){}
}