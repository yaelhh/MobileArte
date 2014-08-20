package dominio;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable{
//	Variables
	private int idUsuario;
	private String miNombre;
	private String miApellido;
	private String miNombreUsuario;
	private String fechaNacimiento;
	private int mascaras;
	private ArrayList<Obra> misObrasVistas;
	private ArrayList<Obra> misProximasObras;
	private int estaLogueado;
	private String imagenUsuario;
	private String password;
	
	
//Constructor
	public Usuario(int idUsuario, String miNombre, String miApellido,
			String miNombreUsuario, String fechaNacimiento, int mascaras,
			ArrayList<Obra> misObrasVistas, ArrayList<Obra> misProximasObras, int estaLogueado, String miImagen, String miPass) {
		super();
		this.idUsuario = idUsuario;
		this.miNombre = miNombre;
		this.miApellido = miApellido;
		this.miNombreUsuario = miNombreUsuario;
		this.fechaNacimiento = fechaNacimiento;
		this.mascaras = mascaras;
		this.misObrasVistas = misObrasVistas;
		this.misProximasObras = misProximasObras;
		this.estaLogueado = estaLogueado;
		this.imagenUsuario = miImagen;
		this.password = miPass;
	}
	public Usuario() {

	}

	public Usuario(String nomUsuario, String miPassword, int logueado) {
		this.miNombreUsuario = nomUsuario;
		this.password = miPassword;
		this.estaLogueado = logueado;

	}

public Usuario(Parcel in){
	readFromParcel(in);
}

	public Usuario(String nomUsuario, String pass, int i, String fechaN) {
		this.miNombreUsuario = nomUsuario;
		this.password = pass;
		this.estaLogueado = i;
		this.fechaNacimiento = fechaN;
	}
	
	//get and set
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getMiNombre() {
		return miNombre;
	}
	public void setMiNombre(String miNombre) {
		this.miNombre = miNombre;
	}
	public String getMiApellido() {
		return miApellido;
	}
	public void setMiApellido(String miApellido) {
		this.miApellido = miApellido;
	}
	public String getMiNombreUsuario() {
		return miNombreUsuario;
	}
	public void setMiNombreUsuario(String miNombreUsuario) {
		this.miNombreUsuario = miNombreUsuario;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getMascaras() {
		return mascaras;
	}
	public void setMascaras(int mascaras) {
		this.mascaras = mascaras;
	}
	public ArrayList<Obra> getMisObrasVistas() {
		return misObrasVistas;
	}
	public void setMisObrasVistas(ArrayList<Obra> misObrasVistas) {
		this.misObrasVistas = misObrasVistas;
	}
	public ArrayList<Obra> getMisProximasObras() {
		return misProximasObras;
	}
	public void setMisProximasObras(ArrayList<Obra> misProximasObras) {
		this.misProximasObras = misProximasObras;
	}
	public int getLogueado() {
		return estaLogueado;
	}
	public void setEstaLogueado(int estaLogueado) {
		this.estaLogueado = estaLogueado;
	}
	
	public String getImagenUsuario() {
		return imagenUsuario;
	}
	public void setImagenUsuario(String imagenUsuario) {
		this.imagenUsuario = imagenUsuario;
	}
	public int isEstaLogueado() {
		return estaLogueado;
	}
	
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.idUsuario);
		dest.writeString(this.miNombre);
		dest.writeString(this.miApellido);
		dest.writeString(miNombreUsuario);
		dest.writeString(this.fechaNacimiento);
		dest.writeInt(this.mascaras);
		dest.writeTypedList(this.misObrasVistas);
		dest.writeTypedList(this.misProximasObras);
		dest.writeInt(this.estaLogueado);
		dest.writeString(this.imagenUsuario);
		dest.writeString(this.password);
		
	}	
	private void readFromParcel(Parcel in) {
		this.idUsuario = in.readInt();
		this.miNombre = in.readString();
		this.miApellido = in.readString();
		this.miNombreUsuario = in.readString();
		this.fechaNacimiento= in.readString();
		this.mascaras = in.readInt();
		in.readTypedList(misObrasVistas,Obra.CREATOR);
		in.readTypedList(misProximasObras,Obra.CREATOR);
		this.estaLogueado = in.readInt();
		this.imagenUsuario = in.readString();
		this.password = in.readString();
			
   }
	public static final Parcelable.Creator<Usuario> CREATOR
    = new Parcelable.Creator<Usuario>() {
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }
 
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
	

}
