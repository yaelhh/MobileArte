package backend;

import android.text.Editable;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;


public class UsuarioBackend extends GenericJson {

	@Key("_id") // nombre del campo definido en el backend
	private String idUsuario;
	@Key
	private String nombre;
	@Key
	private String apellido;
	@Key
	private String nombreUsuario;
	@Key
	private String password;
	@Key
	private String fechaNacimiento;
	@Key
	private String mascaras;
	@Key
	private String idMiObrasVistas;	
	@Key
	private String idMisProximasObras;
	@Key
	private String estaLogueado;
	@Key
	private String email;
	 
	
	public UsuarioBackend() {}
	
	public UsuarioBackend (String miNombre, String miApellido) {
		super();
		this.nombre = miNombre;
		this.apellido = miApellido;
	}
	
	public UsuarioBackend (String nu) {
		super();
		this.nombreUsuario = nu;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getMascaras() {
		return mascaras;
	}

	public void setMascaras(String mascaras) {
		this.mascaras = mascaras;
	}

	public String getEstaLogueado() {
		return estaLogueado;
	}

	public void setEstaLogueado(String estaLogueado) {
		this.estaLogueado = estaLogueado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
}