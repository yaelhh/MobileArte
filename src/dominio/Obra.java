
package dominio;

import java.util.ArrayList;


import android.R.integer;
import android.os.Parcel;
import android.os.Parcelable;

public class Obra implements Parcelable{

	private Integer idObra;

	private String nombre;

	private String descripcion;

	private ArrayList<Funcion> listaFunciones;
	private int[] listaImagenes;
//	private Sala sala;
//	private ArrayList<Sala> listaSalas;
//	
//	public ArrayList<Sala> getListaSalas() {
//		return listaSalas;
//	}
//	public void setListaSalas(ArrayList<Sala> listaSalas) {
//		this.listaSalas = listaSalas;
//	}
	public void setListaFunciones(ArrayList<Funcion> listaFunciones) {
		this.listaFunciones = listaFunciones;
	}
	//	get and set
	
	public int getIdObra() {
		return idObra;
	}

	public void setIdObra(Integer idObra) {
		this.idObra = idObra;
	}
	public String getNombre() {
		return nombre;
	}
	public ArrayList<Funcion> getListaFunciones() {
		return listaFunciones;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int[] getListaImagenes() {
		return listaImagenes;
	}
	public void setListaImagenes(int[] listaImagenes) {
		this.listaImagenes = listaImagenes;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
//	Constructor

	public Obra(int idObra, String nombre) {

		super();
		this.idObra = idObra;
		this.nombre = nombre;
		listaFunciones= new ArrayList<Funcion>();
		listaImagenes = new int[3];

	}
	public Obra(int idObra, String nombre, String descripcion) {
		super();
		this.idObra = idObra;
		this.nombre = nombre;
		this.descripcion = descripcion;
		listaFunciones= new ArrayList<Funcion>();
		listaImagenes = new int[3];

	}
	
	public Obra(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		listaFunciones= new ArrayList<Funcion>();
		listaImagenes = new int[3];
	}

	public Obra(String nombre) {
		super();
		this.nombre = nombre;
		listaFunciones= new ArrayList<Funcion>();
		listaImagenes = new int[3];
	}
	public Obra (Parcel in) {
//		listaButacas = new ArrayList<Butaca>();
		listaFunciones= new ArrayList<Funcion>();
		listaImagenes = new int[3];
		readFromParcel(in);
	}
	public Obra() {
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(idObra);	
		dest.writeString(nombre);
		dest.writeString(descripcion);
		dest.writeTypedList(listaFunciones);
//		int[] list= new int[listaImagenes.length];
//		for(int i=0;i<listaImagenes.length;i++){
//			list[i]=(int)listaImagenes[i]; 
//		}
		dest.writeIntArray(listaImagenes);
//		dest.writeParcelable(sala, flags);
	}
	//Clase para recuperar los datos, IMPORTANTE leerlos en el mismo orden en que se escribieron 
		private void readFromParcel(Parcel in) {
			idObra= in.readInt();
			nombre= in.readString();
			descripcion= in.readString();
			in.readTypedList(listaFunciones,Funcion.CREATOR);
//			creo una nueva lista de tipo int[] para poder realizar readIntArray
//			int[] list= new int[listaImagenes.length];
//			for(int i=0;i<listaImagenes.length;i++){
//				list[i]=(int)listaImagenes[i]; 
//			}
			in.readIntArray(listaImagenes);
//			sala= in.readParcelable(sala.getClass().getClassLoader());
			
		}

		//Necesario para usar la clase en Arrays
		public static final Parcelable.Creator<Obra> CREATOR = new Parcelable.Creator<Obra>() {
			public Obra createFromParcel(Parcel in) {
				return new Obra (in);
			}

			public Obra [] newArray(int size) {
				return new Obra[size];
			}
		};



	
}
