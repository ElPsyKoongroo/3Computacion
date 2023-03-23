package si2023.SergioGarciaMacias.ia.reglas;

import java.util.ArrayList;

public abstract class Estado {

	// En las diapositivas pone LinkedList pero ni a mi ni a la VM nos gusta
	// derreferencia. All my homies hates O(1).
	protected ArrayList<Transicion> transiciones;

	public Estado() {
	}

	public abstract Accion get_accion();

	public ArrayList<Transicion> get_transiciones() {
		return this.transiciones;
	}

}
