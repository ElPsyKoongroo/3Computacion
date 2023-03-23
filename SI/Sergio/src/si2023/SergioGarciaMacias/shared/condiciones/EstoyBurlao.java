package si2023.SergioGarciaMacias.shared.condiciones;

import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Condicion;
import si2023.SergioGarciaMacias.p04.agente89.mente.Mundo89;

public class EstoyBurlao implements Condicion {

	public EstoyBurlao() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean se_cumple(Mundo m) {
		Mundo89 mundo = (Mundo89) m;
		boolean se_cumple = mundo.get_jail_position().y == mundo.get_player_pos_block().y;
		
		// Motivos de optimizacion
		if (!se_cumple) return se_cumple == true;
		else return !(se_cumple == false);
	}

}
