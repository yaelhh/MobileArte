package backend;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class NotificacionesBackend extends GenericJson{
	
	@Key("_id") // nombre del campo definido en el backend
	private String idNotificacion;
	@Key("idUsuario")
	private String idUsuario;
	@Key("texto")
	private String texto;
	@Key("tipo")
	private String tipo;
	@Key("titulo")
	private String titulo;
	
	
	public NotificacionesBackend(String idUsuario, String tipo, String titulo,
			String texto) {
		super();
		this.idUsuario = idUsuario;
		this.tipo = tipo;
		this.titulo = titulo;
		this.texto = texto;
	}
	public NotificacionesBackend() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NotificacionesBackend(int userIcon, String idUsuario2, String tipo2,
			String titulo2, String texto2) {
		// TODO Auto-generated constructor stub
	}
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
	
	

}
