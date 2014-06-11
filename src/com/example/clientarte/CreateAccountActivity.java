package com.example.clientarte;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CreateAccountActivity extends ActionBarActivity {

	private TextView dateDisplay; 
	private Button pickDate; 
	/*private int year; 
	private int month; 
	private int day; 
	static final int DATE_DIALOG_ID = 0; */
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.activity_create_account, new PlaceholderFragment()).commit();
		}
		
		/*pickDate.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { showDialog(DATE_DIALOG_ID); } });
		final Calendar c = Calendar.getInstance(); 
		year = c.get(Calendar.YEAR); 
		month = c.get(Calendar.MONTH); 
		day = c.get(Calendar.DAY_OF_MONTH);
		setContentView(R.layout.activity_create_account);

		dateDisplay = (TextView) findViewById(R.id.dateDisplay); 
		pickDate = (Button) findViewById(R.id.pickDate); 
		
		pickDate.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { showDialog(DATE_DIALOG_ID); } });
		 updateDate() ;*/
		
	}
	
	
	
	/*private DatePickerDialog mDateSetListener = new DatePickerDialog(this, mDateSetListener, year, month, day) { 

		public void onDateSet(DatePicker view, int yearOf, int monthOfYear, int dayOfMonth) {
		 year = yearOf; 
		month = monthOfYear; 
		day = dayOfMonth; 
		updateDate();
		//Show the date on the TextView 
		}
	};
		*/
	
	/*private DatePickerDialog onCreateDialog(int id) { 
		switch (id) { 
		case DATE_DIALOG_ID: 
			return new DatePickerDialog(this, mDateSetListener, year, month, day); 
			} 
		return null;
	} */
	
	/*private void updateDate() { 
		dateDisplay.setText( 
				new StringBuilder() 
				.append(month + 1).append("-")
				.append(day).append("-") 
				.append(year).append(" ")); 
				} */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_account, menu);
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

		
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_create_account,
					container, false);
			return rootView;
		}
	}

}
