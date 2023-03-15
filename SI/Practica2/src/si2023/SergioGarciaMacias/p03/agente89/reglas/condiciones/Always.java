package si2023.SergioGarciaMacias.p03.agente89.reglas.condiciones;

import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Condicion;

public class Always implements Condicion {

	public Always() {
		// TODO Auto-generated constructor stub
    }

	@Override
	public boolean se_cumple(Mundo m) {
		return true;
	}

}
