package dominio;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Parcel;
import android.os.Parcelable;

public class Sala implements Parcelable{
//Variables
	private String idSala;
	private String nombreSala;
	private ArrayList<Obra> listaObras= new ArrayList<Obra>();
	private ArrayList<Sector> listaSectores;
	private HashMap<String, Boolean> listaComodidades;
	private int capacidad;
	
	//Constructor	
	public Sala(String idSala, String nombreSala, int capacidad) {
		this.idSala = idSala;
		this.nombreSala = nombreSala;
		this.capacidad= capacidad;
		ArrayList<Obra> listaObras= new ArrayList<Obra>();
	}
	public Sala(){
		ArrayList<Obra> listaObras= new ArrayList<Obra>();

	}
	public Sala(Parcel in) {
//		super();
//		this.idSala = idSala;
//		this.nombreSala = nombreSala;
//		this.listaSectores = listaSectores;
//		this.listaComodidades = listaComodidades;
//		this.capacidad = capacidad;
		readFromParcel(in);
	}
	//Get and Set 
	public String getIdSala() {
		return idSala;
	}

	public void setIdSala(String idSala) {
		this.idSala = idSala;
	}

	public String getNombreSala() {
		return nombreSala;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}

	public ArrayList<Obra> getListaObras() {
		return listaObras;
	}

	public void setListaObras(ArrayList<Obra> listaObras) {
		this.listaObras = listaObras;
	}

	public ArrayList<Sector> getListaSectores() {
		return listaSectores;
	}

	public void setListaSectores(ArrayList<Sector> listaSectores) {
		this.listaSectores = listaSectores;
	}

	public HashMap<String, Boolean> getListaComodidades() {
		return listaComodidades;
	}

	public void setListaComodidades(HashMap<String, Boolean> listaComodidades) {
		this.listaComodidades = listaComodidades;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.idSala);
//		dest.writeDouble(this.miPrecio);
		dest.writeString(this.nombreSala);
		dest.writeList(this.listaSectores);
		dest.writeInt(this.capacidad);
	}	
	private void readFromParcel(Parcel in) {
		this.idSala = in.readString();
		this.nombreSala = in.readString();
		in.readTypedList(this.listaSectores, Sector.CREATOR);
//		in.readTypedList(this.listaComodidades, Comodidad.CREATOR);
		this.capacidad = in.readInt();
			
   }
	public static final Parcelable.Creator<Sala> CREATOR
    = new Parcelable.Creator<Sala>() {
        public Sala createFromParcel(Parcel in) {
            return new Sala(in);
        }
 
        public Sala[] newArray(int size) {
            return new Sala[size];
        }
    };

	
}
