package si2023.SergioGarciaMacias.p03.agente89.reglas.condiciones;

import java.util.ArrayList;

import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Condicion;
import si2023.SergioGarciaMacias.p03.agente89.mente.Mundo89;
import tools.Vector2d;

public class AliadoCayendo implements Condicion {

	public AliadoCayendo() { 
		// TODO Auto-generated constructor stub
	}

	public static ArrayList<Vector2d> dectecta_parguelas(Mundo m) {
		Mundo89 mapa = (Mundo89) m;
		Mundo89.iType[][] grid = mapa.get_grid(); 

		// -2 Porque hay dos bloques de suelo
		int altura_efectiva = grid[0].length - 2;

		ArrayList<Vector2d> aliados_cayendo = new ArrayList<>();

		// Mirar si hay algun parguela cayendo
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[x].length; y++) {
				if (grid[x][y] == Mundo89.iType.Civil_cayendo || grid[x][y] == Mundo89.iType.Civil) {
					aliados_cayendo.add(new Vector2d(x, y));
				}
			}
		}

		// Mirar si debajo de los parguelas esta vacia para ir a rescatarlo
		ArrayList<Vector2d> se_van_a_estampar = new ArrayList<>();
		for (Vector2d parguela : aliados_cayendo) {
			Mundo89.iType[] columna = grid[(int) parguela.x];
			int parguela_y = (int) parguela.y;
			boolean caida_directa = true;
			
			if (columna[parguela_y+1] == Mundo89.iType.Nube)
				caida_directa = false;
			
			/*
			while (parguela_y != altura_efectiva) {
				if (columna[parguela_y++] == Mundo89.iType.Nube)
					caida_directa = false;
			}
			*/
			
			if (caida_directa)
				se_van_a_estampar.add(parguela);
		}

		return se_van_a_estampar;
	}

	@Override
	public boolean se_cumple(Mundo m) {
		return !dectecta_parguelas(m).isEmpty();
	}

}
