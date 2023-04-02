package si2023.sergiogarcia1alu.p03.agente89.nodos;

import ontology.Types.ACTIONS;
import si2023.sergiogarcia1alu.ia.mente.Mundo;
import si2023.sergiogarcia1alu.ia.reglas.Condicion;
import si2023.sergiogarcia1alu.ia.reglas.Nodo;

public class NodoEstandar extends Nodo {

	public NodoEstandar(Condicion c, Nodo true_path, Nodo false_path) {
		this.drc = true_path;
		this.izq = false_path;
		this.cond = c;
	}

    @Override
    public ACTIONS get_action(Mundo m) {
        return null;
    }
}
