package dominio;

import java.util.Date;

import android.R.integer;
import android.os.Parcel;
import android.os.Parcelable;

public class Funcion implements Parcelable{
	//Variables
	private Integer idFuncion;
	private Double duracion;
	private Date horaComiento;
	//Constructor
	public Funcion(Integer idFuncion, Double duracion, Date horaComiento) {
		super();
		this.idFuncion = idFuncion;
		this.duracion = duracion;
		this.horaComiento = horaComiento;
	}
	public Funcion (Parcel in) {
//		listaButacas = new ArrayList<Butaca>();
		readFromParcel(in);
	}
	
	//get and set
	public Integer getIdFuncion() {
		return idFuncion;
	}
	public void setIdFuncion(Integer idFuncion) {
		this.idFuncion = idFuncion;
	}
	public Double getDuracion() {
		return duracion;
	}
	public void setDuracion(Double duracion) {
		this.duracion = duracion;
	}
	public Date getHoraComiento() {
		return horaComiento;
	}
	public void setHoraComiento(Date horaComiento) {
		this.horaComiento = horaComiento;
	}
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
	

}
