package dominio;

import android.os.Parcel;
import android.os.Parcelable;

public class Notificaciones implements Parcelable {

	private String idNotificacion;
	private String idUsuario;
	private String tipo;
	private String titulo;
	private String texto;
	private String fecha_publicacion;
	private boolean leido;
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(idUsuario);
		dest.writeString(tipo);
		dest.writeString(titulo);
		dest.writeString(texto);
		dest.writeString(fecha_publicacion);
		dest.writeInt(leido ? 1 : 0);
	}
	
	public Notificaciones(String idNotificacion, String idUsuario, String tipo,
			String titulo, String texto, String fecha_publicacion, boolean checked) {
		super();
		this.idNotificacion = idNotificacion;
		this.idUsuario = idUsuario;
		this.tipo = tipo;
		this.titulo = titulo;
		this.texto = texto;
		this.fecha_publicacion = fecha_publicacion;
		this.leido = checked;
	}
	
	public Notificaciones(Parcel in) {
		this.idUsuario = in.readString();
		this.tipo = in.readString();
		this.titulo = in.readString();
		this.texto = in.readString();
		this.fecha_publicacion = in.readString();
		this.leido = in.readInt() == 1 ? true:false;
	}
	
	public Notificaciones(String string, String string2, boolean b) {
		// TODO Auto-generated constructor stub
	}

	public static final Parcelable.Creator<Notificaciones> CREATOR = new Parcelable.Creator<Notificaciones>() {
	     public Notificaciones createFromParcel(Parcel in) {
	           return new Notificaciones(in);
	        }
	 
	       public Notificaciones[] newArray(int size) {
	          return new Notificaciones[size];
	      }
	   };

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getFecha_publicacion() {
		return fecha_publicacion;
	}

	public void setFecha_publicacion(String fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}
	
	

}
