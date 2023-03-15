package si2023.SergioGarciaMacias.p03.agente89.reglas;

import java.util.ArrayList;

import si2023.SergioGarciaMacias.ia.reglas.Regla;
import si2023.SergioGarciaMacias.p03.agente89.reglas.acciones.IrCentro;
import si2023.SergioGarciaMacias.p03.agente89.reglas.condiciones.Always;

public class Default extends Regla {

	public Default() {
		this.antecedentes = new ArrayList<>();
		this.antecedentes.add(new Always());

		this.act = new IrCentro();
	}

}
