package si2023.sergiogarcia1alu.shared.acciones;

import ontology.Types.ACTIONS;
import si2023.sergiogarcia1alu.ia.mente.Mundo;
import si2023.sergiogarcia1alu.ia.reglas.Accion;

public class SalirDeCarcel implements Accion {

	public SalirDeCarcel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ACTIONS do_action(Mundo m) {
		return ACTIONS.ACTION_UP;
	}

}
