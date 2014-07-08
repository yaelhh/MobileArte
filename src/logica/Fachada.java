package logica;

import java.util.ArrayList;

import dominio.Obra;

public class Fachada {
	
	private static final Fachada instance = new Fachada();
    public static Fachada getInstance() { return instance; }

	private ControladoraObras co = new ControladoraObras();
	private ControladoraCompras cc = new ControladoraCompras();
	private ControladoraSalas cs = new ControladoraSalas();
	private ControladoraUsuarios cu = new ControladoraUsuarios();
	
	///////////MANEJO OBRAS////////////
	public ArrayList retornarObras (){return co.getListaObras();}

	//public ArrayList retornarFuncionesObra(String obra) {return co.getListaFunciones(obra);}
	
	
	
	
}
