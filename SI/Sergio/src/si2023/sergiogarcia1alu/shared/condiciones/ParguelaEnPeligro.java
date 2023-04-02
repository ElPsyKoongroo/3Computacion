package si2023.sergiogarcia1alu.shared.condiciones;

import java.util.ArrayList;

import si2023.sergiogarcia1alu.ia.mente.Mundo;
import si2023.sergiogarcia1alu.ia.reglas.Condicion;
import si2023.sergiogarcia1alu.shared.Mundo89;
import si2023.sergiogarcia1alu.shared.Mundo89.iType;
import tools.Vector2d;

/**
 * 
 * 
 * Esta clase se encarga de detectar si hay algun Parguela "potencialmente"
 * cayendo.
 * 
 * 
 * Se considera que un Parguela está potencialmente cayendo si a partir de su
 * nivel Y + FACTOR_DE_TONTEO no hay ninguna nube. <br>
 * 
 * 
 * Tambien da prioridad a los Parguelas que se encuentren cerca de las paredes.
 * <br>
 * 
 * 
 */
public class ParguelaEnPeligro implements Condicion {

	// Indica la cantidad de bloques que puede mirar pa'bajo el algoritmo
	private static final int FACTOR_DE_TONTEO = 5;

	// Indica lo lejos que tiene que ponerse del parguela al que está protegiendo
	// Esto es bueno porque al estar mas cerca del centro puede llegar antes a
	// cualquier
	// posicion.
	private static final int FACTOR_DE_LEJANIA = 4;

	public ParguelaEnPeligro() {
		// TODO Auto-generated constructor stub
	}

	// En caso de que no haya parguelas en el mundo devuelve null
	public static Vector2d previene(Mundo m) {
		Mundo89 mundo = (Mundo89) m;
		Vector2d preventivo;
		ArrayList<Vector2d> parguelas = mundo.get_pos_iType(iType.Parguela);
		if (parguelas.isEmpty()) {
			return null;
		} else
			preventivo = parguelas.get(0);

		boolean tiene_nube = false;
		for (Vector2d parguela : parguelas) {

			int y = (int) (parguela.y + 2);
			while (y < Mundo89.NIVEL_DEL_SUELO && (y - parguela.y) < FACTOR_DE_TONTEO) {
				if (mundo.get_pos_type((int) parguela.x, y) == iType.Nube)
					tiene_nube = true;
				y++;
			}

			// Pasada de izq a drc
			for (int x = 0; x < parguela.x; x++)
				if (mundo.get_pos_type(x, (int) parguela.y + 1) == iType.Nube)
					tiene_nube = true;

			// Pasada de drc a izq
			for (int x = mundo.get_grid().length - 1; x > parguela.x; x--)
				if (mundo.get_pos_type(x, (int) parguela.y + 1) == iType.Nube)
					tiene_nube = true;

			/*
			 * Codigo experimental: Este codigo se encarga de mirar si el parguela al que va
			 * a proteger esta mas cerca de la columna de malos que el que habia
			 * seleccionado anteriormente.
			 * 
			 * No he encontrado diferencia en el WinRate activando/desactivando este codigo.
			 */

//			int actual_diff_inic = (int) parguela.dist(new Vector2d(0, parguela.y));
//			int actual_diff_final = (int) parguela.dist(new Vector2d(mundo.get_dimensions()[0], parguela.y));
//
//			int prevent_diff_inic = (int) preventivo.dist(new Vector2d(0, preventivo.y));
//			int prevent_diff_final = (int) preventivo.dist(new Vector2d(mundo.get_dimensions()[0], preventivo.y));
//
//			boolean cerca_pared = actual_diff_final < prevent_diff_final || actual_diff_inic <  prevent_diff_inic;
			boolean cerca_pared = true;

			if (!tiene_nube && (preventivo.y < parguela.y) && cerca_pared)
				preventivo = parguela;
		}

		int[] dimensiones = mundo.get_dimensions();

		if (preventivo.x > dimensiones[0] / 2) {
			preventivo = preventivo.add(-FACTOR_DE_LEJANIA, 0);
		} else {
			preventivo = preventivo.add(FACTOR_DE_LEJANIA, 0);
		}
		preventivo = preventivo.add(0, 2);

		return preventivo;
	}

	@Override
	public boolean se_cumple(Mundo m) {
		return previene(m) != null;
	}

}
