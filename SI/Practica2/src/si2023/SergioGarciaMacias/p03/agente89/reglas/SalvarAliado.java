package si2023.SergioGarciaMacias.p03.agente89.reglas;

import java.util.ArrayList;

import si2023.SergioGarciaMacias.ia.reglas.Regla;
import si2023.SergioGarciaMacias.p03.agente89.reglas.acciones.SalvaAliado;
import si2023.SergioGarciaMacias.p03.agente89.reglas.condiciones.AliadoCayendo;

public class SalvarAliado extends Regla {

	public SalvarAliado() {
		this.antecedentes = new ArrayList<>();
		this.antecedentes.add(new AliadoCayendo());

		this.act = new SalvaAliado();
	}  
}
