package dominio;

import java.util.Date;

import android.R.integer;
import android.os.Parcel;
import android.os.Parcelable;

public class Funcion implements Parcelable{
	//Variables
	private Integer idFuncion;
	private Double duracion;
<<<<<<< HEAD
	private Date horaComiento;
	//Constructor
	public Funcion(Integer idFuncion, Double duracion, Date horaComiento) {
=======
	private Date horaComienzo;
	private Date fechaObra;
	
	public Funcion(integer idFuncion, Double duracion, Date horaComienzo,Date fechaObra) {
>>>>>>> refs/heads/master
		super();
		this.idFuncion = idFuncion;
		this.duracion = duracion;
		this.horaComienzo = horaComienzo;
		this.fechaObra = fechaObra;
	}
<<<<<<< HEAD
	public Funcion (Parcel in) {
//		listaButacas = new ArrayList<Butaca>();
		readFromParcel(in);
	}
	
	//get and set
	public Integer getIdFuncion() {
=======

	public integer getIdFuncion() {
>>>>>>> refs/heads/master
		return idFuncion;
	}
<<<<<<< HEAD
	public void setIdFuncion(Integer idFuncion) {
=======

	public void setIdFuncion(integer idFuncion) {
>>>>>>> refs/heads/master
		this.idFuncion = idFuncion;
	}

	public Double getDuracion() {
		return duracion;
	}

	public void setDuracion(Double duracion) {
		this.duracion = duracion;
	}

	public Date getHoraComienzo() {
		return horaComienzo;
	}

	public void setHoraComienzo(Date horaComienzo) {
		this.horaComienzo = horaComienzo;
	}
<<<<<<< HEAD
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeSerializable(horaComiento);
		dest.writeInt(idFuncion);
		dest.writeDouble(duracion);
		
	}
	private void readFromParcel(Parcel in) {
		idFuncion= in.readInt();
		duracion= in.readDouble();
		horaComiento= (java.util.Date) in.readSerializable();
		
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
=======

	public Date getFechaObra() {
		return fechaObra;
	}

	public void setFechaObra(Date fechaObra) {
		this.fechaObra = fechaObra;
	}
	
	
	
	
	
>>>>>>> refs/heads/master
	

}
