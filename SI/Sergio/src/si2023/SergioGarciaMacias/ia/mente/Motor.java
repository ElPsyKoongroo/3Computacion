package si2023.SergioGarciaMacias.ia.mente;

import ontology.Types.ACTIONS;

public interface Motor {
	ACTIONS decide(Mundo mundo);
	ACTIONS pensar(Mundo mundo);
}
