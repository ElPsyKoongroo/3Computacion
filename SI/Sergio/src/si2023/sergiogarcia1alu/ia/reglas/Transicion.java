package si2023.sergiogarcia1alu.ia.reglas;

import si2023.sergiogarcia1alu.ia.mente.Mundo;


public class Transicion {

	String estado_objetivo;
	Condicion condicion;
	
	
	public Transicion(String eo, Condicion c) {
		this.estado_objetivo = eo;
		this.condicion = c;
	}	
	
	public boolean se_dispara(Mundo m) {
		return condicion.se_cumple(m);
	}

	public String siguiente_estado() {
		return this.estado_objetivo;
	}
	
}
