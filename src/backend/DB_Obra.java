package backend;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dominio.Obra;


public class DB_Obra {

	private SQLiteDatabase db ;
	private MySQLiteOpenHelper dbHelper;
	//private String[] columnas = { TablaObras.COLUMNA_ID,
	//		TablaObras.COLUMNA_NOMBRE, TablaObras.COLUMNA_DESCRIPCION};

	public DB_Obra(Context context) {
		//dbHelper.onCreate(db);
	//	dbHelper = MySQLiteOpenHelper.getInstance(context);
		dbHelper.onCreate(db);
	}

	public void open() {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public void crearObra(String obra) {
		ContentValues values = new ContentValues();
//		values.put(TablaObras.COLUMNA_NOMBRE, obra);
//		values.put(TablaObras.COLUMNA_DESCRIPCION, obra);
//		//db.insert(TablaObras.TABLA_OBRAS, null, values);
//		db.insert(TablaObras.TABLA_OBRAS, "null", values);
	}

	/*public List<Obra> getAllObras() {
		List<Obra> listaObras = new ArrayList<Obra>();

		Cursor cursor = db.query(TablaObras.TABLA_OBRAS, columnas, null, null,
				null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Obra nuevaObra = cursorToObra(cursor);
			listaObras.add(nuevaObra);
			cursor.moveToNext();
		}

		cursor.close();
		return listaObras;
	}*/

	public void borrarObra(Obra ob) {
		/*int id = ob.getIdObra();
		db.delete(TablaObras.TABLA_OBRAS, TablaObras.COLUMNA_ID + " = " + id,
				null);*/
	}

	private Obra cursorToObra(Cursor cursor) {
		Obra obra = new Obra();
		//obra.setIdObra(cursor.getInt(cursor)(0));
		//nota.setId(cursor.getLong(0));
		obra.setIdObra(cursor.getInt(0));
		obra.setNombre(cursor.getString(1));
		return obra;
	}	
}
