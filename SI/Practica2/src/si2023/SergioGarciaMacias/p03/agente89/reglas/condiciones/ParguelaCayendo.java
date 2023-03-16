package si2023.SergioGarciaMacias.p03.agente89.reglas.condiciones;

import java.util.ArrayList;

import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Condicion;
import si2023.SergioGarciaMacias.p03.agente89.mente.Mundo89;
import si2023.SergioGarciaMacias.p03.agente89.mente.Mundo89.iType;
import tools.Vector2d;

public class ParguelaCayendo implements Condicion {

	// Indica cuantos bloques mas pa'bajo tiene que mirar hasta dar por echo
	// que el Parguela va a estamparse
	private static final int FACTOR_DE_TONTEO = 5;

	public ParguelaCayendo() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 
	 * Este metodo no solo detecta los parguelas que estan en caida libre hacia el suelo, tambien
	 * detecta los que no tengan ninguna nube abajo suya tantos bloques como indique FACTOR_DE_TONTEO.
	 * 
	 * 	Supongamos un FACTOR_DE_TONTEO = 2 y el siguiente escenario donde P->Parguela, S->Suelo, N->Nube
	 * 
	 * 		P		P	P
	 * 		1		N   1
	 * 		2			N
	 *      3
	 * 		N
	 * 
	 * 
	 * 		SSSSSSSSSSSSSSSSSSSSSSSSS
	 * 
	 * El parguela de mas de la izq no tiene una nube abajo suya en una distancia de dos bloques por
	 * lo que el algoritmo lo detectaria como que esta en caida libre hacia el suelo. Lo contrario ocurriria
	 * con el parguela de las de la derecha, tiene una nube a 2 bloques debajo suya por lo que no lo detectaria
	 * en caida libre.
	 * 
	 * */

	public static ArrayList<Vector2d> dectecta_parguelas(Mundo m) {
		Mundo89 mapa = (Mundo89) m;

		if (mapa.n_aliados() == 0)
			return new ArrayList<>();

		Mundo89.iType[][] grid = mapa.get_grid();

		ArrayList<Vector2d> aliados_cayendo =  mapa.get_pos_iType(iType.Parguela_cayendo);

		// Mirar si debajo de los parguelas esta vacia para ir a rescatarlo
		ArrayList<Vector2d> se_van_a_estampar = new ArrayList<>();
		ArrayList<Vector2d> falsos_parguelas = new ArrayList<>();
		for (Vector2d parguela : aliados_cayendo) {
			Mundo89.iType[] columna = grid[(int) parguela.x];
			int parguela_y = (int) parguela.y;
			boolean caida_directa = true;

			int step = 1;
			while (parguela_y + step <= Mundo89.NIVEL_DEL_SUELO && step < FACTOR_DE_TONTEO) {
				if (columna[parguela_y + step] != Mundo89.iType.Nube)
					falsos_parguelas.add(parguela);
				step++;
			}

			while (parguela_y != Mundo89.NIVEL_DEL_SUELO) {
				if (columna[parguela_y++] == Mundo89.iType.Nube)
					caida_directa = false;
			}

			if (caida_directa)
				se_van_a_estampar.add(parguela);
		}

		if (se_van_a_estampar.isEmpty())
			return falsos_parguelas;
		
		return se_van_a_estampar;
	}

	@Override
	public boolean se_cumple(Mundo m) {
		return !dectecta_parguelas(m).isEmpty();
	}

}
