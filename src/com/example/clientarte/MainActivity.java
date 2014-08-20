package com.example.clientarte;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData.Item;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;
import backend.DatabaseHelper;
import backend.NotificacionesBackend;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.java.core.KinveyClientCallback;

import dominio.Usuario;



public class MainActivity extends Activity implements OnQueryTextListener {


	private String[] titulos;
	private DrawerLayout NavDrawerLayout;
	private ListView NavList;
	private ArrayList<Item_objct> NavItms;
	private TypedArray NavIcons;
	private ActionBarDrawerToggle mDrawerToggle;
	@SuppressWarnings("unused")
	private CharSequence mDrawerTitle;
	@SuppressWarnings("unused")
	private CharSequence mTitle;
	private NavigationAdapter NavAdapter;
	private SearchView mSearchView;
	private static final int REQUEST_TEXT = 5;
	private Usuario usuarioLogueado;
	private Item opNotificacion;
	private Client mKinvey;
	private DatabaseHelper dh;
	private Button btnNot;
	private MenuItem notificacion;

	//Variables de la notificacion
	NotificationManager nm;
	Notification notif;
	static String ns = Context.NOTIFICATION_SERVICE;

	//Defino los iconos de la notificacion en la barra de notificacion
	int icono_v = R.drawable.ic_stat_ic_dialog_email;
	int icono_v02 = R.drawable.ic_stat_ic_dialog_email;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);	
		dh = new DatabaseHelper(getApplicationContext());

		final ObjetosBackend obj= (ObjetosBackend) getApplicationContext();
		mKinvey = obj.getmKinveyClient();
		//Drawer Layout
		NavDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		//Lista
		NavList = (ListView) findViewById(R.id.lista);
		//Declaramos el header el cual sera el layout de header.xml
		View header = getLayoutInflater().inflate(R.layout.header, null);
		//Establecemos header
		NavList.addHeaderView(header);
		//Tomamos listado  de imgs desde drawable
		NavIcons = getResources().obtainTypedArray(R.array.navigation_iconos);			
		//Tomamos listado  de titulos desde el string-array de los recursos @string/nav_options
		titulos = getResources().getStringArray(R.array.nav_options);
		//Listado de titulos de barra de navegacion
		NavItms = new ArrayList<Item_objct>();
		//Agregamos objetos Item_objct al array
		//Home	      
		NavItms.add(new Item_objct(titulos[0], NavIcons.getResourceId(0, -1)));
		//Programacion
		NavItms.add(new Item_objct(titulos[1], NavIcons.getResourceId(1, -1)));
		//Nosotros
		NavItms.add(new Item_objct(titulos[2], NavIcons.getResourceId(2, -1)));
		//Comunidad
		NavItms.add(new Item_objct(titulos[3], NavIcons.getResourceId(3, -1)));
		//Novedades
		NavItms.add(new Item_objct(titulos[4], NavIcons.getResourceId(4, -1)));
		//Perfil
		NavItms.add(new Item_objct(titulos[5], NavIcons.getResourceId(5, -1)));
		//Ajustes
		NavItms.add(new Item_objct(titulos[6], NavIcons.getResourceId(6, -1)));
		

		//Declaramos y seteamos nuestro adaptador al cual le pasamos el array con los titulos	       
		NavAdapter= new NavigationAdapter(this,NavItms);
		NavList.setAdapter(NavAdapter);	
		//Siempre vamos a mostrar el mismo titulo
		mTitle = mDrawerTitle = getTitle();

		//Declaramos el mDrawerToggle y las imgs a utilizar
		mDrawerToggle = new ActionBarDrawerToggle(
				this,                  /* host Activity */
				NavDrawerLayout,         /* DrawerLayout object */
				R.drawable.ic_drawer,  /* Icono de navegacion*/
				R.string.menu_open,  /* "open drawer" description */
				R.string.menu_close  /* "close drawer" description */
				) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				Log.e("Cerrado completo", "!!");
			}


			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				Log.e("Apertura completa", "!!");
			}
		};	        

		// Establecemos que mDrawerToggle declarado anteriormente sea el DrawerListener
		NavDrawerLayout.setDrawerListener(mDrawerToggle);
		//Establecemos que el ActionBar muestre el Boton Home
		getActionBar().setDisplayHomeAsUpEnabled(true);

		//Establecemos la accion al clickear sobre cualquier item del menu.
		//De la misma forma que hariamos en una app comun con un listview.
		NavList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
				MostrarFragment(position);
			}
		});

		//Cuando la aplicacion cargue por defecto mostrar la opcion Home
		MostrarFragment(1);
		//validarUsuarioNotificaciones(obj);
		//accederServicioNotificaciones();
//		validarCumpleanosDesdeBase(obj);
		
		
	}	

	/*Pasando la posicion de la opcion en el menu nos mostrara el Fragment correspondiente*/
	@SuppressWarnings("unused")
	public void MostrarFragment(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 1:
			fragment = new HomeFragment();
			break;
		case 2:
			fragment = new ProgramacionFragment();
			break;
		case 3:
			fragment = new NosotrosFragment();
			break;
		case 4:
			fragment = new ComunidadFragment();
			break;
		case 5:
			fragment = new NovedadesFragment();
			break;  
		case 6:
			fragment = new PerfilFragment();
			break;          
			/*case 7:
        fragment = new AjustesFragment();
        break;*/    
		default:
			//si no esta la opcion mostrara un toast y nos mandara a Home
			Toast.makeText(getApplicationContext(),"Opcion "+titulos[position-1]+"no disponible!", Toast.LENGTH_SHORT).show();
			fragment = new HomeFragment();
			position=1;
			break;
		}
		//Validamos si el fragment no es nulo
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

			// Actualizamos el contenido segun la opcion elegida
			NavList.setItemChecked(position, true);
			NavList.setSelection(position);
			//Cambiamos el titulo en donde decia "
			setTitle(titulos[position-1]);
			//Cerramos el menu deslizable
			NavDrawerLayout.closeDrawer(NavList);
		} else
			//Si el fragment es nulo mostramos un mensaje de error.
			Log.e("Error  ", "MostrarFragment"+position);
	}



	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			Log.e("mDrawerToggle pushed", "x");
			return true;
		}
		// Handle your other action bar items...
		//opNotificacion = (Item)findViewById(R.id.action_notification);
		switch (item.getItemId()) {
		case R.id.action_notification:
			//composeMessage();
			Intent intent = new Intent(MainActivity.this, NotificacionesActivity.class);
			startActivity(intent);
			return true;
		default:

			
		}  
		return super.onOptionsItemSelected(item);
	} 	
	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//	// Handle presses on the action bar items
//	switch (item.getItemId()) {
//	case R.id.action_notification:
//	//composeMessage();
//		Intent intent = new Intent(MainActivity.this, NotificacionesActivity.class);
//		startActivity(intent);
//	return true;
//	default:
//	return super.onOptionsItemSelected(item);
//	}
//	}
	//@Override
	public boolean onQueryTextChange(String newText) {

		Toast.makeText(this, newText, Toast.LENGTH_SHORT).show();

		return false;
	}
	//@Override
	public boolean onQueryTextSubmit(String text) {

		Toast.makeText(this, "Searching for " + text, Toast.LENGTH_LONG).show();
		return false;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		MenuItem searchItem = menu.findItem(R.id.action_search);
		mSearchView = (SearchView) searchItem.getActionView();
		mSearchView.setQueryHint("Search...");
		mSearchView.setOnQueryTextListener((OnQueryTextListener) this);
		
		
		return true;
	}
	
	public void llamarNotificacionesActvity(){
		Intent intent = new Intent(MainActivity.this, NotificacionesActivity.class);
		startActivity(intent);
	}

	@Override 
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		View view = null;
		if (requestCode == 5) { 
			if (resultCode == RESULT_OK) { 
				//BUSCO USUARIO SEGUN NOMBRE DE USUARIO KINVEY LOGUEADO
				String userNameLogueado = data.getStringExtra("username");
				Toast t=Toast.makeText(this,"Me traigo datos" + userNameLogueado, Toast.LENGTH_SHORT);
				t.show();
				//mKinveyClient.user().setUsername(userNameLogueado);
				usuarioLogueado.setEstaLogueado(1);
				usuarioLogueado.setMiNombreUsuario(userNameLogueado);

				Toast t2=Toast.makeText(this,"Usuario Logueado" + usuarioLogueado.getMiNombreUsuario().toString(), Toast.LENGTH_SHORT);
				t2.show();

			} else if (resultCode == RESULT_CANCELED) {  
			} 
		}
	}


	//public void validarUsuarioOpcion(View view){
	//
	//	Intent intent = new Intent(MainActivity.this, PerfilActivity.class); 
	//	MainActivity.this.startActivityForResult(intent, REQUEST_TEXT);
	//}


	//	private Notification getBigTextStyle(Notification.Builder builder) {
	//        builder
	//                .setContentTitle("Reduced BigText title")
	//                .setContentText("Reduced content")
	//                .setContentInfo("Info")
	//                .setSmallIcon(R.drawable.ic_launcher)
	//                .setLargeIcon(bitmapIcon);
	// 
	//        return new Notification.BigTextStyle(builder)
	//                .bigText("Looooooooooooooong teeeeeeeeeext.... (and more, and more, and more)")
	//                .setBigContentTitle("Expanded BigText title")
	//                .setSummaryText("Summary text")
	//                .build();
	//    }

	private void accederServicioNotificaciones(Usuario u){
		String cantMascaras = 100 + "";
		String Tipo = "Feliz cumple";
		//String Titulo = "Feliz cumple" + u.getNombre() + u.getApellido();
		String Titulo = "Feliz cumple" + u.getMiNombreUsuario();
		String Texto = "Art-e le desea muy feliz cumpleaños!! Por este motivo tenemos el agrado de obsequiarle" + " " + cantMascaras + " " + "mascaras";

		// Inicio el servicio de notificaciones accediendo al servicio
		nm = (NotificationManager) getSystemService(ns);

		// Realizo una notificacion por medio de un metodo hecho por mi
		//notificacion(icono_v02, "Feliz cumple", "texto contenido", "texto extendido");
		notificacion(icono_v02, Tipo, Titulo, Texto);

		// Lanzo la notificacion creada en el paso anterior
		nm.notify(1, notif);
		persistirNotificacion(Tipo, Titulo, Texto);
	}

	public void persistirNotificacion(String tipo, String titulo, String texto){
		final NotificacionesBackend entity = new NotificacionesBackend ();
		entity.setIdUsuario(mKinvey.user().getUsername().toString());
		entity.setTipo(tipo);
		entity.setTitulo(titulo);
		entity.setTexto(texto);

		AsyncAppData<NotificacionesBackend> myevents = mKinvey.appData("NotificacionesUsuario",NotificacionesBackend.class);
		myevents.save(entity, new KinveyClientCallback<NotificacionesBackend>() {

			@Override
			public void onFailure(Throwable e) {

			}
			@Override
			public void onSuccess(NotificacionesBackend arg0) {

				Toast.makeText(MainActivity.this,"Entity Saved\nTitle: " + arg0.getIdUsuario()
						+ "\nDescription: " + arg0.getTipo(), Toast.LENGTH_LONG).show();
			}
		});
	}

	public void notificacion(int icon, CharSequence textoEstado, CharSequence titulo, CharSequence texto) {
		// Capturo la hora del evento
		long hora = System.currentTimeMillis();

		// Definimos la accion de la pulsacion sobre la notificacion (esto es opcional)
		Context context = getApplicationContext();
		Intent notificationIntent = new Intent(this, NotificacionesActivity.class);

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		eliminarNotificacion();
		// Defino la notificacion, icono, texto y hora
		notif = new Notification(icon, textoEstado, hora);
		notif.setLatestEventInfo(context, titulo, texto, contentIntent);

		//Defino que la notificacion sea permamente
		notif.flags = Notification.FLAG_ONGOING_EVENT;
	}

	public void eliminarNotificacion(){
		// Quitamos notificaciones
		nm.cancel(1);
	}


	//	public void validarUsuarioNotificaciones(ObjetosBackend ob){
	////		eliminarNotificacion();
	//		String nombreLogueado = ob.captarUsuarioLogueado().user().getUsername().toString();
	//		if (mKinvey.user().isUserLoggedIn()){
	//			UsuarioBackend usuario = ob.obtenerNacimientoUsuario(mKinvey.user().getUsername().toString());
	//			if (!validarFecha(usuario)){
	//				accederServicioNotificaciones(usuario);	
	//			}
	//			Toast.makeText(MainActivity.this, "Segui tranqui", icono_v).show();
	//		}
	//
	//	}

	public void validarCumpleanosDesdeBase(ObjetosBackend ob){
		//		eliminarNotificacion();
		if (mKinvey.user() != null){
			
		
		if (mKinvey.user().isUserLoggedIn()){
			String nombreLogueado = ob.captarUsuarioLogueado().user().getUsername().toString();
			//UsuarioBackend usuario = ob.obtenerNacimientoUsuario(mKinvey.user().getUsername().toString());
			Usuario u = dh.obtenerFechaCumpleanos(nombreLogueado);
			try{
				if (u != null){
					if (validarFecha(u)){
						accederServicioNotificaciones(u);
					}
				}
			}catch (Exception i){
				Toast.makeText(MainActivity.this, "Segui tranqui", icono_v).show();	
			}

		}
		}

	}

	public boolean validarFecha(Usuario u){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
		String fechaActual = df1.format(c.getTime());
		boolean retorno = false;
		if (u.getFechaNacimiento().equalsIgnoreCase(fechaActual)){
			retorno = true;
		}else{
			retorno = false;
		}

		return retorno;
	}
	
	


}