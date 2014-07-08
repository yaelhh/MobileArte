package dominio;

public class Entrada {
	private Integer idEntrada;
	private Compra miCompra;
	private Butaca miButaca;
	private double precioUnitario;
	
	public Entrada(Integer idEntrada, Compra miCompra, Butaca miButaca,
			double precioUnitario) {
		super();
		this.idEntrada = idEntrada;
		this.miCompra = miCompra;
		this.miButaca = miButaca;
		this.precioUnitario = precioUnitario;
	}

	public Integer getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(Integer idEntrada) {
		this.idEntrada = idEntrada;
	}

	public Compra getMiCompra() {
		return miCompra;
	}

	public void setMiCompra(Compra miCompra) {
		this.miCompra = miCompra;
	}

	public Butaca getMiButaca() {
		return miButaca;
	}

	public void setMiButaca(Butaca miButaca) {
		this.miButaca = miButaca;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	

}
