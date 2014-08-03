package com.example.clientarte;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import backend.DBAdapter;
import backend.Obras.UsuarioColumns;
import dominio.Obra;


public class SQLiteActivity extends ListActivity {

	private DBAdapter dbAdapter;
	private Boolean mBound;
	
	//final String[] from = { ClientesColumns.NOMBRE, ClientesColumns.APELLIDOS, ClientesColumns.EDAD };
	final String[] from = { UsuarioColumns.nombreUsuario, UsuarioColumns.passwordUsuario, UsuarioColumns.estaLogueadoUsuario };	
	final int[] to = new int[] { R.id.nombre, R.id.apellidos, R.id.edad };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		registerForContextMenu(this.getListView());
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (v.getId() == android.R.id.list) {

			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

			@SuppressWarnings("unchecked")
			HashMap<String, String> item = (HashMap<String, String>) this
					.getListAdapter().getItem(info.position);

			menu.setHeaderTitle(item.get(UsuarioColumns.nombreUsuario) + " "
					+ item.get(UsuarioColumns.passwordUsuario) + " "
					+ item.get(UsuarioColumns.estaLogueadoUsuario));
			String[] menuItems = getResources()
					.getStringArray(R.array.listmenu);
			for (int i = 0; i < menuItems.length; i++) {
				menu.add(Menu.NONE, i, i, menuItems[i]);
			}
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		@SuppressWarnings("unchecked")
		HashMap<String, String> itm = (HashMap<String, String>) this
				.getListAdapter().getItem(info.position);
		int menuItemIndex = item.getItemId();
		switch (menuItemIndex) {
		case 0:
			showEditClientDialog(
					//Integer.parseInt(itm.get(UsuarioColumns._ID)),
					itm.get(UsuarioColumns.nombreUsuario),
					itm.get(UsuarioColumns.passwordUsuario),
					itm.get(UsuarioColumns.estaLogueadoUsuario));
			return true;
		case 1:
			showConfirmDialog(info.position);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);
		return true;
	}

	

	private void showDialogNuevoCliente() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		LayoutInflater factory = LayoutInflater.from(this);
		final View textEntryView = factory.inflate(R.layout.dialog, null);
		builder.setView(textEntryView);
		builder.setTitle("Nuevo Cliente")
				.setCancelable(false)
				.setPositiveButton("Añadir",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								EditText name = (EditText) textEntryView
										.findViewById(R.id.nuevonombre);
								EditText surname = (EditText) textEntryView
										.findViewById(R.id.nuevoapellidos);
								EditText age = (EditText) textEntryView
										.findViewById(R.id.nuevoedad);
								/*addClient(name.getText().toString(), surname
										.getText().toString(), age.getText()
										.toString());*/

							}

						})
				.setNegativeButton("Cancelar",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();

	}

	private void showEditClientDialog(String name,
			String surname, String state) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		LayoutInflater factory = LayoutInflater.from(this);
		final View textEntryView = factory.inflate(R.layout.dialog, null);
		builder.setView(textEntryView);
		EditText Name = (EditText) textEntryView.findViewById(R.id.nuevonombre);
		EditText Surname = (EditText) textEntryView
				.findViewById(R.id.nuevoapellidos);
		EditText Age = (EditText) textEntryView.findViewById(R.id.nuevoedad);
		Name.setText(name);
		Surname.setText(surname);
		//Age.setText(age);
		builder.setTitle("Editar Cliente")
				.setCancelable(false)
				.setPositiveButton("Guardar",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								EditText name = (EditText) textEntryView
										.findViewById(R.id.nuevonombre);
								EditText surname = (EditText) textEntryView
										.findViewById(R.id.nuevoapellidos);
								EditText age = (EditText) textEntryView
										.findViewById(R.id.nuevoedad);
								
							}

						})
				.setNegativeButton("Cancelar",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();

	}

	private void showConfirmDialog(int position) {
		@SuppressWarnings("unchecked")
		final HashMap<String, String> itm = (HashMap<String, String>) this
				.getListAdapter().getItem(position);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(
				"¿Estás seguro de querer BORRAR a "
						+ itm.get(UsuarioColumns.nombreUsuario) + " "
						+ itm.get(UsuarioColumns.passwordUsuario) + "?")
				.setCancelable(false)
				.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						deleteClient(Integer.parseInt(itm.get(BaseColumns._ID)));

					}
				})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}

	protected void deleteClient(int ID) {
		dbAdapter.deleteCliente(ID);
<<<<<<< HEAD
		//loadClientes();
=======
//		loadClientes();
>>>>>>> develop
	}

	/*protected void editClient(int iD, String nombre, String apellidos,
			String edad) {
		dbAdapter.updateCliente(iD, nombre, apellidos, Integer.parseInt(edad));
		loadClientes();
	}*/
	
	protected void editClient(int iD, String nombre, String apellidos) {
<<<<<<< HEAD
		//dbAdapter.updateCliente(iD, nombre, apellidos);
		//loadClientes();
=======
		dbAdapter.updateCliente(iD, nombre, apellidos);
//		loadClientes();
>>>>>>> develop
	}

	/*private void addClient(String nombre, String apellidos, String edad) {
		dbAdapter.insertCliente(nombre, apellidos, Integer.parseInt(edad));
		loadClientes();
	}*/

<<<<<<< HEAD
=======
//	Lo comento porque me da error getAllObras, ya que lo comente en la clase dbAdapter
>>>>>>> develop
//	private void loadClientes() {
//		ArrayList<Obra> obras = dbAdapter.getAllObras();
//
//		// Fill List with DATA
//		ArrayList<HashMap<String, String>> HClientes = new ArrayList<HashMap<String, String>>();
//		for (Obra obra : obras) {
//
//			HashMap<String, String> clientData = new HashMap<String, String>();
//
//			clientData
//					.put(ObrasColumns._ID, String.valueOf(obra.getIdObra()));
//			clientData.put(from[0], obra.getNombre());
//			clientData.put(from[1], obra.getDescripcion());
//
//			HClientes.add(clientData);
//		}
//
//		SimpleAdapter ListAdapter = new SimpleAdapter(this, HClientes,
//				R.layout.row, from, to);
//		setListAdapter(ListAdapter);
//	}
/*
	@Override
	protected void onStart() {
		super.onStart();
		// Enlazamos con el Servicio
		Intent intent = new Intent(this, DBAdapter.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		super.onStop();
		// Nos desenlazamos del servicio
		if (mBound) {
			unbindService(mConnection);
			mBound = false;
		}
	}

	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			// Enlazamos con LocalService, casteamos el IBinder y obtenemos así
			// la instancia del Servicio
			LocalBinder binder = (LocalBinder) service;
			dbAdapter = binder.getService();
			mBound = true;
			loadClientes();
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			mBound = false;
		}
	};*/
}