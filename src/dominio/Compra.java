package dominio;

import java.util.ArrayList;
import java.util.Date;

import android.R.integer;
import android.os.Parcel;
import android.os.Parcelable;

public class Compra implements Parcelable{
	private String idCompra;
	private String fechaRealizada;
	private String fechaVigencia;
	private Obra miObra;
	private boolean pago;
	private Usuario miUsuario;
	private int precioTotal;
	private String code;
	private Funcion funcionSeleccionada;
	private ArrayList<Butaca> butacasSeleccionadas;
	
	public Compra(){
		
	}
	public Compra(String fechaRealizada, 
			Obra miObra, boolean pago, Usuario miUsuario, int precioTotal,
			Funcion funcionSeleccionada,ArrayList<Butaca> butacasSeleccionadas ) {
		super();
		this.idCompra = idCompra;
		this.fechaRealizada = fechaRealizada;
		this.miObra = miObra;
		this.pago = pago;
		this.miUsuario = miUsuario;
		this.precioTotal = precioTotal;
		this.funcionSeleccionada=funcionSeleccionada;
		this.butacasSeleccionadas=butacasSeleccionadas;
		
	}

	public String getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(String idCompra) {
		this.idCompra = idCompra;
	}

	public String getFechaRealizada() {
		return fechaRealizada;
	}

	public void setFechaRealizada(String fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}

	public String getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(String fechaVigencia) {
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

	public int getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(int precioTotal) {
		this.precioTotal = precioTotal;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Funcion getFuncionSeleccionada() {
		return funcionSeleccionada;
	}
	public void setFuncionSeleccionada(Funcion funcionSeleccionada) {
		this.funcionSeleccionada = funcionSeleccionada;
	}
	public ArrayList<Butaca> getButacasSeleccionadas() {
		return butacasSeleccionadas;
	}
	public void setButacasSeleccionadas(ArrayList<Butaca> butacasSeleccionadas) {
		this.butacasSeleccionadas = butacasSeleccionadas;
	}
	
	// Constructor para crear el objeto a partir de un parcelable
		public Compra (Parcel in) {
			readFromParcel(in);
		}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(idCompra);
		dest.writeString(fechaRealizada);
		dest.writeString(fechaVigencia);
		dest.writeParcelable(miObra,flags);
		dest.writeBooleanArray(new boolean[] {pago});
		dest.writeParcelable(miUsuario,flags);
		dest.writeInt(precioTotal);
		dest.writeString(code);
		dest.writeParcelable(funcionSeleccionada, flags);
		dest.writeTypedList(butacasSeleccionadas);

	}
	//Clase para recuperar los datos, IMPORTANTE leerlos en el mismo orden en que se escribieron 
		private void readFromParcel(Parcel in) {
			idCompra= in.readString();
			fechaRealizada=in.readString();
			fechaVigencia=in.readString();
			miObra=in.readParcelable(Obra.class.getClassLoader());
			boolean[] temp = new boolean[1];
			in.readBooleanArray(temp);
			pago = temp[0];
			miUsuario=in.readParcelable(Usuario.class.getClassLoader());
			precioTotal= in.readInt();
			code= in.readString();
			funcionSeleccionada= in.readParcelable(Funcion.class.getClassLoader());
			in.readTypedList(butacasSeleccionadas, Butaca.CREATOR);

		}
		

		//Necesario para usar la clase en Arrays
		public static final Parcelable.Creator<Compra> CREATOR = new Parcelable.Creator<Compra>() {
			public Compra createFromParcel(Parcel in) {
				return new Compra (in);
			}

			public Compra [] newArray(int size) {
				return new Compra[size];
			}
		};

	

		public String toString (){
			return  "Compra: "+idCompra +"Obra: "+ miObra.getNombre()+ "Funcion "+ funcionSeleccionada.toString()+ "";
		}
	
	
	

}
