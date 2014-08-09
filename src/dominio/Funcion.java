package dominio;

import java.util.ArrayList;
import java.util.Date;

import com.kinvey.java.LinkedResources.LinkedGenericJson;

import android.R.integer;
import android.os.Parcel;
import android.os.Parcelable;

public class Funcion implements Parcelable, Comparable <Funcion>{
	//Variables
	private int idFuncion;
	private Double duracion;
	private String horaComienzo;
	private String fechaObra;
	private ArrayList<Sector> listaSectores= new ArrayList<Sector>();
	//Constructor



	public Funcion(int idFuncion, Double duracion,String fechaObra,String horaComienzo) {
		super();
		this.idFuncion = idFuncion;
		this.duracion = duracion;
		this.fechaObra = fechaObra;
		this.horaComienzo= horaComienzo;
		ArrayList<Sector> listaSectores= new ArrayList<Sector>();
	}

	public Funcion (Parcel in) {
		listaSectores = new ArrayList<Sector>();
		readFromParcel(in);
	}
	public Funcion(){}
	//get and set

	public ArrayList<Sector> getListaSetores() {
		return listaSectores;
	}

	public void setListaSectores(ArrayList<Sector> listaSectores) {
		this.listaSectores = listaSectores;
	}

	public int getIdFuncion() {

		return idFuncion;
	}

	public void setIdFuncion(int idFuncion) {
		this.idFuncion = idFuncion;
	}

	public Double getDuracion() {
		return duracion;
	}

	public void setDuracion(Double duracion) {
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


	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(idFuncion);
		dest.writeDouble(duracion);
		dest.writeString(fechaObra);
		dest.writeString(horaComienzo);
		dest.writeTypedList(listaSectores);

	}
	private void readFromParcel(Parcel in) {
		idFuncion= in.readInt();
		duracion= in.readDouble();
		fechaObra= in.readString();
		horaComienzo= in.readString();
		in.readTypedList(listaSectores, Sector.CREATOR);



	}
	//Necesario para usar la clase en Arrays
	public static final Parcelable.Creator<Funcion> CREATOR = new Parcelable.Creator<Funcion>() {
		public Funcion createFromParcel(Parcel in) {
			return new Funcion (in);
		}

		public Funcion [] newArray(int size) {
			return new Funcion[size];
		}
	};


	public String toString (){
		return  fechaObra +" - " + horaComienzo;
	}

	
	@Override
	public int compareTo(Funcion o) {
		return this.fechaObra.compareTo(o.fechaObra);
		
	} 
} 



