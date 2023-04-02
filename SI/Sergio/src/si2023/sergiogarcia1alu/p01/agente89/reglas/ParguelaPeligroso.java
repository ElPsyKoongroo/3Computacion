package si2023.sergiogarcia1alu.p01.agente89.reglas;

import java.util.ArrayList;

import si2023.sergiogarcia1alu.ia.reglas.Regla;
import si2023.sergiogarcia1alu.shared.acciones.SalvaParguelaPeligro;
import si2023.sergiogarcia1alu.shared.condiciones.ParguelaMedioMuerto;

public class ParguelaPeligroso extends Regla {

	public ParguelaPeligroso() {
		this.antecedentes = new ArrayList<>();
		this.antecedentes.add(new ParguelaMedioMuerto());

		this.act = new SalvaParguelaPeligro();
	}

}
