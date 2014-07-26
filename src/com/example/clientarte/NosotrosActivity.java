package com.example.clientarte;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.SearchView.OnQueryTextListener;



public class NosotrosActivity extends  Activity implements OnQueryTextListener{

	private SearchView mSearchView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nosotros);

		
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
	
	

	
}
