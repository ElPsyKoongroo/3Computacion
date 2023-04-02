package si2023.sergiogarcia1alu.shared.condiciones;

import si2023.sergiogarcia1alu.ia.mente.Mundo;
import si2023.sergiogarcia1alu.ia.reglas.Condicion;

public class Always implements Condicion {

	public Always() {
		// TODO Auto-generated constructor stub
    }

	@Override
	public boolean se_cumple(Mundo m) {
		return true;
	}

}
