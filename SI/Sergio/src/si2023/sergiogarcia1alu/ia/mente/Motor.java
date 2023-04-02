package si2023.sergiogarcia1alu.ia.mente;

import ontology.Types.ACTIONS;

public interface Motor {
	ACTIONS decide(Mundo mundo);
	ACTIONS pensar(Mundo mundo);
}
