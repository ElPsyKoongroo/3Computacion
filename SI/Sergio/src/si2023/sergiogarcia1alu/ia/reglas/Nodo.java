package si2023.sergiogarcia1alu.ia.reglas;

import ontology.Types.ACTIONS;
import si2023.sergiogarcia1alu.ia.mente.Mundo;

public abstract class Nodo {
	
	public Nodo izq, drc;
	protected Condicion cond;
	
	public Nodo() {}
	
	public abstract ACTIONS get_action(Mundo m);
	
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
