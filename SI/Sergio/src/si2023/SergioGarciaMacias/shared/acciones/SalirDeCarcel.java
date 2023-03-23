package si2023.SergioGarciaMacias.shared.acciones;

import ontology.Types.ACTIONS;
import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Accion;

public class SalirDeCarcel implements Accion {

	public SalirDeCarcel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ACTIONS do_action(Mundo m) {
		return ACTIONS.ACTION_UP;
	}

}
