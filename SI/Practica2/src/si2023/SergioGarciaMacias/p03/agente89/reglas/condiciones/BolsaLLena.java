package si2023.SergioGarciaMacias.p03.agente89.reglas.condiciones;

import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Condicion;
import si2023.SergioGarciaMacias.p03.agente89.mente.Mundo89;

public class BolsaLLena implements Condicion {

	public BolsaLLena() {
		// TODO Auto-generated constructor stub
    }

	@Override
	public boolean se_cumple(Mundo m) {
		Mundo89 mundo = (Mundo89)(m);	
		return mundo.get_bolsa_llena();
	}

}
