package backend;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import dominio.Usuario;

public class DatabaseHelper extends SQLiteOpenHelper{


	//The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.example.clientarte/databases/";
 
    private static String DB_NAME = "Sodre.db";
 
    private SQLiteDatabase myDataBase; 
 
    private final Context myContext;
 
    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DatabaseHelper(Context context) {
    	super(context, DB_NAME, null, 1);
        this.myContext = context;
    }	
 
  /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		//do nothing - database already exist
    	}else{
 
    		//By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
        	this.getReadableDatabase();
 
        	try {
 
    			copyDataBase();
 
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }
 
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	}catch(SQLiteException e){
 
    		//database does't exist yet.
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
 
    	return checkDB != null ? true : false;
    }
 
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
 
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
 
    public void openDataBase() throws SQLException{
 
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    }
 
    @Override
	public synchronized void close() {
 
    	    if(myDataBase != null)
    		    myDataBase.close();
 
    	    super.close();
 
	}

	
	private static final String TABLA_USUARIOS = "db_usuarios";
	private static final String COLUMNA_IDUSUARIOS = "idUsuario";
    private static final String COLUMNA_NOMBREUSUARIO = "nombreUsuario";
	private static final String COLUMNA_PASSWORD = "password";
	private static final String COLUMNA_ESTADOUSUARIO = "estadoUsuario";
	private static final String COLUMNA_FNACIMIENTO = "fechaNacimiento";
	
	private static final String CREATE_TABLA_USUARIOS = "CREATE TABLE "
            + TABLA_USUARIOS + "(" + COLUMNA_IDUSUARIOS + " INTEGER PRIMARY KEY," + COLUMNA_NOMBREUSUARIO
            + " TEXT," + COLUMNA_PASSWORD + " TEXT," + COLUMNA_ESTADOUSUARIO
            + " INTEGER," +  COLUMNA_FNACIMIENTO + " TEXT " + ")";
    
    
 
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLA_USUARIOS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public long createUsuario(Usuario todo) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMNA_NOMBREUSUARIO, todo.getMiNombreUsuario());
		values.put(COLUMNA_PASSWORD, todo.getPassword());
		values.put(COLUMNA_ESTADOUSUARIO, 1);
		values.put(COLUMNA_FNACIMIENTO, todo.getFechaNacimiento());
		long todo_id = db.insert(TABLA_USUARIOS, null, values);
		return todo_id;
	}
	
	public Usuario getTodo(String nUsuario) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        String selectQuery = "SELECT  * FROM " + TABLA_USUARIOS + " WHERE "
                + COLUMNA_NOMBREUSUARIO + " = " + nUsuario;
        Cursor c = db.rawQuery(selectQuery, null);
        
        if (c != null)
            c.moveToFirst();
        
        Usuario u = new Usuario();
        u.setIdUsuario(c.getInt(c.getColumnIndex(COLUMNA_IDUSUARIOS)));
        u.setMiNombreUsuario((c.getString(c.getColumnIndex(COLUMNA_NOMBREUSUARIO))));
        u.setPassword(c.getString(c.getColumnIndex(COLUMNA_PASSWORD)));
        u.setEstaLogueado(c.getInt(c.getColumnIndex(COLUMNA_ESTADOUSUARIO)));
        
        return u;
    }
	
	// closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
    
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    public List<Usuario> getAllTags() {
        List<Usuario> tags = new ArrayList<Usuario>();
        String selectQuery = "SELECT  * FROM " + TABLA_USUARIOS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Usuario u = new Usuario();
                u.setIdUsuario(c.getInt(c.getColumnIndex(COLUMNA_IDUSUARIOS)));
                u.setMiNombreUsuario((c.getString(c.getColumnIndex(COLUMNA_NOMBREUSUARIO))));
                u.setPassword(c.getString(c.getColumnIndex(COLUMNA_PASSWORD)));
                u.setEstaLogueado(c.getInt(c.getColumnIndex(COLUMNA_ESTADOUSUARIO)));
 
                // adding to tags list
                tags.add(u);
            } while (c.moveToNext());
        }
        return tags;
    }

    public Usuario obtenerUsuarioLogueado(String estaLogueado) {
    	SQLiteDatabase db = this.getReadableDatabase();

    	String selectQuery = "SELECT  * FROM " + TABLA_USUARIOS + " WHERE "
    			+ COLUMNA_ESTADOUSUARIO + " = " + estaLogueado;
    	Usuario u = new Usuario();
    	Cursor c = db.rawQuery(selectQuery, null);
    	if(db!=null){
    		try { 
    			if (c != null)
    				c.moveToFirst();


    			u.setIdUsuario(c.getInt(c.getColumnIndex(COLUMNA_IDUSUARIOS)));
    			u.setMiNombreUsuario((c.getString(c.getColumnIndex(COLUMNA_NOMBREUSUARIO))));
    			u.setPassword(c.getString(c.getColumnIndex(COLUMNA_PASSWORD)));
    			u.setEstaLogueado(c.getInt(c.getColumnIndex(COLUMNA_ESTADOUSUARIO)));
    		} catch (Exception ioex) {
    			ioex.printStackTrace(); 
    		}
    	}
    		return u;
    	}
	
	public int actualizarUsuarioLogueado(String nombreUsuario){
		int filasAfectadas = 0;
		SQLiteDatabase db = getWritableDatabase();
		if(db!=null){
			ContentValues values = new ContentValues();
			values.put(COLUMNA_ESTADOUSUARIO, 1);
			filasAfectadas = (int) db.update(TABLA_USUARIOS, values, "estadoUsuario = ?", new String[]{String.valueOf(nombreUsuario)});  
		}
		db.close();   
		return filasAfectadas;
	}
	
	public void actualizarUsuarioLogueadoDeslogueado(String nombreUsuario){
		SQLiteDatabase db = getWritableDatabase();
		String query = "UPDATE db_usuarios SET estadoUsuario='0' WHERE nombreUsuario " + "=" + "'"+ nombreUsuario+ "'";
		if(db!=null){
			db.execSQL(query);
		}
		db.close();   
	}
	
	public Usuario obtenerFechaCumpleanos (String nUsuario) {
		SQLiteDatabase db = this.getReadableDatabase();
		Usuario u = new Usuario();
		if (db!=null){
			String query = "SELECT * FROM " + TABLA_USUARIOS + "  WHERE " + COLUMNA_NOMBREUSUARIO + "=" + "'"+ nUsuario+ "'";
			Cursor c = db.rawQuery(query, null);
			try { 
				if (c != null)
					c.moveToFirst();


				u.setIdUsuario(c.getInt(c.getColumnIndex(COLUMNA_IDUSUARIOS)));
				u.setMiNombreUsuario((c.getString(c.getColumnIndex(COLUMNA_NOMBREUSUARIO))));
				u.setPassword(c.getString(c.getColumnIndex(COLUMNA_PASSWORD)));
				u.setEstaLogueado(c.getInt(c.getColumnIndex(COLUMNA_ESTADOUSUARIO)));
				u.setFechaNacimiento(c.getString(c.getColumnIndex(COLUMNA_FNACIMIENTO)));
			} catch (Exception ioex) {
				 ioex.printStackTrace(); 
			}
		}
		return u;
	}
	
	public void actualizarPassUsuarioLogueado(String nombreUsuario, String pass){
		SQLiteDatabase db = getWritableDatabase();
		String query = "UPDATE db_usuarios SET password=" + "'" + pass + "'" + " WHERE nombreUsuario " + "=" + "'"+ nombreUsuario+ "'";
		if(db!=null){
			db.execSQL(query);
		}
		db.close();  
	}
	
	//ContentValues valores = new ContentValues();
//    valores.put(COLUMNA_ESTADOUSUARIO, 0);
//    db.update("db_usuarios", valores, "nombreUsuario=" + nombreUsuario, null);
 
}