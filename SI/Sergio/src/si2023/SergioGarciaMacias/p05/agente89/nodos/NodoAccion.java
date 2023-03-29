package si2023.SergioGarciaMacias.p05.agente89.nodos;

import ontology.Types.ACTIONS;
import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Accion;
import si2023.SergioGarciaMacias.ia.reglas.Nodo;

public class NodoAccion extends Nodo {

	private Accion acc;
	
	public NodoAccion(Accion a) {
		this.drc = null;
		this.izq = null;
		this.acc = a;
	}

	
	public ACTIONS get_action(Mundo m) {
		return this.acc.do_action(m);
	}
}
