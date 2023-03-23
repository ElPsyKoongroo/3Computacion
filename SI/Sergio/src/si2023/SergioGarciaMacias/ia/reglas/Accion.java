package si2023.SergioGarciaMacias.ia.reglas;

import ontology.Types.ACTIONS;
import si2023.SergioGarciaMacias.ia.mente.Mundo;

public interface Accion {
	ACTIONS do_action(Mundo m);
}
