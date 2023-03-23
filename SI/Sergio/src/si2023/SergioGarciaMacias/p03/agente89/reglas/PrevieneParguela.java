package si2023.SergioGarciaMacias.p03.agente89.reglas;

import java.util.ArrayList;

import si2023.SergioGarciaMacias.ia.reglas.Regla;
import si2023.SergioGarciaMacias.shared.acciones.ProtegeParguela;
import si2023.SergioGarciaMacias.shared.condiciones.ParguelaEnPeligro;

public class PrevieneParguela extends Regla {

	public PrevieneParguela() {
		this.antecedentes = new ArrayList<>();
		this.antecedentes.add(new ParguelaEnPeligro());

		this.act = new ProtegeParguela();
	}

}
