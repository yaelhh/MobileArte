
package dominio;

import java.util.ArrayList;

<<<<<<< HEAD
import android.R.integer;
import android.os.Parcel;
import android.os.Parcelable;

public class Obra implements Parcelable{
=======
public class Obra {
>>>>>>> refs/heads/master
	//Variables
<<<<<<< HEAD
	private Integer idObra;
=======
	private int idObra;
>>>>>>> refs/heads/master
	private String nombre;
<<<<<<< HEAD
//	private ArrayList listaImagenes;
=======
>>>>>>> refs/heads/master
	private String descripcion;
<<<<<<< HEAD
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
	public Integer getIdObra() {
=======
	private ArrayList listaImagenes;
	private ArrayList listaFunciones;
//	get and set
	public int getIdObra() {
>>>>>>> refs/heads/master
		return idObra;
	}
<<<<<<< HEAD
	public void setIdObra(Integer idObra) {
=======
	public void setIdObra(int idObra) {
>>>>>>> refs/heads/master
		this.idObra = idObra;
	}
	public String getNombre() {
		return nombre;
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
<<<<<<< HEAD
	public Obra(Integer idObra, String nombre) {
=======
	public Obra(int idObra, String nombre) {
>>>>>>> refs/heads/master
		super();
		this.idObra = idObra;
		this.nombre = nombre;
		listaFunciones= new ArrayList<Funcion>();

	}
	public Obra (Parcel in) {
//		listaButacas = new ArrayList<Butaca>();
		listaFunciones= new ArrayList<Funcion>();
		readFromParcel(in);
	}
	public Obra() {
		// TODO Auto-generated constructor stub
	}
	
<<<<<<< HEAD
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
=======
	public Obra(int idObra, String nombre, String descripcion) {
		super();
		this.idObra = idObra;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Obra(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Obra(String nombre) {
		super();
		this.nombre = nombre;
	}
>>>>>>> refs/heads/master
	
}
