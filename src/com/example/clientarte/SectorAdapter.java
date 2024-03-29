package com.example.clientarte;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class SectorAdapter extends BaseAdapter {
	private Context mContext;

    public SectorAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(50, 50));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds;
//    	{
//            R.drawable.butaca_roja, R.drawable.butaca_roja,
//            R.drawable.butaca_roja, R.drawable.butaca_roja,
//            R.drawable.ic_libre, R.drawable.ic_libre,
//            R.drawable.ic_libre, R.drawable.ic_libre,
//            R.drawable.ic_libre, R.drawable.ic_libre,
//            R.drawable.ic_libre, R.drawable.ic_libre,
//            R.drawable.ic_libre, R.drawable.ic_libre,
//            R.drawable.ic_libre, R.drawable.ic_libre,
//            R.drawable.ic_libre, R.drawable.ic_libre,
//            R.drawable.ic_libre, R.drawable.ic_libre,
//            R.drawable.ic_libre, R.drawable.ic_libre,
//            R.drawable.ic_libre, R.drawable.ic_libre,
//            R.drawable.ic_libre, R.drawable.ic_libre,
//           };

	public Integer[] getmThumbIds() {
		return mThumbIds;
	}

	public void setmThumbIds(Integer[] mThumbIds) {
		this.mThumbIds = mThumbIds;
	}

}
