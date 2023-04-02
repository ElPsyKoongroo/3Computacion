package si2023.sergiogarcia1alu.p01.agente89.reglas;

import java.util.ArrayList;

import si2023.sergiogarcia1alu.ia.reglas.Regla;
import si2023.sergiogarcia1alu.shared.acciones.CapturaEnemigo;
import si2023.sergiogarcia1alu.shared.condiciones.HayEnemigo;

public class CapturarEnemigo extends Regla {

	public CapturarEnemigo() {
		this.antecedentes = new ArrayList<>();
		this.antecedentes.add(new HayEnemigo());
		 
		this.act = new CapturaEnemigo();
	}

}
