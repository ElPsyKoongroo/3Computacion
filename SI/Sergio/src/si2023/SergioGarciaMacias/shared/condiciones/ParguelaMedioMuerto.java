package si2023.SergioGarciaMacias.shared.condiciones;

import java.util.ArrayList;

import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Condicion;
import si2023.SergioGarciaMacias.shared.Mundo89;
import si2023.SergioGarciaMacias.shared.Mundo89.iType;
import tools.Vector2d;

public class ParguelaMedioMuerto implements Condicion {

	// Distancia desde el suelo que se considera como "Prioritaria" para que Superman vaya
	// a salvar a los parguelas
	private static final int ALTURA_MAXIMA = 3;
	
	
	public ParguelaMedioMuerto() {
		// TODO Auto-generated constructor stub
	}

	public static ArrayList<Vector2d> parguelas_medio_muertos(Mundo m) {
		Mundo89 mundo = (Mundo89)m;
		ArrayList<Vector2d> medio_muerto = new ArrayList<>();
		for(Vector2d parguela : mundo.get_pos_iType(iType.Parguela_cayendo)) 
			if (parguela.y >= Mundo89.NIVEL_DEL_SUELO-ALTURA_MAXIMA)
				medio_muerto.add(parguela);

		return medio_muerto;
	}
	
	@Override
	public boolean se_cumple(Mundo m) {
		return parguelas_medio_muertos(m).size() > 0;
	}

}
