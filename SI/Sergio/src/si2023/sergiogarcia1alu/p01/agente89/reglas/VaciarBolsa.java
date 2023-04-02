package si2023.sergiogarcia1alu.p01.agente89.reglas;

import java.util.ArrayList;

import si2023.sergiogarcia1alu.ia.reglas.Regla;
import si2023.sergiogarcia1alu.shared.acciones.IrCarcel;
import si2023.sergiogarcia1alu.shared.condiciones.BolsaLLena;

public class VaciarBolsa extends Regla {
	public VaciarBolsa() {
		 this.antecedentes = new ArrayList<>();
		 this.antecedentes.add(new BolsaLLena()); 

		 // Accion -> Calcular el camino para ir a la carcel
		 // Podriamos hacer esa accion static ? peligro...
		 this.act = new IrCarcel();
	} 
}
