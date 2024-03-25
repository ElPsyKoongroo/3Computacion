package si2024.sergiogarcia1alu.p03.agene18.reglas;

import java.util.ArrayList;

import si2023.sergiogarcia1alu.ia.reglas.Regla;

public class Cazar extends Regla {
	
	public Cazar() {
		this.antecedentes = new ArrayList();
		this.antecedentes.add(new Cerquita());
		this.act = new Caza();
	}
}
