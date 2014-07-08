package dominio;

import java.util.Date;

import android.R.integer;

public class Compra {
	private integer idCompra;
	private Date fechaRealizada;
	private Date fechaVigencia;
	private Obra miObra;
	private boolean pago;
	private Usuario miUsuario;
	private Double precioTotal;
	private String code;
	
	public Compra(integer idCompra, Date fechaRealizada, Date fechaVigencia,
			Obra miObra, boolean pago, Usuario miUsuario, Double precioTotal,
			String code) {
		super();
		this.idCompra = idCompra;
		this.fechaRealizada = fechaRealizada;
		this.fechaVigencia = fechaVigencia;
		this.miObra = miObra;
		this.pago = pago;
		this.miUsuario = miUsuario;
		this.precioTotal = precioTotal;
		this.code = code;
	}

	public integer getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(integer idCompra) {
		this.idCompra = idCompra;
	}

	public Date getFechaRealizada() {
		return fechaRealizada;
	}

	public void setFechaRealizada(Date fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}

	public Date getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public Obra getMiObra() {
		return miObra;
	}

	public void setMiObra(Obra miObra) {
		this.miObra = miObra;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public Usuario getMiUsuario() {
		return miUsuario;
	}

	public void setMiUsuario(Usuario miUsuario) {
		this.miUsuario = miUsuario;
	}

	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	

}
