package dominio;

import java.util.ArrayList;
import java.util.Date;




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
	private boolean estaLogueado;
	
//Constructor
	public Usuario(int idUsuario, String miNombre, String miApellido,
			String miNombreUsuario, String fechaNacimiento, int mascaras,
			ArrayList<Obra> misObrasVistas, ArrayList<Obra> misProximasObras, boolean estaLogueado) {
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
	}
public Usuario() {
		
	}
public Usuario(Parcel in){
	readFromParcel(in);
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
	public boolean getLogueado() {
		return estaLogueado;
	}
	public void setEstaLogueado(boolean estaLogueado) {
		this.estaLogueado = estaLogueado;
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
		dest.writeBooleanArray(new boolean[] {this.estaLogueado});
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
		boolean[] temp = new boolean[1];
		in.readBooleanArray(temp);
		estaLogueado = temp[0];
			
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
