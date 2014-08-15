package backend;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;





import com.google.api.client.util.Key;
import com.kinvey.java.LinkedResources.LinkedGenericJson;
import com.kinvey.java.model.KinveyMetaData;


public class UsuarioBackend extends LinkedGenericJson {

	@Key("_id") // nombre del campo definido en el backend
	private String idUsuario;
	@Key
	private String nombre;
	@Key
	private String apellido;
	@Key("username")
	private String username;
	@Key
	private String password;
	@Key("fechaNacimiento")
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
	private String imagenAdjunta;
	
	public UsuarioBackend() {
		//"attachment" is the JSON element used to maintain a Linked File.
        //putFile("attachment");
	}
	
//	public UsuarioBackend(String userid){
//		meta = new KinveyMetaData();
//        acl = new KinveyMetaData.AccessControlList();
//        putFile(attachmentName);
//        author = new KinveyReference();
//        author.setCollection(User.USER_COLLECTION_NAME);
//        author.setId(userid);
//	}
	
	public UsuarioBackend (String miNombre, String miApellido) {
		super();
		this.nombre = miNombre;
		this.apellido = miApellido;
	}
	
	public UsuarioBackend (String miNombre, String miApellido, String estaLog) {
		this.nombre = miNombre;
		this.apellido = miApellido;
		this.estaLogueado  = estaLog;
	}
	
	public UsuarioBackend (String nu) {
		super();
		this.username = nu;
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
		return username;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.username = nombreUsuario;
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

	public String getImagenAdjunta() {
		return imagenAdjunta;
	}

	public void setImagenAdjunta(String imagenAdjunta) {
		this.imagenAdjunta = imagenAdjunta;
	}
	
	

	
	
	
	
	
	
}