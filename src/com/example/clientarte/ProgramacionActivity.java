package com.example.clientarte;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

//

public class ProgramacionActivity extends Activity implements OnQueryTextListener{         
CalendarView cal;
private SearchView mSearchView;
private ImageButton mObra;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programacion);
       
//        if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.containerProgramacion, new PlaceholderFragment()).commit();
//		}
        
        mObra= (ImageButton)findViewById(R.id.GalleryObra);
        
        addListenerOnButton();
        cal = (CalendarView) findViewById(R.id.calendarView1);
        
        cal.setOnDateChangeListener(new OnDateChangeListener() {
			
		@Override
		public void onSelectedDayChange(CalendarView view, int year, int month,
				int dayOfMonth) {
			
			
			Toast.makeText(getBaseContext(),"Selected Date is\n\n"
				+dayOfMonth+" : "+month+" : "+year , 
				Toast.LENGTH_LONG).show();
		}
	});
      
		
    }
 
    
    
    @Override
	public boolean onQueryTextChange(String newText) {

	    Toast.makeText(this, newText, Toast.LENGTH_SHORT).show();

	    return false;
	}
	@Override
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
        mSearchView.setQueryHint("Search…");
        mSearchView.setOnQueryTextListener(this);
        
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_notification:
	            //composeMessage();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	
	public void addListenerOnButton() {
		 
		mObra.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ProgramacionActivity.this, ObraActivity.class);
				startActivity(intent);
			}

		});
}
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_programacion, container, false);
			return rootView;
		}
	}
}
		 
