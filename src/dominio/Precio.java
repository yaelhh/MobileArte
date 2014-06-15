package dominio;

import java.util.Date;

import android.R.integer;

public class Precio {
	//Variables
		private integer idPrecio;
		private double monto;
	
		//Constructor
		public Precio(integer idPrecio, double monto) {
			super();
			this.idPrecio = idPrecio;
			this.monto = monto;
		}
		
		
	//get and set
		public integer getIdPrecio() {
			return idPrecio;
		}
		public void setIdPrecio(integer idPrecio) {
			this.idPrecio = idPrecio;
		}
		public double getMonto() {
			return monto;
		}
		public void setMonto(double monto) {
			this.monto = monto;
		}

}
