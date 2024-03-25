package si2024.sergiogarcia1alu.p03.agene18.reglas;

import java.util.ArrayList;

import si2023.sergiogarcia1alu.ia.reglas.Regla;

/*
 * Morfeo: ¿Qué estas esperando?, tú eres más rápido. 
 *          No pienses que lo eres, ten la seguirdad.
 *
 * Morfeo: Corre Neo.
 */
public class CorreNeo extends Regla {
	
	public CorreNeo() {
		this.antecedentes = new ArrayList();
		this.antecedentes.add(new AgenteSmith());
		this.act = new Huir();
	}
}
