package com.example.clientarte;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.CalendarView.OnDateChangeListener;

/*  Fragment para seccion Programacion */ 
public class ProgramacionFragment extends Fragment {
	CalendarView cal;
	private SearchView mSearchView;
	private ImageButton mObra;
     
    public ProgramacionFragment(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.activity_programacion, container, false);
        
        
        cal = (CalendarView)rootView.findViewById(R.id.calendarView1);
        
        cal.setOnDateChangeListener(new OnDateChangeListener() {
			
		@Override
		public void onSelectedDayChange(CalendarView view, int year, int month,
				int dayOfMonth) {
			
			
			Toast.makeText(getActivity().getBaseContext(),"Selected Date is\n\n"
				+dayOfMonth+" : "+month+" : "+year , 
				Toast.LENGTH_LONG).show();
		}
	});
//       mObra= (ImageButton)rootView.findViewById(R.id.GalleryObra);
//        addListenerOnButton();
        return rootView;
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
   	
   	
//   	public void addListenerOnButton() {
//   		 
//   		mObra.setOnClickListener(new OnClickListener() {
//    
//   			@Override
//   			public void onClick(View v) {
//   				Intent intent = new Intent(getActivity(), ObraActivity.class);
//   				startActivity(intent);
//   			}
//
//   		});
//   }
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