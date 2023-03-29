package si2023.SergioGarciaMacias.p05.agente89.nodos;

import si2023.SergioGarciaMacias.ia.reglas.Condicion;
import si2023.SergioGarciaMacias.ia.reglas.Nodo;

public class NodoEstandar extends Nodo {

	public NodoEstandar(Condicion c, Nodo true_path, Nodo false_path) {
		this.drc = true_path;
		this.izq = false_path;
		this.cond = c;
	}
}
