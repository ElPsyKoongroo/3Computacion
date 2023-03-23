package si2023.SergioGarciaMacias.ia.mente;

import ontology.Types.ACTIONS;

public interface Motor {
	ACTIONS AnalizarMapa(Mundo mundo);
	ACTIONS Pensar(Mundo mundo);
}
