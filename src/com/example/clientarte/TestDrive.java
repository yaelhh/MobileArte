package com.example.clientarte;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import backend.ObraBackend;
import backend.SalaBackend;

import com.example.clientarte.MainActivityBackend_old.PlaceholderFragment;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.Query;
import com.kinvey.java.User;
import com.kinvey.java.core.KinveyClientCallback;

public class TestDrive extends ActionBarActivity {

	public static final String TAG = "ArteBackend";
	private String appKey="kid_VT8_It3ePE";
	private String appSecret="1b0fa51481984d2da5910f78a9d26ccc";
	private String mensaje;
	private Client kinveyClient;

    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_backend);
	
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		//Conexión de la APP a Kinvey
		//kinveyClient = new Client.Builder(this.getApplicationContext()).build();
		kinveyClient = new Client.Builder(appKey, appSecret, this).build();
		kinveyClient.ping(new KinveyPingCallback() {
		    public void onFailure(Throwable t) {
		        Log.e("Probando Kinvey Connection", "Kinvey Ping Failed", t);
		    }
		    public void onSuccess(Boolean b) {
		        Log.d("Probando Kinvey Connection", "Kinvey Ping Success");
		    }
		});
		//mKinveyClient.user().login("nlema", "nlema", new KinveyUserCallback() {
		if (!kinveyClient.user().isUserLoggedIn()) {
			kinveyClient.user().login(new KinveyUserCallback() {
				public void onFailure(Throwable error) {
					mensaje = "Error al realizar el login.";
					Log.e("Realizando Kinvey Login", mensaje, error);
				}
				@Override
				public void onSuccess(User u) {
					mensaje = "Bienvenido usuario: " + u.getId() + ".";
					Log.d("Realizando Kinvey Login", mensaje);
				}
			});
		} else {
			mensaje = "Utilizando usuario implícito cacheado: " + kinveyClient.user().getId() + ".";
			Log.d("Realizando Kinvey Login", mensaje);
		}
	}
    
  //Recuperar una sala
  	public void recuperarSala (View view) {
          //appData es la interface para guardar y recuperar entidades 
  		kinveyClient.appData("Sala", SalaBackend.class).getEntity("3", new KinveyClientCallback<SalaBackend>() {
              @Override
              public void onSuccess(SalaBackend result) {
                  mensaje = "Sala id: " + result.getIdSala() + ", Nombre: " + result.getNombreSala();
                  Log.d(TAG + "- recuperarSala", mensaje);
              }
              @Override
              public void onFailure(Throwable error) {
                  Log.e(TAG + "- recuperarSala", "Falla en AppData.getEntity", error);
              }
          });
      }
  	
  //Recuperar todas las salas
    public void recuperarSalas(View view) {
        Query myQuery = kinveyClient.query();
        kinveyClient.appData("Sala", SalaBackend.class).get(myQuery, new KinveyListCallback<SalaBackend>() {
            @Override
            public void onSuccess(SalaBackend[] resultadoconsulta) {
                //for (Sala sala : result) {
            	for (int i = 0; i < resultadoconsulta.length; i++) {
                	mensaje = "Sala id: " + resultadoconsulta[i].getIdSala() + ", Nombre: " + resultadoconsulta[i].getNombreSala();
                	Log.d(TAG + "- recuperarSalas", mensaje);
                }
            }
            @Override
            public void onFailure(Throwable error) {
                Log.e(TAG, "AppData.get by Query Failure", error);
            }
        });
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_backend, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_backend, container,
					false);
			return rootView;
		}
	}

//	public void onLoadClick(View view) {
//       // bar.setVisibility(View.VISIBLE);
//        kinveyClient.appData("entityCollection", Entity.class).getEntity("myEntity", new KinveyClientCallback<Entity>() {
//            @Override
//            public void onSuccess(Entity result) {
//               // bar.setVisibility(View.GONE);
//                Toast.makeText(TestDrive.this,"Save Worked!\nTitle: " + result.getSubValues()
//                + "\nDescription: " + result.get("Description"), Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Throwable error) {
//                //bar.setVisibility(View.GONE);
//                Log.e(TAG, "AppData.getEntity Failure", error);
//                Toast.makeText(TestDrive.this, "Save Failed!\n: " + error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }

//    public void onQueryClick(View view) {
//       //bar.setVisibility(View.VISIBLE);
//        com.kinvey.java.Query myQuery = kinveyClient.query();
//        
//        myQuery.equals("_id","myEntity");
//        
//        kinveyClient.appData("entityCollection", Entity.class).get(myQuery, new KinveyListCallback<Entity>() {
//            @Override
//            public void onSuccess(Entity[] result) {
//                //bar.setVisibility(View.GONE);
//                for (Entity entity : result) {
//                    Toast.makeText(TestDrive.this,"Load Worked!\nTitle: " + entity.getTitle()
//                            + "\nDescription: " + entity.get("Description"), Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable error) {
//              //  bar.setVisibility(View.GONE);
//                Log.e(TAG, "AppData.get by Query Failure", error);
//                Toast.makeText(TestDrive.this, "Load Failed!\n " + error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }

//    public void onLoadAllClick(View view) {
//       // bar.setVisibility(View.VISIBLE);
//        //kinveyClient.appData("entityCollection", Entity.class).get(new Query(), new KinveyListCallback<Entity>() {
//        kinveyClient.appData("entityCollection", Entity.class).get( new KinveyListCallback<Entity>() {
//            @Override
//            public void onSuccess(Entity[] result) {
//             //   bar.setVisibility(View.GONE);
//                for (Entity entity : result) {
//                    Toast.makeText(TestDrive.this,"Entity Retrieved\nTitle: " + entity.getTitle()
//                            + "\nDescription: " + entity.get("Description"), Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable error) {
//            //    bar.setVisibility(View.GONE);
//                Log.e(TAG, "AppData.get all Failure", error);
//                Toast.makeText(TestDrive.this, "Get All error: " + error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }

//    public void onSaveClick(View view) {
//    	// bar.setVisibility(View.VISIBLE);
//    	Entity entity = new Entity("myEntity");
//    	entity.put("Description","This is a description of a dynamically-added Entity property.");
//    	kinveyClient.appData("entityCollection", Entity.class).save(entity, new KinveyClientCallback<Entity>() {
//    		@Override
//    		public void onSuccess(Entity result) {
//    			bar.setVisibility(View.GONE);
//    			Toast.makeText(TestDrive.this,"Entity Saved\nTitle: " + result.getTitle()
//    					+ "\nDescription: " + result.get("Description"), Toast.LENGTH_LONG).show();
//    		}
//
//    		@Override
//    		public void onFailure(Throwable error) {
//    			bar.setVisibility(View.GONE);
//    			Log.e(TAG, "AppData.save Failure", error);
//    			Toast.makeText(TestDrive.this, "Save All error: " + error.getMessage(), Toast.LENGTH_LONG).show();
//    		}
//    	});
//    }
//
//    public void onDeleteClick(View view) {
//    	bar.setVisibility(View.VISIBLE);
//    	kinveyClient.appData("entityCollection", Entity.class).delete("myEntity", new KinveyDeleteCallback() {
//    		@Override
//    		public void onSuccess(KinveyDeleteResponse result) {
//    			bar.setVisibility(View.GONE);
//    			Toast.makeText(TestDrive.this,"Number of Entities Deleted: " + result.getCount(), Toast.LENGTH_LONG).show();
//    		}
//
//    		@Override
//    		public void onFailure(Throwable error) {
//    			bar.setVisibility(View.GONE);
//    			Log.e(TAG, "AppData.delete Failure", error);
//    			Toast.makeText(TestDrive.this, "Delete error: " + error.getMessage(), Toast.LENGTH_LONG).show();
//    		}
//    	});
//    }
}