package com.example.clientarte;

import android.graphics.Bitmap;


public class ListaEntradaObra {
	private String tituloObra;
	private int imagenObra;
	private Bitmap bitmapObra;
	
	public ListaEntradaObra(int imagenObra,String tituloObra) {
		super();
		this.tituloObra = tituloObra;
		this.imagenObra = imagenObra;
	}

	public ListaEntradaObra(Bitmap bitmap, String nombre) {
		// TODO Auto-generated constructor stub
		super();
		this.tituloObra = nombre;
		this.bitmapObra = bitmap;
	}

	public String getTituloObra() {
		return tituloObra;
	}

	public void setTituloObra(String tituloObra) {
		this.tituloObra = tituloObra;
	}

	public int getImagenObra() {
		return imagenObra;
	}

	public void setImagenObra(int imagenObra) {
		this.imagenObra = imagenObra;
	}

	public Bitmap getBitmapObra() {
		return bitmapObra;
	}

	public void setBitmapObra(Bitmap bitmapObra) {
		this.bitmapObra = bitmapObra;
	}
	
	

}
