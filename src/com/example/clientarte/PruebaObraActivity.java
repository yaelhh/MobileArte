package com.example.clientarte;

import android.support.v7.app.ActionBarActivity;

public class PruebaObraActivity extends ActionBarActivity {

//	 private int requestCode = 1;
//	    private ListView lvNotas;
//	    private DB_Obra dataSource;
//	 
//	    @Override
//	    protected void onCreate(Bundle savedInstanceState) {
//	        super.onCreate(savedInstanceState);
//	        setContentView(R.layout.activity_prueba_obra);
//	 
//	        // Instanciamos NotasDataSource para
//	        // poder realizar acciones con la base de datos
//	        dataSource = new DB_Obra(this);
//	        dataSource.open();
//	 
//	        // Instanciamos los elementos
//	        lvNotas = (ListView) findViewById(R.id.lvNotas);
//	 
//	        // Cargamos la lista de notas disponibles
//	        List<Obra> listaObras = dataSource.getAllObras();
//	        ArrayAdapter<Obra> adapter = new ArrayAdapter<Obra>(this,
//	                android.R.layout.simple_list_item_1, listaObras);
//	 
//	        // Establecemos el adapter
//	        lvNotas.setAdapter(adapter);
//	 
//	        // Establecemos un Listener para el evento de pulsación
//	        lvNotas.setOnItemClickListener((OnItemClickListener) this);
//	 
//	    }
//	 
//	    public void agregarNota(View v) {
//	        Intent i = new Intent(this, PruebaObraActivity.class);
//	        startActivityForResult(i, requestCode);
//	    }
//	 
//	    public void onItemClick(final AdapterView<?> adapterView, View view,
//	            final int position, long id) {
//	        // TODO Auto-generated method stub
//	        AlertDialog.Builder builder = new AlertDialog.Builder(this)
//	            .setTitle("Borrar Nota")
//	            .setMessage("¿Desea borrar esta nota?")
//	            .setPositiveButton("Aceptar",
//	                    new DialogInterface.OnClickListener() {
//	 
//	                        @Override
//	                        public void onClick(DialogInterface arg0, int arg1) {
//	                            // TODO Auto-generated method stub
//	                            Obra nota = (Obra) adapterView
//	                                    .getItemAtPosition(position);
//	                            dataSource.borrarObra(nota);
//	                             
//	                            // Refrescamos la lista
//	                            refrescarLista();
//	                        }
//	                })
//	 
//	            .setNegativeButton("Cancelar",
//	                    new DialogInterface.OnClickListener() {
//	 
//	                        @Override
//	                        public void onClick(DialogInterface dialog,
//	                                int which) {
//	                            // TODO Auto-generated method stub
//	                            return;
//	                        }
//	                });
//	        builder.show();
//	    }
//	 
//	    @Override
//	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//	        // TODO Auto-generated method stub
//	        super.onActivityResult(requestCode, resultCode, data);
//	        if (requestCode == this.requestCode && resultCode == RESULT_OK) {
//	            // Actualizar el Adapter
//	            dataSource.open();
//	            refrescarLista();
//	        }
//	    }
//	 
//	    private void refrescarLista() {
//	        List<Obra> listaObras = dataSource.getAllObras();
//	        ArrayAdapter<Obra> adapter = new ArrayAdapter<Obra>(this,
//	            android.R.layout.simple_list_item_1, listaObras);
//	        lvNotas.setAdapter(adapter);
//	    }
//	 
//	    @Override
//	    protected void onPause() {
//	        // TODO Auto-generated method stub
//	        dataSource.close();
//	        super.onPause();
//	    }
//	 
//	    @Override
//	    protected void onResume() {
//	        // TODO Auto-generated method stub
//	        dataSource.open();
//	        super.onResume();
//	    }
	 	

}
