package backend.Obras;
/**
 * @author Miguel S. Mendoza
 *
 */
public class UsuarioTable implements UsuarioColumns {

	public final static String TABLE_NAME = "clientes";

	public final static String[] COLS = { UsuarioColumns.nombreUsuario, UsuarioColumns.passwordUsuario, UsuarioColumns.estaLogueadoUsuario };

	public static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_NAME + " (" + nombreUsuario + " TEXT PRIMARY KEY , "
			+ passwordUsuario + " TEXT NOT NULL, " + estaLogueadoUsuario + " TEXT );";

}
