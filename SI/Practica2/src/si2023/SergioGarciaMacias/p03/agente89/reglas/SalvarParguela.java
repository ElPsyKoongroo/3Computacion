package si2023.SergioGarciaMacias.p03.agente89.reglas;

import java.util.ArrayList;

import si2023.SergioGarciaMacias.ia.reglas.Regla;
import si2023.SergioGarciaMacias.p03.agente89.reglas.acciones.SalvaParguela;
import si2023.SergioGarciaMacias.p03.agente89.reglas.condiciones.ParguelaCayendo;

public class SalvarParguela extends Regla {

	public SalvarParguela() {
		this.antecedentes = new ArrayList<>();
		this.antecedentes.add(new ParguelaCayendo());

		this.act = new SalvaParguela();
	}  
}
