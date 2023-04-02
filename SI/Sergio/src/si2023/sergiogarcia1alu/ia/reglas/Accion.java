package si2023.sergiogarcia1alu.ia.reglas;

import ontology.Types.ACTIONS;
import si2023.sergiogarcia1alu.ia.mente.Mundo;

public interface Accion {
	ACTIONS do_action(Mundo m);
}
