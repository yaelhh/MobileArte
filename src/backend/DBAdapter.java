package backend;

import java.util.ArrayList;

import dominio.Obra;
import backend.Obras.ObrasColumns;
import backend.Obras.ObrasTable;
import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Binder;
import android.os.IBinder;
import android.provider.BaseColumns;

public class DBAdapter extends Service {

	private final IBinder mBinder = new LocalBinder();

	private DBHelper dbHelper;

	private SQLiteDatabase db;

	public class LocalBinder extends Binder {
		public DBAdapter getService() {
			return DBAdapter.this;
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}

	@Override
	public void onCreate() {
		dbHelper = new DBHelper(this);
		try {
		 db = dbHelper.getWritableDatabase();
		 } catch (SQLiteException ex) {
		 db = dbHelper.getReadableDatabase();
		 }
		db = dbHelper.getDataBase();
	}

	@Override
	public void onDestroy() {
		db.close();
	}
/*
	public Cursor getCursorItem(long _rowIndex) throws SQLException {
		Cursor result = db.query(true, ClientesTable.TABLE_NAME,
				ClientesTable.COLS, BaseColumns._ID + "=" + _rowIndex, null,
				null, null, null, null);
		if ((result.getCount() == 0) || !result.moveToFirst()) {
			throw new SQLException("No items found for row: " + _rowIndex);
		}
		return result;
	}
*/
	/**
	 * Obtiene un cursor con todos los clientes de la base de datos
	 */
	public Cursor getCursorClientes() {
		return db.query(ObrasTable.TABLE_NAME, ObrasTable.COLS, null,
				null, null, null, BaseColumns._ID);
	}

	public ArrayList<Obra> getAllObras() {
		ArrayList<Obra> obras = new ArrayList<Obra>();
		Cursor result = db.query(ObrasTable.TABLE_NAME, ObrasTable.COLS,
				null, null, null, null, BaseColumns._ID);
		if (result.moveToFirst())
			do {
				obras.add(new Obra(result.getInt(result
						.getColumnIndex(BaseColumns._ID)), result
						.getString(result
								.getColumnIndex(ObrasColumns.nombreObra)),
						result.getString(result
								.getColumnIndex(ObrasColumns.descripcionObra))));
			} while (result.moveToNext());
		return obras;
	}

	/**
	 * INSERTAR NUEVO CLIENTE
	 * */
	/*public long insertCliente(String nombre, String apellidos, Integer edad) {
		ContentValues newValues = new ContentValues();
		newValues.put(ObrasColumns.NOMBRE, nombre);
		newValues.put(ObrasColumns.APELLIDOS, apellidos);
		newValues.put(ObrasColumns.EDAD, edad);
		return db.insert(ObrasTable.TABLE_NAME, null, newValues);
	}*/

	/**
	 * BORRAR CLIENTE CON _id = _rowIndex
	 * */
	public boolean deleteCliente(int _rowIndex) {
		return db.delete(ObrasTable.TABLE_NAME, BaseColumns._ID + "="
				+ _rowIndex, null) > 0;
	}

	/**
	 * ACTUALIZAR CIENTE _id = _rowIndex
	 * */
	public boolean updateCliente(Integer _rowIndex, String nombre,
			String descripcion) {
		ContentValues newValues = new ContentValues();
		newValues.put(ObrasColumns.nombreObra, nombre);
		newValues.put(ObrasColumns.descripcionObra, descripcion);
		//newValues.put(ObrasColumns.EDAD, edad);
		return db.update(ObrasTable.TABLE_NAME, newValues, BaseColumns._ID
				+ "=" + _rowIndex, null) > 0;
	}

}
