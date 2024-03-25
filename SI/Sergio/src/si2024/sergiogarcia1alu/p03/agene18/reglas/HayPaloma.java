package si2024.sergiogarcia1alu.p03.agene18.reglas;

import si2023.sergiogarcia1alu.ia.mente.Mundo;
import si2023.sergiogarcia1alu.ia.reglas.Condicion;
import si2024.sergiogarcia1alu.shared.Mundo18;

public class HayPaloma implements Condicion {

	@Override
	public boolean se_cumple(Mundo m) {
		Mundo18 mundo = (Mundo18) m;
		var palomas = mundo.get_pos_iType(Mundo18.iType.Paloma);
		return !palomas.isEmpty();
	}

}
