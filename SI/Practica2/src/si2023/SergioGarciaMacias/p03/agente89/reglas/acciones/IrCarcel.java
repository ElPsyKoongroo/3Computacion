package si2023.SergioGarciaMacias.p03.agente89.reglas.acciones;

import java.util.Stack;

import ontology.Types.ACTIONS;
import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Accion;
import si2023.SergioGarciaMacias.p03.agente89.mente.Mundo89;
import tools.Vector2d;

/*
	Es un singleton, cuidadito con las threads.

	A diferencia de las demas clases que mueven a Superman, esta calcula el camino
	desde la posicion del jugador y mete todas las acciones necesarias en un Stack. A medida que
	el juego solicita una accion el Stack se va vaciando y asi hasta que quede vacio.
	completamente.
*/

public class IrCarcel implements Accion {
	private static Stack<ACTIONS> path_to_jail = new Stack<>();

	public IrCarcel() {
		// TODO Auto-generated constructor stub
	}

	private ACTIONS pop() {
		return IrCarcel.path_to_jail.pop();
	}

	@Override
	public ACTIONS do_action(Mundo m) {
		if (!path_to_jail.isEmpty()) {
			return this.pop();
		}

		Mundo89 mundo = (Mundo89) m;
		Vector2d jail_pos = mundo.get_jail_position();
		double x_diff = jail_pos.x - mundo.get_player_pos_block().x;
		double y_diff = jail_pos.y - mundo.get_player_pos_block().y;

		while (Math.abs(Math.floor(x_diff) - Math.floor(y_diff)) > 0) {
			if (y_diff < 0) {
				path_to_jail.add(ACTIONS.ACTION_UP);
				y_diff += 1;
			} else if (y_diff > 0) {
				path_to_jail.add(ACTIONS.ACTION_DOWN);
				y_diff -= 1;
			}

			if (x_diff > 0) {
				path_to_jail.add(ACTIONS.ACTION_RIGHT);
				x_diff -= 1;
			} else if (x_diff < 0) {
				path_to_jail.add(ACTIONS.ACTION_LEFT);
				x_diff += 1;
			}
		}
		if (!path_to_jail.isEmpty())
			return this.pop();
		return ACTIONS.ACTION_DOWN;

	}
}
