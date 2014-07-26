package dominio;


import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;


public class Sector implements Parcelable {

	private int idSector;
	private int totalButacas;
	private int linea;
	private ArrayList<Butaca> listaButacas;
	private int precioSector;

	
	public int getPrecioSector() {
		return precioSector;
	}

	public void setPrecioSector(int precioSector) {
		this.precioSector = precioSector;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	
	// Constructor por defecto
	public Sector() {
		// TODO Auto-generated constructor stub
		listaButacas = new ArrayList<Butaca>();
	}

	// Constructor para crear el objeto a partir de un parcelable
	public Sector (Parcel in) {
		listaButacas = new ArrayList<Butaca>();
		readFromParcel(in);
	}
	
	// Set & Get	
	public void setIdSector (int id) {
		this.idSector = id;
	}
	
	public void setTotalButacas (int cant) {
		this.totalButacas = cant;
	}
	
	public void setListaButacas (ArrayList<Butaca> lista) {
		this.listaButacas = lista;
	}
	
	public int getIdSector() {
		return this.idSector;
	}
	
	public int getTotalButacas() {
		return this.totalButacas;
	}
	
	public ArrayList<Butaca> getListaButacas() {
		return this.listaButacas;
	}
	
	//Método obligatorio
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	//Escribir a un parcel. OJO el orden es importante, debe escribirse como se declararon
	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		arg0.writeInt(idSector);
		arg0.writeInt(totalButacas);
		arg0.writeTypedList(listaButacas);
		arg0.writeInt(linea);
		arg0.writeInt(precioSector);
	}

	//Clase para recuperar los datos, IMPORTANTE leerlos en el mismo orden en que se escribieron 
	private void readFromParcel(Parcel in) {
		idSector = in.readInt();
		totalButacas = in.readInt();
		in.readTypedList(listaButacas, Butaca.CREATOR);
		linea= in.readInt();
		precioSector=in.readInt();
	}

	//Necesario para usar la clase en Arrays
	public static final Parcelable.Creator<Sector> CREATOR = new Parcelable.Creator<Sector>() {
		public Sector createFromParcel(Parcel in) {
			return new Sector (in);
		}

		public Sector [] newArray(int size) {
			return new Sector[size];
		}
	};

}
