package si2024.sergiogarcia1alu.p03.agene18.reglas;

import java.util.ArrayList;

import si2023.sergiogarcia1alu.ia.reglas.Regla;

public class Acercar extends Regla {
	
	public Acercar() {
		this.antecedentes = new ArrayList();
		this.antecedentes.add(new HayPaloma());
		this.act = new CogePaloma();
	}
}
