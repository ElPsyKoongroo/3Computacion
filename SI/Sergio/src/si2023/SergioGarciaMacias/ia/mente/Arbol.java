package si2023.SergioGarciaMacias.ia.mente;

import ontology.Types.ACTIONS;
import si2023.SergioGarciaMacias.ia.reglas.Nodo;

public abstract class Arbol {

	protected Nodo root;
		
	public Arbol() {};
	
	public Nodo get_root() {
		return this.root;
	}
	
	public abstract ACTIONS decide(Mundo m);
	public abstract ACTIONS pensar(Mundo m);
}
