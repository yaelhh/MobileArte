package backend;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class FuncionBackend extends GenericJson {
	//Variables
	
	@Key("_id")
	private int idFuncion;
	@Key
	private String duracion;
	@Key
	private String fechaObra;
	@Key
	private String horaComienzo;
	@Key
	private int idObra;
	
	//Constructor
	
	public FuncionBackend(int idFuncion, String duracion,String fechaObra,String horaComienzo,int idObra) {

		super();
		this.idFuncion = idFuncion;
		this.duracion = duracion;
		this.fechaObra = fechaObra;
		this.horaComienzo= horaComienzo;
		this.idObra= idObra;
	}
	 public FuncionBackend(){}
	
//	public FuncionBackend (Parcel in) {
////		listaButacas = new ArrayList<Butaca>();
//		readFromParcel(in);
//	}
	
	//get and set
	public int getIdFuncion() {

		return idFuncion;
	}

	public void setIdFuncion(int idFuncion) {
		this.idFuncion = idFuncion;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getHoraComienzo() {
		return horaComienzo;
	}

	public void setHoraComienzo(String horaComienzo) {
		this.horaComienzo = horaComienzo;
	}
	public String getFechaObra() {
		return fechaObra;
	}

	public void setFechaObra(String fechaObra) {
		this.fechaObra = fechaObra;
	}
	public int getIdObra() {
		return idObra;
	}
	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}
	

}
