package com.example.clientarte;

import dominio.Compra;
import android.graphics.Bitmap;


public class ListaEntradaCompra {
	private String tituloObra;
	private int codigoQR;
	private String funcion;
	private Bitmap bitmapObra;
	private int precio;
	private boolean pago;
	private Compra compra;
	
	public ListaEntradaCompra(int codigoQR,String tituloObra,String funcion,int precio,boolean pago,Compra compra) {
		super();
		this.tituloObra = tituloObra;
		this.codigoQR = codigoQR;
		this.funcion=funcion;
		this.precio=precio;
		this.pago=pago;
		this.compra=compra;
	}

	public String getTituloObra() {
		return tituloObra;
	}

	public void setTituloObra(String tituloObra) {
		this.tituloObra = tituloObra;
	}

	public int getCodigoQR() {
		return codigoQR;
	}

	public void setCodigoQR(int codigoQR) {
		this.codigoQR = codigoQR;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public Bitmap getBitmapObra() {
		return bitmapObra;
	}

	public void setBitmapObra(Bitmap bitmapObra) {
		this.bitmapObra = bitmapObra;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
	
}
