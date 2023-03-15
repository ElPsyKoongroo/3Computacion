package si2023.SergioGarciaMacias.p03.agente89.reglas.condiciones;

import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Condicion;
import si2023.SergioGarciaMacias.p03.agente89.mente.Mundo89;

public class HayEnemigo implements Condicion {
	public HayEnemigo() {}

	@Override
	public boolean se_cumple(Mundo m) {
		Mundo89 mundo = (Mundo89) m;
		int n_malos = mundo.get_enemigos().size(); 
		return  mundo.n_aliados() == 0;
	}

}
