package com.example.clientarte;

import android.widget.ImageButton;

public class ListaEntradaObra {
	private String tituloObra;
	private int imagenObra;
	
	public ListaEntradaObra(int imagenObra,String tituloObra) {
		super();
		this.tituloObra = tituloObra;
		this.imagenObra = imagenObra;
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
	
	

}
