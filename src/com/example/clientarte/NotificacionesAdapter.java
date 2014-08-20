package com.example.clientarte;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import dominio.Notificaciones;

public class NotificacionesAdapter extends BaseAdapter {
	 
	static class ViewHolder{
		TextView tvFecha_pub;
		TextView tvtitulo_post;
		CheckBox cb;
	}
	 
	   private static final String TAG = "CustomAdapter";
	  private static int convertViewCounter = 0;
	 
	  
	  private ArrayList<Notificaciones> data; 
	  private LayoutInflater inflater = null;

	  public NotificacionesAdapter(Context c, ArrayList<Notificaciones> d)
	    {
	       Log.v(TAG, "Constructing CustomAdapter");
	 
	       this.data = d;
	      inflater = LayoutInflater.from(c);
	  }
	 
	   @Override
	   public int getCount()
	   {
	       Log.v(TAG, "in getCount()");
	        return data.size();
	 }
	 
	   @Override
	   public Object getItem(int position)
	 {
	       Log.v(TAG, "in getItem() for position " + position);
	        return data.get(position);
	  }
	 
	   @Override
	   public long getItemId(int position)
	 {
	       Log.v(TAG, "in getItemId() for position " + position);
	      return position;
	    }
	 
	   @Override
	   public int getViewTypeCount()
	   {
	       Log.v(TAG, "in getViewTypeCount()");
	        return 1;
	   }
	 
	   @Override
	   public int getItemViewType(int position)
	    {
	       Log.v(TAG, "in getItemViewType() for position " + position);
	        return 0;
	   }
	 
	   @Override
	   public void notifyDataSetChanged()
	  {
	       super.notifyDataSetChanged();
	   }
	 
	   @Override
	   public View getView(int position, View convertView, ViewGroup parent)
	   {
	 
	       ViewHolder holder;
	 
	      Log.v(TAG, "in getView for position " + position + ", convertView is "
	              + ((convertView == null) ? "null" : "being recycled"));
	 
	     if (convertView == null)
	        {
	           convertView = inflater.inflate(R.layout.activity_notificaciones, null);
	 
	          convertViewCounter++;
	            Log.v(TAG, convertViewCounter + " convertViews have been created");
	 
	            holder = new ViewHolder();
	 
	          holder.tvFecha_pub = (TextView) convertView
	                 .findViewById(R.id.action_bar);
	            holder.tvtitulo_post = (TextView) convertView
	                   .findViewById(R.id.action_bar);
	           holder.cb = (CheckBox) convertView.findViewById(R.id.action_bar);
	            holder.cb.setOnClickListener(checkListener);
	 
	            convertView.setTag(holder);
	 
	     } else
	          holder = (ViewHolder) convertView.getTag();
	 
	     // Para porde hacer click en el checkbox
	        Notificaciones d = (Notificaciones) getItem(position);
	      holder.cb.setTag(d);
	        // Setting all values in listview
	      holder.tvFecha_pub.setText(data.get(position).getFecha_publicacion());
	      //holder.tvFecha_pub.setText(data.get(position).getTipo());
	      holder.tvFecha_pub.setText(data.get(position).getTipo());
	      holder.tvtitulo_post.setText(data.get(position).getTitulo());
	      holder.tvtitulo_post.setText(data.get(position).getTexto());
	      holder.cb.setChecked(data.get(position).isLeido());
	 
	      return convertView;
	 }
	 
	   public void setCheck(int position)
	  {
	       Notificaciones d = data.get(position);
	 
	        d.setLeido(!d.isLeido());
	      notifyDataSetChanged();
	 }
	 
	   public void checkAll(boolean state)
	 {
	       for (int i = 0; i < data.size(); i++)
	           data.get(i).setLeido(state);
	  }
	 
	   public void cancelSelectedPost()
	    {
	 
	       int i = 0;
	      while (i < getCount())
	      {
	           if (data.get(i).isLeido())
	           {
	               data.remove(data.indexOf(data.get(i)));
	         } else
	              i++;
	        }
	       notifyDataSetChanged();
	 
	 }
	 
	   public boolean haveSomethingSelected()
	  {
	       for (int i = 0; i < data.size(); i++)
	           if (data.get(i).isLeido())
	               return true;
	        return false;
	   }
	   
	   protected void onCreate(Bundle savedInstanceState) {
		   
	   }
	   
	   
	   
	   
	   /**
	  * Este método es para poder seleccionar una fila directamente con el
	    * checkbox en lugar de tener que pulsar en la liste en sí
	   */
	 private OnClickListener checkListener = new OnClickListener()
	   {
	 
	       @Override
	       public void onClick(View v)
	     {
	           Notificaciones d = (Notificaciones) v.getTag();
	         d.setLeido(!d.isLeido());
	      }
	   };
	}