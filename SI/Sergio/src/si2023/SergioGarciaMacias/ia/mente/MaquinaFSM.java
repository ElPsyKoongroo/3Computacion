package si2023.SergioGarciaMacias.ia.mente;

import java.util.HashMap;

import ontology.Types.ACTIONS;
import si2023.SergioGarciaMacias.ia.reglas.Estado;
import si2023.SergioGarciaMacias.ia.reglas.Transicion;

/*
 * OOP be like: Oh lets have all the shit in the parent class without letting
 * the child class access the shit, also lets make a keyword to let the child
 * class have access to parent class but only the childs so we still have the
 * encapsulation stuff.
 * 
 * Congrats, not even the JVM understand what you are doing.
 * 
 * 
 * */
public abstract class MaquinaFSM {

	protected Estado estado_inicial;
	protected Estado estado_actual;

	protected HashMap<String, Estado> conversion_estado;

	public MaquinaFSM() {
	}

	public ACTIONS disparo(Mundo m) {
		for (Transicion t : estado_actual.get_transiciones()) {
			if (t.se_dispara(m)) {
				estado_actual = conversion_estado.get(t.siguiente_estado());
				break;
			}
		}
		return estado_actual.get_accion().do_action(m);
	}

}
