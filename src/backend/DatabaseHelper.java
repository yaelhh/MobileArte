package backend;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import dominio.Funcion;
import dominio.Obra;

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
    
    /*ALTA TABLA OBRAS*/
	private static final String TABLA_OBRAS = "db_obras";
	private static final String COLUMNA_IDOBRAS = "idObra";
    private static final String COLUMNA_NOMBREOBRAS = "nombreObra";
    private static final String COLUMNA_DESCRIPCION = "descripcionObra";

/*ALTA TABLA OBRASFUNCIONES*/	
	private static final String TABLA_OBRASFUNCIONES = "db_obrasFunciones";
	private static final String COLUMNA_IDOBRASFUNCIONES = "idObraFuncion";
    private static final String COLUMNA_IDOBRAF = "idObra";
	private static final String COLUMNA_IDFUNCION = "idFuncion";
	
/*ALTA TABLA OBRASIMAGENES*/	
	private static final String TABLA_OBRASIMAGENES = "db_obrasImagenes";
	private static final String COLUMNA_IDOBRASIMAGENES = "idObraImagen";
    private static final String COLUMNA_IDOBRAI = "idObra";
	private static final String COLUMNA_IMAGEN = "imagen";

/*ALTA TABLA USUARIOS*/	
	private static final String TABLA_USUARIOS = "db_usuarios";
	private static final String COLUMNA_IDUSUARIOS = "idUsuario";
    private static final String COLUMNA_NOMBREU = "nombre";
    private static final String COLUMNA_APELLIDO = "apellido";
    private static final String COLUMNA_NOMBREUSUARIO = "nombreUsuario";
	private static final String COLUMNA_PASSWORD = "password";
	private static final String COLUMNA_FCHNACIMIENTO = "fchNacimiento";
	private static final String COLUMNA_MASCARAS = "mascaras";
	private static final String COLUMNA_ESTADOUSUARIO = "estadoUsuario";
	
/*ALTA TABLA BUTACAS*/	
	private static final String TABLA_BUTACAS = "db_butacas";
	private static final String COLUMNA_IDBUTACA = "idButaca";
    private static final String COLUMNA_SECTOR = "idSector";
    private static final String COLUMNA_ESTADO = "estadoButaca";
	
/*ALTA TABLA COMODIDADES*/	
	private static final String TABLA_COMODIDADES = "db_comodidades";
	private static final String COLUMNA_IDCOMODIDAD = "idComodidad";
    private static final String COLUMNA_NOMBRECOMODIDAD = "nombreComodidad";	

/*ALTA TABLA COMPRAS*/	
	private static final String TABLA_COMPRAS = "db_compras";
	private static final String COLUMNA_IDCOMPRAS = "idCompra";
    private static final String COLUMNA_FCHREALIZADA = "fchRealizada";
	private static final String COLUMNA_FCHVIGENCIA = "fchVigencia";
    private static final String COLUMNA_OBRA = "idObra";
    private static final String COLUMNA_ESTADOPAGO = "esPago";
	private static final String COLUMNA_USUARIO = "idUsuario";
	private static final String COLUMNA_PRECIOTOTAL = "precioTotal";
	private static final String COLUMNA_CODE = "codigo";
	
/*ALTA TABLA ENTRADAS*/	
	private static final String TABLA_ENTRADAS = "db_entradas";
	private static final String COLUMNA_IDENTRADAS = "idEntrada";
    private static final String COLUMNA_COMPRA = "idCompra";
	private static final String COLUMNA_BUTACA = "idButaca";
    private static final String COLUMNA_PRECIOENTRADA = "precioUnitario";
	
/*ALTA TABLA FUNCIONES*/	
	private static final String TABLA_FUNCIONES = "db_funciones";
	private static final String COLUMNA_IDFUNCIONES = "idFuncion";
    private static final String COLUMNA_DURACION = "duracionFuncion";
	private static final String COLUMNA_HORACOMIENZO = "horaComienzo";
    private static final String COLUMNA_FECHA = "fechaFuncion";	

/*ALTA TABLA PRECIOS*/	
	private static final String TABLA_PRECIOS = "db_precios";
	private static final String COLUMNA_IDPRECIOS = "idPrecio";
    private static final String COLUMNA_MONTO = "monto";	
	
/*ALTA TABLA SALAS*/	
	private static final String TABLA_SALAS = "db_salas";
	private static final String COLUMNA_IDSALAS = "idSala";
    private static final String COLUMNA_NOMBRE = "nombreSala";
	private static final String COLUMNA_SALAOBRA = "idSalaObra";
    private static final String COLUMNA_COMODIDADSALAS = "idSalaComodidad";
	private static final String COLUMNA_CAPACIDAD = "capacidadSala";
	private static final String COLUMNA_SALASECTORES = "idSalaSector";

/*ALTA TABLA SALASOBRAS*/	
	private static final String TABLA_SALAOBRAS = "db_salasObras";
	private static final String COLUMNA_IDSALAOBRAS = "idSalaObra";
    private static final String COLUMNA_IDOBRA = "idObra";
	private static final String COLUMNA_IDSALAO = "idSala";
    
/*ALTA TABLA COMODIDADSALAS*/	
	private static final String TABLA_COMODIDADSALAS = "db_salasComodidades";
	private static final String COLUMNA_IDCOMODIDADSALAS = "idSalaComodidad";
    private static final String COLUMNA_IDCOMODIDADENSALA = "idComodidad";
	private static final String COLUMNA_IDSALAC = "idSala";
	
/*ALTA TABLA SALASSECTORES*/	
	private static final String TABLA_SALASECTORES = "db_salaSectores";
	private static final String COLUMNA_IDSALASECTORES = "idSalaSector";
    private static final String COLUMNA_IDSECTOR = "idSector";
	private static final String COLUMNA_IDSALA = "idSala";

/*ALTA TABLA SECTORES*/	
	private static final String TABLA_SECTORES = "db_sectores";
	private static final String COLUMNA_ID = "idSector";
    private static final String COLUMNA_PRECIO = "precioSector";
	private static final String COLUMNA_BUTACAS = "totalButacas";
	

	
	private static final String CREATE_TABLE_OBRA = "CREATE TABLE "
            + TABLA_OBRAS + "(" + COLUMNA_ID + " integer primary key autoincrement," + COLUMNA_NOMBRE
            + " text not null," + COLUMNA_DESCRIPCION + " text not null" + ")";
			
	private static final String CREATE_TABLA_OBRASFUNCIONES = "CREATE TABLE "
            + TABLA_OBRASFUNCIONES + "(" + COLUMNA_IDOBRASFUNCIONES + "  integer primary key autoincrement," + COLUMNA_IDOBRAF
            + " INTEGER," + COLUMNA_IDFUNCION + " INTEGER" + ")";
	
	private static final String CREATE_TABLA_OBRASIMAGENES = "CREATE TABLE "
            + TABLA_OBRASIMAGENES + "(" + COLUMNA_IDOBRASIMAGENES + "  integer primary key autoincrement," + COLUMNA_IDOBRAI
            + " INTEGER," + COLUMNA_IMAGEN + " TEXT" + ")";
	
	
	private static final String CREATE_TABLA_USUARIOS = "CREATE TABLE "
            + TABLA_USUARIOS + "(" + COLUMNA_IDUSUARIOS + " integer primary key autoincrement," + COLUMNA_NOMBREU
            + " text not null," + COLUMNA_APELLIDO + " text not null," + COLUMNA_NOMBREUSUARIO + "text not null," + COLUMNA_PASSWORD 
			+ "text not null," + COLUMNA_FCHNACIMIENTO + "TIMESTAMP NOT NULL DEFAULT current_timestamp," + COLUMNA_MASCARAS + "INTEGER," + COLUMNA_ESTADOUSUARIO 
			+ "INTEGER not null" + " )";
			
	private static final String CREATE_TABLA_BUTACAS = "CREATE TABLE "
            + TABLA_BUTACAS + "(" + COLUMNA_IDBUTACA + " integer primary key autoincrement," + COLUMNA_SECTOR
            + " INTEGER," + COLUMNA_ESTADO + " INTEGER " + " )";
	
	private static final String CREATE_TABLA_COMODIDADES = "CREATE TABLE "
            + TABLA_COMODIDADES + "(" + COLUMNA_IDCOMODIDAD + " integer primary key autoincrement," + COLUMNA_NOMBRECOMODIDAD
            + " TEXT " + " )";
			
	private static final String CREATE_TABLA_COMPRAS = "CREATE TABLE "
            + TABLA_COMPRAS + "(" + COLUMNA_IDCOMPRAS + " integer primary key autoincrement," + COLUMNA_FCHREALIZADA
            + " TIMESTAMP NOT NULL DEFAULT current_timestamp," + COLUMNA_FCHVIGENCIA + " TIMESTAMP NOT NULL DEFAULT current_timestamp," + COLUMNA_OBRA + "INTEGER," + COLUMNA_ESTADOPAGO 
			+ "INTEGER," + COLUMNA_USUARIO + "INTEGER," + COLUMNA_PRECIOTOTAL + "INTEGER," + COLUMNA_CODE 
			+ "INTEGER" + " )";
			
	private static final String CREATE_TABLA_ENTRADAS = "CREATE TABLE "
            + TABLA_ENTRADAS + "(" + COLUMNA_IDENTRADAS + " integer primary key autoincrement," + COLUMNA_COMPRA
            + " INTEGER," + COLUMNA_BUTACA + " INTEGER," + COLUMNA_PRECIOENTRADA + "INTEGER" + " )";
			
	private static final String CREATE_TABLA_FUNCIONES = "CREATE TABLE "
            + TABLA_FUNCIONES + "(" + COLUMNA_IDFUNCIONES + "integer primary key autoincrement," + COLUMNA_DURACION
            + " INTEGER," + COLUMNA_HORACOMIENZO + " INTEGER," + COLUMNA_FECHA + "TIMESTAMP NOT NULL DEFAULT current_timestamp" + " )";
			
	private static final String CREATE_TABLA_PRECIOS = "CREATE TABLE "
            + TABLA_PRECIOS + "(" + COLUMNA_IDPRECIOS + " integer primary key autoincrement," + COLUMNA_MONTO
            + "INTEGER" + " )";
			
	private static final String CREATE_TABLA_SALAS = "CREATE TABLE "
            + TABLA_SALAS + "(" + COLUMNA_IDSALAS + " integer primary key autoincrement," + COLUMNA_NOMBRE
            + " TEXT," + COLUMNA_SALAOBRA + " INTEGER," + COLUMNA_COMODIDADSALAS + "INTEGER," + COLUMNA_CAPACIDAD 
			+ "INTEGER," + COLUMNA_SALASECTORES + "INTEGER," + " )";
			
	private static final String CREATE_TABLA_SALAOBRAS = "CREATE TABLE "
            + TABLA_SALAOBRAS + "(" + COLUMNA_IDSALAOBRAS + " integer primary key autoincrement," + COLUMNA_IDOBRA
            + " INTEGER," + COLUMNA_IDSALAO + "INTEGER," + " )";
			
	private static final String CREATE_TABLA_COMODIDADSALAS = "CREATE TABLE "
            + TABLA_COMODIDADSALAS + "(" + COLUMNA_IDCOMODIDADSALAS + " integer primary key autoincrement," + COLUMNA_IDCOMODIDADENSALA
            + " INTEGER," + COLUMNA_IDSALAC + "INTEGER," + " )";
			
	private static final String CREATE_TABLA_SALASECTORES = "CREATE TABLE "
            + TABLA_SALASECTORES + "(" + COLUMNA_IDSALASECTORES + " integer primary key autoincrement," + COLUMNA_IDSECTOR
            + " INTEGER," + COLUMNA_IDSALA + "INTEGER," + " )";
			
	private static final String CREATE_TABLA_SECTORES = "CREATE TABLE "
            + TABLA_SECTORES + "(" + COLUMNA_ID + " integer primary key autoincrement," + COLUMNA_PRECIO
            + " INTEGER," + COLUMNA_BUTACAS + "INTEGER," + " )";    
    
    
    
 
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_OBRA);
		db.execSQL(CREATE_TABLA_OBRASFUNCIONES);
		db.execSQL(CREATE_TABLA_OBRASIMAGENES);
		db.execSQL(CREATE_TABLA_USUARIOS);
		db.execSQL(CREATE_TABLA_BUTACAS);
		db.execSQL(CREATE_TABLA_COMODIDADES);
		db.execSQL(CREATE_TABLA_COMPRAS);
		db.execSQL(CREATE_TABLA_ENTRADAS);
		db.execSQL(CREATE_TABLA_FUNCIONES);
		db.execSQL(CREATE_TABLA_PRECIOS);
		db.execSQL(CREATE_TABLA_SALAS);
		db.execSQL(CREATE_TABLA_SALAOBRAS);
		db.execSQL(CREATE_TABLA_COMODIDADSALAS);
		db.execSQL(CREATE_TABLA_SALASECTORES);
		db.execSQL(CREATE_TABLA_SECTORES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public long createObra(Obra todo) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(COLUMNA_NOMBRE, todo.getNombre());
        values.put(COLUMNA_DESCRIPCION, todo.getDescripcion());
        long todo_id = db.insert(TABLA_OBRAS, null, values);
        return todo_id;
    }
	
	public long createFunciones(Funcion unaFuncion) {
        SQLiteDatabase db = this.getWritableDatabase();
        
        Date fechaComienzo = (Date) unaFuncion.getFechaObra();
        Date horaComienzo = (Date) unaFuncion.getHoraComienzo();
        
        ContentValues values = new ContentValues();
        values.put(COLUMNA_DURACION, unaFuncion.getDuracion());
        values.put(COLUMNA_HORACOMIENZO, horaComienzo.toString());
        values.put(COLUMNA_FECHA, fechaComienzo.toString());
        long funciones_id = db.insert(TABLA_FUNCIONES, null, values);
        return funciones_id;
    }
	
	public long createObrasFunciones(Obra unaObra, int funcionSeleccionada) {
        SQLiteDatabase db = this.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        values.put(COLUMNA_IDOBRAF, unaObra.getIdObra());
        values.put(COLUMNA_IDFUNCION, funcionSeleccionada);
        long obraFunciones_id = db.insert(TABLA_OBRASFUNCIONES, null, values);
        return obraFunciones_id;
    }
	
	public long createObrasImagenes(Obra unaObra) {
        SQLiteDatabase db = this.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        values.put(COLUMNA_IDOBRAI, unaObra.getIdObra());
        values.put(COLUMNA_IMAGEN, "lo creo yo");
        long obraImagenes_id = db.insert(TABLA_OBRASIMAGENES, null, values);
        return obraImagenes_id;
    }
	
	/*public long createButacas(Butaca unaButaca) {
        SQLiteDatabase db = this.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        values.put(COLUMNA_SECTOR, unaButaca.getMiSector().getIdSector());
        values.put(COLUMNA_IMAGEN, "lo creo yo");
        long obraImagenes_id = db.insert(TABLA_OBRASIMAGENES, null, values);
        return obraImagenes_id;
    }*/
	
	
	
 

        // Add your public helper methods to access and get content from the database.
       // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
       // to you to create adapters for your views.
 
}