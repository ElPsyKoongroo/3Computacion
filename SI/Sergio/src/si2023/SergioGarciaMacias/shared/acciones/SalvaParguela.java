package si2023.SergioGarciaMacias.shared.acciones;

import java.util.ArrayList;

import core.game.Observation;
import ontology.Types.ACTIONS;
import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Accion;
import si2023.SergioGarciaMacias.shared.Mundo89;
import si2023.SergioGarciaMacias.shared.condiciones.ParguelaCayendo;
import tools.Vector2d;

// Singleton ? 
public class SalvaParguela implements Accion {

	private static int parguela_id = -1;

	public SalvaParguela() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Este metodo se va a reutilizar a lo largo del programa cuando Superman tenga
	 * que moverse primero en el eje X y despues en el eje Y.
	 * 
	 * El orden es importante ya que ofrece un mayor WinRate de esta forma.
	 */

	public static ACTIONS selecciona_accion(Vector2d closest_parguela, Vector2d player_pos) {
		if (closest_parguela.x > player_pos.x)
			return ACTIONS.ACTION_RIGHT;
		else if (closest_parguela.x < player_pos.x)
			return ACTIONS.ACTION_LEFT;

		if (closest_parguela.y >= player_pos.y)
			return ACTIONS.ACTION_DOWN;
		else if (closest_parguela.y < player_pos.y)
			return ACTIONS.ACTION_UP;

		return ACTIONS.ACTION_NIL;
	}

	public Vector2d selecciona_parguela(ArrayList<Vector2d> parguelas, Vector2d player_pos) {
		double closest_parguela = parguelas.get(0).dist(player_pos);
		Vector2d p = parguelas.get(0);

		for (Vector2d parguela : parguelas) {
			if (parguela.dist(player_pos) < closest_parguela) {
				p = parguela;
				closest_parguela = parguela.dist(player_pos);
			}
		}

		return p;
	}

	@SuppressWarnings("unused")

	/**
	 * Busca el parguela mas cercano con respecto al jugador
	 */
	private int[] selecciona_parguela2(ArrayList<Vector2d> parguelas, Vector2d player_pos) {
		double closest_parguela_to_player = parguelas.get(0).dist(player_pos);
		int coords[] = new int[2];

		coords[0] = (int) parguelas.get(0).x;
		coords[1] = (int) parguelas.get(0).y;

		for (Vector2d parguela : parguelas) {
			if (parguela.dist(player_pos) < closest_parguela_to_player) {
				closest_parguela_to_player = parguela.dist(player_pos);
				coords[0] = (int) parguela.x;
				coords[1] = (int) parguela.y;
			}
		}

		return coords;

	}

	@SuppressWarnings("unused")
	private int[] busca_parguela(ArrayList<Observation> parguelas) {
		for (Observation o : parguelas) {
			if (o.obsID == SalvaParguela.parguela_id)
				return new int[] { (int) o.position.x, (int) o.position.y };
		}
		return null;
	}

	@Override
	public ACTIONS do_action(Mundo m) {
		Mundo89 mundo = (Mundo89) m;
		ArrayList<Vector2d> parguelas = ParguelaCayendo.dectecta_parguelas(m);

		Vector2d player_pos = mundo.get_player_pos_block();

		/*
		 * FORMA EXPERIMENTAL DE SELECCIONAR LOS PARGUELAS (ofrece los mismos
		 * resultados)
		 * 
		 * Esta forma lo que hace es seleccionar un parguela de los que estan cayendo e
		 * ir a buscar a ese independientemente de si caen nuevos parguelas o no.
		 * 
		 * Una vez se rescata al parguela que esta cayendo se busca un nuevo parguela
		 * valorando la proximidad a Superman.
		 */

//		int coords[] = busca_parguela(mundo.get_obs_iType(iType.Civil_cayendo));
//		if (coords == null) {
//			coords = selecciona_parguela2(parguelas, player_pos);
//			try {
//				parguela_id = mundo.map_pos(coords[0], coords[1]).get(0).obsID;
//				Vector2d closest_parguela = new Vector2d(coords[0], coords[1]);
//				return selecciona_accion(closest_parguela, player_pos);
//			} catch (Exception e) {
//			}
//		} else {
//			Vector2d closest_parguela = new Vector2d(coords[0], coords[1]);
//			return selecciona_accion(closest_parguela, player_pos);
//		}

		Vector2d closest_parguela = selecciona_parguela(parguelas, player_pos);
		return selecciona_accion(closest_parguela, player_pos);
	}

}
