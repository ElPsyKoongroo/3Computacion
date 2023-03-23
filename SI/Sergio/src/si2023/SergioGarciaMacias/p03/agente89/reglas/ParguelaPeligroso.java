package si2023.SergioGarciaMacias.p03.agente89.reglas;

import java.util.ArrayList;

import si2023.SergioGarciaMacias.ia.reglas.Regla;
import si2023.SergioGarciaMacias.shared.acciones.SalvaParguelaPeligro;
import si2023.SergioGarciaMacias.shared.condiciones.ParguelaMedioMuerto;

public class ParguelaPeligroso extends Regla {

	public ParguelaPeligroso() {
		this.antecedentes = new ArrayList<>();
		this.antecedentes.add(new ParguelaMedioMuerto());

		this.act = new SalvaParguelaPeligro();
	}

}
