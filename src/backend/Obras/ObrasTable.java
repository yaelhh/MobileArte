package backend.Obras;
/**
 * @author Miguel S. Mendoza
 *
 */
public class ObrasTable implements ObrasColumns {

	public final static String TABLE_NAME = "clientes";

	public final static String[] COLS = { ObrasColumns.idObra, ObrasColumns.nombreObra, ObrasColumns.descripcionObra };

	public static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_NAME + " (" + idObra + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ idObra + " TEXT NOT NULL, " + nombreObra + " TEXT );";

}
