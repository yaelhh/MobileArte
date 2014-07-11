package dominio;

import java.util.Date;

import android.R.integer;
import android.os.Parcel;
import android.os.Parcelable;

public class Funcion implements Parcelable{
	//Variables
	private int idFuncion;
	private Double duracion;
	private Date horaComienzo;
	private Date fechaObra;
	//Constructor
	
	public Funcion(int idFuncion, Double duracion,Date fechaObra) {

		super();
		this.idFuncion = idFuncion;
		this.duracion = duracion;
		this.horaComienzo = horaComienzo;
		this.fechaObra = fechaObra;
	}

	public Funcion (Parcel in) {
//		listaButacas = new ArrayList<Butaca>();
		readFromParcel(in);
	}
	
	//get and set
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

	public Date getHoraComienzo() {
		return horaComienzo;
	}

	public void setHoraComienzo(Date horaComienzo) {
		this.horaComienzo = horaComienzo;
	}
	public Date getFechaObra() {
		return fechaObra;
	}

	public void setFechaObra(Date fechaObra) {
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
		dest.writeSerializable(horaComienzo);
		dest.writeInt(idFuncion);
		dest.writeDouble(duracion);
		
	}
	private void readFromParcel(Parcel in) {
		idFuncion= in.readInt();
		duracion= in.readDouble();
		horaComienzo= (java.util.Date) in.readSerializable();
		
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


	

	

}
