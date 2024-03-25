package si2023.sergiogarcia1alu.p01.agente89.reglas;

import java.util.ArrayList;

import si2023.sergiogarcia1alu.ia.reglas.Regla;
import si2023.sergiogarcia1alu.shared.acciones.SalvaParguela;
import si2023.sergiogarcia1alu.shared.condiciones.ParguelaCayendo;

public class SalvarParguela extends Regla {
	public SalvarParguela() {
		this.antecedentes = new ArrayList<>();
		this.antecedentes.add(new ParguelaCayendo());

		this.act = new SalvaParguela();
	}  
}
