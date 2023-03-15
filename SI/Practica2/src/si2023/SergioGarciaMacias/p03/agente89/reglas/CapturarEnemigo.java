package si2023.SergioGarciaMacias.p03.agente89.reglas;

import java.util.ArrayList;

import si2023.SergioGarciaMacias.ia.reglas.Regla;
import si2023.SergioGarciaMacias.p03.agente89.reglas.acciones.CapturaEnemigo;
import si2023.SergioGarciaMacias.p03.agente89.reglas.condiciones.HayEnemigo;

public class CapturarEnemigo extends Regla {

	public CapturarEnemigo() {
		this.antecedentes = new ArrayList<>();
		this.antecedentes.add(new HayEnemigo());
		 
		this.act = new CapturaEnemigo();
	}

}
