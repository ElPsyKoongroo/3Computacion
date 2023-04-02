package si2023.sergiogarcia1alu.p01.agente89.reglas;

import java.util.ArrayList;

import si2023.sergiogarcia1alu.ia.reglas.Regla;
import si2023.sergiogarcia1alu.shared.acciones.SalirDeCarcel;
import si2023.sergiogarcia1alu.shared.condiciones.EstoyBurlao;

public class SalirCarcel extends Regla {
	public SalirCarcel() {
		 this.antecedentes = new ArrayList<>();
		 this.antecedentes.add(new EstoyBurlao()); 

		 // Accion -> Calcular el camino para ir a la carcel
		 // Podriamos hacer esa accion static ? peligro...
		 this.act = new SalirDeCarcel();
	} 
}
