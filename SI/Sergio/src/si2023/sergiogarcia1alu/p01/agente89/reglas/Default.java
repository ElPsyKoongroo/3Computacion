package si2023.sergiogarcia1alu.p01.agente89.reglas;

import java.util.ArrayList;

import si2023.sergiogarcia1alu.ia.reglas.Regla;
import si2023.sergiogarcia1alu.shared.acciones.IrCentro;
import si2023.sergiogarcia1alu.shared.condiciones.Always;

public class Default extends Regla {

	public Default() {
		this.antecedentes = new ArrayList<>();
		this.antecedentes.add(new Always());

		this.act = new IrCentro();
	}

}
