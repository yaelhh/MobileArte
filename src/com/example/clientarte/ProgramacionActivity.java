package com.example.clientarte;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.SearchView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;


public class ProgramacionActivity extends Activity implements OnQueryTextListener{         
CalendarView cal;
private SearchView mSearchView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programacion);
        
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
}
		 
