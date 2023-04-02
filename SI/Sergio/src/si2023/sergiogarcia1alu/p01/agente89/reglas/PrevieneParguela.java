package si2023.sergiogarcia1alu.p01.agente89.reglas;

import java.util.ArrayList;

import si2023.sergiogarcia1alu.ia.reglas.Regla;
import si2023.sergiogarcia1alu.shared.acciones.ProtegeParguela;
import si2023.sergiogarcia1alu.shared.condiciones.ParguelaEnPeligro;

public class PrevieneParguela extends Regla {

	public PrevieneParguela() {
		this.antecedentes = new ArrayList<>();
		this.antecedentes.add(new ParguelaEnPeligro());

		this.act = new ProtegeParguela();
	}

}
