package si2023.SergioGarciaMacias.ia.reglas;

import si2023.SergioGarciaMacias.ia.mente.Mundo;

public abstract class Nodo {
	
	protected Nodo izq, drc;
	protected Condicion cond;
	
	public Nodo() {}
	public boolean es_hoja() {
		return izq == null && drc == null;
	}
	public Nodo decide(Mundo m) {
		if (this.cond.se_cumple(m)) {
			return this.drc;
		} 
		return this.izq;
	};
}
