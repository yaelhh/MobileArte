package com.example.clientarte;





import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SearchView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;


public class NosotrosActivity extends MapActivity {
//public class NosotrosActivity extends  MapActivity implements OnQueryTextListener{

	private SearchView mSearchView;
	private MapView mapView;
	private MapController myMapController;
//	private MapView mapa = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nosotros);
		
		//Obtenemos una referencia al control MapView
		mapView = (MapView)findViewById(R.id.map);
	 myMapController = mapView.getController();
//	 GeoPoint centro = new GeoPoint((int)-34.904307,(int)-56.198644);
	 mapView.setBuiltInZoomControls(true);
	  List mapOverlays = mapView.getOverlays();
	Drawable drawable = this.getResources().getDrawable(R.drawable.ic_action_place);
	 HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay( drawable, this);
	  GeoPoint point = new GeoPoint(-27337005, -55866165);
	 OverlayItem overlayitem = new OverlayItem(point, "Bazinga!", "Ha upei desde Encarnacion!");
	 itemizedoverlay.addOverlay(overlayitem);
	 mapOverlays.add(itemizedoverlay);
		
	}
	
//	@Override
//    protected boolean isRouteDisplayed() {
//    	return false;
//    }
	
	

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        
       
        return true;
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	

	public class HelloItemizedOverlay extends ItemizedOverlay {
	private ArrayList mOverlays = new ArrayList();
	private Context mContext;
	 public HelloItemizedOverlay(Drawable defaultMarker, Context context) {
	 super(boundCenterBottom(defaultMarker));
	 mContext = context;
	
	 }
	 public void addOverlay(OverlayItem overlay) {
	 mOverlays.add(overlay);
	 populate();
	}
	@Override
	protected OverlayItem createItem(int i) {
	 return (OverlayItem) mOverlays.get(i);
	 }
	 @Override
	 public int size() {
	 return mOverlays.size();
	}
	@Override
	protected boolean onTap(int index) {
	  OverlayItem item = (OverlayItem) mOverlays.get(index);
	 AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage(item.getSnippet());
	  dialog.show();
	 return true;
	 }
	}
	

	
}
