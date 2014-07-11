package dominio;


import android.os.Parcel;
import android.os.Parcelable;

public class Butaca implements Parcelable{

	private int idButaca;
	private Boolean estadoButaca;
	
	public Butaca() {
		
		
		// TODO Auto-generated constructor stub
	}

	// Constructor para crear el objeto a partir de un parcelable
	public Butaca (Parcel in) {
		readFromParcel(in);
	}
	
	// Set & Get	
	public void setIdButaca (int id) {
		this.idButaca = id;
	}
	
	public void setEstadoButaca (boolean estado) {
		this.estadoButaca = estado;
	}
	
	public int getIdButaca() {
		return this.idButaca;
	}
	
	public boolean getEstadoButaca() {
		return this.estadoButaca;
	}
		
	//Método obligatorio
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	//Escribir a un parcel. OJO el orden es importante, debe escribirse como se declararon
	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		arg0.writeInt(idButaca);
		arg0.writeBooleanArray(new boolean[] {estadoButaca});
	}

	//Clase para recuperar los datos, IMPORTANTE leerlos en el mismo orden en que se escribieron 
	private void readFromParcel(Parcel in) {
		idButaca = in.readInt();
		boolean[] temp = new boolean[1];
		in.readBooleanArray(temp);
		estadoButaca = temp[0];
	}

	//Necesario para usar la clase en Arrays
	public static final Parcelable.Creator<Butaca> CREATOR = new Parcelable.Creator<Butaca>() {
		public Butaca createFromParcel(Parcel in) {
			return new Butaca (in);
		}

		public Butaca [] newArray(int size) {
			return new Butaca[size];
		}
	};

}
