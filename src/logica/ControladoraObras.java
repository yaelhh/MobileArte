package logica;

import java.util.ArrayList;

import dominio.Obra;

public class ControladoraObras {
	
	private ArrayList listaObras = new ArrayList();

	public ArrayList getListaObras() {
		return listaObras;
	}

	public void setListaObras(ArrayList listaObras) {
		this.listaObras = listaObras;
	}

	public ControladoraObras(ArrayList listaObras) {this.listaObras = listaObras;}

	public ControladoraObras() {
		// TODO Auto-generated constructor stub
	}

	
	
	/*public buscarObraPorNombre (String obra){
		for (int i = 0; i<this.listaObras.size();i++){
			Obra aux = (Obra)listaObras.get(i);
			if (aux.getNombre() == obra){
				aux
			}
		}
	}*/
	
	

}
