package si2023.sergiogarcia1alu.ia.reglas;
import java.util.ArrayList;

import si2023.sergiogarcia1alu.ia.mente.Mundo;

public abstract class Regla {
	protected ArrayList<Condicion> antecedentes;
	protected Accion act;

	
	
	public boolean se_cumple(Mundo m) {
		for(Condicion c : this.antecedentes) {
			if (c.se_cumple(m)) return true; 
		}
		return false;
	}
	
	public Accion act() {
		return this.act;
	}
}
