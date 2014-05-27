package com.example.clientarte;

import com.example.imageswitcher.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Build;
import android.widget.ViewSwitcher.ViewFactory;
import android.app.ActionBar.LayoutParams;

public class MainActivity extends ActionBarActivity {

	private ImageButton img;
	private ImageSwitcher imageSwitcher;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageButton)findViewById(R.id.imageButton1);
        imageSwitcher = (ImageSwitcher)findViewById(R.id.Gallery02);

        imageSwitcher.setFactory(new ViewFactory() {
			
			@Override
			public View makeView() {
				ImageView myView = new ImageView(getApplicationContext());
			      myView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			      myView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.
			      FILL_PARENT,LayoutParams.FILL_PARENT));
			      return myView;
			}
        });
     }
    
    public void next(View view){
        Toast.makeText(getApplicationContext(), "Next Image", 
        Toast.LENGTH_LONG).show();
        Animation in = AnimationUtils.loadAnimation(this,
        android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this,
        android.R.anim.slide_out_right);
        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);
        imageSwitcher.setImageResource(R.drawable.ic_launcher);
     }
     public void previous(View view){
        Toast.makeText(getApplicationContext(), "previous Image", 
        Toast.LENGTH_LONG).show();
        Animation in = AnimationUtils.loadAnimation(this,
        android.R.anim.slide_out_right);
        Animation out = AnimationUtils.loadAnimation(this,
        android.R.anim.slide_in_left);
        imageSwitcher.setInAnimation(out);
        imageSwitcher.setOutAnimation(in);
        imageSwitcher.setImageResource(R.drawable.ic_launcher);
     }
     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            View rootView = inflater.inflate(R.layout.activity_main, container, false);
            return rootView;
        }
    }

}
