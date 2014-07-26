package com.example.clientarte;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryImageAdapter extends BaseAdapter {
	 private Context mContext;

	    private int[] mImageIds;
	    

	    public int[] getmImageIds() {
			return mImageIds;
		}

		public void setmImageIds(int[] mImageIds) {
			this.mImageIds = mImageIds;
		}

		public GalleryImageAdapter(Context context) 
	    {
	        mContext = context;
	    }

	    public int getCount() {
	        return mImageIds.length;
	    }

	    public Object getItem(int position) {
	        return position;
	    }

	    public long getItemId(int position) {
	        return position;
	    }


	    // Override this method according to your need
	    public View getView(int index, View view, ViewGroup viewGroup) 
	    {
	        // TODO Auto-generated method stub
	        ImageView i = new ImageView(mContext);

	        i.setImageResource(mImageIds[index]);
	        i.setLayoutParams(new Gallery.LayoutParams(500, 150));
	    
	        i.setScaleType(ImageView.ScaleType.FIT_XY);

	        return i;
	    }

		
}
