package backend;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
	 
	private static final String DATABASE_NAME = "SodreDos.db";
    private static final int DATABASE_VERSION = 1;
    private static String DB_PATH = "/data/data/com.example.clientarte/databases/";
    //private static MySQLiteOpenHelper mOpenHelper = null;
    
    public static String TABLA_OBRAS = "db_obras";
    
    //Columnas
    public static String COLUMNA_ID = "idObra";
    public static String COLUMNA_NOMBRE = "nombreObra";
    public static String COLUMNA_DESCRIPCION = "descripcionObra";
    
    private static final String DATABASE_CREATE = "create table "
            + TABLA_OBRAS + "(" + COLUMNA_ID
            + " integer primary key autoincrement, " + COLUMNA_NOMBRE
            + " text not null" + COLUMNA_DESCRIPCION
            + " text not null);";
    
    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("delete table if exists " + TABLA_OBRAS);
        onCreate(db);
    }
     
//    public static class TablaObras{
//       
//
//        /*public static String COLUMNA_ID_IMAGENES = "idObraImagenes";
//        public static String COLUMNA_ID_FUNCIONES = "idObraFuncion";*/
//    }
    
    /*public static class TablaObraImagen{
        public static String TABLA_OBRAIMAGEN = "db_obraImagen";
        public static String COLUMNA_ID = "idObraImagen";
        public static String COLUMNA_IDOBRA = "idObra";
        public static String COLUMNA_IMAGEN = "imagen";
    }
    
    public static class TablaFunciones{
        public static String TABLA_FUNCIONES = "db_funciones";
        public static String COLUMNA_ID = "idFuncion";
        public static String COLUMNA_DURACION = "duracionObra";
        public static String COLUMNA_HORACOMIENZO = "horaComienzo";
        public static String COLUMNA_FECHAOBRA = "fechaObra";
        public static String COLUMNA_IDSALA = "idSala";
    }
    
    public static class TablaSalas{
        public static String TABLA_SALAS = "db_salas";
        public static String COLUMNA_ID = "idSala";
        public static String COLUMNA_NOMBRE = "nombreSala";
        public static String COLUMNA_CAPACIDAD = "idCapacidadSala";
    }
    
    public static class TablaComodidades{
        public static String TABLA_COMODIDADES = "db_comodidades";
        public static String COLUMNA_ID = "idComodidad";
        public static String COLUMNA_DISCAPACITADOS = "accesoDiscapacitados";
        public static String COLUMNA_DETECTORHUMO = "detectorHumo";
        public static String COLUMNA_SALIDAEMERGENCIA = "salidaEmergencia";
        public static String COLUMNA_WIFI = "wifi";
    }
    
    public static class TablaButaca{
        public static String TABLA_BUTACAS = "db_butacas";
        public static String COLUMNA_ID = "idButaca";
        public static String COLUMNA_IDSECTOR = "idSector";
        public static String COLUMNA_ESTADO = "estado";
    }
    
    public static class TablaSector{
        public static String TABLA_SECTOR = "db_sectores";
        public static String COLUMNA_ID = "idSector";
        public static String COLUMNA_IDSALA = "idSala";
        public static String COLUMNA_IDPRECIO = "idPrecio";
        public static String COLUMNA_CANTBUTACAS = "cantButacas";
    }
    
    public static class TablaCompra{
        public static String TABLA_COMPRA = "db_compras";
        public static String COLUMNA_ID = "idCompra";
        public static String COLUMNA_IDCODQR = "idCodQR";
        public static String COLUMNA_FECHAREALIZADA = "fechaRealizada";
        public static String COLUMNA_FECHAVIGENCIA = "fechaVigencia";
        public static String COLUMNA_IDPRECIO = "idPrecio";
        public static String COLUMNA_IDUSUARIO = "idUsuario";
        public static String COLUMNA_PRECIOTOTAL = "idPrecioTotal";
        public static String COLUMNA_ESTADO = "estaPaga";
    }
    
    public static class TablaEntrada{
        public static String TABLA_ENTRADAS = "db_entradas";
        public static String COLUMNA_ID = "idEntrada";
        public static String COLUMNA_IDCOMPRA = "idCompra";
        public static String COLUMNA_PRECIOUNITARIO = "precioUnitario";
        public static String COLUMNA_IDBUTACAS = "idButacas";
        public static String COLUMNA_IDOBRA = "idObra";
    }
    
    public static class TablaUsuario{
        public static String TABLA_USUARIOS = "db_usuarios";
        public static String COLUMNA_ID = "idUsuario";
        public static String COLUMNA_NOMBRE = "nombre";
        public static String COLUMNA_APELLIDO = "apellido";
        public static String COLUMNA_NOMBREUSUARIO = "nombreUsuario";
        public static String COLUMNA_PASSWORD = "password";
        public static String COLUMNA_FECHANACIMIENTO = "fechaNacimiento";
        public static String COLUMNA_CANTMASCARAS = "cantMascaras";
        public static String COLUMNA_ESTADO = "estaLogueado";
    }*/
     
    
    /*+ TablaFunciones.TABLA_FUNCIONES + "(" + TablaFunciones.COLUMNA_ID
            + " integer primary key autoincrement, " + TablaFunciones.COLUMNA_NOMBRE
            + " text not null" + TablaFunciones.COLUMNA_DESCRIPCION
            + " text not null);"*/
    
    
    /*private static final String DATABASE_CREATE = "create table "
            + TablaObras.TABLA_OBRAS + "(" + TablaObras.COLUMNA_ID
            + " integer primary key autoincrement, " + TablaObras.COLUMNA_DESCRIPCION
            + " text not null);";*/
     
   
            
            /*+ TablaObraImagen.TABLA_OBRAIMAGEN + "(" + TablaObraImagen.COLUMNA_ID
            + " integer primary key autoincrement, " + TablaObraImagen.COLUMNA_IMAGEN
            + " text not null" + TablaObraImagen.COLUMNA_IDOBRA
            + " text not null);"
            
            + TablaFunciones.TABLA_FUNCIONES + "(" + TablaFunciones.COLUMNA_ID
            + " integer primary key autoincrement, " + TablaFunciones.COLUMNA_DURACION
            + " text not null" + TablaFunciones.COLUMNA_HORACOMIENZO
            + " text not null" + TablaFunciones.COLUMNA_FECHAOBRA
            + " text not null" + TablaFunciones.COLUMNA_IDSALA
            + " text not null);"
            
            + TablaSalas.TABLA_SALAS + "(" + TablaSalas.COLUMNA_ID
            + " integer primary key autoincrement, " + TablaSalas.COLUMNA_NOMBRE
            + " text not null" + TablaSalas.COLUMNA_CAPACIDAD + " text not null);"
            
            + TablaComodidades.TABLA_COMODIDADES + "(" + TablaComodidades.COLUMNA_ID
            + " integer primary key autoincrement, " + TablaComodidades.COLUMNA_DISCAPACITADOS
            + " text not null" + TablaComodidades.COLUMNA_DETECTORHUMO
            + " text not null" + TablaComodidades.COLUMNA_SALIDAEMERGENCIA
            + " text not null" + TablaComodidades.COLUMNA_WIFI
            + " text not null);"
            
            + TablaButaca.TABLA_BUTACAS + "(" + TablaButaca.COLUMNA_ID
            + " integer primary key autoincrement, " 
            + " text not null" + TablaButaca.COLUMNA_IDSECTOR
            + " text not null" + TablaButaca.COLUMNA_ESTADO
            + " text not null);"
            
            + TablaSector.TABLA_SECTOR + "(" + TablaSector.COLUMNA_ID
            + " integer primary key autoincrement, " + TablaSector.COLUMNA_IDSALA
            + " text not null" + TablaSector.COLUMNA_IDPRECIO
            + " text not null" + TablaSector.COLUMNA_CANTBUTACAS
            + " text not null);"
            
            + TablaCompra.TABLA_COMPRA + "(" + TablaCompra.COLUMNA_ID
            + " integer primary key autoincrement, " + TablaCompra.COLUMNA_IDCODQR
            + " text not null" + TablaCompra.COLUMNA_FECHAREALIZADA
            + " text not null" + TablaCompra.COLUMNA_FECHAVIGENCIA
            + " text not null" + TablaCompra.COLUMNA_IDPRECIO
            + " text not null" + TablaCompra.COLUMNA_IDUSUARIO
            + " text not null" + TablaCompra.COLUMNA_PRECIOTOTAL
            + " text not null" + TablaCompra.COLUMNA_ESTADO
            + " boolean not null);"
            
            + TablaEntrada.TABLA_ENTRADAS + "(" + TablaEntrada.COLUMNA_ID
            + " integer primary key autoincrement, " + TablaEntrada.COLUMNA_IDCOMPRA
            + " text not null" + TablaEntrada.COLUMNA_PRECIOUNITARIO
            + " text not null" + TablaEntrada.COLUMNA_IDBUTACAS
            + " text not null" + TablaEntrada.COLUMNA_IDOBRA
            + " text not null);"
            
            + TablaUsuario.TABLA_USUARIOS + "(" + TablaUsuario.COLUMNA_ID
            + " integer primary key autoincrement, " + TablaUsuario.COLUMNA_NOMBRE
            + " text not null" + TablaUsuario.COLUMNA_APELLIDO
            + " text not null" + TablaUsuario.COLUMNA_NOMBREUSUARIO
            + " text not null" + TablaUsuario.COLUMNA_PASSWORD
            + " text not null" + TablaUsuario.COLUMNA_FECHANACIMIENTO
            + " text not null" + TablaUsuario.COLUMNA_CANTMASCARAS
            + " text not null" + TablaUsuario.COLUMNA_ESTADO
            + " text not null);"*/
            
            ;
    
  
 
 
 

    
   /* public static MySQLiteOpenHelper getInstance(Context context){
		if (mOpenHelper == null){
			mOpenHelper = new MySQLiteOpenHelper(context.getApplicationContext());
		}
		
		return mOpenHelper;
	}*/
	
	
	
}
